package com.demo.fred.meijercouponlist.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.demo.fred.meijercouponlist.model.CouponsList
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.available_coupon_list.model.AvailableCouponListDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CouponViewModel(application: Application) : AndroidViewModel(application) {
    private val availableCouponListDataModel =
        AvailableCouponListDataModel()
    private lateinit var disposable: Disposable

    private lateinit var clippedCoupon: List<MeijerCoupon>

    private val _clippedCouponLiveData = MutableLiveData<List<MeijerCoupon>>()
    val clippedCouponLiveData: LiveData<List<MeijerCoupon>>
        get() = _clippedCouponLiveData

    private val couponsListLiveData: MutableLiveData<CouponsList> by lazy {
        loadCoupons()
    }

    fun getCoupons(): LiveData<CouponsList> {
        return couponsListLiveData
    }

    private fun loadCoupons(): MutableLiveData<CouponsList> {
        val mutableCouponList = MutableLiveData<CouponsList>()
        disposable = availableCouponListDataModel.getCoupons()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableCouponList.value = it
                //clippedCoupon = it.listOfCoupons
                Log.d(TAG, clippedCoupon[0].title)
            }, { error ->
                Log.e(TAG, "Error: " + error.message)
            })

        return mutableCouponList
    }


    companion object {
        private val TAG = this::class.java.simpleName
    }
}