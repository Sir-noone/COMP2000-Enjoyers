public class COVID extends Disease {
    
    public COVID(double lethalityRate, int incubationPeriod, int recoveryPeriod, double mortalityRate,
            TransmissionStrategy transmissionStrategy) {
        super(3.0, 5, 14, transmissionStrategy); 
        // transmissionStrategy will be airborne
        }

}
