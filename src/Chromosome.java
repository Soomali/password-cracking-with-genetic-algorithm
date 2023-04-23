import java.util.Random;

public class Chromosome extends  PopulationEntity<Integer,String> {
    public static String[] characters = {
            "a", "b", "c", "ç", "d", "e", "f", "g", "ğ", "h", "ı", "i", "j", "k", "l", "m",
            "n", "o", "ö", "p", "q", "r", "s", "ş", "t", "u", "ü", "v", "w", "x", "y", "z",
            "A", "B", "C", "Ç", "D", "E", "F", "G", "Ğ", "H", "I", "İ", "J", "K", "L", "M",
            "N", "O", "Ö", "P", "Q", "R", "S", "Ş", "T", "U", "Ü", "V", "W", "X", "Y", "Z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "!", "'", "+", "^", "%", "&", "/", "(", ")", "=", "_", "-", "*", ",", ".", ";", ":",
            ">", "<", "|", "\"", "?", " ", "@"
    };
    private static final Random random = new Random();

    private String value;
    public Chromosome(String value){
        this.value = value;
    }
    public Chromosome(int length){
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length);
            sb.append(characters[index]);
        }
        this.value = sb.toString();
    }
    @Override
    public ICrossable cross(ICrossable other,int crossRate) {
        if(other instanceof Chromosome){
            String otherValue = ((Chromosome) other).value;
            String value = this.value;
            for(int i = 0; i< crossRate; i++){
                int changedIndex = random.nextInt(otherValue.length());

                value = value.substring(0,changedIndex) + otherValue.charAt(changedIndex) + value.substring(changedIndex + 1);
            }
            return new Chromosome(value);
        }
        return null;
    }

    @Override
    public int getFitnessScore(String environmentGoal) {
        int len = this.value.length();
        int total = 0;
        for(int i = 0; i < len; i++){
            if(value.charAt(i) == environmentGoal.charAt(i)){
                total++;
            }
        }
        return total;
    }


    @Override
    public void mutate(Integer mutationRate) {
        var changedLetters = mutationRate;
        if(mutationRate > value.length()){
            changedLetters = value.length();
        }
        for(int i = 0; i < changedLetters; i++){
            int letter = random.nextInt(value.length());

            value = value.substring(0,letter) + characters[random.nextInt(characters.length)] + value.substring(letter + 1);
        }
    }

    @Override
    String getValue() {
        return value;
    }
}
