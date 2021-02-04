package me.apon.gradle.plugin.sample

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        val min = width.coerceAtMost(height)
        val smallestWidth = min / (dm.densityDpi / 160.0)
        assets
        val v = resources.getDimension(R.dimen.base_swdp)
        val tv = findViewById<TextView>(R.id.text)
        tv.text =
            "d700:" + v + "\nwidth:" + width + "\nheight:" + height + "\nmin:" + min + "\ndpi:" + dm.densityDpi + "\nsmallestWidth:" + smallestWidth + "\nbase:" + v / (dm.densityDpi / 160.0)
    }
}
