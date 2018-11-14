package GameWorld;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import GameObject.GameObject;
import GameObject.*;

public class RunGame extends JFrame {
  private KeyboardPanel kp;
  
  public RunGame() throws IOException {
    kp = new KeyboardPanel();
    this.add(kp);
    
    pack();
    setTitle( "TankWar" );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    setLocationRelativeTo(null);
    
    
    setSize(600, 600);
 
   
    setVisible( true );

    setResizable( false );
  }
  

  
  public static void main( String[] args ) {
    try {
      new RunGame();
    } catch( IOException e ) {
      e.printStackTrace();
    }
  }
}
