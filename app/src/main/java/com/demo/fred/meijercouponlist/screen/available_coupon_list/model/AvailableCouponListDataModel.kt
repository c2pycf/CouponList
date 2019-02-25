package com.demo.fred.meijercouponlist.screen.available_coupon_list.model

import com.demo.fred.meijercouponlist.model.CouponsList
import com.demo.fred.meijercouponlist.network.MeijerService
import io.reactivex.Observable

/**
 * Class: AvailableCouponListDataModel
 * Author: Fred Chen
 * Time: 22/02/2019
 *
 * Model to Fetch date from service and pass to presenter
 */
class AvailableCouponListDataModel {
    private val retrofit: MeijerService.Creator = MeijerService
    private val maijerAPI = retrofit.create()

    fun getCoupons(): Observable<CouponsList> =
        maijerAPI.getCoupon("34lgBae%2FxIEnqksQpkn3w9F0JTKCafuiCr0ejLNLvBzlOlOZBa1CMA%3D%3D")
}