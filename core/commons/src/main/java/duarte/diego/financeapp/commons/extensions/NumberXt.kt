package duarte.diego.financeapp.commons.extensions

import java.text.NumberFormat
import java.util.*

fun Number.asCurrency(): String = NumberFormat
    .getCurrencyInstance(Locale("pt", "BR"))
    .format(this.toDouble())
