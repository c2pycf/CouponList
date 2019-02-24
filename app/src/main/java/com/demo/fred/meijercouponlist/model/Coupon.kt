package com.demo.fred.meijercouponlist.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for coupon and coupons list
 */
data class CouponsList(
    @SerializedName("couponCount") val couponCount: String,
    @SerializedName("availableCouponCount") val availableCouponCount: String,
    @SerializedName("listOfCoupons") val listOfCoupons: List<MeijerCoupon>
)

data class MeijerCoupon(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageURL") val imageUrl: String?,
    @SerializedName("redemptionEndDate") val date: String,
    @SerializedName("isClipped") val isClipped: Boolean
)
