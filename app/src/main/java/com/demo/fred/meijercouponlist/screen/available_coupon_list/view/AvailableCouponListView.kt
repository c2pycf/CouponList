package com.demo.fred.meijercouponlist.screen.available_coupon_list.view

import com.demo.fred.meijercouponlist.adapter.AvailableCouponAdapter
import com.demo.fred.meijercouponlist.model.MeijerCoupon

interface AvailableCouponListView{
    fun setAdapter(couponAdapter: AvailableCouponAdapter)
    fun showMessage(message: String)
    fun setTotal(couponCount: String)
    fun startLoading()
    fun stopLoading()
    fun clipped(coupon: MeijerCoupon)
}