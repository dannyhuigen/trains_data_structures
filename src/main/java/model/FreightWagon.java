package model;

public class FreightWagon extends Wagon {

    int maxWeight;

    /**
     * The constructor of the freight wagon
     * @param wagonId the id of the wagon
     * @param maxWeight the maxweight of the wagon
     */
    public FreightWagon(int wagonId, int maxWeight) {
        super(wagonId);
        this.maxWeight = maxWeight;
    }

    /**
     * Gets the max weight of the train
     * @return the max weight
     */
    public int getMaxWeight() {
        return maxWeight;
    }
}
