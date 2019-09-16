package model;

public class PassengerWagon extends Wagon {

    int numberOfSeats;

    /**
     * Constructor passenger train
     * @param wagonId the id of the wagon
     * @param numberOfSeats the amount of seats of the wagon
     */
    public PassengerWagon(int wagonId, int numberOfSeats) {
        super(wagonId);
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Gets the number of seats
     * @return the number of seats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
