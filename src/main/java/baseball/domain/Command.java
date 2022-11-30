package baseball.domain;

import java.util.Arrays;

public enum Command {
    RESTART(1),
    QUIT(2);

    private static String INVALID_COMMAND_INTEGER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 COMMAND 숫자의 입력 입니다.";

    private int commandNum;

    Command(int num) {
        commandNum = num;
    }

    public static Command getEnumByInt(int num) {
        return Arrays.stream(Command.values())
                .filter(cm -> cm.commandNum == num)
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(INVALID_COMMAND_INTEGER_ERROR_MESSAGE));
    }
}
