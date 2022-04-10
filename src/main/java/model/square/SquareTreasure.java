package model.square;

public class SquareTreasure extends Square{

    private int countTreasures;

    public SquareTreasure(int countTreasures) {
        super(SquareType.TREASURE);
        this.countTreasures = countTreasures;
    }

    public int getCountTreasures(){
        return this.countTreasures;
    }

    public void removeTreasure(){
        if(this.countTreasures > 0){
            this.countTreasures--;
        }
    }


}
