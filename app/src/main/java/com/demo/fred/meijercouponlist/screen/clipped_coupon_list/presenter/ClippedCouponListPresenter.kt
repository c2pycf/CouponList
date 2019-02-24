package com.demo.fred.meijercouponlist.screen.clipped_coupon_list.presenter

import com.demo.fred.meijercouponlist.adapter.AvailableCouponAdapter
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.view.ClippedCouponListView
import com.demo.fred.meijercouponlist.util.Page
import org.jetbrains.annotations.TestOnly

class ClippedCouponListPresenter{

    private var mView: ClippedCouponListView? = null
    private var couponAdapter = AvailableCouponAdapter(Page.CLIPPED)

    fun attach(clippedCouponListView: ClippedCouponListView) {
        mView = clippedCouponListView
        mView?.setAdapter(couponAdapter)
    }

    fun loadList(coupons: List<MeijerCoupon>) {
        couponAdapter.loadItem(coupons)
        mView?.setTotal(coupons.size)
    }

    fun create(){
        couponAdapter.onItemClick = {
            couponAdapter.remove(it)
            mView?.clipped(it)
            couponAdapter.remove(it)
            mView?.setAdapter(couponAdapter)
            mView?.setTotal(couponAdapter.itemCount)
        }
    }

    @TestOnly
    fun getView() : ClippedCouponListView?{
        return mView
    }

    fun getAdapter() : AvailableCouponAdapter{
        return couponAdapter
    }

}