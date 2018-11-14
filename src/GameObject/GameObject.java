package GameObject;

import java.awt.*;



public abstract class GameObject {
  private double x;
  private double y;
  private boolean show;
  
  public GameObject() {};
  
  
  public int[][] getWallLocation() { return MapInitialization.wallLocation; }
  
  public void setX( double x ) { this.x = x; }
  
  public void setY( double y ) { this.y = y; }
  
  public double getX() { return this.x; }
  
  public double getY() { return this.y; }
  
  public void setShow( boolean show ) { this.show = show; }
  
  public boolean getShow() { return this.show; }
  
}
