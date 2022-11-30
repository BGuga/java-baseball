package baseball.domain;

import java.util.EnumMap;

public class BaseBallGame {
    private final BallMaker ballMaker;
    private final Ball computerBall;
    private final int DEFAULT_MIN = 1;
    private final int DEFAULT_MAX = 9;
    private final int DEFAULT_BALL_SIZE = 3;

    public BaseBallGame() {
        ballMaker = new BallMaker(new RandomNumberMaker(), DEFAULT_MIN, DEFAULT_MAX);
        computerBall = ballMaker.generateNSizeRandomBall(DEFAULT_BALL_SIZE);
    }

    public EnumMap<BallResult, Integer> throwBall(Ball ball) {
        return computerBall.getTotalBallResult(ball);
    }

}
