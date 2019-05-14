package com.ice.restring.example

import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (findViewById<View>(R.id.text_view2) as TextView).setText(R.string.subtitle)
    }
}
