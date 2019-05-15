package com.ice.restring.shadow


import android.content.res.AssetManager

import org.robolectric.annotation.Implements
import org.robolectric.shadows.ShadowAssetManager

import java.util.LinkedHashMap

@Implements(AssetManager::class)
class MyShadowAssetManager : ShadowAssetManager() {

    private val resourceEntryNames = LinkedHashMap<Int, String>()

    fun getResourceText(id: Int): CharSequence {
        return "@$id"
    }

    fun getResourceEntryName(resid: Int): String? {
        return if (resourceEntryNames.containsKey(resid)) {
            resourceEntryNames[resid]
        } else super.getResourceEntryName(resid)
    }

    fun addResourceEntryNameForTesting(resId: Int, stringName: String) {
        resourceEntryNames[resId] = stringName
    }
}
