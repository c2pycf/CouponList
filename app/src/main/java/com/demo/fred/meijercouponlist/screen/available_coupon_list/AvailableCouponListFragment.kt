package com.demo.fred.meijercouponlist.screen.available_coupon_list

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.demo.fred.meijercouponlist.R
import com.demo.fred.meijercouponlist.adapter.AvailableCouponAdapter
import com.demo.fred.meijercouponlist.model.MeijerCoupon
import com.demo.fred.meijercouponlist.screen.available_coupon_list.presenter.AvailableCouponListPresenter
import com.demo.fred.meijercouponlist.screen.available_coupon_list.view.AvailableCouponListView
import com.demo.fred.meijercouponlist.util.AddClipCoupon
import com.demo.fred.meijercouponlist.util.ClipCoupon

/**
 * Class: AvailableCouponListFragment
 * Author: Fred Chen
 * Time: 22/02/2019
 * Description:
 * Display coupon list from the server and user can click to clip the coupon
 * to another list
 */
class AvailableCouponListFragment : Fragment(), AvailableCouponListView, AddClipCoupon {

    private lateinit var availableCouponRecyclerView: RecyclerView
    private lateinit var availableCouponListPresenter: AvailableCouponListPresenter
    private lateinit var tvTotal: TextView
    private lateinit var progressLoading: ProgressBar
    private lateinit var clipCouponEvent: ClipCoupon

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        clipCouponEvent = activity as ClipCoupon
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_available_coupon_list, container, false)
        availableCouponRecyclerView = view.findViewById(R.id.available_coupon_recycler_view)
        tvTotal = view.findViewById(R.id.tv_coupon_total)
        progressLoading = view.findViewById(R.id.coupon_loading)
        availableCouponRecyclerView.itemAnimator = DefaultItemAnimator()
        availableCouponListPresenter = AvailableCouponListPresenter()
        availableCouponListPresenter.attachView(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        availableCouponListPresenter.create()

    }

    override fun clipped(coupon: MeijerCoupon) {
        clipCouponEvent.getClippedCoupon(coupon)
    }

    override fun setAdapter(couponAdapter: AvailableCouponAdapter) {
        availableCouponRecyclerView.adapter = couponAdapter
    }

    override fun setTotal(couponCount: String) {
        val total = resources.getString(R.string.coupon_total).plus(couponCount)
        tvTotal.text = total
    }

    override fun showMessage(message: String) {
        Snackbar.make(availableCouponRecyclerView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun startLoading() {
        progressLoading.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        progressLoading.visibility = View.GONE
    }

    override fun addCoupons(coupons: List<MeijerCoupon>) {
    }

    override fun addCoupon(coupon: MeijerCoupon) {
        availableCouponListPresenter.addCoupon(coupon)
    }


    companion object {

        private val TAG = this::class.java.simpleName
        private const val PAGE_NUM = "PAGE_NUM"
        fun newInstance(page: Int): AvailableCouponListFragment {
            val fragment = AvailableCouponListFragment()
            val args = Bundle()
            args.putInt(PAGE_NUM, page)
            fragment.arguments = args
            return fragment
        }
    }

}
