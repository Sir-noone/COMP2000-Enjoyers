import java.util.Random;
import java.util.function.ToDoubleBiFunction;

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
     * Lambda expression used to calculate the transmission
     * probability for a particular disease and target Human.
     * 
     * ToDoubleBiFunction takes two inputs and returns a double.
     * Here, the inputs are Disease and target Human,
     * and the result is the probability of transmission.
     */
    private final ToDoubleBiFunction<Disease, Human> probabilityCalculator = 
        (disease, target) ->
            disease.getInfectionRate() * target.health();

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
         * 
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
        double probability =
            calculateTransmissionProbability(disease, target);

        /*
         * Generate a random value between 0.0 and 1.0.
         *
         * Transmission succeeds when the random value is lower
         * than the calculated probability.
         */
        if (random.nextDouble() < probability) {

            /*
             * Use Human's infect(Disease) method rather than
             * directly modifying the infected field.
             *
             * This preserves encapsulation by allowing Human to
             * control how its infection state.
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
     * The probability calculation is provided by a lambda expression
     * stored in probabilityCalculator. This separates the probability
     * rule from the main tranmission process.
     *
     * @param disease the disease being transmitted
     * @param target the target human
     * @return a probability between 0.0 and 1.0
     */
    private double calculateTransmissionProbability(
        Disease disease, Human target) {

        double probability = 
            probabilityCalculator.applyAsDouble(disease, target);

        // Ensure the probability is remains within the valid
        // probability range of 0.0 to 1.0.
        return Math.max(0.0, Math.min(1.0, probability));
    }
}