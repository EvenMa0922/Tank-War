package GameWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import GameObject.*;
import java.io.*;



public class KeyboardPanel extends GameWorld implements KeyListener {
  protected final String TANK_BLUE = "Tank Graphics/Tank_blue_basic_strip60.png";
  protected final String TANK_RED = "Tank Graphics/Tank_red_basic_strip60.png";
  protected final String TANK_MISSILE = "Tank Graphics/Shell_basic_strip60.png";
  protected final String MISSILE_EXPLOSION = "Tank Graphics/Explosion_small_strip6.png";
  protected final String MISSILE_SOUND = "Tank Graphics/turret.wav";
  
  private Tank blueTank, redTank;
  private ArrayList<Integer> pressedKey; 
  private ArrayList<Missile> missile1, missile2;
  private ArrayList<Explosion> explosion;
  //private AudioStream sound;
  private InputStream in;
  private int interval1 = 40;
  private int interval2 = 40;
  

  
  public KeyboardPanel() {
    super();
    
    try {
      this.blueTank = new Tank( new Sprite( TANK_BLUE, 64), this, 100, 100);
      this.redTank = new Tank( new Sprite( TANK_RED, 64), this, 660, 1050);
      this.in = new FileInputStream(MISSILE_SOUND);
     // this.sound = new AudioStream(in);
    } catch ( IOException exception ) {
      System.err.println( "Faied to load sprite of tank.");
    }
   
    this.missile1 = new ArrayList<Missile>();
    this.missile2 = new ArrayList<Missile>();
    this.explosion = new ArrayList<Explosion>();
    this.pressedKey = new ArrayList<Integer>();
    this.addKeyListener(this);
    this.setFocusable(true);
  }
  
  public void control( ArrayList<Integer> pressedKey ) {
    for ( Integer key : pressedKey ) {
      if ( key == 38 ) {
        blueTank.setX(( blueTank.getX() + blueTank.getXDisplacement()) % WIDTH );
        blueTank.setY(( blueTank.getY() - blueTank.getYDisplacement()) % HEIGHT );
        if ( blueTank.checkCollision() ) {
          blueTank.setX(( blueTank.getX() - blueTank.getXDisplacement()) % WIDTH );
          blueTank.setY(( blueTank.getY() + blueTank.getYDisplacement()) % HEIGHT );
        }
        if ( blueTank.checkCollision( redTank ) ) {
          blueTank.setX(( blueTank.getX() - blueTank.getXDisplacement()) % WIDTH );
          blueTank.setY(( blueTank.getY() + blueTank.getYDisplacement()) % HEIGHT );
        }
      }
      if ( key == 40 ) {
        blueTank.setX(( blueTank.getX() - blueTank.getXDisplacement()) % WIDTH );
        blueTank.setY(( blueTank.getY() + blueTank.getYDisplacement()) % HEIGHT );
        if ( blueTank.checkCollision() ) {
          blueTank.setX(( blueTank.getX() + blueTank.getXDisplacement()) % WIDTH );
          blueTank.setY(( blueTank.getY() - blueTank.getYDisplacement()) % HEIGHT );
        }
        if ( blueTank.checkCollision( redTank ) ) {
          blueTank.setX(( blueTank.getX() + blueTank.getXDisplacement()) % WIDTH );
          blueTank.setY(( blueTank.getY() - blueTank.getYDisplacement()) % HEIGHT );
        }
      }
      if ( key == 37 ) {
        blueTank.setCurrentFrame((blueTank.getCurrentFrame() + 1) % 60);
      }
      if ( key == 39 ) {
        blueTank.setCurrentFrame((blueTank.getCurrentFrame() - 1) % 60);
      }
      if ( key == 16 ) {
//        AudioPlayer.player.start(sound);
        try {

          if ( missile1.size() == 0 && this.interval1 > 25 ) {
            this.interval1 = 0;
            missile1.add( new Missile ( new Sprite( TANK_MISSILE, 24 ), this, blueTank ) );
          } else {
            if ( missile1.get( missile1.size() - 1 ).getInterval() > 20 ) {
              missile1.add( new Missile ( new Sprite( TANK_MISSILE, 24), this, blueTank ) );
            }
          }
        } catch ( IOException exception ) {
          System.err.println( "Faied to load sprite of blue tank missile.");
        }
        for( Missile missiles : missile1 ) {
          this.clock.addObserver( missiles );
        }
      }
      // redTank:
      if ( key == 87 ) {
        redTank.setX(( redTank.getX() + redTank.getXDisplacement()) % WIDTH );
        redTank.setY(( redTank.getY() - redTank.getYDisplacement()) % HEIGHT );
        if ( redTank.checkCollision() ) {
          redTank.setX(( redTank.getX() - redTank.getXDisplacement()) % WIDTH );
          redTank.setY(( redTank.getY() + redTank.getYDisplacement()) % HEIGHT );
        }
        if ( redTank.checkCollision( blueTank ) ) {
          redTank.setX(( redTank.getX() - redTank.getXDisplacement()) % WIDTH );
          redTank.setY(( redTank.getY() + redTank.getYDisplacement()) % HEIGHT );
        }
      }
      if ( key == 83 ) {
        redTank.setX(( redTank.getX() - redTank.getXDisplacement()) % WIDTH );
        redTank.setY(( redTank.getY() + redTank.getYDisplacement()) % HEIGHT );
        if ( redTank.checkCollision() ) {
          redTank.setX(( redTank.getX() + redTank.getXDisplacement()) % WIDTH );
          redTank.setY(( redTank.getY() - redTank.getYDisplacement()) % HEIGHT );
        }
        if ( redTank.checkCollision( blueTank ) ) {
          redTank.setX(( redTank.getX() + redTank.getXDisplacement()) % WIDTH );
          redTank.setY(( redTank.getY() - redTank.getYDisplacement()) % HEIGHT );
        }
      }
      if ( key == 65 ) {
        redTank.setCurrentFrame(( redTank.getCurrentFrame() + 1 ) % 60);
      }
      if ( key == 68 ) {
        redTank.setCurrentFrame(( redTank.getCurrentFrame() - 1 ) % 60);
      }
      if ( key == 70 ) {
        try {
       //   AudioPlayer.player.start(sound);
          if ( missile2.size() == 0 && this.interval2 > 25 ) {
            this.interval2 = 0;
            missile2.add( new Missile ( new Sprite( TANK_MISSILE, 24 ), this, redTank ) );
          } else {
            if ( missile2.get( missile2.size() - 1 ).getInterval() > 20 ) {
              missile2.add( new Missile ( new Sprite( TANK_MISSILE, 24), this, redTank ) );
            }
          }
        } catch ( IOException exception ) {
          System.err.println( "Faied to load sprite of red tank missile.");
        }
        for( Missile missiles : missile2 ) {
          this.clock.addObserver( missiles );
        }
        redTank.setInterval();
      }
    }
  }
  

