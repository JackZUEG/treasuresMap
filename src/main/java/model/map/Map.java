package model.map;

import exception.ElementOutOfTheMapException;
import model.adventurer.Adventurer;
import model.coordinates.Coordinates;
import model.square.Square;
import model.square.SquareTreasure;
import model.square.SquareType;
import utils.MapUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private ArrayList<Adventurer> listAdventurers;
    private Square[][] squares;
    private Coordinates coordinates;

    public Map(Coordinates coordinates) {
        this.listAdventurers = new ArrayList<>();
        this.coordinates = coordinates;
        this.squares = new Square[coordinates.getCoordinateX()][coordinates.getCoordinateY()];
        setPlainMap();
    }

    public List<Adventurer> getListAdventurers() {
        return listAdventurers;
    }

    public void setListAdventurers(ArrayList<Adventurer> listAdventurers) {
        this.listAdventurers = listAdventurers;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void addAdventurer(Adventurer adventurer) throws ElementOutOfTheMapException {
        if(adventurer != null){
            Coordinates coordinatesAdventurer = adventurer.getCoordinates();
            if(!MapUtils.isSquareMountainOrTaken(this, coordinatesAdventurer)){
                this.listAdventurers.add(adventurer);
                this.getSquare(coordinatesAdventurer).setTaken(true);
            } else {
                throw new ElementOutOfTheMapException("La case est deja prise par une montagne ou un aventurier");
            }

        }
    }

    public void setPlainMap(){
        for(int x = 0; x < coordinates.getCoordinateX(); x++){
            for(int y = 0; y < coordinates.getCoordinateY(); y++){
                this.squares[x][y] = new Square(SquareType.PLAIN);
            }
        }
    }

    public void addSquare(Coordinates coordinates, Square square){
        this.squares[coordinates.getCoordinateX()][coordinates.getCoordinateY()] = square;
    }

    public Square getSquare(Coordinates coordinates) throws ElementOutOfTheMapException {
        if(MapUtils.isCoordinatesInMap(this, coordinates)){
            return squares[coordinates.getCoordinateX()][coordinates.getCoordinateY()];
        }
        throw new ElementOutOfTheMapException("La case est en dehors de la carte");
    }

    public String toString(){
        String[] infos = new String[3];
        infos[0] = "C";
        infos[1] = String.valueOf(this.getCoordinates().getCoordinateX());
        infos[2] = String.valueOf(this.getCoordinates().getCoordinateY());
        String lineDimMap = StringUtils.buildLine(infos);
        List<String> listSquaresString = new ArrayList<>();
        List<String> listAdventurersString = new ArrayList<>();

        for(int x = 0; x < getCoordinates().getCoordinateX(); x++){
            for(int y = 0; y < getCoordinates().getCoordinateY(); y++){
                try {
                    SquareType squareType = getSquare(new Coordinates(x, y)).getSquareType();
                    if(squareType == SquareType.MOUNTAIN){
                        infos[0] = "M";
                    } else if(squareType == SquareType.TREASURE){
                        infos[0] = "T";
                        infos = new String[4];
                        SquareTreasure squareTreasure = (SquareTreasure) getSquare(new Coordinates(x, y));
                        infos[3] = String.valueOf(squareTreasure.getCountTreasures());
                    }
                    infos[1] = String.valueOf(this.getCoordinates().getCoordinateX());
                    infos[2] = String.valueOf(this.getCoordinates().getCoordinateY());

                    listSquaresString.add(StringUtils.buildLine(infos));

                } catch (ElementOutOfTheMapException e) {
                    e.printStackTrace();
                }
            }
        }

        for(Adventurer adventurer: this.getListAdventurers()){
            String[] infosAdventurer = new String[3];
            infosAdventurer[0] = "A";
            infosAdventurer[1] = adventurer.getName();
            infosAdventurer[2] = String.valueOf(this.getCoordinates().getCoordinateX());
            infosAdventurer[3] = String.valueOf(this.getCoordinates().getCoordinateY());
            infosAdventurer[4] = adventurer.getDirection().toString();
            infosAdventurer[5] = String.valueOf(adventurer.getRecoveredTreasures());
            listAdventurersString.add(StringUtils.buildLine(infos));
        }

        StringBuilder


        return
    }

}
