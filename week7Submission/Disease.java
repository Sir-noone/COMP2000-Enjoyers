public class Disease{
    private double infectionRate;
    private int incubationPeriod;
    private int recoveryPeriod;
    // private double mortalityRate;
    private TransmissionStrategy transmissionStrategy;

    public Disease(double infectionRate, int incubationPeriod, int recoveryPeriod, TransmissionStrategy transmissionStrategy) {

        this.infectionRate = infectionRate;
        this.incubationPeriod = incubationPeriod;
        this.recoveryPeriod = recoveryPeriod;
        // this.mortalityRate = mortalityRate;
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

    public double getInfectionRate() { // Returns the value of lethalityRate
        return infectionRate;
    } 
    // public double getMortalityRate() { // Returns the value of mortalityRate
    //     return mortalityRate;
    // }
    public TransmissionStrategy getTransmissionStrategy() { // Returns the transmissionStrategy
        return transmissionStrategy;
    }

    public int getIncubationPeriod(){  // Returns the value of incubationPeriod
        return incubationPeriod;
    }

    public int getRecoveryPeriod() { // Returns the value of recoveryPeriod
        return recoveryPeriod;
    }
    public void update(){
    
    }

}
