package com.demo.fred.meijercouponlist.screen.clipped_coupon_list

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.demo.fred.meijercouponlist.R
import com.demo.fred.meijercouponlist.adapter.AvailableCouponAdapter
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.presenter.ClippedCouponListPresenter
import com.demo.fred.meijercouponlist.screen.clipped_coupon_list.view.ClippedCouponListView
import com.demo.fred.meijercouponlist.util.AddClipCoupon
import com.demo.fred.meijercouponlist.util.ClipCoupon

/**
 *
 */
class ClippedCouponListFragment : Fragment(), ClippedCouponListView, AddClipCoupon {

    private lateinit var clipCouponRecyclerView: RecyclerView
    private lateinit var presenter: ClippedCouponListPresenter
    private lateinit var clipCouponEvent: ClipCoupon
    private lateinit var tvTotal: TextView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        clipCouponEvent = activity as ClipCoupon
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_clipped_coupon_list, container, false)
        clipCouponRecyclerView = view.findViewById(R.id.clipped_coupon_recycler_view)
        tvTotal = view.findViewById(R.id.tv_coupon_total_clip)
        clipCouponRecyclerView.itemAnimator = DefaultItemAnimator()
        presenter = ClippedCouponListPresenter()
        presenter.attach(this)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.create()
    }


    override fun addCoupons(coupons: List<MeijerCoupon>) {
        presenter.loadList(coupons)
    }

    override fun setAdapter(couponAdapter: AvailableCouponAdapter) {
        clipCouponRecyclerView.adapter = couponAdapter
    }

    override fun clipped(coupon: MeijerCoupon) {
        clipCouponEvent.getClippedCoupon(coupon)
    }

    override fun addCoupon(coupon: MeijerCoupon) {

    }

    override fun setTotal(size: Int) {
        val total = resources.getString(R.string.coupon_total).plus(size.toString())
        tvTotal.text = total
    }


    companion object {
        private const val PAGE_NUM = "PAGE_NUM"
        fun newInstance(page: Int): ClippedCouponListFragment {
            val fragment = ClippedCouponListFragment()
            val args = Bundle()
            args.putInt(PAGE_NUM, page)
            fragment.arguments = args
            return fragment
        }
    }
}