package com.example.final_project_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project_android.Model.ApiPropertyItem
import com.example.final_project_android.adapters.ApiPropertiesRecyclerAdapter
import com.example.final_project_android.api.ApiInterface
import com.example.final_project_android.databinding.FragmentApiPropertiesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.example.final_project_android.ViewModels.ApiPropertiesViewModel
import com.example.final_project_android.adapters.ApiPropertyRecyclerViewActivity

class ApiPropertiesFragment : Fragment() {
    var apiPropertiesRecyclerView: RecyclerView? = null
    var adapter: ApiPropertiesRecyclerAdapter? = null
    var progressBar: ProgressBar? = null

    private var _binding: FragmentApiPropertiesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ApiPropertiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApiPropertiesBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[ApiPropertiesViewModel::class.java]

        progressBar = binding.apiPropertiesProgressBar
        progressBar?.visibility = View.VISIBLE

        getMyData()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getMyData() {
        val BASE_URL = "https://re.ofir.dev/"

        val retrofitBuilder =
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<ApiPropertyItem>?> {

            override fun onResponse(
                call: Call<List<ApiPropertyItem>?>,
                response: Response<List<ApiPropertyItem>?>
            ) {
                viewModel.apiProperties = response.body()!!
                Log.i("Api properties size:", "${response.body()!!.size}")

                apiPropertiesRecyclerView = binding.rvApiPropertiesFragmentList
                apiPropertiesRecyclerView?.setHasFixedSize(true)
                apiPropertiesRecyclerView?.layoutManager = LinearLayoutManager(context)
                adapter = ApiPropertiesRecyclerAdapter(viewModel.apiProperties)

                adapter?.listener = object : ApiPropertyRecyclerViewActivity.OnItemClickListener {
                    override fun onItemClick(position: Int) {}

                    override fun onApiPropertyClicked(property: ApiPropertyItem?) {
                        Log.i("TAG", "PROPERTY $property")
                    }
                }

                apiPropertiesRecyclerView?.adapter = adapter

                progressBar?.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<ApiPropertyItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}