public class Disease{
    private double lethalityRate;
    int incubationPeriod;
    int recoveryPeriod;
    private double mortalityRate;
    private TransmissionStrategy transmissionStrategy;

    public Disease(double lethalityRate, int incubationPeriod, int recoveryPeriod, double mortalityRate, TransmissionStrategy transmissionStrategy) {

        this.lethalityRate = lethalityRate;
        this.incubationPeriod = incubationPeriod;
        this.recoveryPeriod = recoveryPeriod;
        this.mortalityRate = mortalityRate;
        this.transmissionStrategy = transmissionStrategy;
    } 
    public boolean infect(Human source, Human target) { 
        return transmissionStrategy.transmit(source, target, this);
    } 
    // the transmittionStrategy checks wether the disease is successfully transmitted, 
    // then if it is, uses "this" to assign current values of disease

    // public void Infect(){
    //     // Disease will need to have access to the grid array index to infect but there is already an infect function in Human.java
    // }

    public double getlethalityRate() { // Returns the value of lethalityRate
        return lethalityRate;
    } 
    public double getMortalityRate() { // Returns the value of mortalityRate
        return mortalityRate;
    }
    public TransmissionStrategy getTransmissionStrategy() { // Returns the transmissionStrategy
        return transmissionStrategy;
    }
    
    public void update(){
    
    }

}
