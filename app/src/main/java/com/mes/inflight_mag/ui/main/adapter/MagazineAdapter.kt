package com.mes.inflight_mag.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.mes.inflight_mag.R
import com.mes.inflight_mag.data.db.model.Magazine
import kotlinx.android.synthetic.main.magazine_item_layout.view.*

class MagazineAdapter(
    private val magazines: ArrayList<Magazine>
) : RecyclerView.Adapter<MagazineAdapter.DataViewHolder>() {

    var onItemClick: ((Magazine) -> Unit)? = null


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(magazines[adapterPosition])
            }

        }

        fun bind(magazine: Magazine) {

            itemView.magazine_title.text = magazine.title
            itemView.magazine_no_of_issues.text = magazine.issueCount.toString().plus(" Issues")
            if (magazine.icon != "" || magazine.icon != "n/a" ){
                try {
                    Glide.with(itemView.magazine_img.context)
                        .load(magazine.icon)
                        .error(R.drawable.ic_magazine)
                        .placeholder(R.drawable.ic_magazine)
                        .into(itemView.magazine_img)

                    itemView.magazine_img.visibility = View.VISIBLE
                    itemView.magazine_icon.visibility = View.GONE
                }catch (e: GlideException){
                    itemView.magazine_img.visibility = View.GONE
                    itemView.magazine_icon.visibility = View.VISIBLE
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.magazine_item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = magazines.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(magazines[position])

    fun addData(list: List<Magazine>) {
        magazines.addAll(list)
    }

    fun clear() {
        magazines.clear()
    }
}

