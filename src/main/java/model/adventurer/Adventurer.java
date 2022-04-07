package model.adventurer;

import exception.IncorrectDirectionException;
import exception.IncorrectMovementException;
import model.coordinates.Coordinates;
import utils.AdventurerUtils;

import java.util.ArrayList;
import java.util.List;

public class Adventurer {

    private String name;
    private Coordinates coordinates;
    private int recoveredTreasures;
    private Direction direction;
    private List<Movement> nextMovements;

    public Adventurer(String name, Coordinates coordinates, String direction, String movements) throws IncorrectMovementException, IncorrectDirectionException {
        this.name = name;
        this.coordinates = coordinates;
        this.recoveredTreasures = recoveredTreasures;
        this.direction = createDirection(direction);
        this.nextMovements = createMovements(movements);
    }

    private Direction createDirection(String direction) throws IncorrectDirectionException {
        return AdventurerUtils.getDirection(direction);
    }

    private List<Movement> createMovements(String movements) throws IncorrectMovementException {
        List<Movement> listMovements = new ArrayList<>();

        for(int pos = 0; pos < movements.length(); pos++){
            listMovements.add(AdventurerUtils.getMovement(movements.charAt(pos)));
        }
        return listMovements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getRecoveredTreasures() {
        return recoveredTreasures;
    }

    public void setRecoveredTreasures(int recoveredTreasures) {
        this.recoveredTreasures = recoveredTreasures;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void turnDirection(Movement movement){
        if(getDirection() == Direction.NORTH){
            if(movement.equals(Movement.TURNRIGHT)){
                setDirection(Direction.EAST);
            } else if(movement.equals(Movement.TURNLEFT)){
                setDirection(Direction.WEST);
            }
        } else if(getDirection() == Direction.SOUTH){
            if(movement.equals(Movement.TURNRIGHT)){
                setDirection(Direction.WEST);
            } else if(movement.equals(Movement.TURNLEFT)){
                setDirection(Direction.EAST);
            }
        } else if(getDirection() == Direction.EAST){
            if(movement.equals(Movement.TURNRIGHT)){
                setDirection(Direction.SOUTH);
            } else if(movement.equals(Movement.TURNLEFT)){
                setDirection(Direction.NORTH);
            }
        } else if(getDirection() == Direction.WEST){
            if(movement.equals(Movement.TURNRIGHT)){
                setDirection(Direction.NORTH);
            } else if(movement.equals(Movement.TURNLEFT)){
                setDirection(Direction.SOUTH);
            }
        }
    }

    public List<Movement> getNextMovements() {
        return nextMovements;
    }

    public void setNextMovements(List<Movement> nextMovements) {
        this.nextMovements = nextMovements;
    }

    public Movement getNextMovement(){
        return nextMovements.get(0);
    }

    public void removeNextMovement(){
        if(nextMovements != null){
            nextMovements.remove(0);
        }
    }

    public void getTreasure(){
        setRecoveredTreasures(getRecoveredTreasures()+1);
    }

    public boolean isOutOfMovements(){
        return this.getNextMovements().size() == 0;
    }

}