  @Override
  public void update(Observable o, Object arg) {
    this.repaint(); 
  }
  
  @Override
  public void paintComponent( Graphics g2d ) {
    BufferedImage i = new BufferedImage(1175, 1175, BufferedImage.TYPE_INT_RGB );
    BufferedImage j = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    Graphics gg2 = j.getGraphics();
    Graphics gg = i.getGraphics();
    super.paintComponent( gg );
    
    
    if ( blueTank.getShow() ) {
      blueTank.repaint(gg);
    }
    
    if ( redTank.getShow() ) {
      redTank.repaint(gg);
    }
   
    control( pressedKey );
    
    blueTank.setInterval();
    
    this.interval1++;
    this.interval2++;
    
    for ( Missile missiles : missile1) {
      if ( missiles.checkCollision() || missiles.checkCollision( redTank ) ) {
        try {
          explosion.add( new Explosion( new Sprite ( MISSILE_EXPLOSION, 32), this, missiles));
        } catch ( IOException exception ) {
          System.err.println( "Faied to load sprite of missile explosion.");
        }
        for( Explosion explosions : explosion ) {
          this.clock.addObserver( explosions );
        }
        this.clock.deleteObserver(missiles);
        missile1.remove( missiles );        
      }
      else {
        missiles.repaint(gg);
      }
    }
    
    for ( Missile missiles : missile2) {
      if ( missiles.checkCollision() || missiles.checkCollision( blueTank ) ) {

        try {
          explosion.add( new Explosion( new Sprite ( MISSILE_EXPLOSION, 32), this, missiles));
        } catch ( IOException exception ) {
          System.err.println( "Faied to load sprite of missile explosion.");
        }
        for( Explosion explosions : explosion ) {
          this.clock.addObserver( explosions );
        }      
        this.clock.deleteObserver(missiles);
        missile2.remove( missiles );        
      }
      else {
        missiles.repaint(gg);
      }
    }
    
    for ( Explosion explosions : explosion ) {
      if ( !explosions.getShow() ) {
        this.clock.deleteObserver( explosions );
        explosion.remove( explosions );
      } else {
        explosions.repaint(gg);
      }
    }
   

    g2d.drawImage(i.getSubimage(this.blueTank.getWindowX(), this.blueTank.getWindowY(), 300, 600), 0, 0, this);
    g2d.drawImage(i.getSubimage(this.redTank.getWindowX(), this.redTank.getWindowY(), 300, 600), 300, 0, this);
    gg2.drawImage(i, 0, 0, 200, 200, this);
    g2d.drawImage(j, 200, 350, this);
    //g2d.drawRect(50, 50, 150, 30);
    g2d.setColor(Color.green);
    g2d.fillRect(50, 50, this.blueTank.getLife()*30, 30);
    g2d.drawString(this.blueTank.getLifeString(), 200, 50);
    g2d.fillRect(400, 50, this.redTank.getLife()*30, 30);
    g2d.drawString(this.redTank.getLifeString(), 390, 50);
    //g2d.setColor(Color.green);
  }

 

  @Override
  public void keyReleased(KeyEvent e) {
    pressedKey.remove( new Integer( e.getKeyCode() ) );
  }


  @Override
  public void keyPressed(KeyEvent e) {
    if ( !pressedKey.contains( e.getKeyCode() ) ) {
      pressedKey.add( new Integer(e.getKeyCode()) );
    }   
  } 

  @Override
  public void keyTyped(KeyEvent e) {}

  
}

