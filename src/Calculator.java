import java.util.ArrayList;

import static java.lang.Character.isDigit;

public class Calculator {



    public static String calc(String expression) {

        while (expression.contains("*") || expression.contains("/") || expression.contains("-") || expression.contains("+") ) {

            if (expression.indexOf("-") == 0 && !expression.contains("*") && !expression.contains("/") && !expression.contains("+")){
                return expression;
            }

            ArrayList<Expression> expressionList = new ArrayList<Expression>();
            char[] charExpression = expression.toCharArray();
            anchor:
            for (int i = 0; i < charExpression.length; i++) {
                if (isDigit(charExpression[i]) || charExpression[i] == '-') {
                    String exp = "";
                    for (; i < charExpression.length; i++) {
                        if (i < charExpression.length - 1) {
                            if (charExpression[i + 1] == '(') {
                                String str = "" + charExpression[i];
                                Expression expr = new Expression(exp, str);
                                expressionList.add(expr);
                                continue anchor;
                            }
                        } else {
                            exp += charExpression[i];
                            Expression expr = new Expression(exp);
                            expressionList.add(expr);
                            continue anchor;
                        }
                        exp += charExpression[i];
                    }
                }
                if (charExpression[i] == '(') {
                    int counter = 0;
                    int firstParentheses = i;
                    for (; i < charExpression.length; i++) {

                        if (charExpression[i] == '(') {
                            counter += 1;
                        }
                        if (charExpression[i] == ')') {
                            counter -= 1;
                        }
                        if (counter == 0) {
                            String exp = "";
                            for (int y = firstParentheses + 1; y < i; y++) {
                                exp += charExpression[y];
                            }
                            if (i + 1 < charExpression.length) {
                                String str = "" + charExpression[i + 1];
                                Expression expr = new Expression(exp, str);
                                expressionList.add(expr);
                            } else {
                                Expression expr = new Expression(exp);
                                expressionList.add(expr);
                            }
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < expressionList.size(); i++) {
                if (!expressionList.get(i).value.contains("(") && !expressionList.get(i).value.contains(")")) {
                    String str = Calculator.calculator(expressionList.get(i).value);
                    Expression exp = new Expression(str, expressionList.get(i).sing);
                    expressionList.set(i, exp);
                }
            }
            expression = "";
            for (int i = 0; i < expressionList.size(); i++) {
                if (expressionList.get(i).value != null) {
                    expression += expressionList.get(i).value;
                }
                if (expressionList.get(i).sing != null) {
                    expression += expressionList.get(i).sing;
                }
            }
        }
        return expression;
    }

    public static String calculator (String expression) {

        while (expression.contains("*") || expression.contains("/") || expression.contains("-") || expression.contains("+") ) {

            if (expression.indexOf("-") == 0 && !expression.contains("*") && !expression.contains("/") && !expression.contains("+")){
                return expression;
            }

            if (expression.contains("*") || expression.contains("/")) {
                int indexX = expression.indexOf("*");
                int indexY = expression.indexOf("/");

                if (indexX == -1) {
                    indexX = Integer.MAX_VALUE;
                }
                if (indexY == -1) {
                    indexY = Integer.MAX_VALUE;
                }

                if (indexX < indexY) {
                    String result = "";
                    String[] expressionArray = expression.split("\\*");
                    expressionArray = numberClear(expressionArray);
                    int first = Integer.parseInt(expressionArray[0]);
                    int second = Integer.parseInt(expressionArray[1]);
                    int resultInt = first * second;
                    result = "" + resultInt;
                    String expressionStr = "" + first + '*' + second;
                    expression = expression.replace(expressionStr, result);
                } else {
                    String result = "";
                    String[] expressionArray = expression.split("/");
                    expressionArray = numberClear(expressionArray);
                    int first = Integer.parseInt(expressionArray[0]);
                    int second = Integer.parseInt(expressionArray[1]);
                    int resultInt = first / second;
                    result = "" + resultInt;
                    String expressionStr = "" + first + "/" + second;
                    expression = expression.replace(expressionStr, result);
                }
            } else if (expression.contains("+") || expression.contains("-")) {


                int indexX = expression.indexOf("+");
                int indexY = expression.indexOf("-");

                if (indexX == -1) {
                    indexX = Integer.MAX_VALUE;
                }
                if (indexY == -1) {
                    indexY = Integer.MAX_VALUE;
                }

                if (indexX < indexY) {
                    String result = "";
                    String[] expressionArray = expression.split("\\+");
                    expressionArray = numberClear(expressionArray);
                    int first = Integer.parseInt(expressionArray[0]);
                    int second = Integer.parseInt(expressionArray[1]);
                    int resultInt = first + second;
                    result = "" + resultInt;
                    String expressionStr = "" + first + '+' + second;
                    expression = expression.replace(expressionStr, result);
                } else {
                    String result = "";
                    String[] expressionArray = expression.split("-");
                    expressionArray = numberClear(expressionArray);
                    int first = Integer.parseInt(expressionArray[0]);
                    int second = Integer.parseInt(expressionArray[1]);
                    int resultInt = first - second;
                    result = "" + resultInt;
                    String expressionStr = "" + first + "-" + second;
                    expression = expression.replace(expressionStr, result);
                }
            }

        }
        return expression;
    }

    public static String[] numberClear(String[] expressionArray) {
        char[] first = expressionArray[0].toCharArray();
        String firstClear = "";


        for (int i = first.length-1; i >= 0; i --){

            if (i == 0){
                firstClear += first[i];
                StringBuilder builder = new StringBuilder(firstClear);
                firstClear = builder.reverse().toString();
                break;
            }
            if (!isDigit(first[i-1])){
                firstClear += first[i];
                StringBuilder builder = new StringBuilder(firstClear);
                firstClear = builder.reverse().toString();
                break;
            }
            if (isDigit(first[i])){
                firstClear += first[i];
            } else if (first[i] == '-'){
                if (!isDigit(first[i-1])) {
                    StringBuilder builder = new StringBuilder(firstClear);
                    firstClear = builder.reverse().toString();
                    firstClear = first[i] + firstClear;
                    break;
                }else{
                    break;
                }
            } else {
                break;
            }
        }
        char[] second = expressionArray[1].toCharArray();
        String secondClear = "";
        int x = 0;
        if (second[0] == '-'){
            secondClear += second[0];
            x++;
        }
        for (; x < second.length; x ++){
            if (isDigit(second[x])){
                secondClear += second[x];
            } else {
                break;
            }
        }
        expressionArray[0] = firstClear;
        expressionArray[1] = secondClear;
        return expressionArray;
    }


}
