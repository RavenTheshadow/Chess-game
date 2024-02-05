package Pieces;

import Control.Move;
import Main.Board;

import java.awt.image.BufferedImage;

public class Pawn extends Pieces {
    public Pawn(Board board, int row, int col, boolean isWhite) {
        super(board);
        this.row        = row;
        this.col        = col;
        this.isWhite    = isWhite;
        this.xPos       = col * board.titleSize;
        this.yPos       = row * board.titleSize;
        this.Name       = "Pawn";
        this.Sprite     = this.getImageBuffer().getSubimage(5 * this.Chunk, (isWhite) ? 0 : this.Chunk, this.Chunk, this.Chunk).getScaledInstance(board.titleSize,
                board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public void move() {}
    public void draw() {}
    public boolean gamelogic(Move move) {
        return true;
    }
}
