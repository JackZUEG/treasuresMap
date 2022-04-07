package commons;

import exception.ElementOutOfTheMapException;
import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import exception.NoDimensionsMapFound;
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

    private String fileName;
    private File fileMap;
    private Map map;

    public MapBuilder(String fileName){
        this.fileName = fileName;
    }

    public Map readMap(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String> linesInFile = reader.lines().collect(Collectors.toList());

            createMap(linesInFile);
            createElements(linesInFile);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoDimensionsMapFound e){
            System.out.println(e.getMessage());
        } catch (IncorrectDirectionException | IncorrectMovementException e) {
            e.printStackTrace();
        } catch (ElementOutOfTheMapException e) {
            e.printStackTrace();
        }
        return this.map;
    }

    public void createMap(List<String> lines) throws NoDimensionsMapFound {
        Coordinates mapDimensions = LineParser.getMapDimensions(lines);
        this.map = new Map(mapDimensions);
        this.map.setPlainMap();
    }

    public void createElements(List<String> lines) throws IncorrectDirectionException, IncorrectMovementException, ElementOutOfTheMapException {
        for(String line: lines){
            String[] infos = line.replaceAll(" ", "").split("-");
            switch(infos[0].charAt(0)){
                case 'M', 'T':
                    createSquare(infos);
                    break;
                case 'A':
                    createAdventurer(infos);
                    break;
            }
        }
    }

    public void createAdventurer(String[] infos) throws IncorrectMovementException, IncorrectDirectionException, ElementOutOfTheMapException {
        Coordinates adventurerCoord = new Coordinates(Integer.parseInt(infos[2]), Integer.parseInt(infos[3]));
        Adventurer adventurer = new Adventurer(infos[1], adventurerCoord, infos[4], infos[5]);
        this.map.addAdventurer(adventurer);
    }

    public void createSquare(String[] infos){
        Square square = new Square(SquareType.PLAIN);

        if(infos[0].equals("M")){
            square = new Square(SquareType.MOUNTAIN);
        } else if(infos[0].equals("T")){
            square = new SquareTreasure(SquareType.TREASURE, Integer.parseInt(infos[3]));
        }
        this.map.addSquare(new Coordinates(Integer.parseInt(infos[1]), Integer.parseInt(infos[2])), square);
    }
}
