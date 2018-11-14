package GameObject;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Missile extends GameObject implements Observer {
  private int currentFrame;
  private Sprite sprite;
  private ImageObserver observer;
  private int width, height;
  private int interval;
  
  public Missile() {}
  
  
  public Missile( Sprite sprite, ImageObserver o, Tank tank ) throws IOException {
    this.setX( tank.getX() + (tank.getWidth()/2 - 11) + 45*Math.cos(tank.getAngleInRadian()) );
    this.setY( tank.getY() + (tank.getHeight()/2 - 11) - 45*Math.sin(tank.getAngleInRadian()) );
    this.currentFrame = tank.getCurrentFrame();
    this.sprite = sprite;
    this.observer = o;
    this.width = sprite.getFrame(0).getWidth();
    this.height = sprite.getFrame(0).getHeight();
    this.setShow( true );
    this.interval = 0;
  }
  
  public int getWidth() { return this.width; }
  
  public int getHeight() { return this.height; }
  
  public double getAngleInDegree() { return this.getCurrentFrame() * 6; }
  
  public double getAngleInRadian() { return Math.toRadians(this.getAngleInDegree()); }
  
  public double getXDisplacement() { return 13 * Math.cos(this.getAngleInRadian()); }
  
  public double getYDisplacement() { return 13 * Math.sin(this.getAngleInRadian()); }
  
  
  public int getCurrentFrame() { 
    if ( currentFrame <= 0) {
      currentFrame = 60;
    }
    return currentFrame; 
  }
  
  public boolean checkCollision() {
    boolean collided = false;
    double x0, y0, x1, y1, x2, y2, x3, y3;
    int i, j;
    
    x0 = this.getX();
    y0 = this.getY();
    
    x1 = this.getX() + this.width;
    y1 = this.getY();
    
    x2 = this.getX();
    y2 = this.getY() + this.height;
    
    x3 = this.getX() + this.width;
    y3 = this.getY() + this.height;
    
    i = (int)x0 / 32;
    j = (int)y0 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) { 
      collided = true; 
      if ( MapInitialization.wallLocation[i][j] == 2 ){
        MapInitialization.wallLocation[i][j] = 0;
      }   
    }
    
    i = (int)x1 / 32;
    j = (int)y1 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) { 
      collided = true; 
      if ( MapInitialization.wallLocation[i][j] == 2 ){
        MapInitialization.wallLocation[i][j] = 0;
      }   
    }
    
    i = (int)x2 / 32;
    j = (int)y2 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) { 
      collided = true; 
      if ( MapInitialization.wallLocation[i][j] == 2 ){
        MapInitialization.wallLocation[i][j] = 0;
      }   
    }
    
    i = (int)x3 / 32;
    j = (int)y3 / 32;
    if ( MapInitialization.wallLocation[i][j] != 0 ) { 
      collided = true; 
      if ( MapInitialization.wallLocation[i][j] == 2 ){
        MapInitialization.wallLocation[i][j] = 0;
      }   
    }
    
    
    return collided;
    
  }
  
  public boolean checkCollision( Tank tank ) {
    boolean collided = false;
    double x0, y0, x1, y1;
    
    x0 = this.getX() + 9;
    y0 = this.getY() + 9;
    
    x1 = tank.getX() + tank.getWidth()/2 - 5;
    y1 = tank.getY() + tank.getHeight()/2 - 5;
    
    if ( (x1 - x0)*(x1 - x0) + (y1 - y0)*(y1 - y0) <= 44 * 44 ) {
      collided = true;
      tank.setLife(1);
      if ( tank.getLife() == 0 ) {
        tank.setShow( false );
        tank.setX(-100);
        tank.setY(-200);
      }
    }
    
    
    return collided;
    
  }
  
  public int getInterval() { return this.interval; }
  
  public Sprite getSprite() { return this.sprite; }

  public void repaint(Graphics graphics) {
    graphics.drawImage(
        this.sprite.getFrame( currentFrame ), (int)getX(), (int)getY(), observer);  
  }
  
  @Override
  public void update(Observable o, Object arg) {
    this.interval++;
    this.setX( (this.getX() + this.getXDisplacement()) % 1175 );
    this.setY( (this.getY() - this.getYDisplacement()) % 1175 );
  }

}
