package model.adventurer;

public enum Movement {
    FORWARD("A"),
    TURNLEFT("G"),
    TURNRIGHT("R");

    private String value;
    private boolean isExecuted;

    Movement(String value) {
        this.value = value;
        this.isExecuted = false;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }
}
