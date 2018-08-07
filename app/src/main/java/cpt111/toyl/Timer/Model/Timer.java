package cpt111.toyl.Timer.Model;

public class Timer extends AbstractTimer{

    private long length;
    private long elapsed;
    private boolean isCountDown;

    public Timer(String name, long length) {
        super.setName(name);
        this.length = length;
        // starts at 0
        this.elapsed = 0;
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
