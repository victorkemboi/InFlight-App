package com.mes.user_app.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mes.user_app.R
import com.mes.user_app.data.model.core.User
import com.mes.user_app.ui.main.adapter.MainAdapter
import com.mes.user_app.ui.main.viewmodel.MainViewModel
import com.mes.user_app.utils.livedata_adapter.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        Log.d("Observer: ", "Setup the observer")
        mainViewModel.userList().observe(this, {
            userResponse -> run {
            Log.d("Users: ", userResponse.toString())
            if(userResponse.isSuccessful){
                val  userList = userResponse.body as ArrayList<User>
                Log.d("Users: ", userList.toString())
                when(userList.size){
                    0 ->{
                        recyclerView.visibility = View.GONE
                    }
                    else -> {
                        renderList(userList)
                        recyclerView.visibility = View.VISIBLE
                    }
                }
            }else{

                recyclerView.visibility = View.GONE
            }
            progressBar.visibility = View.GONE

        }

        })
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

}
