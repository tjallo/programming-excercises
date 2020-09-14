package nl.ru.ai.karel;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;

/**
 * @author sparky
 */
public class Karel extends JFrame
{
  private static final long serialVersionUID=1L;
  private static Karel karel;
  private DrawingArea drawingArea;

  /**
   * Set the map representing Karel's world
   * @param mapName
   */
  public static void map(String mapName)
  {
    if(karel==null)
      karel=new Karel(mapName);
  }

  /**
   * Creates a robot with an empty map
   */
  private Karel()
  {
    this("empty.map");
  }
  
  /**
   * Creates a robot with the specified map
   * 
   * @param mapName
   */
  private Karel(String mapName)
  {
    // Frame Parameters
    setTitle("Karel");
    setLocation(200,300);

    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        System.exit(0);
      }
    });

    Container contentPane=getContentPane();
    drawingArea=new DrawingArea(mapName);
    contentPane.add(drawingArea);
    pack();
    setResizable(false);
    setVisible(true);
  }

  /**
   * Karel turns to the left
   */
  public static void turnLeft()
  {
    if(karel==null)
      karel=new Karel();
    karel.drawingArea.turnLeft();
  }
  
  /**
   * Karel turns to the right
   */
  public static void turnRight()
  {
    if(karel==null)
      karel=new Karel();
    karel.drawingArea.turnRight();
  }

  /**
   * Karel performs a step
   */
  public static void step()
  {
    if(karel==null)
      karel=new Karel();
    karel.drawingArea.step();
  }
  
  /**
   * Checks if karel is in front of a wall or may fall off his world
   * @return true if he is in front of a wall or may fall off his world
   */
  public static boolean inFrontOfWall()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.inFrontOfWall();
  }

  /**
   * Set Karel's speed, small numbers means high speed
   * @param i
   */
  public static void speed(int i)
  {
    if(karel==null)
      karel=new Karel();
    karel.drawingArea.speed(i);
  }

  /**
   * Catches the pokemon on Karel's current position
   */
  public static void getPokemon()
  {
    if(karel==null)
      karel=new Karel();
    karel.drawingArea.getPokemon();
  }
  
  /**
   * Get the number of balls in Karel's possession
   */
  public static int getNumberOfBalls()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.getNumberOfBalls();
  }
  
  /**
   * Get the number of pokemon Karel has got
   */
  public static int getNumberOfPokemon()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.getNumberOfPokemon();
  }
  
  /**
   * Checks if Karel is standing on a ball
   * @return true if Karel is on a ball, false otherwise
   */
  public static boolean onBall()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.onBall();
  }

  /**
   * Checks if Karel is standing on a pokemon
   * @return true if Karel is on a pokemon, false otherwise
   */
  public static boolean onPokemon()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.onPokemon();
  }
  
  /**
   * Karel finished his job
   */
  public static void ready()
  {
    if(karel==null)
      karel=new Karel();
    karel.drawingArea.ready();
  }

  /**
   * Karel takes a ball
   */
  public static void getBall()
  {
    if(karel==null)
      karel=new Karel();
    karel.drawingArea.getBall();
  }
  
  /**
   * Karel drops a ball
   */
  public static void putBall()
  {
    if(karel==null)
      karel=new Karel();
    karel.drawingArea.putBall();
  }
  
  /**
   * Checks if Karel is facing north
   * @return true if Karel is facing north, false otherwise
   */
  public static boolean north()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.north();
  }

  /**
   * Checks if Karel is facing east
   * @return true if Karel is facing east, false otherwise
   */
  public static boolean east()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.east();
  }
  
  /**
   * Checks if Karel is facing south
   * @return true if Karel is facing south, false otherwise
   */
  public static boolean south()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.south();
  }

  /**
   * Checks if Karel is facing west
   * @return true if Karel is facing west, false otherwise
   */
  public static boolean west()
  {
    if(karel==null)
      karel=new Karel();
    return karel.drawingArea.west();
  }
  
  /**
   * Creates a random number, between from and to including the bounds
   * @param from
   * @param to
   * @return random number
   */
  public static int random(int from, int to)
  {
    return new Random().nextInt(to-from+1)+from;
  }
}
