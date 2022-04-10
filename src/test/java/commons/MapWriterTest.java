package commons;

import exception.InvalidInputFileException;
import exception.NoDimensionsMapFound;
import model.map.Map;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MapWriterTest {
    private Map map;

    @BeforeEach
    void setUp() {
        try {
            this.map = MapBuilder.readMap("src\\test\\resources\\input\\mapPlayerTestPlayMapOk");
            MapPlayer.playTreasureMap(map);
        } catch (NoDimensionsMapFound | InvalidInputFileException e) {
            fail(e.getCause());
        }
    }

    @Test
    void writeTreasureMapInFileExpectOk() {
        try{
            MapWriter.writeTreasureMapInFile(map, "src\\test\\resources\\output\\resultTestMapWriter");
            String originalFile = "src\\test\\resources\\output\\resultTestMapWriterToCompare";
            String fileToCompare = "src\\test\\resources\\output\\resultTestMapWriter";
            assertTrue(StringUtils.isFileEqualTo(Path.of(originalFile), Path.of(fileToCompare)));
        } catch(IOException e){
            fail(e.getCause());
        }
    }
}