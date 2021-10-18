package duarte.diego.financeapp.commons.extensions

import duarte.diego.financeapp.commons.constants.Constants.DATE
import duarte.diego.financeapp.commons.constants.Constants.DATE_BR
import duarte.diego.financeapp.commons.constants.Constants.DATE_HOUR
import duarte.diego.financeapp.commons.constants.Constants.DATE_HOUR_BR
import duarte.diego.financeapp.commons.enums.Mask
import duarte.diego.financeapp.commons.enums.RegexEnum
import java.text.Normalizer
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.math.roundToInt

fun String.unmask() = this.replace("[^\\d]".toRegex(), "")

fun String.isEmail() = RegexEnum.EMAIL.match(this)
fun String.isNotEmail() = !isEmail()

fun String.isPhoneNumber() = RegexEnum.PHONE_NUMBER.match(this)
fun String.isPhoneNumberNoMask() = RegexEnum.PHONE_NUMBER_NO_MASK.match(this)
fun String.isNotPhoneNumber() = !isPhoneNumber()

fun String.isCep() = length == 8
fun String.isNotCep() = !isCep()

fun String.unAccent(): String {
    val nfdNormalizedString = Normalizer.normalize(this, Normalizer.Form.NFD)
    val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
    return pattern.matcher(nfdNormalizedString).replaceAll("") ?: ""
}

fun String.puttingPhoneMask(): String {
    var phoneWithMask = ""
    var i = 0
    for (m in Mask.PHONE.value.toCharArray()) {
        if (m != '#') {
            phoneWithMask += m
            continue
        }
        try {
            phoneWithMask += this[i]
        } catch (e: java.lang.Exception) {
            break
        }
        i++
    }
    return phoneWithMask
}

fun String.puttingPhoneMaskWithoutCountryCode() = puttingPhoneMask().replace("+55 ", "")

fun String.puttingCpfMask(): String {
    return replace(RegexEnum.CPF.value) { matchResult ->
        matchResult.groupValues.mapIndexedNotNull { index, value ->
            when {
                value == this -> null
                matchResult.groupValues.lastIndex == index -> "-${value}"
                matchResult.groupValues.lastIndex - 1 == index -> value
                else -> "${value}."
            }
        }.joinToString(separator = "", truncated = "")
    }
}

fun String.puttingCnpjMask(): String {
    return replace(RegexEnum.CNPJ.value) { matchResult ->
        matchResult.groupValues.mapIndexedNotNull { index, value ->
            when {
                value == this -> null
                matchResult.groupValues.lastIndex == index -> "-${value}"
                matchResult.groupValues.lastIndex - 1 == index -> value
                else -> "${value}."
            }
        }.joinToString(separator = "", truncated = "").replaceRange(10, 11, "/")
    }
}

fun String.isNotValidName() = !isValidName()
fun String.isValidName() = RegexEnum.NAME.match(this)
fun String.isNotCompoundName() = !this.contains(" ")

fun String.isKey() = RegexEnum.KEY.match(this)
fun String.isKeyNoMask() = RegexEnum.KEY_NO_MASK.match(this)
fun String.isNotKey() = !isKey()
fun String.isNotKeyNoMask() = !isKeyNoMask()

fun String.isGeneric() = RegexEnum.GENERIC.match(this)

fun String.isValidIdentifier() = RegexEnum.PIX_IDENTIFIER.match(this)
fun String.isNotValidIdentifier() = !isValidIdentifier()

fun String.isDate() = try {
    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).also {
        it.isLenient = false
        it.parse(this)
    }
    RegexEnum.DATE.value.matches(this)
} catch (e: Exception) {
    false
}

fun String.isNotDate() = !isDate()

fun String.isCardDate() = RegexEnum.DATE_MONTH_YEAR.match(this)

fun String.isNotCardDate() = !isCardDate()

fun String.isRepeating() = toCharArray().toSet().size == 1

fun String.isSequencial(): Boolean {
    val pattern = "0123456789"
    val patternReverse = "9876543210"
    return pattern.contains(this) || patternReverse.contains(this)
}

fun String.isNotCpf() = !isCpf()

fun String.isNotCnpj() = !isCnpj()

fun String.removeNotNumbers() = this.replace("[^\\d]".toRegex(), "")

