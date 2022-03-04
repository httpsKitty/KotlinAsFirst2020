package femboy.chess

class RookPiece(x: Int = 0, y: Int = 0) : Piece(x, y) {
    override fun canHit(targetPiece: Piece): Boolean =
        x == targetPiece.x || y == targetPiece.y
}