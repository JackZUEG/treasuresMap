package model.adventurer;

/**
 * Enumeration representant les differentes directions
 */
public enum Direction {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("O");

    private String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
