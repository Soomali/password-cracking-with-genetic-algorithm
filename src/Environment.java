public class Environment {
    Population currentPopulation;
    String environmentGoal;
    int mutationFactor;
    int crossRate;
    int mutationRate;
    public Environment(Population currentPopulation, String environmentGoal,int mutationFactor, int crossRate, int mutationRate){
        this.currentPopulation = currentPopulation;
        this.environmentGoal = environmentGoal;
        this.mutationFactor = mutationFactor;
        this.crossRate = crossRate;
        this.mutationRate = mutationRate;
    }
    public int getFitnessScore(){
        return currentPopulation.getFitnessScore(environmentGoal);
    }
    public boolean isEnvironmentGoalReached(){
        return getFittestEntity().getValue().equals(environmentGoal);
    }
    public PopulationEntity<Integer,String> getFittestEntity(){
        return currentPopulation.getFittestEntity(environmentGoal);
    }
    public void passGeneration(){
        currentPopulation = new Population(
                currentPopulation.generateNextGeneration(mutationFactor,mutationRate,crossRate,environmentGoal),
                currentPopulation.populationCount,
                currentPopulation.populationChange
        );
    }

}
