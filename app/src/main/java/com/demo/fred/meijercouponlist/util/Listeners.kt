package com.demo.fred.meijercouponlist.util

import com.demo.fred.meijercouponlist.model.MeijerCoupon


/**
 * Class: Listeners
 * Author: Fred Chen
 * Time: 22/02/2019
 *
 * Description:
 * Desc: Setup different Listeners in util package for different use
 *
 */

/**
 * Handle Clip/Unclip clicked
 */
interface ClipCoupon {
    fun getClippedCoupon(coupon: MeijerCoupon)
}

/**
 * After clip/unclip clicked add the coupon to the list
 */
interface AddClipCoupon {
    fun addCoupons(coupons: List<MeijerCoupon>)

    fun addCoupon(coupon: MeijerCoupon)
}

/**
 * Enum class for fragment pages
 */
enum class Page {
    AVAILABLE, CLIPPED
}