public class LongerThan implements BooleanCondition<String> {
    private final int maxLength;

    public LongerThan(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean test(String string) {
        if(string.length() > maxLength) {
            return true;
        } else {
            return false;
        }
    }
}