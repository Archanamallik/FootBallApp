package com.example.footballdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.footballdetails.network.ContinentsApi
import com.example.footballdetails.repository.ContinentRepository
import com.example.footballdetails.utils.BASE_URL

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
class GetContinentsApiTest {
@get:Rule
val instantExecutorRule = InstantTaskExecutorRule()
private val server = MockWebServer()
private lateinit var repository: ContinentRepository
private lateinit var mockedResponse: String
private val gson = GsonBuilder()
.setLenient()
.create()
@Before
fun init() {
server.start(8000)

val okHttpClient = OkHttpClient
.Builder()
.build()
val service = Retrofit.Builder()
.addConverterFactory(MoshiConverterFactory.create())
.baseUrl(BASE_URL)
.client(okHttpClient)
.build().create(ContinentsApi::class.java)
repository = ContinentRepository(service)
}
@Test
fun testApiSuccess() {
mockedResponse = MockResponseFileReader("apiFiles/continents.json").content
server.enqueue(
MockResponse()
.setResponseCode(200)
.setBody(mockedResponse)
)
val response = runBlocking { repository.getContinents() }
val json = gson.toJson(response)
val resultResponse = JsonParser.parseString(json)
val expectedresponse = JsonParser.parseString(mockedResponse)
Assert.assertNotNull(response)
Assert.assertTrue(resultResponse.equals(expectedresponse))
}
@After
fun tearDown() {
server.shutdown()
}
}