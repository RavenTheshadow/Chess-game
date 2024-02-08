package Pieces;

import Main.Board;
import Control.Move;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;

public class Knight extends Pieces {
    public Knight(Board board, int row, int col, boolean isWhite) {
        super(board);
        this.row        = row;
        this.col        = col;
        this.isWhite    = isWhite;
        this.xPos       = col * board.titleSize;
        this.yPos       = row * board.titleSize;
        this.Name       = "Knight";
        this.Sprite     = this.getImageBuffer().getSubimage(3 * this.Chunk, (isWhite) ? 0 : this.Chunk, this.Chunk, this.Chunk).getScaledInstance(board.titleSize,
                board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public void move() {}
    public void draw() {}
    public boolean isBound() {return true;}
    public  boolean gamelogic(Move move) {
        return abs(move.newCol - move.oldCol) == 2 && abs(move.newRow - move.oldRow) == 1 ||
                abs(move.newRow - move.oldRow) == 2 && abs(move.newCol - move.oldCol) == 1;
    }
}
