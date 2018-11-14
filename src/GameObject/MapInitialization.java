package GameObject;

public class MapInitialization {
  public static int[][] wallLocation;
  
  public MapInitialization() {
    wallLocation = new int[37][37];
    setWalls();
  }
  
  public void setWalls() {
    for( int i = 0; i < 37; i++) {
      wallLocation[0][i] = 1;
      wallLocation[36][i] = 1;
      wallLocation[i][0] = 1;
      wallLocation[i][36] = 1;
    }
    
    for( int i = 1; i < 6; i++){
      wallLocation[i][17] = 1;
      wallLocation[30 + i][17] = 1;
      wallLocation[17][i] = 1;
      wallLocation[17][30 + i] = 1;
    }
    
    for ( int i = 1; i < 7; i++ ) {
      wallLocation[17 - i][5] = 1;
      wallLocation[17 + i][31] = 1;
    }
    
    
    for ( int i = 14; i < 20; i++) {
      for ( int j = 8; j < 30; j++) {
        wallLocation[i][j] = 2;
      }
    }
    
    
    for (int i = 14; i < 17; i++) {
      for ( int j = 8; j < 16; j++ ) {
        wallLocation[i][j] = 0;
      }
    }
    
    for ( int i = 16; i < 18; i++ ) {
      for ( int j = 19; j < 21; j++) {
        wallLocation[i][j] = 0;
      }
    }
    
    for ( int i = 18; i < 19; i++ ) {
      for ( int j = 12; j < 18; j++ ) {
        wallLocation[i][j] = 1;
      }
    }
    
    for ( int i = 15; i < 16; i++ ) {
      for ( int j = 23; j < 29; j++ ) {
        wallLocation[i][j] = 1;
      }
    }
    
    for ( int i = 1; i < 15; i++ ) {
      wallLocation[17 + i][8] = 1;
      wallLocation[16 - i][28] = 1;
    }
    
    for ( int i = 1; i < 6; i++) {
      wallLocation[i][28] = 0;
    }
    
    for ( int i = 18; i < 20; i++ ) {
      for ( int j = 23; j < 29; j++ ) {
        wallLocation[i][j] = 0;
      }
    }
    for ( int i = 13; i < 20; i++) {
      wallLocation[i][29] = 0;
    }
  }
  
}
