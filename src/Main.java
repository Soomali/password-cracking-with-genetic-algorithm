import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<PopulationEntity<Integer,String>> chromosomes = new ArrayList<PopulationEntity<Integer,String>>();
        String environmentGoal = "ChatGPT and GPT-4";
        int populationCount = 100;
        int populationChange = 85;
        int mutationRate = 2;
        int mutationFactor = 30;
        int crossRate = 6;
        int generationCount = 1000000;
        for(int i = 0; i <populationCount;i++){
            chromosomes.add(new Chromosome(environmentGoal.length()));
        }

        Environment environment = new Environment(
                new Population(chromosomes,populationCount,populationChange),
                environmentGoal,
                mutationFactor,
                mutationRate,
                crossRate
        );
        for(int i = 0; i <generationCount;i++){
            System.out.println("current generation: " + i);
            System.out.println("fittest entity on current environment: " + environment.getFittestEntity().getFitnessScore(environmentGoal));
            System.out.println("fittest entity on current environment: " + environment.getFittestEntity().getValue());
            System.out.println("average fitness of current population: " + environment.getFitnessScore() / populationCount);
            System.out.println("is environment goal reached: " + environment.isEnvironmentGoalReached());
            System.out.println("------------------------------------------------------------------");
            if(environment.isEnvironmentGoalReached()){
                break;
            }
            environment.passGeneration();
        }

    }
}