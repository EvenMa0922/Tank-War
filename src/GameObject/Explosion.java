package GameObject;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Explosion extends GameObject implements Observer {
  private int currentFrame;
  private Sprite sprite;
  private ImageObserver observer;
  private int width, height;
  private int frameCount;
  
  public Explosion() {}
  
  public Explosion( Sprite sprite, ImageObserver o, Missile missile ) {
    this.setX( missile.getX() + (missile.getWidth()/2 - 10)  );
    this.setY( missile.getY() + (missile.getHeight()/2 - 10)  );
    this.currentFrame = 0;
    this.sprite = sprite;
    this.observer = o;
    this.width = sprite.getFrame(0).getWidth();
    this.height = sprite.getFrame(0).getHeight();
    this.setShow( true );
  }
  
  public Sprite getSprite() { return this.sprite; }

  public void repaint(Graphics graphics) {
    graphics.drawImage(
        this.sprite.getFrame( currentFrame ), (int)getX(), (int)getY(), observer);  
  }

  @Override
  public void update(Observable o, Object arg) {
    this.frameCount++;
    if ( frameCount % 5 == 0) {
      currentFrame++;
    }
    if ( this.currentFrame == 6 ) {
      this.setShow( false );
      this.setX(-100);
      this.setY(-100);
      o.deleteObserver(this);
    }
    
  }
  
  
}
