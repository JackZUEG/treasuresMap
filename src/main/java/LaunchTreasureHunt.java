import commons.MapBuilder;
import commons.MapPlayer;
import commons.MapWriter;
import exception.InvalidInputFileException;
import exception.MessagesException;
import exception.NoDimensionsMapFound;
import model.map.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LaunchTreasureHunt {
    public static void main(String[] args) {

        try{
            if(args.length == 0){
                throw new InvalidInputFileException(MessagesException.InvalidInputFileException.getMsg());
            }
            String fileNameOutput = "resultTreasureHunt";

            System.out.println("---------------Creation de la carte--------------");
            Map map = MapBuilder.readMap(args[0]);
            System.out.println("-----------------Creation terminee---------------");
            System.out.println("-----------Chasse aux tresors en cours-----------");
            MapPlayer.playTreasureMap(map);
            System.out.println("-----------Chasse aux tresors terminee-----------");
            MapWriter.writeTreasureMapInFile(map, fileNameOutput);
            System.out.println("---------Resultat disponible dans target---------");

        } catch(NoDimensionsMapFound | InvalidInputFileException e){
            System.out.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
