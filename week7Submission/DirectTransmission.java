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

    // Number of cells away that is considered close enough for direct transmission.
    private static final int CONTACT_RADIUS = 1;

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

         // Check for invalid arguments before attempting transmission.
         // This prevents a NullPointerException if any of the parameters are null.
         
        if (source == null || target == null || disease == null) {
            throw new IllegalArgumentException("Source, target, and disease cannot be null.");
        }

        // The source must already be infected & target must not already be infected for transmission to occur.
        if (!source.isInfected() || target.isInfected()) {
            return false;
        }

        if (source instanceof Human sourceHuman && target instanceof Human targetHuman
                && !withinContactRadius(sourceHuman, targetHuman)) {
            return false;
        }

        // A valid contact is infected immediately
        target.infect();
        return true;
    }

    private boolean withinContactRadius(Human source, Human target) {
        int columnDistance = Math.abs(source.loc.x - target.loc.x) / Cell.size;
        int rowDistance = Math.abs(source.loc.y - target.loc.y) / Cell.size;
        return Math.max(columnDistance, rowDistance) <= CONTACT_RADIUS;
    }

}