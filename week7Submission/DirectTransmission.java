import java.util.Random;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.IntStream;

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
     * probability for a particular disease and target human.
     *
     * ToDoubleBiFunction accepts two arguments and returns a double.
     * Here, the arguments are a Disease and a target Human, and the
     * result is the calculated transmission probability.
     */
    private final ToDoubleBiFunction<Disease, Human> probabilityCalculator =
            (disease, target) ->
                    disease.getInfectionRate() * target.health();

    /**
     * Attempts to transmit a disease directly from the source
     * human to the target human.
     *
     * @param source the human who may transmit the disease
     * @param target the human who may receive the disease
     * @param disease the disease being transmitted
     * @return true if transmission succeeds, false otherwise
     * @throws IllegalArgumentException if any argument is null
     */
    @Override
    public boolean transmit(Human source, Human target, Disease disease) {

        // Validate the arguments before attempting transmission.
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

        // Calculate the probability using the disease's infection
        // rate and the target human's health value.
        double probability =
                calculateTransmissionProbability(disease, target);

        // Generate a random value between 0.0 (inclusive)
        // and 1.0 (exclusive). Transmission succeeds when the
        // random value is lower than the calculated probability.
        if (random.nextDouble() < probability) {

            // Use Human's infect method rather than directly changing
            // its infection state, preserving encapsulation.
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
     * The distance is measured using Manhattan distance, which
     * adds the horizontal and vertical grid-cell distances.
     * Diagonally adjacent cells are therefore not considered
     * within the contact radius of one.
     *
     * @param source the human who may transmit the disease
     * @param target the human who may receive the disease
     * @return true if the humans are within the contact radius
     */
    private boolean withinContactRange(Human source, Human target) {

        // Obtain the cells occupied by each human.
        Cell sourceCell = source.loc;
        Cell targetCell = target.loc;

        // A human without a location cannot participate
        // in spatial transmission.
        if (sourceCell == null || targetCell == null) {
            return false;
        }

        // Convert pixel-coordinate differences into grid-cell
        // distances. This assumes the cells are aligned to the grid.
        int columnDistance =
                Math.abs(sourceCell.x - targetCell.x) / Cell.size;

        int rowDistance =
                Math.abs(sourceCell.y - targetCell.y) / Cell.size;

        // Use an IntStream to sum the horizontal and vertical
        // distances. Ordinary addition would be simpler for only
        // two values, but this demonstrates a stream operation.
        int distance = IntStream
                .of(columnDistance, rowDistance)
                .sum();

        return distance <= CONTACT_RADIUS;
    }

    /**
     * Calculates the probability of transmission.
     *
     * The probability rule is provided by the probabilityCalculator
     * lambda, keeping the calculation separate from the main
     * transmission procedure.
     *
     * The result is clamped between 0.0 and 1.0 so that it remains
     * within the valid probability range.
     *
     * @param disease the disease being transmitted
     * @param target the human who may receive the disease
     * @return the calculated transmission probability
     */
    private double calculateTransmissionProbability(
            Disease disease, Human target) {

        double probability =
                probabilityCalculator.applyAsDouble(disease, target);

        // Ensure the probability remains between 0.0 and 1.0.
        return Math.max(0.0, Math.min(1.0, probability));
    }
}
