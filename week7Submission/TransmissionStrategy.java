/**
 * Defines the general behaviour for a disease tranmission strategy.
 * 
 * Different tranmission types can implement this interface using their own rules
 * for determining whether a disease is successfully tranmitted between two infectable entities.
 */
public interface TransmissionStrategy {

/**
 * Attempts to transmit the disease from one infectable entity (human) to another.
 * 
 * The tranmission strategy determines whether the source is capable
 * of tranmissitting the disease and whether the target becomes infected.
 * 
 * @param source the human who may transmit the disease
 * @param target the human who may receive the disease
 * @param disease the disease being transmitted
 * @return true if tranmission succeeds, false otherwise
 */
    boolean transmit(Infectable source, Infectable target, Disease disease);
}