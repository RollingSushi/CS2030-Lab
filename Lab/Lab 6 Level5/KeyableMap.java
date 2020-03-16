import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class KeyableMap<T,K,V extends Keyable<K>> implements Keyable<T> {
    public T mapKeyType;
    public Map<K,V> localMap;

    public KeyableMap(T t) {
        this.mapKeyType = t;
        this.localMap = new HashMap<K,V>();
    }

    public T getKey() {
        return this.mapKeyType;
    }

    public Optional<V> get(K k) {
        return Optional.ofNullable(this.localMap.get(k));
    }

    public KeyableMap<T,K,V> put(V item) {
        Optional<V> opt = Optional.ofNullable(item);
        opt.ifPresentOrElse(x -> this.localMap.putIfAbsent(x.getKey(), x), () -> { });
        return this;
    }

    @Override
    public String toString() {
        String mapString = Arrays.toString(this.localMap.values().toArray());
        String temp = mapString.replace("[", "{");
        String finalMapString = temp.replace("]", "}");
        return this.mapKeyType + ": " + finalMapString;
        
    }
}