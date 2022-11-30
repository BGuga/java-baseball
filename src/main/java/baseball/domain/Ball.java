package baseball.domain;

import java.util.EnumMap;
import java.util.List;

public class Ball {
    private static final String REDUPLICATOIN_ERROR_MESSAGE = "[ERROR] 공은 중복된 데이터로 이루어질 수 없습니다.";
    private static final String BALL_SIZE_ERROR_MESSAGE = "[ERROR] 공의 크기는 %d여야 합니다.";
    private static final String BALL_MIN_VALIDATION_ERROR_MESSAGE = "[ERROR] 최소 숫자는 %d입니다.";
    private static final String BALL_MAX_VALIDATION_ERROR_MESSAGE = "[ERROR] 최대 숫자는 %d입니다.";
    private static final String BALL_VERSION_ERROR_MESSAGE = "[ERROR]: 서로 호환되지 않는 공입니다.";

    private final List<Integer> ballData;
    private final int ballSize;
    private final int minBallNumber;
    private final int maxBallNumber;

    public Ball(List<Integer> ballData) {
        this.ballSize = ballData.size();
        this.minBallNumber = 1;
        this.maxBallNumber = 9;
        ballValidation(ballData);
        this.ballData = ballData;
    }


    public EnumMap<BallResult, Integer> getTotalBallResult(Ball anotherBall) {
        ballVersionValidation(anotherBall);
        EnumMap<BallResult, Integer> result = new EnumMap<BallResult, Integer>(BallResult.class);
        for (int i = 0; i < this.ballSize; i++) {
            BallResult ballResult = anotherBall.getBallResult(i, ballData.get(i));
            result.computeIfPresent(ballResult, (key, value) -> value + 1);
            result.computeIfAbsent(ballResult, (value) -> 1);
        }
        return result;
    }

    public void ballVersionValidation(Ball anotherBall) {
        if (size() != anotherBall.size()) {
            throw new IllegalArgumentException(BALL_VERSION_ERROR_MESSAGE);
        }
    }

    /**
     * 만약에 BallResult에서 Strike를 판단하게 만들기 위해서는 getter를 만들어야 하는 것이 문제다.
     */
    public BallResult getBallResult(int count, int ballValue) {
        if (!ballData.contains(ballValue)) {
            return BallResult.MISS;
        }
        if (ballData.get(count) == ballValue) {
            return BallResult.STRIKE;
        }
        return BallResult.BALL;
    }

    public boolean isAllStrike(Ball anotherBall) {
        EnumMap<BallResult, Integer> result = getTotalBallResult(anotherBall);
        if (!result.containsKey(BallResult.STRIKE) || !(result.size() == 1)) {
            return false;
        }
        if (ballSize != result.get(BallResult.STRIKE)) {
            return false;
        }
        return true;
    }

    public Integer size() {
        return ballData.size();
    }

    private void ballValidation(List<Integer> ballData) {
        checkSize(ballData);
        checkReduplication(ballData);
        checkNumberRange(ballData);
    }

    private void checkSize(List<Integer> ballData) {
        if (ballData.size() != this.ballSize) {
            throw new IllegalArgumentException(String.format(BALL_SIZE_ERROR_MESSAGE, this.ballSize));
        }
    }

    private void checkReduplication(List<Integer> ballData) {
        long unReduplicationNumberCount = ballData.stream().distinct().count();
        if (ballData.size() != unReduplicationNumberCount) {
            throw new IllegalArgumentException(REDUPLICATOIN_ERROR_MESSAGE);
        }
    }

    private void checkNumberRange(List<Integer> ballData) {
        for (Integer num : ballData) {
            checkRange(num);
        }
    }

    private void checkRange(Integer num) {
        if (num < this.minBallNumber) {
            throw new IllegalArgumentException(String.format(BALL_MIN_VALIDATION_ERROR_MESSAGE, this.minBallNumber));
        }
        if (num > this.maxBallNumber) {
            throw new IllegalArgumentException(String.format(BALL_MAX_VALIDATION_ERROR_MESSAGE, this.maxBallNumber));
        }
    }

}
