package com.example.moviesapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.footballdetails.dataclass.PlayersData
import com.example.footballdetails.network.PlayerApi


private const val TAG = "MoviePagingSource"

class PlayersPagingSource(
    private val playerApi: PlayerApi,
    private val countryId: Int,

) :
    PagingSource<Int, PlayersData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayersData> {
        return try {
            val position = params.key ?: 1
            val response = playerApi.getPlayerById(  countryId,position)

            if (response.data != null) {
                return LoadResult.Page(
                    data = response.data,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == response.pagination.count) null else (position + 1)
                )
            } else {

                LoadResult.Error(throw Exception("No Response"))
            }
        } catch (e: Exception) {
            Log.i(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlayersData>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}