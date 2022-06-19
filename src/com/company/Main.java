package com.company;

import java.sql.SQLOutput;
import java.util.*;

public class Main {
    static Map<String, Integer> numToRomInpt = new HashMap();
    static Map<Integer, String> numToRomRes = new HashMap();
    static List<String> numStr = new ArrayList();
    static List<String> operators = new ArrayList();

    static {
        for (int i = 1; i < 11; i++) {
            numToRomInpt.put(RomanNumber.toRoman(i), i);

        }
        for (int i = 1; i < 100; i++) {
            numToRomRes.put(i, RomanNumber.toRoman(i));
        }
        for (int i = 1; i < 11; i++) {
            numStr.add(i + "");
        }
        operators.add("/");
        operators.add("*");
        operators.add("+");
        operators.add("-");
    }

    public static void main(String[] args) throws Exception {
            System.out.println("Пожалуйста введите задачу, например: 3 + 7, 3 - 7, 8 * 2, X / V");
            Scanner scanner = new Scanner(System.in);
            String task = scanner.nextLine();
            System.out.println(calc(task));

    }

    public static String calc(String input) throws Exception {
            String[] taskArr = input.split("\\s+");
            if (taskArr.length != 3) {
                throw new IllegalAmountOperandsException("Неверное количество операндов");
            }
            if (isArabicDecade(taskArr[0]) && isArabicDecade(taskArr[2])) {
                if (isOperator(taskArr[1])) {
                    return calc(Integer.parseInt(taskArr[0]), Integer.parseInt(taskArr[2]), taskArr[1])+"";
                }
            } else {
                if (isRomanicDecade(taskArr[0]) && isRomanicDecade(taskArr[2])) {
                    if (isOperator(taskArr[1])) {
                        int res = calc(numToRomInpt.get(taskArr[0].toUpperCase()), numToRomInpt.get(taskArr[2].toUpperCase()), taskArr[1]);
                        if (res > 0) {
                            return (numToRomRes.get(res));
                        } else {
                            throw new RomeNumNagativeException("В римской системе нет отрицательных чисел.");
                        }
                    }
                } else {
                    throw new IlegalInputException("Не корректный ввод");
                }
            }
            return "не корректная задача";
    }

    public static boolean isArabicDecade(String num) {
        for (int i = 0; i < numStr.size(); i++) {
            if (num.equals(numStr.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRomanicDecade(String num) {
        if (numToRomInpt.containsKey(num.toUpperCase())) {
            return true;
        }
        return false;
    }

    public static boolean isOperator(String operator) throws IllegalOperatorException {
        for (int i = 0; i < operators.size(); i++) {
            if (operator.equals(operators.get(i))) {
                return true;
            }
        }
        throw new IllegalOperatorException("Не корректный оператор");
    }


    public static int calc(int a, int b, String operator) {
        int res = 0;

        if ("/".equals(operator)) {
            res = a / b;
        } else if ("*".equals(operator)) {
            res = a * b;
        } else if ("+".equals(operator)) {
            res = a + b;
        } else if ("-".equals(operator)) {
            res = a - b;
        } else {
            System.out.println("Неверная команда.");
        }
        return res;
    }


}
