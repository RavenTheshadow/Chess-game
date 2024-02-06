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
    public void move() {}
    public void draw() {}
    public  boolean gamelogic(Move move) {
        return move.newRow - move.newCol == move.oldRow - move.oldCol || move.newRow + move.newCol == move.oldRow + move.oldCol ||
                move.newCol == move.oldCol || move.newRow == move.oldRow;
    }
}
