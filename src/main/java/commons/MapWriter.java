package commons;

import model.map.Map;
import utils.StringUtils;

public abstract class MapWriter {

    public static void writeTreasureMap(Map map){
        System.out.println(writeDimensionsMap(map));
    }


    private static String writeDimensionsMap(Map map){
        return map.toString();
    }

}
