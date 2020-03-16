import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Comparator;
import java.util.Collections;
import java.lang.Comparable;


public class ImmutableList<T> {
    private final List<T> list;


    public ImmutableList(List<T> list) {
        this.list = new ArrayList<T>(list);
    }

    @SafeVarargs
    public ImmutableList(T... item) {
        List<T> list = Arrays.asList(item);
        this.list = new ArrayList<T>(list);
    }

    public ImmutableList<T> add(T item) {
        ArrayList<T> newList =  new ArrayList<T>(this.list);
        newList.add(item);
        return new ImmutableList<T>(newList);
    }

    public ImmutableList<T> replace(T item, T newItem) {
        ArrayList<T> newList = new ArrayList<T>(this.list);
        newList.replaceAll(x -> x == item ? newItem : x);
        return new ImmutableList<T>(newList);
    }

    public ImmutableList<T> remove(T item) {
        ArrayList<T> newList =  new ArrayList<T>(this.list);
        newList.remove(item);
        return new ImmutableList<T>(newList);
    }

    public ImmutableList<T> filter(Predicate<? super T> predicate) {
        ArrayList<T> newList =  new ArrayList<T>(this.list);
        newList.removeIf(Predicate.not(predicate));
        return new ImmutableList<T>(newList);
    }

    public <R> ImmutableList<R> map(Function<? super T, ? extends R> function) {
        ArrayList<T> newList =  new ArrayList<T>(this.list);
        ArrayList<R> listAft = new ArrayList<R>();
        for (T i: newList) {
            listAft.add(function.apply(i));
        }
        return new ImmutableList<R>(listAft);
    }

    public ImmutableList<T> limit(long i) {
        ArrayList<T> newList =  new ArrayList<T>(this.list);
        ArrayList<T> listAft =  new ArrayList<T>();
        if (i < 0) {
            throw new IllegalArgumentException("limit size < 0");
        }
        else if (i > newList.size()) {
            newList.ensureCapacity((int) i);
            return new ImmutableList<T>(newList);
        } else {
            for (long j = i-1; j >= 0; j--) {
                listAft.add(newList.get((int) j));
            }
            return new ImmutableList<T>(listAft);
        }
    }

    
    public <U extends Comparable<? super U>> ImmutableList<U> sorted() {
        if (this.list.isEmpty() || this.list.get(0) instanceof java.lang.Comparable){
            @SuppressWarnings("unchecked")
            List<U> newList =  new ArrayList<U>((List<U>)this.list);
            Collections.sort(newList);
            return new ImmutableList<U>(newList); 
        } else {
            throw new IllegalStateException("List elements do not implement Comparable");
        }    
    }

    public ImmutableList<T> sorted(Comparator<T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null");
        } else {
            List<T> newList =  new ArrayList<T>(this.list);
            Collections.sort(newList, comparator);
            return new ImmutableList<T>(newList); 
        }
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    public <U> U[] toArray(U[] u) {
        
        if (u == null) {
            throw new NullPointerException("Input array cannot be null");
        } else {
            try {
                return this.list.toArray(u);
            } catch (ArrayStoreException e) {
                throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
            }
        }
    }

    @Override
    public String toString() {
        return this.list.toString();
    }

}
