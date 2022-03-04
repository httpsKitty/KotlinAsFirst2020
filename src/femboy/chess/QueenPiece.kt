package femboy.chess

class QueenPiece(x: Int = 0, y: Int = 0) : Piece(x, y) {
    override fun canHit(targetPiece: Piece): Boolean =
        RookPiece(x, y).canHit(targetPiece) || BishopPiece(x, y).canHit(targetPiece)

}