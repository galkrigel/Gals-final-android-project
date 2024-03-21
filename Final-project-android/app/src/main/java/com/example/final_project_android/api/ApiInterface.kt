package com.example.final_project_android.api
import com.example.final_project_android.Model.ApiPropertyItem
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("data")
    fun getData(): Call<List<ApiPropertyItem>>
}