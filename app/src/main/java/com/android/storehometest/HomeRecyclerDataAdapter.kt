package com.android.storehometest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey

class HomeRecycleDataAdapter(private val context: Context, private val data: Sections) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        setHasStableIds(true);
    }

    inner class Empty(v: View) : RecyclerView.ViewHolder(v) {
    }
    inner class Ribbon(v: View) : RecyclerView.ViewHolder(v) {
        var ribbonIv: ImageView = v.findViewById<View>(R.id.ribbon_iv) as ImageView
        var ribbonTv: TextView = v.findViewById<View>(R.id.ribbon_tv) as TextView

    }

    inner class CardSmall(v: View) : RecyclerView.ViewHolder(v) {
        var cardSmallIv: ImageView = v.findViewById<View>(R.id.card_small_iv) as ImageView
        var cardSmallNameTv: TextView = v.findViewById<View>(R.id.card_small_name_tv) as TextView
        var cardSmallPriceTv: TextView = v.findViewById<View>(R.id.card_small_price_tv) as TextView

    }

    inner class CardMedium(v: View) : RecyclerView.ViewHolder(v) {
        var cardMediumIv: ImageView = v.findViewById<View>(R.id.card_medium_iv) as ImageView
        var cardMediumNameTv: TextView = v.findViewById<View>(R.id.card_medium_name_tv) as TextView
        var cardMediumPriceTv: TextView = v.findViewById<View>(R.id.card_medium_price_tv) as TextView

    }

    inner class CardLarge(v: View) : RecyclerView.ViewHolder(v) {
        var cardLargeIv: ImageView = v.findViewById<View>(R.id.card_large_iv) as ImageView
        var cardLargeNameTv: TextView = v.findViewById<View>(R.id.card_large_name_tv) as TextView
        var cardLargePriceTv: TextView = v.findViewById<View>(R.id.card_large_iv_price_tv) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View

        return when(data.controlType){
            1 -> {
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_empty_item, parent, false)
                Empty(itemView)
            }
            2 -> {
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_ribbon_item, parent, false)
                Ribbon(itemView)
            }
            4 -> {
                when(data.itemSize){
                    1->{
                        itemView = LayoutInflater.from(parent.context)
                            .inflate(R.layout.recycler_card_small_item, parent, false)
                        CardSmall(itemView)
                    }
                    2->{
                        itemView = LayoutInflater.from(parent.context)
                            .inflate(R.layout.recycler_card_medium_item, parent, false)
                        CardMedium(itemView)
                    }
                    4->{
                        itemView = LayoutInflater.from(parent.context)
                            .inflate(R.layout.recycler_card_large_item, parent, false)
                        CardLarge(itemView)
                    }
                    else -> {
                        itemView = LayoutInflater.from(parent.context)
                            .inflate(R.layout.recycler_empty_item, parent, false)
                        Empty(itemView)
                    }
                }

            }
            else -> {
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_empty_item, parent, false)
                Empty(itemView)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val recycleItem: Items = data.items[position]

        when (data.controlType) {
            2 -> {

                val ribbonHolder = holder as Ribbon
                ribbonHolder.ribbonTv.text = recycleItem.name
                showPic(recycleItem.imageUrl, ribbonHolder.ribbonIv)
            }
            4 -> {
                when(data.itemSize){
                    1->{
                        val cardSmallHolder = holder as CardSmall
                        cardSmallHolder.cardSmallNameTv.text = recycleItem.name
                        cardSmallHolder.cardSmallPriceTv.text = recycleItem.priceToShow
                        showPic(recycleItem.imageUrl, cardSmallHolder.cardSmallIv)
                    }
                    2->{
                        val cardMediumHolder = holder as CardMedium
                        cardMediumHolder.cardMediumNameTv.text = recycleItem.name
                        cardMediumHolder.cardMediumPriceTv.text = recycleItem.priceToShow
                        showPic(recycleItem.imageUrl, cardMediumHolder.cardMediumIv)
                    }
                    4->{
                        val cardLargeHolder = holder as CardLarge
                        cardLargeHolder.cardLargeNameTv .text = recycleItem.name
                        cardLargeHolder.cardLargePriceTv.text = recycleItem.priceToShow
                        showPic(recycleItem.imageUrl, cardLargeHolder.cardLargeIv)
                    }
                    else -> {
//                        val cardMediumHolder = holder as CardMedium
//                        cardMediumHolder.cardMediumNameTv.text = recycleItem.name
//                        cardMediumHolder.cardMediumPriceTv.text = recycleItem.priceToShow
//                        showPic(recycleItem.imageUrl, cardMediumHolder.cardMediumIv)
                    }
                }
            }
        }
    }

    private fun showPic(imageUrl: String, ribbonIv: ImageView) {
        Glide
            .with(context)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .signature(ObjectKey(System.currentTimeMillis()))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(ribbonIv)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount(): Int {
        return data.items.size
    }

}
