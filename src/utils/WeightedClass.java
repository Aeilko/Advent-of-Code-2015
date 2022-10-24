package utils;

public class WeightedClass<E> implements Comparable<WeightedClass> {
    public E object;
    public int weight;

    public WeightedClass(E obj, int w){
        this.object = obj;
        this.weight = w;
    }

    @Override
    public int compareTo(WeightedClass o) {
        if(this.weight < o.weight)
            return -1;
        else if(this.weight > o.weight)
            return 1;
        else
            return 0;
    }
}
