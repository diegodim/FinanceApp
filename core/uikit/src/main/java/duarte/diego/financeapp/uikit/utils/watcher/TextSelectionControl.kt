package duarte.diego.financeapp.uikit.utils.watcher

import android.widget.EditText

class TextSelectionControl(private val editText: EditText, private val specialsChars: List<Char>) {

    private companion object {
        const val SELECTION_IS_NEXT_TO_A_SPECIAL_CHAR = 1
        const val BLANK_CHAR = 32.toChar()
    }

    private var previousTextLength: Int = 0

    fun setSelectionOverSpecialsChars() {
        var position = editText.selectionEnd
        val actualTextLength = editText.text.length

        specialsChars.forEach {
            val specialCharPos = findPosition(editText.text.toString(), it)

            if ((position - specialCharPos) == SELECTION_IS_NEXT_TO_A_SPECIAL_CHAR && previousTextLength < actualTextLength) {
                position++

                if(hasBlankCharAfterPosition(editText.text.toString(), position)) {
                    position++
                }

                return@forEach
            }
        }

        previousTextLength = actualTextLength

        editText.setSelection(position)
    }

    private fun findPosition(text: String, compare: Char): Int {
        return text.indexOf(compare)
    }

    private fun hasBlankCharAfterPosition(text: String, position: Int) = (position - text.indexOf(
        BLANK_CHAR
    )) ==  SELECTION_IS_NEXT_TO_A_SPECIAL_CHAR
}