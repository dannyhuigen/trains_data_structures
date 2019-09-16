package model;

public class Wagon {
    private int wagonId;
    private Wagon previousWagon;
    private Wagon nextWagon;

    /**
     * Wagon constructor
     * @param wagonId the id of the wagon
     */
    public Wagon (int wagonId) {
        this.wagonId = wagonId;
    }

    /**
     * Gets the last wagon in train (recursive)
     * @return the last wagon attached to the wagon
     */
    public Wagon getLastWagonAttached() {
        if (nextWagon == null){
            return this;
        }
        else{
            return nextWagon.getLastWagonAttached();
        }
    }

    /**
     * Sets the next wagon of the wagon
     * @param nextWagon the next wagon
     */
    public void setNextWagon(Wagon nextWagon) {
        // when setting the next wagon, set this wagon to be previous wagon of next wagon
        if (nextWagon != null){
            nextWagon.setPreviousWagon(this);
        }
        this.nextWagon = nextWagon;

    }

    /**
     * Gets the previous wagon of this wagon
     * @return previous wagon
     */
    public Wagon getPreviousWagon() {
        return previousWagon;
    }

    /**
     * Set the previous wagon
     * @param previousWagon the previous wagon
     */
    public void setPreviousWagon(Wagon previousWagon) {
        this.previousWagon = previousWagon;
    }

    /**
     * Get the next wagon of this wagon
     * @return the next wagon
     */
    public Wagon getNextWagon() {
        return nextWagon;
    }

    /**
     * Gets the wagon id of this wagon
     * @return the wagon id
     */
    public int getWagonId() {
        return wagonId;
    }

    /**
     * Gets the number of wagons attached to this wagon
     * @return the number of wagon attached to this wagon
     */
    public int getNumberOfWagonsAttached() {
        if (nextWagon == null){
            return 0;
        }
        else{
            return nextWagon.getNumberOfWagonsAttached() + 1;
        }
    }

    /**
     * Checks if this wagon has a next wagon
     * @return true if this wagon has a next wagon
     */
    public boolean hasNextWagon() {
        return !(nextWagon == null);
    }

    /**
     * Checks if this wagon has a previous wagon
     * @return true if this wagon has a previous wagon
     */
    public boolean hasPreviousWagon() {
        return !(previousWagon == null);
    }

    @Override
    public String toString() {
        return String.format("[Wagon %d]", wagonId);
    }
}
