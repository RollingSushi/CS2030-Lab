public class Box<T> {
    private final T content;
    private final static Box<?> EMPTY_BOX = new Box<>(null);

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
    public static <T> Box<T> empty() {
        return (Box<T>) EMPTY_BOX;
    }

    public boolean isPresent() {
        if (this.content == null) {
            return false;
        } else {
            return true;
        }
    }

    public static <T> Box<T> ofNullable(T content) {
        if (content == null) {
            return empty();
        } else {
            return new Box<>(content);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (object instanceof Box) {
            Box<T> box = (Box<T>) object;
            if (box.content == this.content || box.content.equals(this.content)) {
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
        if (this.content == null) {
            return "[]";
        } else {
            return "[" + this.content + "]";
        }
        
    }
}