package baseball;

import baseball.controller.BaselBallGameController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BaselBallGameController gameController= new BaselBallGameController();
        gameController.playRecurringGame();
    }
}
