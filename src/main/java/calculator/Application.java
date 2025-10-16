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

        System.out.println(processCalculate(input));
    }

    private static int processCalculate(String input) {
        List<String> checkCustom = customChecker(input);

        int result = 0;

        try {
            if (checkCustom.size() == 1) {
                result = calculator(splitter(checkCustom.getFirst(), null));
            } else if (checkCustom.size() == 2) {
                result = calculator(splitter(checkCustom.getFirst(), checkCustom.getLast()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    private static List<String> customChecker(String input) {

        List<String> result = new ArrayList<>();

        if (input.startsWith("/")) {
            String[] temp = input.split("n");
            String customPart =  temp[0];
            String strPart = temp[1];

            result.add(strPart);
            result.add(String.valueOf(customPart.charAt(2)));
        } else {
            result.add(input);
        }

        return result;
    }

    private static int calculator(int[] arr) {
        int result = 0;
        for (int v : arr) {
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
