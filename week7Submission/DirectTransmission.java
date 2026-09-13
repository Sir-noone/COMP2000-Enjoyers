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

    // Random number generator used to determine whether
    // a transmission attempt succeeds.
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

        /**
         * Validate the arguments before attempting transmission.
         * An invalid argument indicates incorrect use of the method,
         * so an IllegalArgumentException is thrown.
         */
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

        /**
         * Calculate the probability of transmission using the
         * disease's infection rate and the target's health.
         */
        double probability = calculateTransmissionProbability(disease);

        /*
         * Generate a random value between 0.0 and 1.0.
         *
         * Transmission succeeds when the random value is lower
         * than the calculated probability.
         */
        if (Math.random() < (disease.getInfectionRate() * target.health())) {
            /*
             * Use Human's infect(Disease) method rather than directly
             * modifying the infected field.
             *
             * This preserves encapsulation by allowing Human to
             * control how its infection state changes.
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
     * Cell stores its position using x and y coordinates.
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

        /**
         * Within CONTACT_RADIUS = 1, the source can transmit to
         * a human in the same cell or one directly adjacent cell.
         */
        int distance = columnDistance + rowDistance;

        return distance <= CONTACT_RADIUS;
    }

    /**
     * Calculates the simplified probability of transmission.
     * 
     * The current project model uses the disease's infection rate
     * and the target Human's health to determine the probability.
     * The result is constrained to the valid proability range
     * from 0.0 to 1.0.
     *
     * @param disease the disease being transmitted
     * @return a probability between 0.0 and 1.0
     */
    private double calculateTransmissionProbability(Disease disease) {

        double baseProbability = disease.getInfectionRate();
        return baseProbability;
    }
}