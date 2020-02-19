public class BoxIt<T> implements Transformer<T, Box<T>> {

    public BoxIt() {
    } 

    public Box<T> transform(T t) {       
        return Box.of(t);
    }
}
