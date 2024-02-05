package Main;
import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame Frame = new JFrame();
        Frame.setLayout(new GridBagLayout());
        Frame.setMinimumSize(new Dimension(800, 800));
        Frame.setLocationRelativeTo(null);
        Board board = new Board();
        Frame.add(board);


        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frame.setVisible(true);
    }
}