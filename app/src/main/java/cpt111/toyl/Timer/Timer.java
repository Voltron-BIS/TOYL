package cpt111.toyl.Timer;

public class Timer {

    private String name;
    private long length;
    private long elapsed;

    public Timer() {

    }

    public Timer(String name, long length) {
        this.name = name;
        this.length = length;
        // starts at 0
        this.elapsed = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }
}
