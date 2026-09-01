import Visuals.*;
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
     * This is a modelling assumption for our simulation rather
     * than a real epidemiological formula.
     */
    private static final double ASSUMED_CONTACTS_PER_PERIOD = 10.0;

    // Generates the random value used to determine whether
    // an attempted transmission succeeds.
    private final Random random = new Random();

    // The Grid used to determine whether two cells are
    // close enough for direct contact.
    private final Grid grid;

    /**
     * Creates a DirectTransmission strategy using the specified grid.
     *
     * @param grid the grid containing the humans
     */
    public DirectTransmission(Grid grid) {
        this.grid = grid;
    }

    /**
     * Attempts to transmit a disease directly from the source
     * to the target.
     *
     * @param source  the entity that may transmit the disease
     * @param target  the entity that may become infected
     * @param disease the disease being transmitted
     * @return true if transmission succeeds, false otherwise
     */
    @Override
    public boolean transmit(Infectable source, Infectable target, Disease disease) {

        // The source must already be infected.
        if (!source.isInfected()) {
            return false;
        }

        // Do not transmit to a target that is already infected.
        if (target.isInfected()) {
            return false;
        }

        /*
         * TODO:
         * Obtain the Cell occupied by the source and target.
         *
         * Human currently inherits a 'loc' field from Actor,
         * but Infectable does not expose that location.
         */

        /*
         * TODO:
         * Once the Human/Cell relationship is available,
         * use grid.getRadius(...) to check whether the source
         * and target are close enough for direct contact.
         */

        // Convert the disease's R0 into a simplified
        // per-contact transmission probability.
        double probability = calculateTransmissionProbability(disease);

        /*
         * Generate a random number between 0.0 and 1.0.
         *
         * Transmission succeeds when that random value is
         * below the calculated probability.
         */
        if (random.nextDouble() < probability) {

            // Tell the target that it has been infected.
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