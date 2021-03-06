package com.demo.fred.meijercouponlist

import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.ClippedCouponListFragment
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.presenter.ClippedCouponListPresenter
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

/**
 * Class: ClippedCouponTest
 * Author: Fred Chen
 * Time: 22/02/2019
 * Runner: Junit4
 * Test: ClippedCouponFragment/Presenter
 *
 * Description:
 * Setup and perform unit test for presenter methods of ClippedCouponFragment
 *
 */
@RunWith(JUnit4::class)
class ClippedCouponTest {
    private lateinit var fragment: ClippedCouponListFragment
    private lateinit var presenter: ClippedCouponListPresenter
    private val coupons = ArrayList<MeijerCoupon>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fragment = mock(ClippedCouponListFragment::class.java)
        presenter = ClippedCouponListPresenter()
        mockArrayList()
    }

    private fun mockArrayList() {
        coupons.add(MeijerCoupon("Test1", "TestDecs1", "TestUrl1", "date1", false))
        coupons.add(MeijerCoupon("Test2", "TestDecs2", "TestUrl2", "date2", false))
        coupons.add(MeijerCoupon("Test3", "TestDecs3", "TestUrl3", "date3", false))
        coupons.add(MeijerCoupon("Test4", "TestDecs4", "TestUrl4", "date4", false))
    }

    /**
     * Test presenter binds the view, verify if view is not empty
     */
    @Test
    fun testAttach() {
        presenter.attach(fragment)
        assertNotNull(presenter.getView())
    }

    @Test
    fun test() {
        presenter.loadList(coupons)
    }

    @Test
    fun testOnclick() {
        presenter.attach(fragment)
        presenter.create()
    }


}