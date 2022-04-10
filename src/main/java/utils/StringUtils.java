package utils;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

/**
 * Classe abstraite facilitant l'utilisation des strings
 */
public abstract class StringUtils {

    /**
     * Methode permettant de construire une ligne a ecrire (Case, Map ou Aventurier)
     * @param infos informations de la ligne a creer
     * @return String ligne a ecrire
     */
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

    /**
     * Methode permettant de comparer le contenu de deux fichiers texte
     * @param firstFile Path du premier fichier
     * @param secondFile Path du second fichier
     * @return Boolean vrai si les deux fichiers sont egaux
     */
    public static boolean isFileEqualTo(Path firstFile, Path secondFile) throws IOException {
        Reader reader1 = new BufferedReader(new FileReader(firstFile.toFile()));
        Reader reader2 = new BufferedReader(new FileReader(secondFile.toFile()));
        return IOUtils.contentEqualsIgnoreEOL(reader1, reader2);
    }

}
