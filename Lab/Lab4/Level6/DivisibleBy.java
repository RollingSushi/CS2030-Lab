public class DivisibleBy implements BooleanCondition<Integer> {
    private final int divisor;

    public DivisibleBy(int divisor) {
        this.divisor = divisor;
    }

    @Override
    public boolean test(Integer dividend) {    
        if((int)dividend % divisor == 0) {
            return true;
        } else {
            return false;
        }
    }
}