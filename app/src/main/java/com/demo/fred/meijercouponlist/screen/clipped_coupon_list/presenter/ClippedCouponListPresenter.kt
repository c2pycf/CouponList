package com.demo.fred.meijercouponlist.screen.clipped_coupon_list.presenter

import com.demo.fred.meijercouponlist.adapter.AvailableCouponAdapter
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.view.ClippedCouponListView
import com.demo.fred.meijercouponlist.util.Page
import org.jetbrains.annotations.TestOnly

/**
 * Class: ClippedCouponListPresenter
 * Author: Fred Chen
 * Time: 22/02/2019
 *
 * Description:
 * Desc: Presenter for ClippedCouponList to handle data passing from available fragment and update UI via view contract
 *
 */

class ClippedCouponListPresenter{

    private var mView: ClippedCouponListView? = null
    private var couponAdapter = AvailableCouponAdapter(Page.CLIPPED)

    fun attach(clippedCouponListView: ClippedCouponListView) {
        mView = clippedCouponListView
        mView?.setAdapter(couponAdapter)
    }

    fun loadList(coupons: List<MeijerCoupon>) {
        couponAdapter.loadItem(coupons)
        mView?.setText(coupons.size)
    }

    fun create(){
        couponAdapter.onItemClick = {
            couponAdapter.remove(it)
            mView?.clipped(it)
            couponAdapter.remove(it)
            mView?.setAdapter(couponAdapter)
            mView?.setText(couponAdapter.itemCount)
        }
    }

    @TestOnly
    fun getView() : ClippedCouponListView?{
        return mView
    }

    @TestOnly
    fun getAdapter() : AvailableCouponAdapter{
        return couponAdapter
    }

}