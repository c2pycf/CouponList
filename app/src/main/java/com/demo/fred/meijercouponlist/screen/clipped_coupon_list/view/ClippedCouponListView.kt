package com.demo.fred.meijercouponlist.screen.clipped_coupon_list.view

import android.support.v7.widget.RecyclerView
import com.demo.fred.meijercouponlist.model.MeijerCoupon

interface ClippedCouponListView {
    fun setAdapter(adapter: RecyclerView.Adapter<*>)
    fun clipped(coupon: MeijerCoupon)
    fun setText(size: Int)
}