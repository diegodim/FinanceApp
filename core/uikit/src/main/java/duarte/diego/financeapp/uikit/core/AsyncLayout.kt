package duarte.diego.financeapp.uikit.core

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.asynclayoutinflater.view.AsyncLayoutInflater

open class AsyncLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
): FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var isInflated = false
    private var pendingActions: MutableList<AsyncLayout.() -> Unit> = ArrayList()

    init {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    }

    fun inflateAsync(layoutResId: Int) {
        AsyncLayoutInflater(context).inflate(layoutResId, this) { view, _, _ ->
            addView(createDataBindingView(view))
            isInflated = true
            pendingActions.forEach { action -> action() }
            pendingActions.clear()
        }
    }

    fun invokeWhenInflated(action: AsyncLayout.() -> Unit) {
        if (isInflated) {
            action()
        } else {
            pendingActions.add(action)
        }
    }

    open fun createDataBindingView(view: View): View? = view
}
