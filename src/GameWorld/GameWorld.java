package GameWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import GameObject.*;

public class GameWorld extends JPanel implements Observer{
  public static final int WIDTH = 1175;
  public static final int HEIGHT = 1175;
  private String tankBlueBasic = "Tank Graphics/Tank_blue_basic_strip60.png";
  private Dimension dimension;
  protected GameClock clock;
  protected BufferedImage image;
  protected Graphics g; 
  protected Background background;
  protected Wall wall;
  protected IndestructibleWall indewall;
  private MapInitialization map;


  
  public GameWorld() {
    this.dimension = new Dimension( WIDTH, HEIGHT );
    this.clock = new GameClock();
    this.clock.addObserver( this );
    
    image = new BufferedImage( WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB );
    g = image.getGraphics();
    
    try {
      this.background = new Background();
    } catch ( IOException exception ) {
      System.err.println( "Failed to load background." );
      exception.printStackTrace();
    }
    
    try {
      this.wall = new Wall();
    } catch ( IOException exception ) {
      System.err.println( "Failed to load wall." );
    }
    
    try {
      this.indewall = new IndestructibleWall();
    } catch ( IOException exception ) {
      System.err.println( "Failed to load indestructible wall." );
    }
    
    
    map = new MapInitialization();
    

    new Thread( this.clock ).start();
  }
  
  
  @Override
  public Dimension getPreferredSize() {
    return this.dimension;
  }

  @Override
  public void update(Observable o, Object arg) {
    this.repaint();
    
  }

  @Override
  public void paintComponent( Graphics g ) {
    
    super.paintComponent( g );
    
    for ( int x = 0; x < 4; x++ ) {
      for ( int y = 0; y < 5; y++ ) {
        background.setX( x * background.getWidth() );
        background.setY( y * background.getHeight() );
        background.repaint( g );
      }
    }
    
    for ( int i = 0; i < 37; i++ ) {
      for ( int j = 0; j < 37; j++) {
        if ( MapInitialization.wallLocation[i][j] == 1 ) {
          indewall.setX( i * indewall.getWidth());
          indewall.setY( j * indewall.getHeight() );
          indewall.repaint( g );
        }else if ( MapInitialization.wallLocation
            [i][j] == 2) {
          wall.setX( i * wall.getWidth());
          wall.setY( j * wall.getHeight());
          wall.repaint( g );
        }
      }
    }
    
    
  }
}