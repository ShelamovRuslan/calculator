import static java.lang.Character.isDigit;

public class CalculatorDebugger {
    public static String calculatorDebugger(String expression) {
        expression = clearExpression(expression);

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
                   if (charExpression[i - 1] == '(' && charExpression[i + 1] == '(') {
                       CalculatorError.calculatorError();
                   }

                   if (arithmeticSign == true){
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

                   if (charExpression[i - 1] == ')' && charExpression[i] == '('){
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

        parentheses(clearExpression);

        clearExpression = multiplicationSigns(clearExpression);

        return clearExpression;

    }

    public static String clearExpression (String expression){
        char[] charExpression = expression.toCharArray();
        expression = "";
        for (char x: charExpression){
            if (isDigit(x) || x == '+' || x == '-' || x == '*' || x == '/' || x == '(' || x == ')'  || x == ',' || x == '.' )
                expression += x;
        }
        return expression;
    }

    public static void parentheses (String expression){
        int count = 0;

        for(int i = 0; i < expression.length(); i++) {

            if(expression.charAt(i) == '(') count++;
            else if(expression.charAt(i) == ')') count--;
            if(count < 0)
                CalculatorError.calculatorError();
        }
        if(count != 0) {
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
            expression += charExpression[i];
        }

        return expression;
    }

}