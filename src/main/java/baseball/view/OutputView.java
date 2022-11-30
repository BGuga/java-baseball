package baseball.view;

import baseball.domain.BallResult;

import java.util.EnumMap;

public class OutputView {
    public void printBallResultMessage(EnumMap<BallResult, Integer> result) {
        if (result.containsKey(BallResult.MISS) && result.size() == 1) {
            printNothingMessage();
            return;
        }
        printBallOrStrikeMessage(result);
    }

    private void printBallOrStrikeMessage(EnumMap<BallResult, Integer> result) {
        if (result.containsKey(BallResult.STRIKE) && result.containsKey(BallResult.BALL)) {
            printBallAndStrikeMessage(result);
            return;
        }
        if (result.containsKey(BallResult.STRIKE)) {
            printStrikeMessage(result);
            return;
        }
        printBallMessage(result);
    }

    private void printNothingMessage() {
        System.out.println("낫싱");
    }

    private void printBallAndStrikeMessage(EnumMap<BallResult, Integer> result) {
        System.out.println(String.format("%d볼 %d스트라이크", result.get(BallResult.BALL), result.get(BallResult.STRIKE)));
    }

    private void printStrikeMessage(EnumMap<BallResult, Integer> result) {
        System.out.println(String.format("%d스트라이크", result.get(BallResult.STRIKE)));
    }

    private void printBallMessage(EnumMap<BallResult, Integer> result) {
        System.out.println(String.format("%d볼", result.get(BallResult.BALL)));
    }

    public void printGameStartingMessage() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    public void printGameEndingMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}
