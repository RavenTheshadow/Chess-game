package Pieces;
import Control.Move;
import Main.Board;

import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public class King extends  Pieces{
    public King(Board board, int row, int col, boolean isWhite) {
        super(board);
        this.row        = row;
        this.col        = col;
        this.isWhite    = isWhite;
        this.xPos       = col * board.titleSize;
        this.yPos       = row * board.titleSize;
        this.Name       = "King";
        this.Sprite     = this.getImageBuffer().getSubimage(0, (isWhite) ? 0 : this.Chunk, this.Chunk, this.Chunk).getScaledInstance(board.titleSize,
                board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isBound() {return true;}
    public  boolean gamelogic(Move move) {
        return abs(move.newCol - move.oldCol) <= 1 && abs(move.newRow - move.oldRow) <= 1;
    }
}
