package duarte.diego.financeapp.commons.enums

enum class RegexEnum(val value: Regex) {
    PASSWORD("""(\d{6})""".toRegex()),
    CNPJ("""(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})""".toRegex()),
    EMAIL("""^[a-z0-9_%+-]+([.-][a-z0-9]+)*@[a-z0-9]+([.-][a-z0-9]+)*\.[a-z]{2,3}$""".toRegex()),
    CPF("""(\d{3})(\d{3})(\d{3})(\d{2})""".toRegex()),
    DATE("""^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$""".toRegex()),
    DATE_MONTH_YEAR("""^(0[1-9]|1[0-2])\/?(([0-9]{4}|[0-9]{2})${'$'})""".toRegex()),
    PHONE_NUMBER("""^\([1-9]{2}\) [0-9] [0-9]{4}-[0-9]{4}$""".toRegex()),
    PHONE_NUMBER_NO_MASK("""^([1-9]{2})([0-9])([0-9]{4})([0-9]{4})""".toRegex()),
    NAME("""(^[[A-Z][a-z] (áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ)]{6,}$)""".toRegex()),
    KEY("""([0-9a-f]{8})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{12})""".toRegex()),
    KEY_NO_MASK("""^([0-9a-f]{8})([0-9a-f]{4})([0-9a-f]{4})([0-9a-f]{4})([0-9a-f]{12})""".toRegex()),
    GENERIC("""([a-zA-Z])""".toRegex()),
    PIX_IDENTIFIER("""^[a-zA-Z0-9]{1,12}$""".toRegex());
    fun match(string: String) = this.value.containsMatchIn(string)
}