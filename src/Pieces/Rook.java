package Pieces;


import Control.Move;
import Main.Board;

import java.awt.image.BufferedImage;

public class Rook extends Pieces {
    public Rook(Board board, int row, int col, boolean isWhite) {
        super(board);
        this.row        = row;
        this.col        = col;
        this.isWhite    = isWhite;
        this.xPos       = col * board.titleSize;
        this.yPos       = row * board.titleSize;
        this.Name       = "Rook";
        this.Sprite     = this.getImageBuffer().getSubimage(4 * this.Chunk, (isWhite) ? 0 : this.Chunk, this.Chunk, this.Chunk).getScaledInstance(board.titleSize,
                board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    private class Bound {
        public int row;
        public int col;
        Bound (int col, int  row) {
            this.col = col;
            this.row = row;
        }
    }
    Bound Left;
    Bound Right;
    Bound Up;
    Bound Down;
    private void makeBound() {
        Left    = new Bound(-1, 8);
        Right   = new Bound(8, 8);
        Up      = new Bound(8, -1);
        Down    = new Bound(8, 8);

        for(int c = this.col - 1; c >= 0; c--) {
            if(board.getPieces(c, row) != null) {
                Left.col = c;
                Left.row = row;
                break;
            }
        }
        for(int c = this.col + 1; c < 8; c++) {
            if(board.getPieces(c, row) != null) {
                Right.col = c;
                Right.row = row;
                break;
            }
        }
        for(int r = this.row - 1; r >= 0; r--) {
            if (board.getPieces(col, r) != null) {
                Up.col = col;
                Up.row = r;
                break;
            }
        }
        for(int r = this.row + 1; r < 8; r++) {
            if(board.getPieces(col, r) != null) {
                Down.col = col;
                Down.row = r;
                break;
            }
        }
    }
    public  boolean isBound() {
        return false;
    }
    public boolean isBound(Move move) {
        makeBound();
        return  ( move.newCol >= Left.col && move.newCol <= Right.col && move.newRow >= Up.row && move.newRow <= Down.row);
    }
    public  boolean gamelogic(Move move) {
        return (move.newCol == move.oldCol || move.newRow == move.oldRow) && isBound(move);
    }
}
