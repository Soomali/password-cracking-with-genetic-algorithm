import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Population implements  IFittable<String> {

    ArrayList<PopulationEntity<Integer,String>> entities;
    int populationCount;
    int populationChange;
    public Population(ArrayList<PopulationEntity<Integer,String>> entities){
        this(entities,10,5);
    }
    public  Population(ArrayList<PopulationEntity<Integer,String>> entities,int populationCount,int populationChange){
        this.populationChange = populationChange;
        this.entities = entities;
        this.populationCount = populationCount;
    }
    public static final Random random = new Random();
    private boolean shouldMutate(int mutationFactor){
        return mutationFactor > random.nextInt(100) ;
    }
    public ArrayList<PopulationEntity<Integer,String>> generateNextGeneration(int mutationFactor,int mutationRate,int crossRate,String environmentGoal) {
        ArrayList<PopulationEntity<Integer,String>> fittest = filterFittest(environmentGoal);
        for(int i = 0; i < populationChange; i++){
            int crossIndex = random.nextInt(populationChange);
            PopulationEntity<Integer,String> entity = entities.get(i);
            PopulationEntity<Integer,String> crossEntity;
            if(crossIndex == i){
                if(crossIndex == populationCount - populationChange - 1){
                    crossEntity = entities.get(crossIndex - 2);
                }else {
                    crossEntity = entities.get(crossIndex + 1);
                }

            }else {
                crossEntity = entities.get(crossIndex);
            }
            var addedEntity = (PopulationEntity<Integer,String>) entity.cross(crossEntity,crossRate);
            if(shouldMutate(mutationFactor)){
                addedEntity.mutate(mutationRate);
            }
            fittest.add(addedEntity);
        }
        return fittest;
    }
    public PopulationEntity<Integer,String> getFittestEntity(String environmentGoal){

        sort(this.entities,environmentGoal);
        return this.entities.get(0);
    }
    private void sort(ArrayList<PopulationEntity<Integer,String>> array,String environmentGoal){
        Comparator<PopulationEntity<Integer,String>> comparator = new Comparator<PopulationEntity<Integer,String>>() {
            @Override
            public int compare(PopulationEntity p1, PopulationEntity p2) {
                return Integer.compare(p2.getFitnessScore(environmentGoal),p1.getFitnessScore(environmentGoal));
            }
        };
        array.sort(comparator);
    }
    private ArrayList<PopulationEntity<Integer,String>> filterFittest(String environmentGoal){
        sort(this.entities,environmentGoal);
        return new ArrayList<>(this.entities.subList(0,populationCount - populationChange ));


    }
    @Override
    public int getFitnessScore(String environmentGoal) {
        var total = 0;

        for(PopulationEntity<Integer,String> i : this.entities){
            total += i.getFitnessScore(environmentGoal);
        }
        return total;
    }
}
