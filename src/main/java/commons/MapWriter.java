package commons;

import exception.IncorrectMovementException;
import model.map.Map;
import utils.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Classe abstraite facilitant l'ecriture de la carte
 */
public abstract class MapWriter {

    /**
     * Methode permettant d'ecrire la carte dans un fichier
     * @param map carte qui doit etre ecrite
     * @param fileNameOutput nom du fichier de sortie
     * @exception IOException exception liee a l'ecriture dans le fichier
     */
    public static void writeTreasureMapInFile(Map map, String fileNameOutput) throws IOException {
        Writer fileWriter = new FileWriter(fileNameOutput, false);
        fileWriter.write(writeDimensionsMap(map));
        fileWriter.close();
    }

    /**
     * Methode retournant la map ecrite
     * @param map carte qui doit etre ecrite
     */
    private static String writeDimensionsMap(Map map){
        return map.toString();
    }

}
