package utils;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public abstract class StringUtils {

    public static String buildLine(String[] infos){
        StringBuilder stringBuilder = new StringBuilder();
        String separator = " - ";

        for(int nbInfo = 0; nbInfo < infos.length; nbInfo++){
            if(!infos[nbInfo].equals(null)){
                stringBuilder.append(infos[nbInfo]);
            }
            if(nbInfo < infos.length - 1){
                stringBuilder.append(separator);
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isFileEqualTo(Path firstFile, Path secondFile) throws IOException {
        Files.writeString(firstFile, "testing line 1 \n line 2");
        Files.writeString(secondFile, "testing line 1 \r\n line 2");

        Reader reader1 = new BufferedReader(new FileReader(firstFile.toFile()));
        Reader reader2 = new BufferedReader(new FileReader(secondFile.toFile()));

        return IOUtils.contentEqualsIgnoreEOL(reader1, reader2);
    }

}
