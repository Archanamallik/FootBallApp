package com.example.footballdetails.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.footballdetails.dataclass.CountriesData
import com.example.footballdetails.dataclass.CountriesModel
import com.example.footballdetails.network.CountriesApi


private const val TAG = "PagingSource"

class PagingSource(
    private val countriesApi: CountriesApi,
   // private val remoteDataSource: IRemoteDataSource,

    /*    private val withGenres: String*/
) :
    PagingSource<Int, CountriesData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CountriesData> {
        return try {
            val position = params.key ?: 1
            val response = countriesApi.getCountries( position)

            if (response.data != null) {
                return LoadResult.Page(
                    data = response.data,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == response.pagination.count) null else (position + 1)
                )
            } else {
                Log.i(TAG, "load: No Response")
                LoadResult.Error(throw Exception("No Response"))
            }

        } catch (e: Exception) {
            Log.i(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CountriesData>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}