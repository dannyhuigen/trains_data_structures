package model;

public class Locomotive {
    private int locNumber;
    private int maxWagons;

    /**
     * The construcor of the locomotive (engine)
     * @param locNumber the loc number
     * @param maxWagons the max wagons that can be attached
     */
    public Locomotive(int locNumber, int maxWagons) {
        this.locNumber = locNumber;
        this.maxWagons = maxWagons;
    }

    /**
     * Gets the max wagons that can be attached
     * @return the max wagons
     */
    public int getMaxWagons() {
        return maxWagons;
    }

    @Override
    public String toString() {
        return String.format("{Loc %d}", locNumber);
    }
}
