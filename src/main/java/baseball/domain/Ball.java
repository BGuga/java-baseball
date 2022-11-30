package baseball.domain;

import java.util.EnumMap;
import java.util.List;

public class Ball {
    private static final String REDUPLICATOIN_ERROR_MESSAGE = "[ERROR] 공은 중복된 데이터로 이루어질 수 없습니다.";

    private final List<Integer> ballData;
    private final int ballSize;
    private final int minBallNumber;
    private final int maxBallNumber;

    public Ball(List<Integer> ballData) {
        this.ballSize = 3;
        this.minBallNumber = 1;
        this.maxBallNumber = 9;
        ballValidation(ballData);
        this.ballData = ballData;
    }

    public EnumMap<BallResult, Integer> getTotalBallResult(Ball anotherBall) {
        EnumMap<BallResult, Integer> result = new EnumMap<BallResult, Integer>(BallResult.class);
        for (int i = 0; i < this.ballSize; i++) {
            BallResult ballResult = anotherBall.getBallResult(i, ballData.get(i));
            result.computeIfPresent(ballResult, (key, value) -> value + 1);
            result.computeIfAbsent(ballResult, (value) -> 1);
        }
        return result;
    }

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

    private void ballValidation(List<Integer> ballData) {
        checkSize(ballData);
        checkReduplication(ballData);
        checkNumberRange(ballData);
    }

    private void checkSize(List<Integer> ballData) {
        if (ballData.size() != this.ballSize) {
            throw new IllegalArgumentException(String.format("[ERROR] 공의 크기는 %d여야 합니다.", this.ballSize));
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
            throw new IllegalArgumentException(String.format("[ERROR] 최소 숫자는 %d입니다.", this.minBallNumber));
        }
        if (num > this.maxBallNumber) {
            throw new IllegalArgumentException(String.format("[ERROR] 최대 숫자는 %d입니다.", this.maxBallNumber));
        }
    }

}
