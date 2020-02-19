public class Box<T> {
    private final T content;

    private Box(T content) {
        this.content = content;
    }

    public static <T> Box<T> of(T content) {
        if (content == null) {
            return null;
        } else {
            return new Box<>(content);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (object instanceof Box) {
            Box<T> box = (Box<T>) object;
            if (box.content.equals(this.content)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[" + this.content + "]";
    }
}