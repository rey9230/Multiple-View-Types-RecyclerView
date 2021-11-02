package com.android.storehometest

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object Utils {

    //Gson().toJson(item, CallModel::class.java)
    private fun getJsonFromAssets(context: Context, fileName: String): String? {
        return try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    fun getHomeData(context: Context) : HomeDataModel {
        return Gson().fromJson(getJsonFromAssets(context,"data.json"), HomeDataModel::class.java)
    }
}