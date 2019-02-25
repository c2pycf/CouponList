package com.demo.fred.meijercouponlist.adapter

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.demo.fred.meijercouponlist.R
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.util.Page

/**
 * Class: AvailableCouponAdapter
 * Author: Fred Chen
 * Time: 22/02/2019
 *
 * Description: Recycler view adapter for available coupon fragment. Binding the view holder with views.
 * Setup clip/unclip Listener
 *
 */
class AvailableCouponAdapter(page: Page) : RecyclerView.Adapter<AvailableCouponAdapter.ViewHolder>() {

    private var pageType: Page = page
    private var recyclerView: RecyclerView? = null
    private var myList: ArrayList<MeijerCoupon>? = null
    internal var onItemClick: ((MeijerCoupon) -> Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_coupon_available, p0, false)

        return ViewHolder(view)
    }

    fun loadItem(couponList: List<MeijerCoupon>?) {
        myList = couponList as ArrayList<MeijerCoupon>
        notifyDataSetChanged()
    }

    fun remove(coupon: MeijerCoupon) {
        myList?.remove(coupon)

    }

    fun addItem(coupon: MeijerCoupon) {
        myList?.add(0, coupon)
    }

    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.title.text = myList?.get(p1)?.title
        p0.title.paintFlags = Paint.FAKE_BOLD_TEXT_FLAG
        p0.description.text = myList?.get(p1)?.description
        val dates = myList?.get(p1)?.date?.split("T")
        val valid = "Valid through ".plus(dates?.get(0).orEmpty())
        p0.date.text = valid
        when (pageType) {
            Page.AVAILABLE -> p0.clip.text = p0.getClipString()
            Page.CLIPPED -> p0.clip.text = p0.getUnclipString()
        }
        p0.clip.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        Glide.with(p0.itemView.context)
            .load(myList?.getOrNull(p1)?.imageUrl)
            .into(p0.img)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_coupon_title)
        val description: TextView = itemView.findViewById(R.id.tv_coupon_desc)
        val clip: TextView = itemView.findViewById(R.id.tv_coupon_clip)
        val img: ImageView = itemView.findViewById(R.id.coupon_available_image)
        val date: TextView = itemView.findViewById(R.id.tv_coupon_time)

        init {
            clip.setOnClickListener {
                if (myList != null) {
                    onItemClick?.invoke(myList!![adapterPosition])
                }
            }
        }

        fun getClipString(): String {
            return itemView.resources.getString(R.string.coupon_clip_button)
        }

        fun getUnclipString(): String {
            return itemView.resources.getString(R.string.coupon_clip_button_unclip)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }


}