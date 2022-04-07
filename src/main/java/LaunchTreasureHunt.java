import commons.MapBuilder;
import commons.MapPlayer;
import commons.MapWriter;
import model.map.Map;

public class LaunchTreasureHunt {
    public static void main(String[] args) throws Exception {

        try{
            //String fileName = args[0];
            String fileName = "src/main/resources/input/map1.txt";
            MapBuilder mapBuilder = new MapBuilder(fileName);
            Map map = mapBuilder.readMap();

            MapPlayer.playTreasureMap(map);

            MapWriter.writeTreasureMap(map);
            System.out.println("Jeu termine");
        } catch(Exception e) {
            e.printStackTrace();
        }



    }
}
