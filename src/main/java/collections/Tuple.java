package collections;

public class Tuple<X,Y> {
    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (!(other instanceof Tuple))
            return false;

        try {
            Tuple<X,Y> tuple = (Tuple<X,Y>)other;

            return (tuple.x != null && tuple.x.equals(this.x)) &&
                    (tuple.y != null && tuple.y.equals(this.y));
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }
}
