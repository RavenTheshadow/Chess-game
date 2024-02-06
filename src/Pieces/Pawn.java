package Pieces;

import Control.Move;
import Main.Board;

import java.awt.image.BufferedImage;

public class Pawn extends Pieces {
    public Pawn(Board board, int row, int col, boolean isWhite) {
        super(board);
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
        this.xPos = col * board.titleSize;
        this.yPos = row * board.titleSize;
        this.Name = "Pawn";
        this.Sprite = this.getImageBuffer().getSubimage(5 * this.Chunk, (isWhite) ? 0 : this.Chunk, this.Chunk, this.Chunk).getScaledInstance(board.titleSize,
                board.titleSize, BufferedImage.SCALE_SMOOTH);
        isFirstmove = true;
    }
    public void move() {}
    public void draw() {}
    public boolean gamelogic(Move move) {
        if (move.capture == null) {
           if(move.newCol != move.oldCol) return false;
           if(this.isWhite) {
               if(isFirstmove) {
                   return move.oldRow - move.newRow > 0 && move.oldRow - move.newRow <= 2;
               }
               else return move.oldRow - move.newRow == 1;
           }
           else {
               if(isFirstmove) {
                   return move.newRow - move.oldRow > 0 && move.newRow - move.oldRow <= 2;
               }
               else return move.newRow - move.oldRow == 1;
           }
        } else {
            return (this.isWhite) ?
                    (move.oldRow - move.newRow == 1) && ((move.oldCol - move.newCol == 1) || (move.oldCol - move.newCol == -1)):
                    (move.newRow - move.oldRow == 1) && ((move.newCol - move.oldCol == 1) || (move.newCol - move.oldCol == -1));
        }
    }
}
