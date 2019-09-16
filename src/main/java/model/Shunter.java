package model;

public class Shunter {

    /**
     * Checks if the train is capable of hooking the wagon to the train
     * @param train the train to check
     * @param wagon the wagon that should be hooked on the train
     * @return true if the train is capable of hooking the wagon
     */
    private static boolean isSuitableWagon(Train train, Wagon wagon) {
        return train.getFirstWagon() == null || train.getFirstWagon().getClass().equals(wagon.getClass());
    }

    /**
     * Checks if the two wagons can be hooked to eachother
     * @param one the first wagon
     * @param two the second wagon
     * @return true if the wagons can be hooked to eachother || are the same childclass
     */
    private static boolean isSuitableWagon(Wagon one, Wagon two) {
        return (one.getClass().equals(two.getClass()));
    }

    /**
     * Checks if the train has place for one or multiple wagons
     * @param train train to check
     * @param wagon wagon to check
     * @return true if the wagon can be hooked on the train
     */
    private static boolean hasPlaceForWagons(Train train, Wagon wagon) {
        return train.getEngine().getMaxWagons() >= (train.getNumberOfWagons() + wagon.getNumberOfWagonsAttached() + 1);
    }

    /**
     * Checks if the train has place for one wagon
     * @param train the train to check
     * @param wagon the wagon to check
     * @return true if the train has place for the wagon
     */
    private static boolean hasPlaceForOneWagon(Train train, Wagon wagon) {
        return train.getEngine().getMaxWagons() > train.getNumberOfWagons();
    }

    /**
     * Tries to hook a wagon on the train rear
     * @param train the train the wagon should be hooked on
     * @param wagon the wagon to hook on the train
     * @return true if the hook went succesfull or false if something failed
     */
    public static boolean hookWagonOnTrainRear(Train train, Wagon wagon) {
         /* check if Locomotive can pull new number of Wagons
         check if wagon is correct kind of wagon for train
         find the last wagon of the train
         hook the wagon on the last wagon (see Wagon class)
         adjust number of Wagons of Train */

         if (isSuitableWagon(train,wagon)){
             if (hasPlaceForWagons(train, wagon)){
                 if (train.getFirstWagon() == null){
                     train.setFirstWagon(wagon);
                     train.resetNumberOfWagons();
                     return true;
                 }
                 else{
                     Wagon firsWagon =  train.getFirstWagon();
                     Wagon lastWagonOnTrain = firsWagon.getLastWagonAttached();
                     lastWagonOnTrain.setNextWagon(wagon);
                     train.resetNumberOfWagons();
                     return true;
                 }
             }
             else{
                 return false;
             }
         }
         return false;
    }

    /**
     * Tries to hook a wagon on the train front
     * @param train the train the wagon should be hooked on
     * @param wagon the wagon that will be hooked on the train
     * @return true if the wagon hook went succesfull
     */
    public static boolean hookWagonOnTrainFront(Train train, Wagon wagon) {
        /* check if Locomotive can pull new number of Wagons
         check if wagon is correct kind of wagon for train
         if Train has no wagons hookOn to Locomotive
         if Train has wagons hookOn to Locomotive and hook firstWagon of Train to lastWagon attached to the wagon
         adjust number of Wagons of Train */

        if (isSuitableWagon(train,wagon)){
            if (hasPlaceForWagons(train, wagon)){
                if (train.hasNoWagons()){
                    train.setFirstWagon(wagon);
                    return true;
                }
                else{
                    Wagon previousWagon = train.getFirstWagon();
                    wagon.getLastWagonAttached().setNextWagon(previousWagon);
                    train.setFirstWagon(wagon);
                    train.resetNumberOfWagons();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Tries to hook a wagon on another wagon
     * @param first the wagon where the second wageon should be hooked on
     * @param second the wagon that hooks on the first wagon
     * @return if the hooking went succesfull
     */
    public static boolean hookWagonOnWagon(Wagon first, Wagon second) {
        /* check if wagons are of the same kind (suitable)
        * if so make second wagon next wagon of first */

        if (isSuitableWagon(first,second)){
            first.getLastWagonAttached().setNextWagon(second);
        }
        return false;
    }

    /**
     * Tries to detach a certain wagon from a train
     * @param train the train the wagon should be detached off
     * @param wagon the wagon that should be detached
     * @return true if the detachment went succesfull
     */
    public static boolean detachAllFromTrain(Train train, Wagon wagon) {
        /* check if wagon is on the train
         detach the wagon from its previousWagon with all its successor
         recalculate the number of wagons of the train */

        if (train.getPositionOfWagon(wagon.getWagonId()) != -1){
            //Wagon is on train
            wagon.getPreviousWagon().setNextWagon(null);
            train.resetNumberOfWagons();
            return true;
        }
        return false;
    }

    /**
     * Tries to detach ONE wagon of a certain train
     * @param train the train where the wagon should be detached off
     * @param wagon the wagon that will be detached
     * @return true if the detaching went succesfull
     */
    public static boolean detachOneWagon(Train train, Wagon wagon) {
        /* check if wagon is on the train
         detach the wagon from its previousWagon and hook the nextWagon to the previousWagon
         so, in fact remove the one wagon from the train
        */

        if (train.getPositionOfWagon(wagon.getWagonId()) != -1){
            //Wagon is on train

            Wagon previousWagon = wagon.getPreviousWagon();
            Wagon nextWagon = wagon.getNextWagon();
            previousWagon.setNextWagon(nextWagon);
            nextWagon.setPreviousWagon(previousWagon);

            wagon.setNextWagon(null);
            wagon.setPreviousWagon(null);

            train.resetNumberOfWagons();
            return true;
        }
         return false;
    }

    /**
     * Move all wagon from one train to another train
     * @param from the train where the wagon(s) should be detached from
     * @param to the train where the detached wagon(s) should move to
     * @param wagon the wagon that should be moved from one train to another
     * @return true if the moving of the wagon wnet succesfull
     */
    public static boolean moveAllFromTrain(Train from, Train to, Wagon wagon) {
        /* check if wagon is on train from
         check if wagon is correct for train and if engine can handle new wagons
         detach Wagon and all successors from train from and hook at the rear of train to
         remember to adjust number of wagons of trains */

        if (isSuitableWagon(to, wagon)){
            //If wagon is suitable for relocation
            if (hasPlaceForWagons(to, wagon)){
                //If the train has place for the new wagon(s)
                if (detachAllFromTrain(from, wagon)){
                    //If the detatch went succesfull from first train

                    //return if the wagon is succesfully hooked to next train
                    return hookWagonOnTrainRear(to, wagon);
                }
            }
        }
        //return false if any of the above statements failed
        return false;
    }

    /**
     * Moves ONE wagon from a train to another train
     * @param from the train where the wagon should be moved from
     * @param to the train where the wagon should be moved to
     * @param wagon the wagon that will be moved
     * @return true if the wagon moved succesfull
     */
    public static boolean moveOneWagon(Train from, Train to, Wagon wagon) {
        // detach only one wagon from train from and hook on rear of train to
        // do necessary checks and adjustments to trains and wagon
        if (isSuitableWagon(to, wagon)){
            //If wagon is suitable for relocation
            if (hasPlaceForOneWagon(to, wagon)){
                //If the train has place for the new wagon(s)
                if (detachOneWagon(from, wagon)){
                    //If the detatch went succesfull from first train

                    //return if the wagon is succesfully hooked to next train
                    return hookWagonOnTrainRear(to, wagon);
                }
            }
        }
        //return false if any of the above statements failed
        return false;
    }
}
