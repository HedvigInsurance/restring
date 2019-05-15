package com.ice.restring

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources

/**
 * A context wrapper which provide another Resources instead of the original one.
 */
class CustomResourcesContextWrapper(
        base: Context,
        private val _resources: Resources
) : ContextWrapper(base) {
    override fun getResources() = _resources
}
