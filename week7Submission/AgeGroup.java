enum AgeGroup {
    
    CHILD(0.1),
    YOUTH(-0.2),
    ADULT(-0.3),
    ELDERLY(0.1);

    private final double ageValue;

    AgeGroup(double ageValue) {
        this.ageValue = ageValue;
    }

    public double getAgeValue() {
        return ageValue;
    }
}