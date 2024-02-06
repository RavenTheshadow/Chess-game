package Main;

import Control.Input;
import Control.Move;
import Pieces.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    Input input = new Input(this);
    public int titleSize = 85;
    public int Cols = 8;
    public int Rows = 8;
    public Board() {
        this.setPreferredSize(new Dimension(Cols * titleSize, Rows * titleSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.add();
    }

    public Pieces getPieces(int cols, int rows) {
        for(Pieces pieces1 : pieces) {
            if(pieces1.getCol() == cols && pieces1.getRow() == rows) {
                return pieces1;
            }
        }
        return null;
    }
    public Pieces selectedPiece;
    public void add() {

        pieces.add(new King(this, 7, 4, true));
        pieces.add(new Queen(this, 7, 3, true));
        pieces.add(new Rook(this, 7, 0, true));
        pieces.add(new Rook(this, 7, 7, true));
        pieces.add(new Knight(this, 7, 1, true));
        pieces.add(new Knight(this, 7, 6, true));
        pieces.add(new Bishop(this, 7, 2, true));
        pieces.add(new Bishop(this, 7, 5, true));
        for(int i = 0; i < 8; i++) {
            pieces.add(new Pawn(this, 6, i, true));
        }

        pieces.add(new King(this, 0, 3, false));
        pieces.add(new Queen(this, 0, 4, false));
        pieces.add(new Rook(this, 0, 0, false));
        pieces.add(new Rook(this, 0, 7, false));
        pieces.add(new Knight(this, 0, 1, false));
        pieces.add(new Knight(this, 0, 6, false));
        pieces.add(new Bishop(this, 0, 2, false));
        pieces.add(new Bishop(this, 0, 5, false));
        for(int i = 0; i < 8; i++) {
            pieces.add(new Pawn(this, 1, i, false));
        }
    }
    ArrayList <Pieces> pieces = new ArrayList<>();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int r = 0; r < Rows; r++) {
            for (int c = 0; c < Cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? Color.WHITE : new Color(75, 206, 43));
                g2d.fillRect(c * titleSize, r * titleSize, titleSize, titleSize);
            }
        }
        if(selectedPiece != null) {
            for (int r = 0; r < Rows; r++) {
                for (int c = 0; c < Cols; c++) {
                    if (this.isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(5, 36, 245, 153));
                        g2d.fillRect(c * titleSize, r * titleSize, titleSize, titleSize);

                        if(getPieces(c, r) != null) {
                            g2d.setColor(new Color(250, 0, 0, 153));
                            g2d.fillRect(c * titleSize, r * titleSize, titleSize, titleSize);
                        }
                    }
                }
            }
        }
        for(Pieces pieces1 : pieces) {
            pieces1.paint(g2d);
        }
    }
    public  void makeMove(Move move) {
        move.pieces.col = move.newCol;
        move.pieces.row = move.newRow;
        move.pieces.xPos = move.newCol * titleSize;
        move.pieces.yPos = move.newRow * titleSize;
        capture(move);
    }
    public  void capture(Move move) {
        if(move.capture != null) {
            pieces.remove(move.capture);
        }
    }
    public  boolean SameColor(Pieces p1, Pieces p2) {
        if(p1 == null || p2 == null) return false;
        return (p1.isWhite && p2.isWhite) || (!p1.isWhite && !p2.isWhite);
    }
    public boolean lastMoveIsWhite = false;
    public boolean Nextmove(Move move) {
        return (!lastMoveIsWhite || !move.pieces.isWhite) && (lastMoveIsWhite || move.pieces.isWhite);
    }
    public  boolean isValidMove(Move move) {
        if (!SameColor(move.pieces, move.capture) && Nextmove(move)) {
            return move.pieces.gamelogic(move);
        }
        return false;
    }
}
