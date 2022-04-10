package commons;

import model.map.Map;
import utils.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public abstract class MapWriter {

    public static void writeTreasureMapInFile(Map map, String fileNameOutput) throws IOException {
        Writer fileWriter = new FileWriter(fileNameOutput, false);
        fileWriter.write(writeDimensionsMap(map));
        fileWriter.close();
    }

    private static String writeDimensionsMap(Map map){
        return map.toString();
    }

}
