package melon.abstractions

/**
 * Преобразует один объект в другой.
 */
interface Transformer<TFrom, TTo> {
    fun transform(from: TFrom): TTo
}