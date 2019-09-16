package model;

public class Train {
    private Locomotive engine;
    private Wagon firstWagon;
    private String destination;
    private String origin;
    private int numberOfWagons;

    /**
     * The constructor of the train class
     * @param engine the engine of the train
     * @param origin the origin of the train
     * @param destination the destination of the train
     */
    public Train(Locomotive engine, String origin, String destination) {
        this.engine = engine;
        this.destination = destination;
        this.origin = origin;
    }

    /**
     * Gets first wagon on train
     * @return first wagon on train
     */
    public Wagon getFirstWagon() {
        return firstWagon;
    }

    /**
     * Sets first wagon on train
     * @param firstWagon the wagon you want to be the first wagon
     */
    public void setFirstWagon(Wagon firstWagon) {
        this.firstWagon = firstWagon;
    }

    /**
     * Resets the number of wagons to the actual wagons on train
     * Method mostly used after a wagon change of any sort
     */
    public void resetNumberOfWagons() {
        if (firstWagon != null){
            numberOfWagons = firstWagon.getNumberOfWagonsAttached() + 1;
        }
        else{
            numberOfWagons = 0;
        }
    }

    /**
     * Get the number of wagons on the train
     * @return number of wagons on train
     */
    public int getNumberOfWagons() {
        return numberOfWagons;
    }

    /**
     * If the wagon has no wagons return true
     * @return if train has one or more wagons
     */
    public boolean hasNoWagons() {
        return (firstWagon == null);
    }

    /**
     * Checks if the train is an passenger train
     * @return true if the first wagon is an instance of a passenger wagon
     */
    public boolean isPassengerTrain() {
        return firstWagon instanceof PassengerWagon;
    }

    /**
     * Checks if the train is an freight train
     * @return true if the first wagon is an instance of a freight train
     */
    public boolean isFreightTrain() {
        return firstWagon instanceof FreightWagon;
    }

    /**
     * Finds a wagon on the train by the id of the wagon
     * @param wagonId the id of the wagon you want the position of
     * @return the position of the wagon
     */
    public int getPositionOfWagon(int wagonId) {
        Wagon wagon = firstWagon;

        //If there is no wagon hooked on the train immediately return -1
        if (firstWagon == null) return -1;

        for (int wagonPos = 1; wagon != null; wagonPos++){

            if (wagon.getWagonId() == wagonId){
                //If wagon id corrosponds with current wagon return the wagon position of the wagon
                return wagonPos;
            }
            //Needed to iterrate over the wagons
            wagon = wagon.getNextWagon();
        }
        //Wagon was not found in loop so return -1
        return -1;
    }

    /**
     * Gets the wagon on a specific position
     * @param position the position of the wagon you would like to recievee
     * @return the position of the wagon on the train (-1 if wagon was not found)
     * @throws IndexOutOfBoundsException
     */
    public Wagon getWagonOnPosition(int position) throws IndexOutOfBoundsException {
        /* find the wagon on a given position on the train
         position of wagons start at 1 (firstWagon of train)
         use exceptions to handle a position that does not exist */

        Wagon wagon = firstWagon;
        int wagonPosition = 1;

        do {
            if (wagonPosition == position){
                return wagon;
            }
            else{
                wagonPosition++;
                wagon = wagon.getNextWagon();
            }
        } while (wagon.getNextWagon() != null);

       return null;
    }

    /**
     * Gets the total number of seats on a passanger train
     * @return the numer of seats on the train
     */
    public int getNumberOfSeats() {
        Wagon wagon = null;
        int amountOfSeats = 0;

        //if there's no wagons return 0
        if (firstWagon == null) return 0;
        do {
            if (wagon == null) wagon = firstWagon; else wagon = wagon.getNextWagon();
            if (wagon instanceof PassengerWagon){
                amountOfSeats += ((PassengerWagon) wagon).getNumberOfSeats();
            }
        } while (wagon.getNextWagon() != null);

        return amountOfSeats;
    }

    /**
     * Gets the total max weight if the freight train
     * @return the max weight of the train
     */
    public int getTotalMaxWeight() {
        Wagon wagon = firstWagon;
        int maxWeight = 0;

        do {
            if (wagon instanceof FreightWagon){
                maxWeight += ((FreightWagon) wagon).getMaxWeight();
            }
            else{
                wagon = wagon.getNextWagon();
            }
        } while (wagon.getNextWagon() != null);

        return maxWeight;

    }

    /**
     * Gets the engine of the train
     * @return the train engine
     */
    public Locomotive getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(engine.toString());
        Wagon next = this.getFirstWagon();
        while (next != null) {
            result.append(next.toString());
            next = next.getNextWagon();
        }
        result.append(String.format(" with %d wagons and %d seats from %s to %s", numberOfWagons, getNumberOfSeats(), origin, destination));
        return result.toString();
    }
}
