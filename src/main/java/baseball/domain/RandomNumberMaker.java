package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberMaker implements NumberMaker {
    @Override
    public List<Integer> generateNSizeUniqueNumber(int size, int min, int max) {
        List<Integer> result = new ArrayList<>();
        while (!(result.size() == size)) {
            insertUniqueRandomNumber(result, min, max);
        }
        System.out.println(result);
        return result;
    }

    private void insertUniqueRandomNumber(List<Integer> result, int min, int max) {
        int randomNumber;
        do {
            randomNumber = Randoms.pickNumberInRange(min, max);;
        } while(result.contains(randomNumber));
        result.add(randomNumber);
    }
}
