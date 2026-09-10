/**
 * Defines the general behaviour for a disease tranmission strategy.
 * 
 * Different tranmission types can implement this interface using different rules
 * for determining whether a disease is successfully tranmitted from one human to another.
 */
public interface TransmissionStrategy {

/**
 * Attempts to transmit the disease from one human to another.
 * 
 * @param source the human who may transmit the disease
 * @param target the human who may receive the disease
 * @param disease the disease being transmitted
 * @return true if tranmission succeeds, false otherwise
 */
    boolean transmit(Human source, Human target, Disease disease);
}