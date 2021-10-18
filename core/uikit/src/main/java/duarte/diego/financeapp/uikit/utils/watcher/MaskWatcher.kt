package duarte.diego.financeapp.uikit.utils.watcher

import android.text.Editable
import android.text.TextWatcher
import duarte.diego.financeapp.commons.enums.Mask.*
import duarte.diego.financeapp.commons.extensions.removeNotNumbers
import java.util.*


class MaskWatcher(
    val mask: String,
    private val textSelectionControl: TextSelectionControl? = null
) : TextWatcher {
    private var isRunning = false

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) =
        Unit

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) =
        Unit

    override fun afterTextChanged(unformattedEditable: Editable) {
        if (isRunning) {
            return
        }
        isRunning = true

        try {
            var countFieldsFromMaskAddedToValue = 0
            var formattedCharSequence = ""
            val value = unformattedEditable.toString().removeNotNumbers()

            value.mapIndexed { index, editableCharacter ->
                var maskRelativeIndex = index + countFieldsFromMaskAddedToValue

                while (mask[maskRelativeIndex] != '#' && editableCharacter != mask[maskRelativeIndex]) {
                    formattedCharSequence += mask[maskRelativeIndex]
                    countFieldsFromMaskAddedToValue++
                    maskRelativeIndex++
                }

                formattedCharSequence += editableCharacter
            }

            unformattedEditable.replace(0, unformattedEditable.length, formattedCharSequence)

            textSelectionControl?.setSelectionOverSpecialsChars()

        } catch (ignore: Exception) {
        }

        isRunning = false
    }

    companion object {


        fun buildPhone(textSelectionControl: TextSelectionControl) =
            MaskWatcher(PHONE.value, textSelectionControl)

        fun buildCpf() =
            MaskWatcher(CPF.value)


        fun buildDate() =
            MaskWatcher(DATE.value)

        fun buildDateMY()=
            MaskWatcher(DATE_MY.value)

        fun buildCep() =
            MaskWatcher(CEP.value)

        fun buildFromString(mask: String) = MaskWatcher(
            valueOf(mask.uppercase()).value
        )
    }
}