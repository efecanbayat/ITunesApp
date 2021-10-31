package com.efecanbayat.itunesapplication.utils

import com.efecanbayat.itunesapplication.data.entity.BaseResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

val gson: Gson = GsonBuilder().create()


fun parseFileAsResponse(
    fileName: String,
    type: Type
): BaseResponse {
    return gson.fromJson(gson.getResourceReader(fileName), type)
}

fun parseFileAsBaseResponse(fileName: String): BaseResponse {
    return parseFileAsResponse(fileName, typeOf<BaseResponse>())
}

fun Gson.getResourceReader(fileName: String): BufferedReader {
    val resource = javaClass.classLoader!!.getResourceAsStream(fileName)
    return BufferedReader(InputStreamReader(resource, Charsets.UTF_8))
}