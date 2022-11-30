package baseball.domain;

public enum Command {
    RESTART(1),
    QUIT(2);

    private int commandNum;

    Command(int num) {
        commandNum = num;
    }
}
