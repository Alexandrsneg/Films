package com.example.films.list

import com.example.films.models.Films
import com.example.films.rest.IFilmsApiService
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InjectViewState
class ListPresenter: MvpPresenter<IListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showLoading(true)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: IFilmsApiService  = retrofit.create(IFilmsApiService::class.java)
        val repos: Call<Films> = service.listRepos()

        repos.enqueue(object: Callback<Films>{
            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                response.body()?.let {
                    viewState.setData(it)
                }
                viewState.showLoading(false)
            }

            override fun onFailure(call: Call<Films>, t: Throwable) {
                viewState.showError(t.message ?: "Что-то пошло не так")
                viewState.showLoading(false)
            }
        })

    }

}