package com.demo.fred.meijercouponlist

import android.app.Activity
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.available_coupon_list.model.AvailableCouponListDataModel
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.ClippedCouponListFragment
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.presenter.ClippedCouponListPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class AvailableCouponTest {
    private lateinit var activity: Activity
    private lateinit var fragment: ClippedCouponListFragment
    private lateinit var presenter: ClippedCouponListPresenter
    private val coupons =  ArrayList<MeijerCoupon>()
    private lateinit var model: AvailableCouponListDataModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fragment = mock(ClippedCouponListFragment::class.java)
        presenter = ClippedCouponListPresenter()
        model = AvailableCouponListDataModel()
        mockArrayList()
    }

    private fun mockArrayList() {
        coupons.add(MeijerCoupon("Test1","TestDecs1","TestUrl1","date1",false))
        coupons.add(MeijerCoupon("Test2","TestDecs2","TestUrl2","date2",false))
        coupons.add(MeijerCoupon("Test3","TestDecs3","TestUrl3","date3",false))
        coupons.add(MeijerCoupon("Test4","TestDecs4","TestUrl4","date4",false))
    }

    @Test
    fun testAttach(){
        presenter.attach(fragment)
    }

    @Test
    fun test(){
        //presenter.loadList(coupons)
    }

    @Test
    fun get_coupon_test(){
        model.getCoupons()
    }
}