package duarte.diego.financeapp.commons.extensions

fun Int.match(value: Int) = this == value

fun Int.notMatch(value: Int) = !this.match(value)