package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        String input = Console.readLine();

        System.out.println("결과 : " + processCalculate(input));
    }

    private static int processCalculate(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }

        List<String> checkCustom = customChecker(input);

        int result = 0;

        try {
            if (checkCustom.size() == 1) {
                result = calculator(splitter(checkCustom.getFirst(), null));
            } else if (checkCustom.size() == 2) {
                result = calculator(splitter(checkCustom.getFirst(), checkCustom.getLast()));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력 형식입니다.");
        }

        return result;
    }

    private static List<String> customChecker(String input) {

        List<String> result = new ArrayList<>();

        if (input.startsWith("//")) {
            String[] temp = input.split("\\\\n", 2);
            if (temp.length < 2) {
                throw new IllegalArgumentException("커스텀 구분자 형식에 오류가 있습니다.");
            }
            String customPart =  temp[0];
            String strPart = temp[1];

            String delimiter = customPart.substring(2);

            if (delimiter.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 한 글자만 지정할 수 있습니다.");
            }

            result.add(strPart);
            result.add(String.valueOf(delimiter.charAt(0)));
        } else {
            result.add(input);
        }

        return result;
    }

    private static int calculator(int[] arr) {
        int result = 0;

        for (int v : arr) {
            if (v <= 0) {
                throw new IllegalArgumentException("양수만 가능합니다.");
            }
            result += v;
        }

        return result;
    }

    private static int[] splitter(String str, String delimiter) {
        if (delimiter == null) {
            return Arrays.stream(str.split("[,:]"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } else {
            return Arrays.stream(str.split("[,:" + Pattern.quote(delimiter) + "]"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }
}
