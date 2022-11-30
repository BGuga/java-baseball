package baseball.controller;

import baseball.domain.BaseBallGame;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.EnumMap;

public class BaselBallGameController {
    private BaseBallGame baseBallGame;
    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    public BaselBallGameController() {
        baseBallGame = new BaseBallGame();
    }

    public void playRecurringGame() {
        outputView.printGameStartingMessage();
        while (!baseBallGame.isNeedToQuit()) {
            playGameUntilEnd();
            InsertGameCommand();
        }
    }

    private void playGameUntilEnd() {
        while (!baseBallGame.isGameEnd()) {
            outputView.printBallResultMessage(baseBallGame.throwBall(inputView.readNBallByUser(baseBallGame.getBallSize())));
        }
        outputView.printGameEndingMessage();
    }

    private void InsertGameCommand() {
        baseBallGame.insertGameCommand(inputView.readGameCommand());
    }
}
