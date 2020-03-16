import java.util.Optional;

public class Assessment implements Keyable<String> {
    private String aName;
    private String grade;

    public Assessment(String aName, String grade) {
        this.aName = aName;
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }

    public String getKey() {
        return this.aName;
    }

    @Override
    public String toString() {
        return "{" + this.aName + ": " + this.grade + "}";
    }

}