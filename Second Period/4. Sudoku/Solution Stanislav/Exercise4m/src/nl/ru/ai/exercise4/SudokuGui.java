package nl.ru.ai.exercise4;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


/**
 * Simple app that renders a Sudoku
 * @author Paul Tiemeijer
 */
public class SudokuGui extends JFrame {
    private static SudokuGui app;
    private static SudokuPanel panel;

    /**
     * draw or update the Soduku
     * @param sudoku an array where indices r*9 to (r+1)*9 - 1 represents the values of row r
     * **/
    public static void drawSudoku(int[] sudoku) {
        if (app == null) app = new SudokuGui();
        panel.digits = sudoku.clone();
        panel.repaint();
    }

    private SudokuGui() {
        setTitle("Sudoku");

        Container contentPane = getContentPane();
        panel = new SudokuPanel();
        contentPane.add(panel);
        pack();

        setResizable(false);
        setVisible(true);
    }

    /** component responsible for rendering **/
    private static class SudokuPanel extends JPanel {
        int cellSize = 50;
        int[] digits = new int[81];

        SudokuPanel() {
            setPreferredSize(new Dimension(cellSize*9, cellSize*9));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);

            for (int row = 1; row < 9; row++) {
                int x = row*cellSize;
                if (row % 3 == 0) {
                    g.fillRect(x, 0, 3, getHeight());
                } else {
                    g.drawLine(x, 0, x, getHeight());
                }
            }

            for (int col = 1; col < 9; col++) {
                int y = col*cellSize;
                if (col % 3 == 0) {
                    g.fillRect(0, y, getWidth(), 3);
                } else {
                    g.drawLine(0, y, getWidth(), y);
                }
            }

            Font defaultFont = g.getFont();
            g.setFont(new Font(defaultFont.getName(), defaultFont.getStyle(), cellSize/2));

            for (int i = 0; i < 81; i++) {
                String str = Integer.toString(digits[i]);
                Rectangle2D bounds = g.getFontMetrics().getStringBounds(str, g);

                int row = i / 9;
                int col = i % 9;
                int x = col * cellSize + (int) (cellSize/2.0 - bounds.getCenterX());
                int y = row * cellSize + (int) (cellSize/2.0 - bounds.getCenterY()) ;
                g.drawString(str, x, y);
            }
        }
    }
}
