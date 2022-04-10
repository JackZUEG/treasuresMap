package model.square;

import java.util.Objects;

public class Square {

    private SquareType squareType;
    private boolean isTaken;

    public Square(SquareType squareType) {
        this.squareType = squareType;
        this.isTaken = false;
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public void setSquareType(SquareType squareType) {
        this.squareType = squareType;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}
