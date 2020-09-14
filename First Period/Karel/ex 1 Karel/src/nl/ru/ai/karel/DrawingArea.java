package nl.ru.ai.karel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author sparky
 */
class DrawingArea extends JPanel
{
  private static final long serialVersionUID = 1L;
  /*
   * Orientations
   */
  private static final int NORTH = 0;
  private static final int EAST = 1;
  private static final int SOUTH = 2;
  private static final int WEST = 3;
  /*
   * Map values
   */
  private static final int EMPTY = 0;
  private static final int STONE = 1;
  private static final int BALL = 2;
  private static final int SIZE = 24;
  /*
   * Karel's fields
   */
  private int rows;
  private int cols;
  private int[][] map;
  private int orientation;
  private int col;
  private int row;
  private int speed;
  /*
   * Do we hava an invisible wall around the grid?
   */
  private boolean invisibleWall;
  /*
   * Secret option to toggle 'evil' dark mode
   */
  private boolean darkMode = true;

  /**
   * @param mapName
   */
  DrawingArea(String mapName)
  {
    speed = 1000;
    read(mapName);
    setPreferredSize(new Dimension(cols * SIZE, rows * SIZE));
    invisibleWall = !mapName.equals("church.map");
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Color foreground;
    Color background;
    if (darkMode)
    {
      foreground = Color.WHITE;
      background = Color.BLACK;
    } else
    {
      foreground = Color.BLACK;
      background = Color.WHITE;
    }
    g.setColor(background);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(foreground);
    g.drawRect(0, 0, getWidth(), getHeight());
    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j++)
      {
        switch (map[i][j])
        {
          case EMPTY:
            g.drawLine(j * SIZE + SIZE / 2, i * SIZE + SIZE / 2, j * SIZE + SIZE / 2, i * SIZE + SIZE / 2);
            break;
          case BALL:
            g.drawArc(j * SIZE + SIZE / 2 - SIZE / 8, i * SIZE + SIZE / 2 - SIZE / 8, SIZE / 4, SIZE / 4, 0, 360);
            break;
          case STONE:
            g.drawRect(j * SIZE + SIZE / 2 - SIZE / 2, i * SIZE + SIZE / 2 - SIZE / 2, SIZE, SIZE);
            break;
        }
      }
    switch (orientation)
    {
      case NORTH:
        g.drawLine(col * SIZE + SIZE / 4, row * SIZE + SIZE / 2, col * SIZE + SIZE / 2, row * SIZE + SIZE / 4);
        g.drawLine(col * SIZE + SIZE / 2, row * SIZE + SIZE / 4, col * SIZE + SIZE / 2 + SIZE / 4, row * SIZE + SIZE / 2);
        break;
      case EAST:
        g.drawLine(col * SIZE + SIZE / 2, row * SIZE + SIZE / 4, col * SIZE + SIZE / 2 + SIZE / 4, row * SIZE + SIZE / 2);
        g.drawLine(col * SIZE + SIZE / 2 + SIZE / 4, row * SIZE + SIZE / 2, col * SIZE + SIZE / 2, row * SIZE + SIZE / 2 + SIZE / 4);
        break;
      case SOUTH:
        g.drawLine(col * SIZE + SIZE / 4, row * SIZE + SIZE / 2, col * SIZE + SIZE / 2, row * SIZE + SIZE / 4 + SIZE / 2);
        g.drawLine(col * SIZE + SIZE / 2, row * SIZE + SIZE / 4 + SIZE / 2, col * SIZE + SIZE / 2 + SIZE / 4, row * SIZE + SIZE / 2);
        break;
      case WEST:
        g.drawLine(col * SIZE + SIZE / 2, row * SIZE + SIZE / 4, col * SIZE + SIZE / 4, row * SIZE + SIZE / 2);
        g.drawLine(col * SIZE + SIZE / 4, row * SIZE + SIZE / 2, col * SIZE + SIZE / 2, row * SIZE + SIZE / 2 + SIZE / 4);
        break;
    }
  }

  private void read(String mapName)
  {
    List<String> lines = new ArrayList<String>();
    try
    {
      BufferedReader in = new BufferedReader(new FileReader(mapName));
      String line;
      while ((line = in.readLine()) != null)
        lines.add(line);
      in.close();
    } catch (IOException e)
    {
      /*
       * File not found or error, generate default empty map
       */
      for (int i = 0; i < 23; i++)
        lines.add(".......................................");
      lines.add("^......................................");
    }
    /*
     * Determine dimensions
     */
    rows = lines.size();
    cols = 1;
    for (int i = 0; i < rows; i++)
    {
      String line = lines.get(i);
      if (line.length() > cols)
        cols = line.length();
    }
    /*
     * Default orientation and position
     */
    orientation = NORTH;
    col = 0;
    row = rows - 1;
    /*
     * Create map
     */
    map = new int[rows][];
    for (int i = 0; i < rows; i++)
    {
      map[i] = new int[cols];
      for (int j = 0; j < cols; j++)
        map[i][j] = EMPTY;
      String line = lines.get(i);
      for (int j = 0; j < line.length(); j++)
        switch (line.charAt(j))
        {
          case 'x':
            map[i][j] = STONE;
            break;
          case 'o':
          case 'O':
          case '0':
            map[i][j] = BALL;
            break;
          case '^':
            row = i;
            col = j;
            orientation = NORTH;
            break;
          case '>':
            row = i;
            col = j;
            orientation = EAST;
            break;
          case 'v':
          case 'V':
            row = i;
            col = j;
            orientation = SOUTH;
            break;
          case '<':
            row = i;
            col = j;
            orientation = WEST;
            break;
          default:
            break;
        }
    }
  }

  void turnLeft()
  {
    switch (orientation)
    {
      case NORTH:
        orientation = WEST;
        break;
      case EAST:
        orientation = NORTH;
        break;
      case SOUTH:
        orientation = EAST;
        break;
      case WEST:
        orientation = SOUTH;
        break;
    }
    refresh();
  }

  private void refresh()
  {
    repaint();
    try
    {
      Thread.sleep(speed);
    } catch (InterruptedException e)
    {
      // ignore
    }
  }

  void turnRight()
  {
    switch (orientation)
    {
      case NORTH:
        orientation = EAST;
        break;
      case EAST:
        orientation = SOUTH;
        break;
      case SOUTH:
        orientation = WEST;
        break;
      case WEST:
        orientation = NORTH;
        break;
    }
    refresh();
  }

  void step()
  {
    switch (orientation)
    {
      case NORTH:
        if (row == 0)
        {
          JOptionPane.showMessageDialog(this, "Karel disappears from sight");
          System.exit(1);
        }
        row--;
        break;
      case EAST:
        if (col + 1 == cols)
        {
          JOptionPane.showMessageDialog(this, "Karel disappears from sight");
          System.exit(1);
        }
        col++;
        break;
      case SOUTH:
        if (row + 1 == rows)
        {
          JOptionPane.showMessageDialog(this, "Karel disappears from sight");
          System.exit(1);
        }
        row++;
        break;
      case WEST:
        if (col == 0)
        {
          JOptionPane.showMessageDialog(this, "Karel disappears from sight");
          System.exit(1);
        }
        col--;
        break;
    }
    if (map[row][col] == STONE)
    {
      JOptionPane.showMessageDialog(this, "Karel bangs his head into the wall");
      System.exit(1);
    }
    refresh();
  }

  boolean inFrontOfWall()
  {
    switch (orientation)
    {
      case NORTH:
        if (row == 0)
          return invisibleWall;
        return map[row - 1][col] == STONE;
      case EAST:
        if (col + 1 == cols)
          return invisibleWall;
        return map[row][col + 1] == STONE;
      case SOUTH:
        if (row + 1 == rows)
          return invisibleWall;
        return map[row + 1][col] == STONE;
      case WEST:
        if (col == 0)
          return invisibleWall;
        return map[row][col - 1] == STONE;
      default:
        return true; // cannot happen
    }
  }

  void speed(int i)
  {
    if (i <= 0)
      speed = 1;
    else
      speed = i;
  }

  boolean onBall()
  {
    return map[row][col] == BALL;
  }

  void getBall()
  {
    if (map[row][col] != BALL)
    {
      JOptionPane.showMessageDialog(this, "There is no ball here");
      System.exit(1);
    }
    map[row][col] = EMPTY;
    refresh();
  }

  void putBall()
  {
    if (map[row][col] == BALL)
    {
      JOptionPane.showMessageDialog(this, "There is already a ball here");
      System.exit(1);
    }
    map[row][col] = BALL;
    refresh();
  }

  boolean north()
  {
    return orientation == NORTH;
  }

  boolean east()
  {
    return orientation == EAST;
  }

  boolean south()
  {
    return orientation == SOUTH;
  }

  boolean west()
  {
    return orientation == WEST;
  }
}
