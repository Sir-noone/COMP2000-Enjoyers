package Visuals;
enum AgeGroup {
    
    CHILD(0.1),
    YOUTH(-0.1),
    ADULT(-0.05),
    ELDERLY(0.05);

    private final double ageValue;

    AgeGroup(double ageValue) {
        this.ageValue = ageValue;
    }

    public double getAgeValue() {
        return ageValue;
    }
}