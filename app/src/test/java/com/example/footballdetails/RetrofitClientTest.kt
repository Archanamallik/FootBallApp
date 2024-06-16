package com.example.footballdetails


import com.codingwithmitch.mvvmrecipeapp.di.RepositoryModule
import com.example.footballdetails.utils.BASE_URL
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {

    @Test
    fun testRetrofitInstance() {
        //Get an instance of Retrofit
        val instance: Retrofit = RepositoryModule.providesRetrofit()
        //Assert that, Retrofit's base url matches to our BASE_URL
        assert(instance.baseUrl().toString() == BASE_URL)
    }

    /*@Test
    fun testContinentsResponse() {
        //Get an instance of PlacesService by proiving the Retrofit instance
        val service = PlacesService(RetrofitClient().retrofit)
        //Create a new request for our API calling
        val query = VenueRecommendationsQueryBuilder()
            .setLatitudeLongitude(52.376510, 4.905890)
            .build()
        //Execute the API call
        val response = service.getVenueRecommendations(query).execute()
        //Check for error body
        val errorBody = response.errorBody()
        assert(errorBody == null)
        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }*/
}