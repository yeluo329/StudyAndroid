package com.example.wanandroid

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wanandroid.util.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var addressTv: TextView
    private lateinit var resultTv: TextView
    private lateinit var activity: MainActivity
    private lateinit var resultIv: ImageView
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity = this
        init()
        initEvent()


    }

    private fun init() {
        button = findViewById(R.id.load)
        addressTv = findViewById(R.id.tv)
        resultTv = findViewById(R.id.result_tv)
        resultIv = findViewById(R.id.result_iv)
//
//        Glide.with(activity)
//            .load("https://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
//            .into(resultIv)
    }

    private fun initEvent() {
        button.setOnClickListener {
//            startActivity<MainActivity2>()
//            post()
            val address = addressTv.text.toString()
            if (!TextUtils.isEmpty(address)) {
                val service = RetrofitUtils.getService(Api::class.java)
                //异步回调
                service.getJsonData("新歌榜", "json").enqueue(object : Callback<Data<MusicInfo>> {
                    override fun onResponse(
                        call: Call<Data<MusicInfo>>,
                        response: Response<Data<MusicInfo>>,
                    ) {
                        Toast.makeText(activity, "get回调成功:异步执行", Toast.LENGTH_SHORT).show()
                        val body = response.body()
                        val data = body?.data
                        resultTv.text = "${data?.name}\n${data?.url}"

                        Glide.with(activity)
                            .load(convertToHttps(data!!.picturl))
                            .into(resultIv)
                    }

                    override fun onFailure(call: Call<Data<MusicInfo>>, t: Throwable) {
                        Log.e("LJZ", "回调失败：" + t.message + "," + t.toString());
                    }


                })
                //同步
//            service.getJsonData("新歌榜", "json").execute()


//                val service = RetrofitUtils.getService(BirthdayAnimalService::class.java)
//                val map = mutableMapOf<String, Any>()
//                map["keyword"] = address
//                map["key"] = KEY
//                service.getData(map).enqueue(object : Callback<Info> {
//                    override fun onResponse(call: Call<Info>, response: Response<Info>) {
//                        val body = response.body()
//                        body?.let {
//                            if (it.reason.equals("SUCCES")) {
//                                resultTv.text = it.result.toString()
//                            }
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<Info>, t: Throwable) {
//                        Log.d("TAG", t.toString())
//                    }
//
//                })
//                val weatherService = RetrofitUtils.getService(WeatherService::class.java)
//                weatherService.getInformation(address, KEY).enqueue(object : Callback<Weather> {
//                    override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
//                        val weather = response.body()
//                        weather?.let {
//                            if (it.errorCode == 0) {
//                                resultTv.text = it.result.toString()
//                            }
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<Weather>, t: Throwable) {
//                        Log.d("TAG", t.toString())
////                        Toast.makeText(this, "网络延迟！", Toast.LENGTH_LONG).show();
//                    }
//
//                })
//            }

//            }
            }
        }
    }

    fun convertToHttps(httpUrl: String): String {
        val url = URL(httpUrl)
        val httpsUrl = URL("https", url.host, url.port, url.file)
        return httpsUrl.toString()
    }


    private fun post() {
        val service = RetrofitUtils.getService(Api::class.java)
        val postDataCall = service.postDataCall("JSON")


        Log.d(TAG, "post == url：" + postDataCall.request().url())
        postDataCall.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val body = response.body()
                body?.let {
                    resultTv.text = "返回的数据：" + "\n\n" + response.body().toString()
                    Toast.makeText(this@MainActivity, "post回调成功:异步执行", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })


    }


    companion object {
        const val KEY = "4facc0d2c2e347591b192de115bf233b"
    }
}
