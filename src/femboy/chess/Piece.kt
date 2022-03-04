package femboy.chess

sealed class Piece(var x: Int = 0, var y: Int = 0) {
    abstract fun canHit(targetPiece: Piece): Boolean
}