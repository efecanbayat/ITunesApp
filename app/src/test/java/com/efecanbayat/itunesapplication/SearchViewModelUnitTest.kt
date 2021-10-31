package com.efecanbayat.itunesapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.efecanbayat.itunesapplication.data.ApiRepository
import com.efecanbayat.itunesapplication.data.entity.BaseResponse
import com.efecanbayat.itunesapplication.data.local.ItemDao
import com.efecanbayat.itunesapplication.data.local.LocalDataSource
import com.efecanbayat.itunesapplication.data.local.SharedPrefManager
import com.efecanbayat.itunesapplication.data.remote.APIService
import com.efecanbayat.itunesapplication.data.remote.RemoteDataSource
import com.efecanbayat.itunesapplication.ui.searchscreen.SearchViewModel
import com.efecanbayat.itunesapplication.utils.CoroutineRule
import com.efecanbayat.itunesapplication.utils.Resource
import com.efecanbayat.itunesapplication.utils.getOrAwaitValue
import com.efecanbayat.itunesapplication.utils.parseFileAsBaseResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelUnitTest {

    @get:Rule
    var coroutineTestRule = CoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: APIService

    @Mock
    private lateinit var itemDao: ItemDao

    @Mock
    private lateinit var sharedPrefManager: SharedPrefManager

    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var apiRepository: ApiRepository
    private lateinit var searchViewModel: SearchViewModel

    private val data = parseFileAsBaseResponse("fakeSearchResponse.json")
    private val mockSuccessResponse = Response.success(data)

    private val errorResponse =
        "{\"errorMessage\":\"Invalid value(s) for key(s): [mediaType]\",\n" +
                " \"queryParameters\":{\"output\":\"json\", \"callback\":\"A javascript function to handle your search results\", \"country\":\"ISO-2A country code\", \"limit\":\"The number of search results to return\", \"term\":\"A search string\", \"lang\":\"ISO-2A language code\"}}"
    private val errorResponseBody =
        errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
    val mockUnSuccessResponse = Response.error<BaseResponse>(400, errorResponseBody)


    private var searchStateObserver = mock<Observer<Resource<Any>>>()

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSource(apiService)
        localDataSource = LocalDataSource(sharedPrefManager, itemDao)
        apiRepository = ApiRepository(remoteDataSource, localDataSource)
        searchViewModel = SearchViewModel(apiRepository)
    }

    @Test
    fun `get live data from viewModel and check is successful`() = coroutineTestRule.runBlockingTest {

        whenever(apiService.getDataByQuery(anyString(), anyString(), anyInt())).thenReturn(
            mockSuccessResponse
        )

        searchViewModel.getDataByQuery("fast", 1).observeForever(searchStateObserver)
        searchViewModel.getDataByQuery("fast", 1).getOrAwaitValue()

        verify(searchStateObserver).onChanged(Resource.loading())
        verify(searchStateObserver).onChanged(Resource.success(data))
    }

    @Test
    fun `get live data from viewModel and check is unsuccessful`() =
        coroutineTestRule.runBlockingTest {

            whenever(apiService.getDataByQuery(anyString(), anyString(), anyInt())).thenReturn(
                mockUnSuccessResponse
            )

            searchViewModel.getDataByQuery("fast", 1).observeForever(searchStateObserver)
            searchViewModel.getDataByQuery("fast", 1).getOrAwaitValue()

            verify(searchStateObserver).onChanged(Resource.loading())
            verify(searchStateObserver).onChanged(Resource.error("Error: ERROR", null))

        }
}