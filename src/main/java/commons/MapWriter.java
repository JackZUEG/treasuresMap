package commons;

import model.map.Map;
import utils.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public abstract class MapWriter {

    public static void writeTreasureMapInFile(Map map) throws IOException {
        Writer fileWriter = new FileWriter("src\\main\\resources\\output\\resultTreasureHunt.txt", false);
        fileWriter.write(writeDimensionsMap(map));
        fileWriter.close();
    }

    private static String writeDimensionsMap(Map map){
        return map.toString();
    }

}
