package com.allybros.tooltip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.allybros.tooltipbubble.Bubble
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btBottom.setOnClickListener{ showBubble(this, btBottom) }
        btBottomLeft.setOnClickListener{ showBubble(this, btBottomLeft) }
        btBottomRight.setOnClickListener{ showBubble(this, btBottomRight) }
        btCenter.setOnClickListener{ showBubble(this, btCenter) }
        btCenterRight.setOnClickListener{ showBubble(this, btCenterRight) }
        btCenterLeft.setOnClickListener{ showBubble(this, btCenterLeft) }
        btTop.setOnClickListener{ showBubble(this, btTop) }
        btTopLeft.setOnClickListener{ showBubble(this, btTopLeft) }
        btTopRight.setOnClickListener{ showBubble(this, btTopRight) }
    }

    private fun showBubble(activity: MainActivity, anchor: Button) {
        Bubble(editText.text.toString(), activity).showBubble(anchor)
    }

}