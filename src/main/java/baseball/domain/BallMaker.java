package baseball.domain;

import java.util.List;

public class BallMaker {

    private final NumberMaker numberMaker;
    private final BallSetting ballSetting;


    /**
     *
     * 생성자로 만들 수 있다면 maker는 어떻게 사용하는게 가장 올바를까
     *
     */
    public BallMaker() {
        numberMaker = new RandomNumberMaker();
        ballSetting = new BallSetting();
    }

    public BallMaker(RandomNumberMaker numberMaker, BallSetting ballSetting) {
        this.numberMaker = numberMaker;
        this.ballSetting = ballSetting;
    }

    public Ball generateNSizeRandomBall(int size) {
        return new Ball(numberMaker.generateNSizeUniqueNumber(size, ballSetting.getMinSize(), ballSetting.getMaxSize()));
    }

    public Ball generateBall(List<Integer> ballData) {
        return new Ball(ballData);
    }
}
