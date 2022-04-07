package commons;

import exception.NoDimensionsMapFound;
import model.coordinates.Coordinates;

import java.util.List;

public abstract class LineParser {

    public static Coordinates getMapDimensions(List<String> lines) throws NoDimensionsMapFound {
        for(String line: lines){
            String[] infos = line.replaceAll(" ", "").split("-");
            if(infos[0].equals("C")){
                int dimX = Integer.parseInt(infos[1]);
                int dimY = Integer.parseInt(infos[2]);
                return new Coordinates(dimX, dimY);
            }
        }
        throw new NoDimensionsMapFound("Pas de dimensions de carte fournie dans le fichier en parametre");
    }
}
