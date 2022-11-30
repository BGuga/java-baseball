package baseball.domain;

import java.util.List;

public interface NumberMaker {
    List<Integer> generateNSizeUniqueNumber(int size, int min, int max);
}
