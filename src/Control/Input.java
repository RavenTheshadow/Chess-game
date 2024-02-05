package Control;

import Main.Board;
import Pieces.Pieces;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {
    Board board;
    public  Input(Board board) {
        this.board = board;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        Pieces P = board.getPieces(col, row);
        if(P != null) {
            board.selectedPiece = P;
        }

        // If mouse press ->Draw line
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.selectedPiece != null) {
            board.selectedPiece.xPos = e.getX() - board.titleSize/2;
            board.selectedPiece.yPos = e.getY() - board.titleSize/2;
            board.repaint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.titleSize;
        int row = e.getY() / board.titleSize;

        if(board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, col, row);

            if(board.isValidMove(move)) {
                board.makeMove(move);
                board.lastMoveIsWhite = move.pieces.isWhite;
            }
            else {
                board.selectedPiece.xPos = board.selectedPiece.getCol() * board.titleSize;
                board.selectedPiece.yPos = board.selectedPiece.getRow() * board.titleSize;
            }
        }

        board.selectedPiece = null;
        board.repaint();
    }
}
