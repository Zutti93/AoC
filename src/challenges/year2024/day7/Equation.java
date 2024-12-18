package challenges.year2024.day7;

import java.util.List;

public class Equation {
    private final Long result;
    private final List<Long> inputs;

    public Equation(Long result, List<Long> inputs) {
        this.result = result;
        this.inputs = inputs;
    }

    public Long getResult() {
        return result;
    }

    public boolean isSolvable() {
        List<List<Operation>> operatorLists = SignHelper.generateOperatorList(inputs.size());
        for (List<Operation> operationList : operatorLists) {
            if (isSolvableWith(operationList)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSolvableWith(List<Operation> operationList) {

        Long tempResult = inputs.get(0);

        for (int i = 1; i < inputs.size(); i++) {
            long nextVal = inputs.get(i);
            Operation operation = operationList.get(i - 1);
            switch (operation) {
                case PLUS -> tempResult += nextVal;
                case MULTIPLICATION -> tempResult *= nextVal;
                case CONCATENATION -> tempResult = concatLong(tempResult, nextVal);
            }
        }

        return tempResult.equals(result);
    }

    private Long concatLong(Long l1, long l2) {
        String concatString = String.valueOf(l1) + l2;
        return Long.parseLong(concatString);
    }
}