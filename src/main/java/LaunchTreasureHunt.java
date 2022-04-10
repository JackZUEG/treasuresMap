import commons.MapBuilder;
import commons.MapPlayer;
import commons.MapWriter;
import exception.InvalidInputFileException;
import exception.NoDimensionsMapFound;
import model.map.Map;

public class LaunchTreasureHunt {
    public static void main(String[] args) {

        try{
            String fileNameOutput = "src\\main\\resources\\output\\resultTreasureHunt";

            System.out.println("---------------Creation de la carte--------------");
            Map map = MapBuilder.readMap(args[0]);
            System.out.println("-----------------Creation terminee---------------");
            System.out.println("-----------Chasse aux tresors en cours-----------");
            MapPlayer.playTreasureMap(map);
            System.out.println("-----------Chasse aux tresors terminee-----------");
            MapWriter.writeTreasureMapInFile(map, fileNameOutput);
            System.out.println("---------Resultat disponible dans output---------");

        } catch(NoDimensionsMapFound | InvalidInputFileException e){
            System.out.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
