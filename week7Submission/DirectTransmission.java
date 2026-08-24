/**
 * Implements direct-contact disease tranmission.
 * 
 * This class will contain the rules used when one infected human comes into direct contact with another human.
 */

public class DirectTransmission implements TransmissionStrategy {

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
         * TODO:
         * Use the Disease class once it is implemented to obtain the tranmission probability/R0 information.
         */

        /**
         * TODO:
         * Use the Human's immunity value when determining the final probability of transmission.
         */

        /**
         * TODO:
         * Use the Grid/Cell system to determine whether the source and target are close enough for direct contact.
         */

        /**
         * TODO:
         * Use a random value to determine whether the tranmission attempt succeeds.
         */
        return false;
    }
}