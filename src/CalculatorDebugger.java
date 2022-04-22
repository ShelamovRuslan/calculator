import static java.lang.Character.isDigit;

public class CalculatorDebugger {
    public static String calculatorDebugger(String expression) {
        expression = clearExpression(expression);
        expression = mainDebugger(expression);
        parentheses(expression);
        expression = multiplicationSigns(expression);
        expression = clearExpressionNull(expression);
        expression = expressionFinal(expression);
        expression = expressionZero(expression);
        expression = expressionMinus(expression);
        return expression;

    }
    public static String expressionMinus (String expression ){
        return expression.replace("--", "");
    }
    public static String expressionZero (String expression ){
        char[] charExpression = expression.toCharArray();
        expression = "";
        for (int x = 0; x < charExpression.length; x++){
            if (charExpression[x] == ' ' && isDigit(charExpression[x-1])){
                expression += ".0 ";
            } else {
                expression += charExpression[x];
            }
        }


        return expression;
    }

    public static String expressionFinal (String expression ){
        char[] charExpression = expression.toCharArray();
        expression = "";
        for (int x = 0; x < charExpression.length; x++){
            if (charExpression[x] == '-' && x == 0 || charExpression[x] == '-' && !isDigit(charExpression[x-1]) && charExpression[x - 1] != ')'
                    && charExpression[x - 1] != '('){
                expression += charExpression[x] ;
            }
            else
            if (charExpression[x] == '-' && charExpression[x - 1] == '('){
                expression += charExpression[x];
            }
            else
            if ( charExpression[x]  == '+' || charExpression[x] == '-' || charExpression[x]  == '*' || charExpression[x]  == '/'){
                expression += " " + charExpression[x]  + " ";
            }else
            if ( charExpression[x]  == '('){
                expression += charExpression[x]  + " ";
            }else
            if ( charExpression[x]  == ')'){
                expression += " " + charExpression[x];
            } else {
                expression += charExpression[x] ;
            }

        }
        return expression;
    }

    public static String clearExpressionNull (String expression){
        expression = expression.replace("--0", "-0");
        expression = expression.replace("*-0", "*0");
        expression = expression.replace("/-0", "/0");
        expression = expression.replace("+-0", "+0");
        return expression;
    }

    public static String clearExpression (String expression){
        char[] charExpression = expression.toCharArray();
        expression = "";
        for (char x: charExpression){
            if (isDigit(x) || x == '+' || x == '-' || x == '*' || x == '/' || x == '(' || x == ')'  || x == ',' || x == '.' )
                expression += x;
        }
        while (expression.contains("()")) {
            expression = expression.replace("()", "");
        }

        return expression;
    }

    public static void parentheses (String expression){
        int countParentheses = 0;

        for(int i = 0; i < expression.length(); i++) {

            if(expression.charAt(i) == '(') countParentheses++;
            else if(expression.charAt(i) == ')') countParentheses--;
            if(countParentheses < 0)
                CalculatorError.calculatorError();
        }
        if(countParentheses != 0) {
            CalculatorError.calculatorError();
        }

    }

    public static String multiplicationSigns (String expression){

        char[] charExpression = expression.toCharArray();
        expression = "";
        anchor: for (int i = 0; i < charExpression.length; i++) {

            if (charExpression[i] == '('){
                if (i > 0) {
                    if (charExpression[i - 1] == ')' || isDigit(charExpression[i - 1])) {
                        expression += '*';
                        expression += charExpression[i];
                        continue anchor;
                    }
                }
            }
            if (i > 0) {
                if (isDigit(charExpression[i]) && charExpression[i - 1] == ')') {
                    expression += '*';
                    expression += charExpression[i];
                    continue anchor;
                }
            }
            expression += charExpression[i];
        }

        return expression;
    }

