package Pieces;


import Control.Move;
import Main.Board;

import java.awt.image.BufferedImage;

public class Queen extends Pieces {
    public Queen(Board board, int row, int col, boolean isWhite) {
        super(board);
        this.row        = row;
        this.col        = col;
        this.isWhite    = isWhite;
        this.xPos       = col * board.titleSize;
        this.yPos       = row * board.titleSize;
        this.Name       = "Queen";
        this.Sprite     = this.getImageBuffer().getSubimage(this.Chunk, (isWhite) ? 0 : this.Chunk, this.Chunk, this.Chunk).getScaledInstance(board.titleSize,
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
    Bound LU;
    Bound LD;
    Bound RU;
    Bound RD;
    public boolean isBound() {
        return true;
    }
    private void makeBound() {
        Left    = new Bound(-1, 8);
        Right   = new Bound(8, 8);
        Up      = new Bound(8, -1);
        Down    = new Bound(8, 8);
        LU      = new Bound(-1, -1);
        LD      = new Bound(-1, 8);
        RU      = new Bound(8, -1);
        RD      = new Bound(8, 8);

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

        int MainCross   = this.col - this.row;
        int NMainCross  = this.col + this.row;

        for(int c = this.col - 1; c >= 0; c--) {
            if(board.getPieces(c, c - MainCross) != null) {
                LU.col = c;
                LU.row = c - MainCross;
                break;
            }
        }
        for(int c = this.col - 1; c >= 0; c--) {
            if (board.getPieces(c, NMainCross - c) != null) {
                LD.col = c;
                LD.row = NMainCross - c;
                break;
            }
        }
        for(int c = this.col + 1; c < 8; c++) {
            if(board.getPieces(c, c - MainCross) != null) {
                RD.col = c;
                RD.row = c - MainCross;
                break;
            }
        }
        for(int c = this.col + 1; c < 8; c++) {
            if (board.getPieces(c, NMainCross - c) != null) {
                RU.col = c;
                RU.row = NMainCross - c;
                break;
            }
        }
    }
    public boolean isBound(Move move) {
        makeBound();
        int MainCross   = this.col - this.row;
        int NMainCross  = this.col + this.row;
        if(move.newCol - move.newRow == MainCross) {
            return move.newCol >= LU.col && move.newRow >= LU.row && move.newCol <= RD.col && move.newRow <= RD.row;
        }
        else if(move.newCol + move.newRow == NMainCross) {
            return move.newCol >= LD.col && move.newRow <= LD.row && move.newCol <= RU.col && move.newRow >= RU.row;
        }
        return ( move.newCol >= Left.col && move.newCol <= Right.col && move.newRow >= Up.row && move.newRow <= Down.row);
    }
    public  boolean gamelogic(Move move) {
        return (move.newRow - move.newCol == move.oldRow - move.oldCol || move.newRow + move.newCol == move.oldRow + move.oldCol ||
                move.newCol == move.oldCol || move.newRow == move.oldRow) && isBound(move);
    }
}
