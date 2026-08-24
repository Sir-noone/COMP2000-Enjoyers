/**
 * Defines the general behaviour for a tranmission method.
 */

public interface TransmissionStrategy {

/**
 * Attempts to transmit the disease from one infectable entity (human) to another.
 * 
 * @param source the human who may transmit the disease
 * @param target the human who may receive the disease
 * @param disease the disease being transmitted
 * @return true if tranmission succeeds, false otherwise
 */

    boolean transmit(Infectable source, Infectable target, Disease disease);

}