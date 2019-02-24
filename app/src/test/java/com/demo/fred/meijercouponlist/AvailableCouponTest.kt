package com.demo.fred.meijercouponlist

import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.available_coupon_list.AvailableCouponListFragment
import com.demo.fred.meijercouponlist.screen.available_coupon_list.model.AvailableCouponListDataModel
import com.demo.fred.meijercouponlist.screen.available_coupon_list.presenter.AvailableCouponListPresenter
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class AvailableCouponTest {
    private lateinit var fragment: AvailableCouponListFragment
    private lateinit var presenter: AvailableCouponListPresenter
    private lateinit var model: AvailableCouponListDataModel
    private val coupons = ArrayList<MeijerCoupon>()

    @Before
    fun setUp() {
        model = AvailableCouponListDataModel()
        MockitoAnnotations.initMocks(this)
        fragment = mock(AvailableCouponListFragment::class.java)
        presenter = AvailableCouponListPresenter()
        mockArrayList()

    }

    private fun mockArrayList() {
        coupons.add(MeijerCoupon("Test1", "TestDecs1", "TestUrl1", "date1", false))
        coupons.add(MeijerCoupon("Test2", "TestDecs2", "TestUrl2", "date2", false))
        coupons.add(MeijerCoupon("Test3", "TestDecs3", "TestUrl3", "date3", false))
        coupons.add(MeijerCoupon("Test4", "TestDecs4", "TestUrl4", "date4", false))
    }

    @Test
    fun testService() {
        model.getCoupons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Assert.assertEquals(it.couponCount, 403)
            }
    }

    @Test
    fun testAttach(){
        presenter.attachView(fragment)
    }

    @Test
    fun testAddCoupon(){
        presenter.addCoupon(mock(MeijerCoupon::class.java))
        Assert.assertNotNull(presenter.getAdapter().itemCount)
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }
}