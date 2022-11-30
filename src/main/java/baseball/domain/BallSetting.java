package baseball.domain;

public class BallSetting {
    private final int defaultMin;
    private final int defaultMax;

    public BallSetting() {
        defaultMax = 9;
        defaultMin = 1;
    }

    public int getMinSize() {
        return defaultMin;
    }

    public int getMaxSize() {
        return defaultMax;
    }
}
