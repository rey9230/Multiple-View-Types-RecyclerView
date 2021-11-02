package com.android.storehometest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
class MainAdapter(homeData: HomeDataModel) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    lateinit var context : Context
    val homeData : HomeDataModel = homeData

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val bannerCv = itemView.findViewById<CardView>(R.id.banner_cv)
        val bannerImage = itemView.findViewById<ImageView>(R.id.bannerImage)
        val mainRv = itemView.findViewById<RecyclerView>(R.id.mainRv)
        val headerText = itemView.findViewById<TextView>(R.id.headerText)
        val detailsText = itemView.findViewById<TextView>(R.id.detailsText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.recycler_base_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bannerCv = holder.bannerCv
        val bannerImage = holder.bannerImage
        val mainRv = holder.mainRv
        val headerText = holder.headerText
        val detailsText = holder.detailsText

        val section: Sections = homeData.data.sections[position]
        if (section.controlType == 1){
            bannerCv.visibility = View.VISIBLE
            mainRv.visibility = View.GONE
            Glide
                .with(context)
                .load(section.items[1].imageUrl)
  //              .circleCrop()
                .placeholder(R.drawable.meat_sausages)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.bannerImage)
        }else if (section.controlType == 2){
            bannerCv.visibility = View.GONE
            mainRv.visibility = View.VISIBLE
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            mainRv.layoutManager = layoutManager
            mainRv.itemAnimator = DefaultItemAnimator()
            mainRv.adapter = HomeRecycleDataAdapter(context, section)
        } else if (section.controlType == 4){
            bannerCv.visibility = View.GONE
            mainRv.visibility = View.VISIBLE
            if (section.alignment == 1){ //Horizontal
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                mainRv.layoutManager = layoutManager
                mainRv.itemAnimator = DefaultItemAnimator()
                mainRv.adapter = HomeRecycleDataAdapter(context, section)
            }
            if (section.alignment == 2){ //Vertical
                if (section.itemSize == 1 ){ //Set Grid
                    val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
                    mainRv.layoutManager = layoutManager
                    mainRv.itemAnimator = DefaultItemAnimator()
                    mainRv.adapter = HomeRecycleDataAdapter(context, section)
                }else{
                    val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
                    mainRv.layoutManager = layoutManager
                    mainRv.itemAnimator = DefaultItemAnimator()
                    mainRv.adapter = HomeRecycleDataAdapter(context, section)
                }
            }
        }

        headerText.text = section.headerText
        detailsText.text = section.detailsText
    }

    override fun getItemCount(): Int {
       return homeData.data.sections.size
    }

    interface OnApplicationAdapterListener {
        suspend fun onReadDerivates()
    }
}