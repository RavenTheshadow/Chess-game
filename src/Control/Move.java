package Control;

import Main.Board;
import Pieces.Pieces;

public class Move {
    public int oldRow, oldCol;
    public int newRow, newCol;

    public Pieces pieces;
    public Pieces capture;

    public Move(Board board, Pieces pieces, int newCol, int newRow) {
        this.oldRow = pieces.getRow();
        this.oldCol = pieces.getCol();
        this.newRow = newRow;
        this.newCol = newCol;

        this.pieces = pieces;
        this.capture= board.getPieces(newCol, newRow);
    }
}
