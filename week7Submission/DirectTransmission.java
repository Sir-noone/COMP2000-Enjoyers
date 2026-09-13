import java.util.Random;

/**
 * Implements direct-contact disease transmission.
 *
 * For direct transmission to occur:
 * 1. The source must already be infected.
 * 2. The target must not already be infected.
 * 3. The source and target must be within the contact radius.
 * 4. The transmission probability check must succeed.
 *
 * This class implements TransmissionStrategy, allowing the Disease
 * class to use different transmission methods polymorphically.
 */
public class DirectTransmission implements TransmissionStrategy {

    // Number of grid cells away that is considered close enough
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
    private final Random random = new Random();

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

        // Prevent invalid objects from being processed.
        if (source == null || target == null || disease == null) {
            throw new IllegalArgumentException(
                    "Source, target, and disease cannot be null."
            );
        }

        // The source must already be infected.
        if (!source.isInfected()) {
            return false;
        }

        // The target must not already be infected.
        if (target.isInfected()) {
            return false;
        }

        // The two humans must be close enough for direct contact.
        if (!withinContactRange(source, target)) {
            return false;
        }

        // Calculate the simplified transmission probability.
        double probability = calculateTransmissionProbability(disease);

        /*
         * Generate a random value between 0.0 and 1.0.
         *
         * If the random value is below the calculated probability,
         * transmission succeeds.
         */
        if (random.nextDouble() < probability) {

            /*
             * Use the Human's infect() method instead of directly
             * changing the infected field.
             *
             * This preserves encapsulation by allowing Human
             * to control its own infection state.
             */
            target.infect(disease);

            return true;
        }

        return false;
    }

    /**
     * Determines whether two humans are close enough for
     * direct transmission.
     *
     * Human inherits its Cell location from Actor.
     * Cell stores its grid position using x and y coordinates.
     *
     * @param source the source human
     * @param target the target human
     * @return true if the humans are within the contact radius
     */
    private boolean withinContactRange(Human source, Human target) {

        // Obtain the Cells occupied by each Human.
        Cell sourceCell = source.loc;
        Cell targetCell = target.loc;

        // A Human without a location cannot participate
        // in spatial transmission.
        if (sourceCell == null || targetCell == null) {
            return false;
        }

        /*
         * Convert the pixel distance between the Cells into
         * a distance measured in grid cells.
         *
         * Cell.size is currently 20.
         */
        int columnDistance =
                Math.abs(sourceCell.x - targetCell.x) / Cell.size;

        int rowDistance =
                Math.abs(sourceCell.y - targetCell.y) / Cell.size;

        /*
         * Use Manhattan distance:
         *
         * distance = horizontal distance + vertical distance
         */
        int distance = columnDistance + rowDistance;

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
                disease.getlethalityRate() / ASSUMED_CONTACTS_PER_PERIOD;

        // Ensure the probability remains between 0.0 and 1.0.
        return Math.max(0.0, Math.min(1.0, baseProbability));
    }
}