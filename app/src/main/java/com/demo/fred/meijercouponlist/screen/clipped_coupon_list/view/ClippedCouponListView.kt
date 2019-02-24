package com.demo.fred.meijercouponlist.screen.clipped_coupon_list.view

import com.demo.fred.meijercouponlist.adapter.AvailableCouponAdapter
import com.demo.fred.meijercouponlist.model.MeijerCoupon

interface ClippedCouponListView{
    fun setAdapter(couponAdapter: AvailableCouponAdapter)
    fun clipped(coupon: MeijerCoupon)
    fun setTotal(size: Int)

}