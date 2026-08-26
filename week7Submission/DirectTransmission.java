import java.util.Random;

/**
 * Implements direct-contact disease tranmission.
 * 
 * This class will contain the rules used when one infected human comes into direct contact with another human.
 */

public class DirectTransmission implements TransmissionStrategy {

    // Random number generator used when determining whether a transmission attempt is successful.
    private final Random random = new Random();

    /**
     * Attempts to transmit a disease directly from the source human to the target human.
     * 
     * @param source the human who may transmit the disease
     * @param target the human who may recieve the disease
     * @param disease the disease being transmitted
     * @return true if tranmission occurs, false otherwise
     */

    @Override
    public boolean transmit(Infectable source, Infectable target, Disease disease) {

        // The source must already be infected.
        if (!source.isInfected()) {
            return false;
        }

        // The target should not already be infected.
        if (target.isInfected()) {
            return false;
        }

        /**
         * The Disease class now provides access to R0.
         * 
         * R0 is not itself a probability, so it should not simply be used directly as the chance of infection.
         * 
         * Our groups still needs to decide how R0 will be converted into simplified tranmission probability.
         */
        double r0 = disease.getR0();

        /**
         * TODO:
         * Apply our group's chosen formula to convert R0 into a probability of transmission.
         */

        /**
         * TODO:
         * Use the Human's immunity value when determining the final probability of transmission.
         * This will be implemented once a getter for immunity is available.
         */

        /**
         * TODO:
         * Use the Grid/Cell system to determine whether the source and target are close enough for direct contact.
         */

        /**
         * TODO:
         * Once the transmission probability has been established,
         * generate a random value and determine whether the transmission succeeds.
         */

        /**
         * Temporary return value.
         * 
         * Transmission cannot yet be fully calculated because,
         * the probability model and proximity checks have not yet been implemented.
         */
        return false;
    }
}