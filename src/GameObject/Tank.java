package GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import GameWorld.*;



public class Tank extends GameObject {
  private Sprite sprite;
  private int currentFrame;
  private ImageObserver observer;
  private int width, height;
  private int life = 5; 
  private int interval;
  
  public Tank() {};
  
  public Tank( Sprite sprite, double x, double y ) throws IOException {
    this( sprite, null, x, y );
  }
  
  public Tank( Sprite sprite, ImageObserver o, double x, double y) throws IOException {
    this.sprite = sprite;
    this.observer = o;
    currentFrame = 0;
    this.setX(x);
    this.setY(y);
    this.setShow(true);
    this.interval = 0;
    this.height = sprite.getFrame(0).getHeight();
    this.width = sprite.getFrame(0).getWidth();
  }
  public String getLifeString() { return Integer.toString(this.life); }
  
  public void setInterval() { this.interval++; }
  
  public boolean checkReload() { return this.interval % 20 == 0; }
  
  public int getWidth() { return this.width; }
  
  public int getHeight() { return this.height; }
  
  public double getAngleInDegree() { return this.getCurrentFrame() * 6; }
  
  public double getAngleInRadian() { return Math.toRadians(this.getAngleInDegree()); }
  
  public double getXDisplacement() { return 4 * Math.cos(this.getAngleInRadian()); }
  
  public double getYDisplacement() { return 4 * Math.sin(this.getAngleInRadian()); }
  
  public boolean checkCollision() {
    boolean collided = false;
    double x0, y0, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6;
    int i, j;
    
    x0 = this.getX();
    y0 = this.getY();
    
    x1 = this.getX() + this.width - 10;
    y1 = this.getY();
    
    x2 = this.getX();
    y2 = this.getY() + this.height - 10;
    
    x3 = this.getX() + this.width - 10;
    y3 = this.getY() + this.height - 10;
    
    x4 = this.getX() + this.width/2 - 5;
    y4 = this.getY() + this.height/2 - 5;
    
    x5 = this.getX() + this.width - 10;
    y5 = this.getY() + this.height/2;
    
    x6 = this.getX();
    y6 = this.getY() + this.height/2;
    
    i = (int)x0 / 32;
    j = (int)y0 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) collided = true;
    
    i = (int)x1 / 32;
    j = (int)y1 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) collided = true;
    
    i = (int)x2 / 32;
    j = (int)y2 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) collided = true;
    
    i = (int)x3 / 32;
    j = (int)y3 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) collided = true;
    
    i = (int)x4 / 32;
    j = (int)y4 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) collided = true;
    
    i = (int)x5 / 32;
    j = (int)y5 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) collided = true;
    
    i = (int)x6 / 32;
    j = (int)y6 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) collided = true;
    
    return collided;
    
  }
  
  public boolean checkCollision( Tank tank ) {
    boolean collided = false;
    double x, y, x1, y1;
    
    x = this.getX() + width / 2;
    y = this.getY() + height / 2;
    
    x1 = tank.getX() + tank.getWidth() / 2;
    y1 = tank.getY() + tank.getHeight() / 2;
    
    if ( (x1 - x)*(x1 - x) + (y1 - y)*(y1 - y) <= 50 * 50 ) collided = true;
    
    return collided;
  }
  
  public int getCurrentFrame() { 
    if ( currentFrame <= 0) {
      currentFrame = 60;
    }
    return currentFrame; 
  }
  
  public int getWindowX() { 
    int x;
    x = (int) (this.getX() - 150);
    if ( x < 0 ) x = 0;
    if ( x > 875) x = 875;
    return x;
  }
  
  public int getWindowY() {
    int y;
    y = (int) (this.getY() - 300 );
    if ( y < 0 ) y = 0;
    if ( y > 575 ) y = 575;
    return y;
  }
  
  public int getLife() { return life; }
  
  public void setLife( int demage ) { life -= demage; }
  
  public void setCurrentFrame( int num ) { this.currentFrame = num; }
  
  public Sprite getSprite() { return this.sprite; }

  public void repaint(Graphics graphics) {
    graphics.drawImage(
        this.sprite.getFrame( currentFrame ), (int)getX(), (int)getY(), observer);  
  }
}
