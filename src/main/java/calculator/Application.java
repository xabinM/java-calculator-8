package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해 주세요.");

        String input = Console.readLine();

        System.out.println(calculator(input));
    }

    private static int calculator(String input) {

        int[] splitInput = Arrays.stream(input.split("[,:]"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int result = 0;
        for (int v : splitInput) {

            result += v;
        }

        return result;
    }
}
