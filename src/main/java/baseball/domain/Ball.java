package baseball.domain;

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

    public void ballValidation(List<Integer> ballData) {
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
