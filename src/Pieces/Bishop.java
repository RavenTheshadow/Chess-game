package Pieces;


import Control.Move;
import Main.Board;

import java.awt.image.BufferedImage;

public class Bishop extends Pieces {
    public Bishop(Board board, int row, int col, boolean isWhite) {
        super(board);
        this.row        = row;
        this.col        = col;
        this.isWhite    = isWhite;
        this.xPos       = col * board.titleSize;
        this.yPos       = row * board.titleSize;
        this.Name       = "Bishop";
        this.Sprite     = this.getImageBuffer().getSubimage(2 * this.Chunk, (isWhite) ? 0 : this.Chunk, this.Chunk, this.Chunk).getScaledInstance(board.titleSize,
                board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    private class Bound {
        public int row;
        public int col;
        Bound(int col, int row) { this.row = row; this.col = col;}
    }
    Bound LU;
    Bound LD;
    Bound RU;
    Bound RD;
    private void makeBound() {
        LU = new Bound(-1, -1);
        LD = new Bound(-1, 8);
        RU = new Bound(8, -1);
        RD = new Bound(8, 8);

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
    public boolean isBound() {return true;}
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
        return false;
    }
    public  boolean gamelogic(Move move) {
        return isBound(move);
    }
}
