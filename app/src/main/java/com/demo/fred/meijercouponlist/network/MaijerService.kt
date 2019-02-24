package com.demo.fred.meijercouponlist.network

import com.demo.fred.meijercouponlist.model.CouponsList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Network client to get data from services using retrofit
 *
 * https://meijerkraig.azurewebsites.net/api/Products?code=34lgBae%2FxIEnqksQpkn3w9F0JTKCafuiCr0ejLNLvBzlOlOZBa1CMA%3D%3D
 *
 * */
interface MeijerService {

    companion object Creator {
        fun create(): MeijerService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://meijerkraig.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(MeijerService::class.java)
        }
    }

    @GET("api/Products")
    fun getCoupon(@Query("code", encoded = true) code: String): Observable<CouponsList>

}