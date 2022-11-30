package baseball.domain;

import java.util.List;

public class BallMaker {

    private final NumberMaker numberMaker;
    private final int minBallSize;
    private final int maxBallSize;


    /**
     *
     * 생성자로 만들 수 있다면 maker는 어떻게 사용하는게 가장 올바를까
     *
     */
    public BallMaker() {
        numberMaker = new RandomNumberMaker();
        minBallSize = 1;
        maxBallSize = 9;
    }

    public BallMaker(RandomNumberMaker numberMaker, int min, int max) {
        this.numberMaker = numberMaker;
        this.minBallSize = min;
        this.maxBallSize = max;
    }

    public Ball generateNSizeRandomBall(int size) {
        return new Ball(numberMaker.generateNSizeUniqueNumber(size, minBallSize, maxBallSize));
    }

    public Ball generateBall(List<Integer> ballData) {
        return new Ball(ballData);
    }
}