fun String.convertToDouble() = replace("""[^(\d,)]""".toRegex(), "")
    .replace(",", ".").toDouble()

fun String.getStateCode() = this.substring(0, 2)

fun String.getNumber() = this.substring(2, length)

fun String.isCnpj(): Boolean {
    if (this.length != 14) return false

    var allDigitsEqual = true
    this.forEach {
        if (this.first() != it) {
            allDigitsEqual = false
        }
    }
    if (allDigitsEqual) return false

    return try {
        val weight = intArrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        val cnpjSub = this.substring(0, 12)
        val digit1 = cnpjSub.validateDigit(weight)
        val digit2 = (cnpjSub + digit1).validateDigit(weight)

        this == cnpjSub.plus(digit1).plus(digit2)
    } catch (error: InputMismatchException) {
        false
    }
}

fun String.isCpf(): Boolean {
    if (this.length != 11) return false

    var allDigitsEqual = true
    this.forEach {
        if (this.first() != it) {
            allDigitsEqual = false
        }
    }
    if (allDigitsEqual) return false

    return try {
        val weight = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
        val cpfSub = this.substring(0, 9)
        val digit1 = cpfSub.validateDigit(weight)
        val digit2 = (cpfSub + digit1).validateDigit(weight)

        this == cpfSub.plus(digit1).plus(digit2)
    } catch (error: InputMismatchException) {
        false
    }
}

private fun String.validateDigit(weight: IntArray): Int {
    try {
        var valid = 0
        var index = this.length - 1
        var digit: Int

        while (index >= 0) {
            digit = this.substring(index, index + 1).toInt()
            valid += digit * weight[weight.size - this.length + index]
            index--
        }

        valid = 11 - valid % 11

        return if (valid > 9) {
            0
        } else {
            valid
        }
    } catch (e: Exception) {
        return -1
    }
}

fun String.puttingAccountNumberMask(): String {
    if (isBlank() || getOrNull(lastIndex - 1) == '-')
        return this
    val stringList = removeNotNumbers().toMutableList()
    stringList.add(lastIndex, '-')
    return stringList.joinToString(separator = "", truncated = "")
}

fun String.toBarcode(): String {
    if (isEmpty()) return "--"

    val out = StringBuffer()
    var j = 0
    val mask = "#####.##### #####.###### #####.###### # ##############"
    for (i in mask.indices) {
        when {
            mask[i] == '#' -> {
                out.append(this[j])
                j++
            }
            mask[i] == '.' -> out.append('.')
            else -> out.append(' ')
        }
    }
    return out.toString()
}

fun java.lang.StringBuilder.insertSafe(offset: Int, str: String): StringBuilder = try {
    insert(offset, str)
} catch (e: IndexOutOfBoundsException) {
    this
}

fun String.toPaidBarcode(): String {
    if (isEmpty()) return "--"

    val out = StringBuffer()
    var j = 0
    val mask = "##### ##### ##### ###### ##### ###### # ##############"
    for (i in mask.indices) {
        when {
            mask[i] == '#' -> {
                out.append(this[j])
                j++
            }
            mask[i] == '.' -> out.append('.')
            else -> out.append(' ')
        }
    }
    return out.toString()
}

fun String.formatAsPhoneNumber() = StringBuilder(this)
    .insertSafe(0, "(")
    .insertSafe(3, ")")
    .insertSafe(4, " ")
    .insertSafe(6, " ")
    .insertSafe(11, "-").toString()

fun String.convertDoubleStringToIntString(): String {
    if (isBlank() || length <= 1 || !contains('.')) return this
    return try {
        val doubleValue = this.toDouble()
        val intValue = doubleValue.roundToInt()
        intValue.toString()
    } catch (e: Exception) {
        this
    }
}

fun String.fromDateToDateBR(): String {
    return try {
        toDate(DATE).format(DATE_BR)
    } catch (e: ParseException) {
        ""
    }
}

fun String.fromDateBRToDate(): String {
    return try {
        toDate(DATE_BR).format(DATE)
    } catch (e: ParseException) {
        ""
    }
}

fun String.fromDateHourToDateHourBR(): String {
    return try {
        toDate(DATE_HOUR).format(DATE_HOUR_BR)
    } catch (e: ParseException) {
        ""
    }
}