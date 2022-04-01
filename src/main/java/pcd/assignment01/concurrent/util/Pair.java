package pcd.assignment01.concurrent.util;

/**
 * @param <X>
 * @param <Y>
 * Class used to represent a generic pair of values
 */
public class Pair <X, Y>{
    private final X start;
    private final Y stop;

    public Pair(final X start, final Y stop) {
        this.start = start;
        this.stop = stop;
    }

    /**
     * @return the first value
     */
    public X getStart() {
        return start;
    }

    /**
     * @return the second value
     */
    public Y getStop() {
        return stop;
    }
}
