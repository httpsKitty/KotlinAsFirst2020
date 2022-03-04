package femboy.chess

import kotlin.math.abs

class BishopPiece(x: Int = 0, y: Int = 0) : Piece(x, y) {
    override fun canHit(targetPiece: Piece): Boolean =
        abs(x - targetPiece.x) == abs(y - targetPiece.y)
}