public interface PriorityQueue<P extends Comparable<? super P>, V> {
    void insert(P priority, V value);
    V extract();
    int size();
}
