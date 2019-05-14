package com.ice.restring

import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater

/**
 * Main Restring context wrapper which wraps the context for providing another layout inflater & resources.
 */
class RestringContextWrapper private constructor(base: Context,
                                                          stringRepository: StringRepository,
                                                          val viewTransformerManager: ViewTransformerManager) : ContextWrapper(CustomResourcesContextWrapper(
        base,
        RestringResources(base.getResources(), stringRepository))) {

    private val layoutInflater by lazy {
        RestringLayoutInflater(LayoutInflater.from(getBaseContext()), this, viewTransformerManager, true)
    }

    override fun getSystemService(name: String): Any? {
        if (name == LAYOUT_INFLATER_SERVICE) {
            return layoutInflater
        }

        return super.getSystemService(name)
    }

    companion object {

        fun wrap(context: Context,
                 stringRepository: StringRepository,
                 viewTransformerManager: ViewTransformerManager): RestringContextWrapper {
            return RestringContextWrapper(context, stringRepository, viewTransformerManager)
        }
    }
}