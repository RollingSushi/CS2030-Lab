import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class Roster extends KeyableMap<String, String, Student> {
    public Roster(String rName) {
        super(rName);
    }

    public String getGrade(String id, String mCode, String aName) throws NoSuchRecordException {
        return this.get(id).flatMap(
            x -> x.get(mCode)).flatMap(
                y -> y.get(aName)).map(
                    z -> z.getGrade()).orElseThrow(
                        () -> new NoSuchRecordException(
                            "No such record: " + id + " " + mCode + " " + aName));
        
    }

    @Override
    public Roster put(Student item) {
        Optional<Student> opt = Optional.ofNullable(item);
        opt.ifPresentOrElse(x -> this.localMap.putIfAbsent(x.getKey(), x), () -> { });
        return (Roster) this;
    }
}