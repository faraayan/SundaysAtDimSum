package gameObjects.helper;


import gameObjects.ReefDisplay;
import gameObjects.stationary.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MapLoader {
    String mapName = "map1";
    InputStreamReader isr;
    BufferedReader mapReader;

    public void setMapName(String mapName){
        this.mapName = mapName;
    }

    public void loadMap(ArrayList<Tile> tiles){
        isr = new InputStreamReader(ReefDisplay.class.getClassLoader().getResourceAsStream("maps/" + mapName));
        mapReader = new BufferedReader(isr);
        tiles.clear();
        try {
            String row = mapReader.readLine();
            if(row == null){
                throw new IOException("No data in file");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int curRow = 0; curRow<numRows; curRow++){
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int curCol = 0; curCol<numCols; curCol++){
                    switch(mapInfo[curCol]){
                        case "E": // ExtraPointsTile
                            tiles.add(new ExtraPointTile(curCol*100, curRow*60, Resource.getResourceImage("extraPointTile")));
                            break;
                        case "L": // HealthTile
                            tiles.add(new HealthTile(curCol*100, curRow*60, Resource.getResourceImage("healthTile")));
                            break;
                        case "S": // SlowSpeedTile
                            tiles.add(new SlowSpeedTile(curCol*100, curRow*60, Resource.getResourceImage("slowSpeedTile")));
                            break;
                        case "1": // 1-Collision RegularTile
                            tiles.add(new RegularTile(curCol*100, curRow*60, 1, Resource.getResourceImage("tile1")));
                            break;
                        case "2": // 2-Collision RegularTile
                            tiles.add(new RegularTile(curCol*100, curRow*60, 2, Resource.getResourceImage("tile2")));
                            break;
                        case "3": // 3-Collision RegularTile
                            tiles.add(new RegularTile(curCol*100, curRow*60, 3, Resource.getResourceImage("tile3")));
                            break;
                        case "4": // 4-Collision RegularTile
                            tiles.add(new RegularTile(curCol*100, curRow*60, 4, Resource.getResourceImage("tile4")));
                            break;
                        case "5": // 5-Collision RegularTile
                            tiles.add(new RegularTile(curCol*100, curRow*60, 5, Resource.getResourceImage("tile5")));
                            break;
                        case "Fara": // Fara Boss
                            tiles.add(new Boss(curCol*100, curRow*60, Resource.getResourceImage("boss_Fara")));
                            break;
                        case "Bryce": // Bryce Boss
                            tiles.add(new Boss(curCol*100, curRow*60, Resource.getResourceImage("boss_Bryce")));
                            break;
                        case "Monica": // Monica Boss
                            tiles.add(new Boss(curCol*100, curRow*60, Resource.getResourceImage("boss_Monica")));
                            break;
                        case "Ama": // Ama Boss
                            tiles.add(new Boss(curCol*100, curRow*60, Resource.getResourceImage("boss_Ama")));
                            break;
                        case "Chloe": // Chloe Boss
                            tiles.add(new Boss(curCol*100, curRow*60, Resource.getResourceImage("boss_Chloe")));
                            break;
                        case "Matt": // Matt Boss
                            tiles.add(new Boss(curCol*100, curRow*60, Resource.getResourceImage("boss_Matt")));
                            break;
                        case "9": // UnbreakTile
                            tiles.add(new UnbreakTile(curCol*100, curRow*60, Resource.getResourceImage("unbreak")));
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
