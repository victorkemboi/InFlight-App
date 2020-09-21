package com.mes.inflight_mag.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mes.inflight_mag.R
import com.mes.inflight_mag.data.db.model.Airline
import kotlinx.android.synthetic.main.airline_item_layout.view.*

class AirlineAdapter(
    private val airlines: ArrayList<Airline>
) : RecyclerView.Adapter<AirlineAdapter.DataViewHolder>() {

    var onItemClick: ((Airline) -> Unit)? = null


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{
                onItemClick?.invoke(airlines[adapterPosition])
            }

        }
        fun bind(airline: Airline) {

            itemView.airline_title.text = airline.name
            when(airline.magCount){
                0->  itemView.airline_no_of_mags.text = airline.magCount.toString().plus("No magazines!")
                1->  itemView.airline_no_of_mags.text = airline.magCount.toString().plus(" Magazine")
                else->  itemView.airline_no_of_mags.text = airline.magCount.toString().plus(" Magazines")
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.airline_item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = airlines.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(airlines[position])

    fun addData(list: List<Airline>) {
        airlines.addAll(list)
    }
    fun clear(){
        airlines.clear()
    }


}