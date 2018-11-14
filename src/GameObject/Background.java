package GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

public class Background extends GameObject {
  private final String location = "Tank Graphics/background_tile.png";
  private BufferedImage image;
  private int height;
  private int width;
  
  public Background() throws IOException {
   image = ImageIO.read( new File ( location ) );
   this.height = image.getHeight();
   this.width = image.getWidth();
  }
  
  public int getWidth() { return width; }
  
  public int getHeight() { return height; }
  
  public void repaint( Graphics graphic) {
    graphic.drawImage( image, (int)getX(), (int)getY(), null );
  }
}
