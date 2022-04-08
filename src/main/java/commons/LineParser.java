package commons;

import exception.NoDimensionsMapFound;
import model.coordinates.Coordinates;

import java.util.List;

public abstract class LineParser {

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
