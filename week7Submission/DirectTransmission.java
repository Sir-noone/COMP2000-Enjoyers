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

    // Number of cells away that is considered close enough for direct transmission.
    private static final int CONTACT_RADIUS = 1;

    /*
     * Simplified project assumption used to convert R0 into
     * an approximate probability for one contact.
     *
     * Example:
     * R0 = 3.0, 3.0 / 10.0 = 0.30
     *
     * This is a modelling assumption for our simulation rather
     * than a real epidemiological formula.
     */
    private static final double ASSUMED_CONTACTS_PER_PERIOD = 10.0;

    // Generates the random value used to determine whether
    // an attempted transmission succeeds.
    private final Random random = new Random();

    /**
     * Attempts to transmit a disease directly from the source to the target.
     *
     * @param source  the entity that may transmit the disease
     * @param target  the entity that may become infected
     * @param disease the disease being transmitted
     * @return true if transmission succeeds, false otherwise
     */
    @Override
    public boolean transmit(Infectable source, Infectable target, Disease disease) {

        /**
         * Check for invalid arguments before attempting transmission.
         * 
         * This prevents a NullPointerException if any of the parameters are null.
         */
        if (source == null || target == null || disease == null) {
            throw new IllegalArgumentException("Source, target, and disease cannot be null.");
        }

        // The source must already be infected & target must not already be infected for transmission to occur.
        if (!source.isInfected() || target.isInfected()) {
            return false;
        }

        /**
         * TODO:
         * Once the Human/Grid relationship is available,
         * check whether the source and target are within the CONTACT_RADIUS.
         * 
         * The Grid class already provides getRadius(), but Human/Cell
         * location information is not currently exposed through the necessary classes and interfaces.
         */

        // Calculate the simplified probability of transmission.
        double probability = calculateTransmissionProbability(disease);

        /*
         * Generate a random number between 0.0 and 1.0.
         *
         * Transmission succeeds when that random value is
         * below the calculated probability.
         */
        if (random.nextDouble() < probability) {

            /**
             * Use the Infectable interface rather than directly
             * modifying the target's fields.
             * 
             * This demonstrates encapsulation because the target
             * controls how its infected state is changed.
             */
            target.infect();

            return true;
        }

        return false;
    }

    /**
     * Converts R0 into a simplified probability of transmission
     * for a single contact.
     *
     * @param disease the disease being transmitted
     * @return a probability between 0.0 and 1.0
     */
    private double calculateTransmissionProbability(Disease disease) {

        double baseProbability =
                disease.getR0() / ASSUMED_CONTACTS_PER_PERIOD;

        // Ensure the probability remains within the valid
        // probability range of 0.0 to 1.0.
        return Math.max(0.0, Math.min(1.0, baseProbability));
    }
}