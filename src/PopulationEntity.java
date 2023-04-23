public abstract class PopulationEntity<T,V> implements IMutable<T>,IFittable<String>,ICrossable,Cloneable {
    abstract V getValue();
    @Override
    public PopulationEntity<T,V> clone() {
        try {
            return (PopulationEntity<T,V>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
