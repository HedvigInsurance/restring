package com.ice.restring

import androidx.appcompat.widget.Toolbar
import android.util.AttributeSet
import android.view.View

/**
 * A transformer which transforms Toolbar(from support library): it transforms the text set as title.
 */
class SupportToolbarTransformer : ViewTransformerManager.Transformer {

    override val viewType
        get() = Toolbar::class.java


    override fun transform(view: View?, attrs: AttributeSet): View? {
        if (view == null || !viewType.isInstance(view)) {
            return view
        }
        val resources = view.context.resources

        for (index in 0 until attrs.attributeCount) {
            when (attrs.getAttributeName(index)) {
                ATTRIBUTE_APP_TITLE, ATTRIBUTE_TITLE -> {
                    val value = attrs.getAttributeValue(index)
                    if (value != null && value.startsWith("@")) {
                        setTitleForView(view, resources.getString(attrs.getAttributeResourceValue(index, 0)))
                    }
                }
            }
        }
        return view
    }

    private fun setTitleForView(view: View, text: String) {
        (view as Toolbar).title = text
    }

    companion object {

        private const val ATTRIBUTE_TITLE = "title"
        private const val ATTRIBUTE_APP_TITLE = "app:title"
    }
}
