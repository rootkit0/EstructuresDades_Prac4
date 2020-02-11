/**
 *
 * @author xbp1
 */
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HeapQueue<P extends Comparable<? super P>, V> implements PriorityQueue<P, V> {
    private final ArrayList<Pair<P, V>> pairs = new ArrayList<>();

    @Override
    public void insert(P priority, V value) {
        //Creem una parella amb els dos paràmetres
        Pair parella = new Pair(priority, value);
        //Afegim la parella al final de l'ArrayList
        pairs.add(parella);
        //Des de l'última posició anem recorrent les prioritats paternes i ordenant-les
        int pos = size()-1;
        while(pos > 0 && pairs.get(pos).compareTo(pairs.get(parent(pos))) > 0) {
            swap(pairs, pos, parent(pos));
            pos = parent(pos);
        }
    }

    @Override
    public V extract() throws NoSuchElementException {
        if(pairs.size() <= 0) {
            throw new NoSuchElementException();
        }
        else {
            //Guardem a maxValue el valor de la parella amb màxima prioritat
            V maxValue = pairs.get(0).value;
            //Posem el parell amb prioritat més baixa al primer lloc
            pairs.set(0, pairs.get(size()-1));
            //Eliminem l'últim parell
            pairs.remove(size()-1);
            //Reordenem l'ArrayList
            reordenar(pairs, 0);
            return maxValue;
        }
    }
    
    private <E extends Comparable<E>> void reordenar(ArrayList<E> arr, int index) {
	int left = left(index); //Index del fill esquerre del paràmetre que l'hi passem
	int right = right(index); //Index del fill dret del paràmetre que l'hi passem
	int min = 0;
        
        //Si la prioritat de la parella en posició index és inferior al seu fill esquerre
	if (hasLeft(index) && arr.get(left).compareTo(arr.get(index)) > 0) {
            min = left;
        }
        //Si la prioritat de la parella en posició index és inferior al seu fill dret
        else if (hasRight(index) && arr.get(right).compareTo(arr.get(min)) > 0) {
            min = right;
        }
        else {
            min = index;
        }
        
	if (min != index) {
            //Intercanviem la parella el fill de la qual hagi complert una de les condicions anteriors
            swap(arr, index, min);
            //Reordenem l'ArrayList per tal de reestablir l'ordre segons les prioritats
            //Tornant a cridar a la mateixa funció de forma recursiva
            reordenar(arr, min);
	}
    } 

    @Override
    public int size() {
        return pairs.size();
    }
    
    private static class Pair<P extends Comparable<? super P>, V> implements Comparable<Pair<P, V>> {
        private final P priority; 
        private final V value;
        //Constructor de la classe Pair
        public Pair(P priority, V value) { 
            this.priority = priority;
            this.value = value;    
        }
        //Funció per a comparar les prioritats de dos parelles
        @Override
        public int compareTo(Pair<P, V> o) {
            if(priority.compareTo(o.priority) < 0) {
                return -1;
            }
            else if(priority.compareTo(o.priority) > 0) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }
    //Funció swap per a intercanviar dos parelles de l'ArrayList
    private static <E> void swap(ArrayList<E> arr, int index1, int index2) {
	E tmp = arr.get(index1);
	arr.set(index1, arr.get(index2));
	arr.set(index2, tmp);
    }
    //Altres funcions auxiliars
    static int parent(int index) {
        return (index-1)/2;
    } 
    static int left(int index) {
        return (2*index)+1;
    } 
    static int right(int index) {
        return (2*index)+2;
    }
    boolean isValid(int index) {
        return 0 <= index && index < size();
    }
    boolean hasLeft(int index) { 
        return isValid(left(index)); 
    }
    boolean hasRight(int index) {
        return isValid(right(index));
    }
}