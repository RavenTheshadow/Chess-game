package Pieces;

import Main.Board;
import Control.Move;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Pieces {
    /*  Get the image */
    protected BufferedImage image;
    protected int Chunk;
    public Image Sprite;
    Board board;
    public boolean isFirstmove;
    public int row, col;
    public int xPos, yPos;
    public int getRow() { return row;}
    public int getCol() { return col;}
    public boolean isWhite;
    public String Name;
    public Pieces(Board board) {this.board = board;}
    private String getImage() {
        return "D://JAVA//Chess//src//Res//Pieces.png";
    }
    public BufferedImage getImageBuffer() {
        try {
            // Load the image in the constructor
            image = ImageIO.read(new File(getImage()));
        } catch (IOException e) {
            System.out.println("File can't read");
            e.printStackTrace();
        }
        Chunk = image.getHeight()/2;
        return image;
    }
    public  void paint(Graphics2D g2d) {
        g2d.drawImage(this.Sprite, xPos, yPos, null);
    }
    public abstract void move();

    public abstract void draw();
    public abstract boolean gamelogic(Move move);
}