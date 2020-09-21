package com.mes.inflight_mag.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mes.inflight_mag.R
import com.mes.inflight_mag.data.db.model.Airline
import com.mes.inflight_mag.ui.main.adapter.AirlineAdapter
import com.mes.inflight_mag.ui.main.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.tool_bar.*

@AndroidEntryPoint
class Home : AppCompatActivity(),   SwipeRefreshLayout.OnRefreshListener  {

    private val homeVM: HomeViewModel by viewModels()
    private lateinit var adapter: AirlineAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupUI()
        initToolbar()
        setUpObservers()
    }

    override fun onRefresh() {
       homeVM.getAirlineList()
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar as Toolbar)
        (toolbar as Toolbar).setNavigationIcon(R.drawable.ic_home)
        supportActionBar!!.title = "Home"
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }


    private fun setUpObservers(){
        homeVM.getAirlines().observe(
            this,{
                airlines ->run{
                if(airlines.size > 0){
                    renderList(airlines)
                    airlines_preview.visibility = View.GONE
                    airline_content.visibility = View.VISIBLE
                }else{
                    airlines_preview.visibility = View.VISIBLE
                    airline_content.visibility = View.GONE
                }

            }
            }
        )

        homeVM.loading.observe(
            this,{
                    loading ->run{
                airlines_swipe_refresh.isRefreshing = loading
            }
            }
        )
    }

    private fun setupUI() {
        airlines_swipe_refresh.setOnRefreshListener(this)
        airlines_swipe_refresh.setColorSchemeColors(
            ContextCompat.getColor( this,android.R.color.holo_green_dark),
            ContextCompat.getColor(this,android.R.color.holo_red_dark)  ,
            ContextCompat.getColor(this, android.R.color.holo_blue_dark)   ,
            ContextCompat.getColor(this, android.R.color.holo_orange_dark)   )

        home_recycler_view.apply {
            layoutManager = GridLayoutManager(this@Home, 2)
        }
        adapter = AirlineAdapter(arrayListOf())
        home_recycler_view.addItemDecoration(
            DividerItemDecoration(
                home_recycler_view.context,
                (home_recycler_view.layoutManager as LinearLayoutManager).orientation
            )
        )
        home_recycler_view.adapter = adapter
    }

    private fun renderList(airlines: ArrayList<Airline>) {
        adapter.clear()
        adapter.addData(airlines)
        adapter.notifyDataSetChanged()

        adapter.onItemClick = {
            airline ->
            val newIntent = Intent(
                this, Magazines::class.java
            )

            newIntent.putExtra("airline", airline)
            Log.d("Airline parcel: ", airline.toString())
            startActivity(newIntent)
        }
    }
}