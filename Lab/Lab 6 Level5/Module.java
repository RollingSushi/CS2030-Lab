import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Module extends KeyableMap<String,String,Assessment> {
    public Module(String mCode) {
        super(mCode);
    }

    
    @Override
    public Module put(Assessment item) {
        Optional<Assessment> opt = Optional.ofNullable(item);
        opt.ifPresentOrElse(x -> this.localMap.putIfAbsent(x.getKey(), x), () -> { });
        return (Module) this;
    }

}