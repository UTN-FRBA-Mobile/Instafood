package ar.com.instafood.interfaces

import ar.com.instafood.models.MenuResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MenuService {

    @GET("restaurants/0/menues")
    fun getMenu(): Observable<MenuResponse>

    companion object {
        fun create(): MenuService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://instafood-server.herokuapp.com/instafood-api/")
                    .build()

            return retrofit.create(MenuService::class.java)
        }
    }
}