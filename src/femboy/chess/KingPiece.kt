package femboy.chess

import kotlin.math.abs
import kotlin.math.pow

class KingPiece(x: Int = 0, y: Int = 0) : Piece(x, y) {
    override fun canHit(targetPiece: Piece): Boolean =
        (x - targetPiece.x).toDouble().pow(2) + (y - targetPiece.y).toDouble().pow(2) <= 2

}