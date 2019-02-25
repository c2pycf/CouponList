package com.demo.fred.meijercouponlist.screen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.demo.fred.meijercouponlist.R
import com.demo.fred.meijercouponlist.adapter.PageAdapter
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.available_coupon_list.AvailableCouponListFragment
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.ClippedCouponListFragment
import com.demo.fred.meijercouponlist.util.AddClipCoupon
import com.demo.fred.meijercouponlist.util.ClipCoupon
import kotlinx.android.synthetic.main.activity_coupon_list.*

/**
 * Class: CouponListActivity
 * Author: Fred Chen
 * Time: 22/02/2019
 *
 * Description:
 * Desc: Display Tabs and fragment within the container. Able to swap between fragments
 *
 */
class CouponListActivity : AppCompatActivity(), ClipCoupon {

    private val clippedCouponList = ArrayList<MeijerCoupon>()
    private lateinit var addClipCouponEvent: AddClipCoupon
    private lateinit var pageAdapter: PageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_list)
        setSupportActionBar(coupon_toolbar)
        supportActionBar?.title = "Coupon"
        pageAdapter = PageAdapter(supportFragmentManager)

        pageAdapter.add(AvailableCouponListFragment.newInstance(0), getString(R.string.pager_title_available))
        pageAdapter.add(ClippedCouponListFragment.newInstance(1), getString(R.string.pager_title_clip))

        coupon_view_pager.adapter = pageAdapter
        tabs_coupon.setupWithViewPager(coupon_view_pager)
    }

    override fun getClippedCoupon(coupon: MeijerCoupon) {
        val currentPagePosition = coupon_view_pager.currentItem
        when (currentPagePosition) {
            0 -> addClipCouponEvent = pageAdapter.getItem(1) as AddClipCoupon
            1 -> addClipCouponEvent = pageAdapter.getItem(0) as AddClipCoupon
        }

        if (clippedCouponList.contains(coupon)) {
            clippedCouponList.remove(coupon)
            addClipCouponEvent.addCoupon(coupon)
        } else {
            clippedCouponList.add(coupon)
        }
        addClipCouponEvent.addCoupon(coupon)
        addClipCouponEvent.addCoupons(clippedCouponList)

    }
}
