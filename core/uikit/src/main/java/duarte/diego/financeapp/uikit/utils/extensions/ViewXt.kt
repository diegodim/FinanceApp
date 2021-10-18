package duarte.diego.financeapp.uikit.utils.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.View
import androidx.core.content.ContextCompat

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.isVisible(visible: Boolean) {
    visibility = if (visible)
        View.VISIBLE
    else
        View.GONE
}

inline val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
inline val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Context.getColorById(colorId: Int): ColorStateList {
    return ColorStateList.valueOf(
        ContextCompat.getColor(
            this,
            colorId
        )
    )
}