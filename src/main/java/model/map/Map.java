package model.map;

import exception.ElementOutOfTheMapException;
import model.adventurer.Adventurer;
import model.coordinates.Coordinates;
import model.square.Square;
import model.square.SquareType;
import utils.MapUtils;

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
}
