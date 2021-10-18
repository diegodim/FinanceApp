package duarte.diego.financeapp.uikit.components

import android.content.Context
import android.graphics.Typeface
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import android.widget.EditText
import androidx.core.content.ContextCompat.getColor
import androidx.core.widget.addTextChangedListener
import android.os.Parcelable
import android.text.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.android.material.textfield.TextInputLayout
import duarte.diego.financeapp.uikit.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize


class FinanceInputText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var contentEditText: EditText
    private var labelTextView: AppCompatTextView
    private var errorTextView: AppCompatTextView
    private var passwordToggleCheckBox: AppCompatCheckBox
    private var debounceJob: Job? = null

    init {

        inflate(context, R.layout.finance_input_text_layout, this)

        contentEditText = findViewById(R.id.financeInputTextContentEditText)
        labelTextView = findViewById(R.id.financeInputTextLabelTextView)
        errorTextView = findViewById(R.id.financeInputTextErrorTextView)
        passwordToggleCheckBox = findViewById(R.id.financeInputTextPasswordToggleImageButton)

        labelTextView.background = this.background

        constraintMatch()

        setupView(attrs)

    }

    private fun setupView( attrs: AttributeSet? = null,){
        contentEditText.setOnFocusChangeListener { _, focus ->
            if (!error.isNullOrBlank()) {
                labelTextView.setTextColor(getColor(context, R.color.dark_red))
            } else if (focus) {

                labelTextView.setTextColor(
                    getColor(
                        context,
                        R.color.design_default_color_primary
                    )
                )
            } else {
                labelTextView.setTextColor(getColor(context, R.color.dark_gray))
            }

        }

        passwordToggleCheckBox.setOnCheckedChangeListener { _, checked ->
            if (contentEditText.isInputTypePassword()) {
                when (checked) {
                    true -> {
                        contentEditText.transformationMethod = null
                    }
                    false -> {
                        contentEditText.transformationMethod = PasswordTransformationMethod()
                    }

                }
                contentEditText.setSelection(contentEditText.length())
            }
        }

        attrs?.apply {

            context.obtainStyledAttributes(this, R.styleable.FinanceInputText).apply {
                label = getString(R.styleable.FinanceInputText_label)
                hint = getString(R.styleable.FinanceInputText_hint)
                error = getString(R.styleable.FinanceInputText_error)
                text = getString(R.styleable.FinanceInputText_textInput) ?: ""
                imeOptions = getInt(R.styleable.FinanceInputText_android_imeOptions, 0)
                minLines = getInt(R.styleable.FinanceInputText_android_minLines, 1)
                maxLength = getInt(R.styleable.FinanceInputText_android_maxLength, 0)
                passwordToggleEnabled =
                    getBoolean(R.styleable.FinanceInputText_passwordToggleEnabled, false)
                inputType = getInteger(
                    R.styleable.FinanceInputText_android_inputType,
                    InputType.TYPE_CLASS_TEXT
                )
                goneLabel = getBoolean(R.styleable.FinanceInputText_goneLabel, false)
                styleLabel = getResourceId(R.styleable.FinanceInputText_styleLabel, 0)

                recycle()
            }
        }
    }

    var label: CharSequence?
        get() = labelTextView.text
        set(value) {
            labelTextView.text = value
        }

    var hint: CharSequence?
        get() = contentEditText.hint
        set(value) {
            contentEditText.hint = value
        }

    var error: CharSequence?
        get() = errorTextView.text
        set(value) {
            errorTextView.text = value
            if(value.isNullOrBlank()) {
                errorTextView.clearAnimation()
                contentEditText.background = getDrawable(context, R.drawable.finance_input_text_background)
                if(contentEditText.isFocused)
                    labelTextView.setTextColor(getColor(context, R.color.colorPrimary))
                else
                    labelTextView.setTextColor(getColor(context, R.color.dark_gray))

            }else {
                errorTextView.clearAnimation()
                contentEditText.background =
                    getDrawable(context, R.drawable.finance_input_text_error_background)
                labelTextView.setTextColor(getColor(context, R.color.dark_red))
                errorTextView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_top_in))
            }
        }

    var text: String?
        get() = contentEditText.text.toString()
        set(value) {
            contentEditText.text = SpannableStringBuilder(value)
        }

    var passwordToggleEnabled: Boolean = passwordToggleCheckBox.visibility == View.VISIBLE
        set(value) {
            passwordToggleCheckBox.visibility = if (value) View.VISIBLE else View.GONE
            field = value
        }

    var inputType: Int
        get() = contentEditText.inputType
        set(value) {
            contentEditText.inputType = value
            contentEditText.setTypeface(Typeface.DEFAULT)
        }

    var imeOptions: Int
        get() = contentEditText.imeOptions
        set(value) {
            contentEditText.imeOptions = value
        }

    var minLines: Int
        get() = contentEditText.minLines
        set(value) {
            contentEditText.minLines = value
        }

    var maxLength: Int = 0
        set(value) {
            field = value
            if (value > 0) {
                contentEditText.filters =
                    arrayOf<InputFilter>(InputFilter.LengthFilter(value))
            }
        }

    var goneLabel: Boolean = false
        set(value) {
            labelTextView.visibility = if (value) View.GONE else View.VISIBLE
            field = value
        }

    var styleLabel: Int = 0
        set(value) {
            labelTextView.setTextAppearance(context, value)
        }

    private fun EditText?.isInputTypePassword(): Boolean {
        return (this != null
                && (this.inputType == InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
                this.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD ||
                this.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD ||
                this.inputType == InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD||
                this.inputType == 0x12 ||
                this.inputType == 0x91 ||
                this.inputType == 0xE1 ||
                this.inputType == 0x81))
    }

    fun addTextChangedListener(textWatcher: (Editable?) -> Unit) {
        contentEditText.addTextChangedListener {
            textWatcher(it)
        }
    }

    fun addTextChangedListener(textWatcher: TextWatcher) {
        contentEditText.addTextChangedListener(textWatcher)
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        return CustomInputTextState(superState, text.toString(), passwordToggleCheckBox.isChecked)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val myState = state as? CustomInputTextState
        super.onRestoreInstanceState(myState?.superSavedState ?: state)

        text = myState?.text ?: ""
        passwordToggleCheckBox.isChecked = myState?.passwordToggleChecked ?: false
        contentEditText.setSelection(contentEditText.length())
    }

    @Parcelize
    class CustomInputTextState(val superSavedState: Parcelable?,
                               val text: String,
                               val passwordToggleChecked: Boolean) :
        View.BaseSavedState(superSavedState), Parcelable

    private fun constraintMatch(){
        contentEditText.id = View.generateViewId()
        labelTextView.id = View.generateViewId()
        errorTextView.id = View.generateViewId()
        passwordToggleCheckBox.id = View.generateViewId()

        val set = ConstraintSet()
        set.clone(this)

        set.connect(errorTextView.id, ConstraintSet.TOP, contentEditText.id, ConstraintSet.BOTTOM)
        set.connect(passwordToggleCheckBox.id, ConstraintSet.TOP, contentEditText.id, ConstraintSet.TOP)
        set.connect(passwordToggleCheckBox.id, ConstraintSet.END, contentEditText.id, ConstraintSet.END)
        set.connect(passwordToggleCheckBox.id, ConstraintSet.BOTTOM, contentEditText.id, ConstraintSet.BOTTOM)
        set.applyTo(this)
    }


    fun clearErrorAfterTyping(afterClear: () -> Unit = {}) = contentEditText.addTextChangedListener {
        if (!it?.toString().isNullOrBlank()) {
            this.error = ""
            afterClear()
        }
    }


    fun afterTextChanged(event: (text: String) -> Unit) {
        contentEditText.doAfterTextChanged {
            event(it?.toString() ?: "")
        }
    }


    fun handleSuccess(event: (text: String) -> Unit = {}) {
        this.error = ""
        cancelDebounce()
        event(contentEditText.text.toString())
    }

    fun handleError(
        error: Throwable,
        viewScope: LifecycleCoroutineScope,
        event: (error: Throwable) -> Unit = {}
    ) {

        doDebounce(viewScope) {
            this.error = error.message
        }


        event(error)
    }

    private fun doDebounce(
        scope: LifecycleCoroutineScope,
        delayMs: Long = 1000,
        event: () -> Unit
    ) {
        contentEditText.doAfterTextChanged {
            cancelDebounce()
        }
        debounce(delayMs, scope) {
            event()
        }
    }

    private fun debounce(
        delayMs: Long = 1000,
        scope: LifecycleCoroutineScope,
        f: () -> Unit
    ) {
        debounceJob = null
        if (debounceJob?.isCompleted != false) {
            debounceJob = scope.launch {
                delay(delayMs)
                f()
            }
        }
    }

    private fun cancelDebounce() = debounceJob?.cancel()

}

