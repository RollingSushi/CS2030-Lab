import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Student extends KeyableMap<String,String,Module> {
    public Student(String id) {
        super(id);
    }

    @Override
    public Student put(Module item) {
        Optional<Module> opt = Optional.ofNullable(item);
        opt.ifPresentOrElse(x -> this.localMap.putIfAbsent(x.getKey(), x), () -> { });
        return (Student) this;
    }
}