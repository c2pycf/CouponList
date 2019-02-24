package com.demo.fred.meijercouponlist.util

import com.demo.fred.meijercouponlist.model.MeijerCoupon


interface ClipCoupon {
    fun getClippedCoupon(coupon: MeijerCoupon)
}

interface AddClipCoupon {
    fun addCoupons(coupons: List<MeijerCoupon>)

    fun addCoupon(coupon: MeijerCoupon)
}

enum class Page {
    AVAILABLE, CLIPPED
}