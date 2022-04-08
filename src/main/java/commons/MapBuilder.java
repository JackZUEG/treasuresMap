package commons;

import exception.*;
import model.adventurer.Adventurer;
import model.adventurer.Direction;
import model.coordinates.Coordinates;
import model.map.Map;
import model.square.Square;
import model.square.SquareTreasure;
import model.square.SquareType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapBuilder {

    public static Map readMap(String fileName) throws NoDimensionsMapFound, InvalidInputFileException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String> linesInFile = reader.lines().collect(Collectors.toList());
            Map map = createMap(linesInFile);
            createElements(map, linesInFile);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IncorrectDirectionException | IncorrectMovementException e){
            System.out.println(e.getMessage());
        } catch (NoDimensionsMapFound e) {
            throw e;
        }
        throw new InvalidInputFileException(MessagesException.InvalidInputFileException.getMsg());
    }

    public static Map createMap(List<String> lines) throws NoDimensionsMapFound {
        try {
            Coordinates mapDimensions = LineParser.getMapDimensions(lines);
            Map map = new Map(mapDimensions);
            map.setPlainMap();
            return map;
        } catch(NoDimensionsMapFound e){
            throw e;
        }
    }

    public static void createElements(Map map, List<String> lines) throws IncorrectDirectionException, IncorrectMovementException {
        for(String line: lines){
            String[] infos = line.replaceAll(" ", "").split("-");
            if(infos.length > 2){
                switch(infos[0].charAt(0)){
                    case 'M':
                        createSquare(map, infos);
                    case 'T':
                        if(infos.length > 3){
                            createSquare(map, infos);
                        }
                        break;
                    case 'A':
                        if(infos.length > 5){
                            createAdventurer(map, infos);
                        }
                        break;
                }
            } else {
                System.out.println("La ligne du fichier numero "+(lines.indexOf(line)+1)+" a ete ignoree car invalide");
            }
        }
    }

    public static void createAdventurer(Map map, String[] infos) throws IncorrectMovementException, IncorrectDirectionException {
        Coordinates adventurerCoord = new Coordinates(Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
        Adventurer adventurer = new Adventurer(infos[1], adventurerCoord, infos[4], infos[5]);
        try{
            map.addAdventurer(adventurer);
        } catch(SquareIsMountainOrTakenException e){
            System.out.println(e.getMessage());
        }

    }

    public static void createSquare(Map map, String[] infos){
        Square square = new Square(SquareType.PLAIN);

        if(infos[0].equals("M")){
            square = new Square(SquareType.MOUNTAIN);
        } else if(infos[0].equals("T")){
            square = new SquareTreasure(SquareType.TREASURE, Integer.parseInt(infos[3]));
        }
        map.addSquare(new Coordinates(Integer.parseInt(infos[1]), Integer.parseInt(infos[2])), square);
    }
}
