package melon.abstractions

/**
 * Нода в дереве элементов.
 */
abstract class Node<T : Node<T>> {
    constructor(children: List<T>) {
        for (child in children) attach(child)
    }

    private var _children: MutableList<T> = mutableListOf()
    val children: List<T> get() = _children

    private var _father: T? = null
    val father: T? get() = _father

    val isRoot: Boolean get() = father == null

    val isLeaf: Boolean get() = children.isEmpty()

    fun attach(node: T): Boolean {
        if (node == this) return false
        if (node.father != null) return false
        if (_children.contains(node)) return false

        node._father = this as T
        _children.add(node)

        return true
    }

    fun detach(node: T): Boolean {
        if (node.father != this) return false

        node._father = null
        _children.remove(node)

        return true
    }

    fun detach(): Boolean {
        return father?.detach(this as T) == true
    }

    abstract override fun toString(): String
}