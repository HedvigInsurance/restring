package com.ice.restring

import android.util.Log
import android.view.LayoutInflater

import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Created by chris on 17/12/14.
 * Copied from Calligraphy:
 * https://github.com/chrisjenx/Calligraphy/blob/master/calligraphy/src/main/java/uk/co/chrisjenx/calligraphy/ReflectionUtils.java
 */
object ReflectionUtils {

    private val TAG = ReflectionUtils::class.java.simpleName

    fun getField(clazz: Class<*>, fieldName: String): Field? = try {
        val f = clazz.getDeclaredField(fieldName)
        f.isAccessible = true
        f
    } catch (ignored: NoSuchFieldException) {
        null
    }

    fun getValue(field: Field?, obj: Any): Any? = try {
        field?.get(obj)
    } catch (ignored: IllegalAccessException) {
        null
    }

    fun setValue(field: Field?, obj: Any, value: Any) {
        try {
            field?.set(obj, value)
        } catch (ignored: IllegalAccessException) {
        }
    }

    fun getMethod(clazz: Class<*>, methodName: String): Method? {
        val methods = clazz.methods
        for (method in methods) {
            if (method.name == methodName) {
                method.isAccessible = true
                return method
            }
        }
        return null
    }

    fun invokeMethod(obj: Any, method: Method?, args: LayoutInflater.Factory2) {
        try {
            method?.invoke(obj, args)
        } catch (e: IllegalAccessException) {
            Log.d(TAG, "Can't invoke method using reflection", e)
        } catch (e: InvocationTargetException) {
            Log.d(TAG, "Can't invoke method using reflection", e)
        }
    }
}
