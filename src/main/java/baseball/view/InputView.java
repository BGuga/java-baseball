package baseball.view;

import baseball.domain.Ball;
import baseball.domain.Command;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    public Ball readNBallByUser(int ballSize) {
        System.out.printf("숫자를 입력해주세요 : ");
        String userInput = Console.readLine();
        return new Ball(makeNumberList(userInput));
    }

    public Command readGameCommand() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String userInput = Console.readLine();
        return Command.getEnumByInt(Integer.parseInt(userInput));
    }

    private List<Integer> makeNumberList(String userInput) {
        IsNumber(userInput);
        return userInput
                .chars()
                .boxed()
                .map(ch -> ch - '0')
                .collect(Collectors.toList());
    }

    private void IsNumber(String userInput) {
        Integer.parseInt(userInput);
    }
}
