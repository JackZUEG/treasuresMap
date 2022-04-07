package model.square;

public class SquareTreasure extends Square{

    private int countTreasures;

    public SquareTreasure(SquareType squareType, int countTreasures) {
        super(squareType);
        this.countTreasures = countTreasures;
    }

    public int getCountTreasures(){
        return this.countTreasures;
    }

    public void removeTreasure(){
        this.countTreasures--;
    }


}
