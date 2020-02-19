public class Box<K> {
    private final K content;
    private final static Box<?> EMPTY_BOX = new Box<>(null);
    // member variable to hold and EMPTY Box

    private Box(K content) {
        this.content = content;
    }

    //Constructor
    public static <K> Box<K> of(K content) {
        if (content == null) {
            return null;
        } else {
            return new Box<>(content);
        }
    }

    @SuppressWarnings("unchecked")
    public static <K> Box<K> empty() {
        return (Box<K>) EMPTY_BOX;
    }

    public boolean isPresent() {
        if (this.content == null) {
            return false;
        } else {
            return true;
        }
    }

    public static <K> Box<K> ofNullable(K content) {
        if (content == null) {
            return empty();
        } else {
            return new Box<>(content);
        }
    }

    public Box<K> filter(BooleanCondition <? super K> cond) {
        if (this.content == null) {
            return empty();
        } else {
            if (cond.test(this.content)) {
                return (Box <K>) this;
            } else {
                return empty();
            }
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (object instanceof Box) {
            Box<K> box = (Box<K>) object;
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