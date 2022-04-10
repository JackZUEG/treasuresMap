package utils;

import java.io.BufferedReader;
import java.io.IOException;
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

    public static boolean isFileEqualTo(Path firstFile, Path secondFile)
    {
        try {
            try (BufferedReader bf1 = Files.newBufferedReader(firstFile);
                 BufferedReader bf2 = Files.newBufferedReader(secondFile))
            {
                int ch;
                while ((ch = bf1.read()) != -1)
                {
                    if (ch != bf2.read()) {
                        return false;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
