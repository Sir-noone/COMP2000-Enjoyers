
public enum AgeGroup {
    
    CHILD(0.8), // min 1.1 max 1.4
    YOUTH(0.2), // min 0.5 max 0.8
    ADULT(0.5), // min 0.8 max 1.1
    ELDERLY(0.7); // min 1.0 max 1.3

    private final double ageValue;

    AgeGroup(double ageValue) {
        this.ageValue = ageValue;
    }

    public double getAgeValue() {
        return ageValue;
    }
}