    public static String mainDebugger (String expression){
        char[] charExpression = expression.toCharArray();
        String clearExpression = "";
        boolean arithmeticSign = false;
        boolean comma = false;

        for (int i = 0; i < charExpression.length; i++) {
            if (isDigit(charExpression[i])){
                clearExpression += charExpression[i];
                arithmeticSign = false;
                continue;
            }
            switch (charExpression[i]) {
                case '+':
                case '*':
                case '/':
                    if (arithmeticSign == true){
                        CalculatorError.calculatorError();
                    }
                    clearExpression += charExpression[i];
                    arithmeticSign = true;
                    comma = false;
                    break;
                case '-':
                    if (i == 0){
                        clearExpression += charExpression[i];
                        break;
                    }
                    if (charExpression[i - 1] == '(' && charExpression[i + 1] == '(') {
                        CalculatorError.calculatorError();
                    }

                    if (arithmeticSign == true){
                        if (!isDigit(charExpression[i-1])){
                            clearExpression += charExpression[i];
                            break;
                        }
                        if (charExpression[i-1] == '-'){
                            if (i - 2 >= 0) {
                                if (charExpression[i - 2] == '-') {
                                    CalculatorError.calculatorError();
                                }
                            }
                            if (!isDigit(charExpression[i - 3]) || charExpression[i - 3] == ')'){
                                CalculatorError.calculatorError();
                            }
                            clearExpression += charExpression[i];
                            arithmeticSign = true;
                            comma = false;
                            break;
                        }


                        CalculatorError.calculatorError();
                    }

                    clearExpression += charExpression[i];
                    arithmeticSign = true;
                    comma = false;
                    break;
                case '(':
                    if (i > 0) {
                        if (arithmeticSign == true) {
                            if (charExpression[i - 2] == '('){
                                CalculatorError.calculatorError();
                            }
                            if (charExpression[i - 2] == ')') {
                                clearExpression += charExpression[i];
                                arithmeticSign = false;
                                break;
                            }
                        }
                    }
                    if (i > 0) {
                        if (charExpression[i - 1] == '(') {
                            clearExpression += charExpression[i];
                            break;
                        }
                        if (!isDigit(charExpression[i - 1]) && charExpression[i - 1] != ')' && arithmeticSign == false) {
                            CalculatorError.calculatorError();
                        }
                        if (arithmeticSign == true){
                            arithmeticSign = false;
                        }
                    }
                    if (isDigit(charExpression[i + 1]) || charExpression[i + 1] == '-'){
                        clearExpression += charExpression[i];
                        break;
                    }
                    if (i == 0){
                        clearExpression += charExpression[i];
                        break;
                    }
                    if (charExpression[i - 1] == ')' && charExpression[i] == '('){
                        clearExpression += charExpression[i];
                        break;
                    }
                    if (charExpression[i] == '(' && isDigit(charExpression[i - 1])) {
                        clearExpression += charExpression[i];
                        break;
                    }
                    if (charExpression[i] == '(' && charExpression[i +1] == '(') {
                        clearExpression += charExpression[i];
                        break;
                    }
                    CalculatorError.calculatorError();

                case ')':
                    if (charExpression[i - 1] == ')') {
                        clearExpression += charExpression[i];
                        break;
                    }
                    if (i + 1 < charExpression.length) {
                        if (!isDigit(charExpression[i - 1])) {
                            CalculatorError.calculatorError();
                        }
                    }
                    if (i + 1 < charExpression.length) {
                        if (isDigit(charExpression[i + 1]) || charExpression[i + 1] == '('){
                            clearExpression += charExpression[i];
                            break;
                        }
                    }

                    clearExpression += charExpression[i];
                    break;
                case '.':
                case ',':
                    if (charExpression.length == i+1){
                        CalculatorError.calculatorError();
                    }
                    if (isDigit(charExpression[i-1]) && isDigit(charExpression[i+1])){
                        clearExpression += '.';
                        comma = true;
                        break;
                    }
                    CalculatorError.calculatorError();
            }
        }

        if (arithmeticSign == true){
            CalculatorError.calculatorError();
        }
        if (comma == true){
            CalculatorError.calculatorError();
        }

        return clearExpression;
    }

}
