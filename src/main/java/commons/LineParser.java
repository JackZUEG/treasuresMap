package commons;

import exception.NoDimensionsMapFound;
import model.coordinates.Coordinates;

import java.util.List;

/**
 * Classe abstraite pour faciliter le parsing des dimensions de la carte
 */
public abstract class LineParser {

    /**
     * Methode permettant de trouver des coordonnees dans une liste
     * @param lines une liste de string
     * @return Coordinates
     * @exception NoDimensionsMapFound Pas de dimensions trouvées
     */
    public static Coordinates getMapDimensions(List<String> lines) throws NoDimensionsMapFound {
        for(String line: lines){
            String[] infos = line.replaceAll(" ", "").split("-");
            if(infos[0].equals("C") && infos.length > 2){
                int dimX = Integer.parseInt(infos[1]);
                int dimY = Integer.parseInt(infos[2]);
                if(dimX > 0 && dimY > 0){
                    return new Coordinates(dimX, dimY);
                }
            }
        }
        throw new NoDimensionsMapFound("Dimensions de carte non fournie ou incorrecte dans le fichier en parametre");
    }
}
