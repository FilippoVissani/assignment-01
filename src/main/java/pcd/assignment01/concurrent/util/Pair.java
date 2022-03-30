package pcd.assignment01.concurrent.util;

public class Pair <X, Y>{
    private final X start;
    private final Y stop;

    public Pair(final X start, final Y stop) {
        this.start = start;
        this.stop = stop;
    }

    public X getStart() {
        return start;
    }

    public Y getStop() {
        return stop;
    }
}
