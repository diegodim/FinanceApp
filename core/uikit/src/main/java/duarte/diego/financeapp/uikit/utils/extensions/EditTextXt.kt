package duarte.diego.financeapp.uikit.utils.extensions

import android.text.InputFilter
import android.text.InputType.*
import android.text.method.DigitsKeyListener
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import duarte.diego.financeapp.uikit.components.FinanceInputText
import duarte.diego.financeapp.uikit.utils.watcher.MaskWatcher
import duarte.diego.financeapp.uikit.utils.watcher.MoneyMask


fun EditText.setMaxLength(maxLength: Int) {
    filters =
        filters.toMutableList().apply { add(InputFilter.LengthFilter(maxLength)) }.toTypedArray()
}

fun FinanceInputText.setupViewAsCep() {
    this.contentEditText.run {
        val maskWatcher = MaskWatcher.buildCep()
        keyListener = DigitsKeyListener.getInstance("0123456789.-")
        addTextChangedListener(maskWatcher)
        setMaxLength(9)
        inputType = 20
    }
}

fun FinanceInputText.setupViewAsName() {
    this.contentEditText.run {
        filters = arrayOf(InputFilter { cs, _, _, _, _, _ ->
            if (cs == "") return@InputFilter cs
            if (cs.toString().matches("[A-Za-zÀ-ÿ '-]*".toRegex())) {
                cs
            } else ""
        })
        inputType = TYPE_CLASS_TEXT or TYPE_TEXT_FLAG_CAP_WORDS or TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }
}

fun FinanceInputText.setupViewAsDate() {
    this.contentEditText.run {
        val maskWatcher = MaskWatcher.buildDate()
        keyListener = DigitsKeyListener.getInstance("0123456789/")
        addTextChangedListener(maskWatcher)
        setMaxLength(10)
        inputType = 20
        isCursorVisible = false
        doAfterTextChanged {
            isCursorVisible = !it.isNullOrBlank()
        }
    }
}

fun FinanceInputText.setupViewAsMoney() {
    this.contentEditText.run {
        val maskWatcher = MoneyMask.getBRFormatter(this)
        keyListener = DigitsKeyListener.getInstance(" R$.,0123456789")
        addTextChangedListener(maskWatcher)
        inputType = TYPE_CLASS_NUMBER
    }
}