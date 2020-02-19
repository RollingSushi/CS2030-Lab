class LastDigitsOfHashCode implements Transformer<Object, Integer> {
    private final int k;

    public LastDigitsOfHashCode(int k) {
        this.k = k;
    }

    public Integer transform(Object obj) {
        return (int) Math.abs(obj.hashCode() % Math.pow(10, k));

    }

}