package dinson.customview.api

import dinson.customview.entity.ExchangeBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *   @author Dinson - 2017/10/13
 */
interface ExchangeApi{

    @GET("https://openexchangerates.org/api/latest.json?app_id=cf7a69aa7d174a4e8528a6a92b107903")
    fun getRate():Observable<ExchangeBean>

}