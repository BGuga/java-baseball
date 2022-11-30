package baseball.domain;

import java.util.EnumMap;

public class BaseBallGame {
    private final BallMaker ballMaker;
    private Ball computerBall;
    private final int DEFAULT_MIN = 1;
    private final int DEFAULT_MAX = 9;
    private final int DEFAULT_BALL_SIZE = 3;
    private boolean gameEnd;
    private boolean needToQuit;

    public BaseBallGame() {
        ballMaker = new BallMaker(new RandomNumberMaker(), DEFAULT_MIN, DEFAULT_MAX);
        computerBall = ballMaker.generateNSizeRandomBall(DEFAULT_BALL_SIZE);
        this.gameEnd = false;
        this.needToQuit = false;
    }

    public EnumMap<BallResult, Integer> throwBall(Ball ball) {
        checkGameStatus(ball);
        return computerBall.getTotalBallResult(ball);
    }

    public boolean isNeedToQuit() {
        return needToQuit;
    }

    public boolean isGameEnd(){
        return gameEnd;
    }

    public void insertGameCommand(Command command) {
        if (command == Command.RESTART) {
            computerBall = ballMaker.generateNSizeRandomBall(3);
            this.gameEnd = false;
        }
        if (command == Command.QUIT) {
            this.needToQuit = true;
        }
    }

    public Integer getBallSize(){
        return DEFAULT_BALL_SIZE;
    }

    private void checkGameStatus(Ball ball) {
        if (computerBall.isAllStrike(ball)) {
            this.gameEnd = true;
        }
    }
}
