/*
 * Copyright 2014 Leonardo Rossetto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package duarte.diego.financeapp.uikit.utils.watcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import duarte.diego.financeapp.commons.constants.Constants.LOCALE_PT_BR
import duarte.diego.financeapp.uikit.R
import java.lang.ref.WeakReference
import java.text.DecimalFormat
import java.text.NumberFormat

class MoneyMask(editText: EditText, private val action: (String) -> Unit = {}) : TextWatcher {

    private val mText: WeakReference<EditText> = WeakReference(editText)
    private val mFormatter: NumberFormat = NumberFormat.getCurrencyInstance(LOCALE_PT_BR)
    private var mIsUpdating: Boolean = false

    override fun afterTextChanged(s: Editable) = Unit

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        mText.get()?.setSelection(mText.get()?.text?.length ?: 0)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        var aux = s
        if (mIsUpdating) {
            mIsUpdating = false
            return
        }
        mIsUpdating = true
        val str = unmask(aux.toString())

        try {
            val parsed = java.lang.Double.parseDouble(str)
            if (parsed == 0.toDouble()) {
                mText.get()?.text = null
                return
            }
            aux = mFormatter.format(parsed / 100)
            if (mText.get()?.text?.toString()?.contentEquals(aux) == false) {
                val v = String.format(
                    mText.get()?.context?.getString(R.string.formatter_default_currency) ?: "", aux
                )
                action(v)
                mText.get()?.setText(v)
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        mText.get()?.setSelection(mText.get()?.text?.length ?: 0)
    }

    companion object {

        fun getBRFormatter(currencyView: EditText, action: (String) -> Unit = {}): MoneyMask {
            val mask = MoneyMask(currencyView, action)
            val formatter = mask.mFormatter as DecimalFormat
            val symbols = formatter.decimalFormatSymbols
            symbols.currencySymbol = ""
            formatter.decimalFormatSymbols = symbols
            return mask
        }

        fun unmask(masked: String?) = masked?.replace("[^\\d]".toRegex(), "") ?: ""

        fun doubleValue(masked: String): Double = try {
            java.lang.Double.parseDouble(unmask(masked)) / 100
        } catch (ignore: NumberFormatException) {
            0.0
        }
    }
}
