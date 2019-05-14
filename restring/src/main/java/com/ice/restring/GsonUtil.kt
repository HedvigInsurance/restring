package com.ice.restring

import com.google.gson.Gson

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object: com.google.gson.reflect.TypeToken<T>(){}.type)
