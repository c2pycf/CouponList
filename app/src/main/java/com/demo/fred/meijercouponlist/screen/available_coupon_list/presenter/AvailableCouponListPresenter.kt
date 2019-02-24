package com.demo.fred.meijercouponlist.screen.available_coupon_list.presenter

import android.util.Log
import com.demo.fred.meijercouponlist.adapter.AvailableCouponAdapter
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.available_coupon_list.model.AvailableCouponListDataModel
import com.demo.fred.meijercouponlist.screen.available_coupon_list.view.AvailableCouponListView
import com.demo.fred.meijercouponlist.util.Page
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.TestOnly

class AvailableCouponListPresenter {
    private val availableCouponListDataModel = AvailableCouponListDataModel()
    private var mView: AvailableCouponListView? = null
    private var couponAdapter = AvailableCouponAdapter(Page.AVAILABLE)
    private var disposable: Disposable? = null

    fun attachView(availableCouponListView: AvailableCouponListView) {
        mView = availableCouponListView
        mView?.startLoading()
    }

    fun create() {
        mView?.setAdapter(couponAdapter)
        requestData()
        onClipClicked()
    }

    private fun onClipClicked() {
        couponAdapter.onItemClick = {
            mView?.showMessage(it.title)
            mView?.clipped(it)
            couponAdapter.remove(it)
            mView?.setAdapter(couponAdapter)
            mView?.setTotal((couponAdapter.itemCount).toString())
        }
    }

    private fun requestData() {
        disposable = availableCouponListDataModel.getCoupons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ couponList ->
                Log.d(TAG, couponList.listOfCoupons[0].title)
                mView?.stopLoading()
                couponAdapter.loadItem(couponList.listOfCoupons)
                mView?.setTotal(couponList.couponCount)
            }, { error ->
                Log.e(TAG, error.message)
                mView?.showMessage(error.message.toString())
            })
    }

    fun addCoupon(coupon: MeijerCoupon) {
        couponAdapter.addItem(coupon)
        mView?.setAdapter(couponAdapter)
        mView?.setTotal((couponAdapter.itemCount).toString())
    }

    @TestOnly
    fun getView(): AvailableCouponListView? {
        return mView
    }

    @TestOnly
    fun getAdapter(): AvailableCouponAdapter {
        return couponAdapter
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }

}