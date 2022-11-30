package baseball.domain;

import java.util.EnumMap;

public class BaseBallGame {
    private final BallMaker ballMaker;
    private Ball computerBall;
    private BallSetting ballSetting;
    private boolean gameEnd;
    private boolean needToQuit;

    /**
     *
     * 숫자의 최대 최소는 어디서 관리되어야 할까
     * 그럼 사이즈는 어디서 관리되어야 할까
     *
     */
    public BaseBallGame() {
        ballSetting = new BallSetting();
        int defaultBallSize = 3;
        ballMaker = new BallMaker(new RandomNumberMaker(), ballSetting);
        computerBall = ballMaker.generateNSizeRandomBall(defaultBallSize);
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

    public boolean isGameEnd() {
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

    public Integer getBallSize() {
        return computerBall.size();
    }

    private void checkGameStatus(Ball ball) {
        if (computerBall.isAllStrike(ball)) {
            this.gameEnd = true;
        }
    }
}
