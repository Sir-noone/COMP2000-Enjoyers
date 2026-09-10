/**
 * Implements direct-contact disease transmission.
 *
 * For direct transmission to occur:
 * 1. The source must already be infected.
 * 2. The target must not already be infected.
 * 3. The source and target must be within the contact radius.
 * 4. The target becomes infected.
 *
 * This class implements TransmissionStrategy, allowing the Disease
 * class to use different transmission methods polymorphically.
 */
public class DirectTransmission implements TransmissionStrategy {

    // Number of cells away that is considered close enough
    // for direct transmission.
    private static final int CONTACT_RADIUS = 1;

    /*
     * Simplified project assumption used to convert R0 into
     * an approximate probability for one contact.
     *
     * Example:
     * R0 = 3.0
     * 3.0 / 10.0 = 0.30
     *
     * This is a modelling assumption for our simulation,
     * rather than a real epidemiological formula.
     */
    private static final double ASSUMED_CONTACTS_PER_PERIOD = 10.0;

    // Random number generator used to determine whether
    // the transmission attempt succeeds.
    //private final Random random = new Random();

    /**
     * Attempts to transmit a disease directly from the source
     * human to the target human.
     *
     * @param source  the infected human
     * @param target  the human who may become infected
     * @param disease the disease being transmitted
     * @return true if transmission succeeds, false otherwise
     */
    @Override
    public boolean transmit(Human source, Human target, Disease disease) {

         // Check for invalid arguments before attempting transmission.
         // This prevents a NullPointerException if any of the parameters are null.
         
        if (source == null || target == null || disease == null) {
            throw new IllegalArgumentException(
                    "Source, target, and disease cannot be null."
            );
        }

        // The source must already be infected.
        if (!source.isInfected()) {
            return false;
        }

        if (source instanceof Human sourceHuman && target instanceof Human targetHuman
                && !withinContactRadius(sourceHuman, targetHuman)) {
            return false;
        }
        // The target should not already be infected.
        if (target.isInfected()) {
            return false;
        }

        // The source and target must be close enough
        // for direct contact to occur.
        if (!withinContactRange(source, target)) {
            return false;
        }

        // Calculate the simplified probability of transmission.
        double probability = calculateTransmissionProbability(disease);

        /*
         * Generate a random value between 0.0 and 1.0.
         *
         * If the random value is lower than the calculated
         * probability, transmission succeeds.
         */
        //if (random.nextDouble() < probability) {

            /*
             * Use Human's infect() method rather than directly
             * modifying its infection state.
             *
             * This keeps responsibility for the Human's state
             * inside the Human class.
             */
            //target.infect();

           // return true;
        //}

        // A valid contact is infected immediately
        target.infect();
        return true;
    }

    private boolean withinContactRadius(Human source, Human target) {
        int columnDistance = Math.abs(source.loc.x - target.loc.x) / Cell.size;
        int rowDistance = Math.abs(source.loc.y - target.loc.y) / Cell.size;
        return Math.max(columnDistance, rowDistance) <= CONTACT_RADIUS;

    }
    /**
     * Determines whether two humans are close enough for
     * direct transmission.
     *
     * The Human class inherits its Cell location from Actor.
     * The current Cell class stores its position using x and y.
     *
     * @param source the source human
     * @param target the target human
     * @return true if the humans are within the contact radius
     */
    private boolean withinContactRange(Human source, Human target) {

        // Obtain the Cell occupied by each Human.
        Cell sourceCell = source.loc;
        Cell targetCell = target.loc;

        // A Human without a location cannot participate
        // in spatial transmission.
        if (sourceCell == null || targetCell == null) {
            return false;
        }

        /*
         * Convert the pixel positions of the cells into
         * grid distances.
         *
         * Cell.size represents the size of each grid cell.
         */
        int xDistance = Math.abs(sourceCell.x - targetCell.x) / Cell.size;
        int yDistance = Math.abs(sourceCell.y - targetCell.y) / Cell.size;

        /*
         * Use distance to determine whether the
         * two Humans are within the direct-contact radius.
         */
        int distance = xDistance + yDistance;

        return distance <= CONTACT_RADIUS;
    }

    /**
     * Converts the disease's R0 into a simplified probability
     * of transmission for one contact.
     *
     * R0 is not itself a probability, so it is divided by the
     * assumed number of contact opportunities during the
     * infectious period.
     *
     * @param disease the disease being transmitted
     * @return a probability between 0.0 and 1.0
     */
    private double calculateTransmissionProbability(Disease disease) {

        double baseProbability =
                disease.getR0() / ASSUMED_CONTACTS_PER_PERIOD;

        // Ensure the probability remains between 0.0 and 1.0.
        return Math.max(0.0, Math.min(1.0, baseProbability));
    }

}