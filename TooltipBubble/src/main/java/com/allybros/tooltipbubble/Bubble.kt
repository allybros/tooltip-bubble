package com.allybros.tooltipbubble

import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.content.Context
import android.graphics.Rect
import android.view.*
import android.widget.PopupWindow
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import android.view.WindowManager
import android.util.DisplayMetrics
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.widget_switch_button_popup.view.*

/**
 * Created by orcunk on 06.12.2021 14:15
 */

class Bubble constructor(
              text: String,
              activity: Activity,
              background: Int? = null,
              anchorTopStyle: Int? = null,
              anchorDownStyle: Int? = null,
              animationStyle: Int? = null
) {
    private val tipWindow: PopupWindow?
    private val contentView: View
    private val inflater: LayoutInflater
    private var text: String = ""
    private var context: Context? = null
    private var activity: Activity? = null
    private var background: Int
    private var anchorTopStyle: Int
    private var anchorDownStyle: Int
    private var animationStyle: Int = R.style.BubbleAnimation

    fun showBubble(anchor: View) {
        tipWindow?.height = WindowManager.LayoutParams.WRAP_CONTENT
        tipWindow?.width = WindowManager.LayoutParams.WRAP_CONTENT
        tipWindow?.isOutsideTouchable = true
        tipWindow?.isTouchable = true
        tipWindow?.isFocusable = true
        tipWindow?.setBackgroundDrawable(BitmapDrawable())
        tipWindow?.contentView = contentView
        val screenPos = IntArray(2)
        anchor.getLocationOnScreen(screenPos)

        val anchorRect = Rect(
            screenPos[0], screenPos[1], screenPos[0]
                    + anchor.width, screenPos[1] + anchor.height
        )
        contentView.measure(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        contentView.root.measure(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        var positionX: Int = anchorRect.centerX()
        var positionY: Int = anchorRect.centerY()


        contentView.root.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                contentView.root.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val screenPopup = IntArray(2)
                contentView.root.getLocationOnScreen(screenPopup)

                val rootRect = Rect(
                    screenPopup[0], screenPopup[1], screenPopup[0]
                            + contentView.root.width, screenPopup[1] + contentView.root.height
                )
                val contentViewWidth: Int = contentView.root.measuredWidth
                val contentViewHeight: Int = contentView.root.measuredHeight
                val rootWidth: Int
                val rootHeight: Int
                val displayMetrics = DisplayMetrics()
                activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
                rootWidth =  displayMetrics.widthPixels
                rootHeight =  displayMetrics.heightPixels

                //Shifting
                var margin = 0
                if((positionX + contentViewWidth) > rootWidth){
                    margin += 12
                }
                margin += (positionX - rootRect.left - 36)
                if(margin < 36){
                    margin += 48
                } else if (margin > contentViewWidth - 96){
                    margin = contentViewWidth - 96
                }


                if (contentViewHeight + anchorRect.bottom < rootHeight){ //isTop ?
                    positionX = anchorRect.centerX() - 24
                    positionY = anchorRect.centerY() + 48

                    setMarginStart(contentView.ivAnchorUp, margin)
                    contentView.ivAnchorUp.visible()
                } else {                                    //isBottom ?
                    positionX = anchorRect.centerX() - 24
                    positionY = anchorRect.centerY() - (contentViewHeight + anchorRect.height()) - 12

                    setMarginStart(contentView.ivAnchorDown, margin)
                    contentView.ivAnchorDown.visible()
                }

                this@Bubble.dismissBubble()
                contentView.visible()
                //Redraw
                contentView.tvCaption.text = text
                setBackground()

                tipWindow?.showAtLocation(anchor, Gravity.NO_GRAVITY, positionX - 48, positionY)
            }
        })


        contentView.tvCaption.text = this.text
        contentView.invisible()
        tipWindow?.showAtLocation(anchor, Gravity.NO_GRAVITY, positionX - 36, positionY)
    }

    fun isBubbleShown(): Boolean {
        return tipWindow != null && tipWindow.isShowing
    }

    fun dismissBubble() {
        if (tipWindow != null && tipWindow.isShowing) tipWindow.dismiss()
    }

    private fun setMarginStart(view: View, marginStart: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val params = view.layoutParams as LinearLayout.LayoutParams
            params.setMargins(marginStart,0,0,0)
            view.layoutParams = params
            view.requestLayout()
        }
    }

    private fun setBackground(){
        contentView.llBackground.background = context?.let { ContextCompat.getDrawable(it, background) }
        contentView.ivAnchorUp.setImageDrawable(context?.let { ContextCompat.getDrawable(it, anchorTopStyle) })
        contentView.ivAnchorDown.setImageDrawable(context?.let { ContextCompat.getDrawable(it, anchorDownStyle) })
    }

    private fun View.invisible(){
        this.visibility = View.INVISIBLE
    }

    private fun View.visible(){
        this.visibility = View.VISIBLE
    }

    init {
        this.activity = activity
        this.context = activity.applicationContext
        tipWindow = PopupWindow(context)
        inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        contentView = inflater.inflate(R.layout.widget_switch_button_popup, null)
        this.text = text
        this.background = background?: R.drawable.bg_spinner_popup
        this.anchorTopStyle = anchorTopStyle?: R.drawable.nav_up
        this.anchorDownStyle = anchorDownStyle?: R.drawable.nav_down
        this.animationStyle = animationStyle?: R.style.BubbleAnimation

        tipWindow.animationStyle = this.animationStyle
    }
}