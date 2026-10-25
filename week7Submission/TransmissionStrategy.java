/**
 * Defines the behaviour of a disease transmission strategy.
 *
 * Different transmission strategies can implement this interface
 * using their own rules to determine whether a disease is
 * successfully transmitted from one human to another.
 *
 * The @FunctionalInterface annotation indicates that this interface
 * has exactly one abstract method and can therefore be implemented
 * using a lambda expression when appropriate.
 */
@FunctionalInterface
public interface TransmissionStrategy {

    /**
     * Attempts to transmit a disease from one human to another.
     *
     * @param source the human who may transmit the disease
     * @param target the human who may receive the disease
     * @param disease the disease being transmitted
     * @return true if transmission succeeds, false otherwise
     */
    boolean transmit(Human source, Human target, Disease disease);
}