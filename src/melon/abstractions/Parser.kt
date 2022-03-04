package melon.abstractions

/**
 * Преобразует токены в дерево нод.
 */
interface Parser<T, TNode : Node<TNode>> {
    /**
     * Парсит токены в корневую ноду.
     */
    fun parse(tokens: List<T>): TNode?
}