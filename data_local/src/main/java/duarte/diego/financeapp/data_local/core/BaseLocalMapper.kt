package duarte.diego.financeapp.data_local.core

interface BaseLocalMapper<L, D> {

    fun toLocal(domain: D): L

    fun fromLocal(local: L): D

    fun toLocal(domain: List<D>): List<L> {
        return domain.map { toLocal(it) }
    }

    fun fromLocal(local: List<L>): List<D> {
        return local.map { fromLocal(it) }
    }
}