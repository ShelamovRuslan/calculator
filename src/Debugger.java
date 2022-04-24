import static java.lang.Character.isDigit;

public class Debugger {
    public static String core(String expression) {
        incorrectSymbol(expression);
        expression = clearExpression(expression);
        parentheses(expression);
        newDebugger(expression);
        expression = multiplicationSigns(expression);
        expression = expressionFinal(expression);
        expression = expressionMinus(expression);
        expression = expressionZero(expression);
        return expression;
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

    public static void incorrectSymbol (String expression){

            char[] charExpression = expression.toCharArray();
            for (char x: charExpression){
                if (!isDigit(x) && x != '+' && x != '-' && x != '*' && x != '/' && x != '(' && x != ')'  && x != ',' && x != '.' && x != ' ' ){
                    Error.incorrectSymbolError();
                }
            }
    }

    public static void newDebugger (String expression ){

       for (String example: new String[] {"++","+/","+*","/+","//","/*","-+","-/","-*","**","*/","*+","(*","(+","(/"}){
          if(expression.contains(example)){
              Error.singError();
          }
       }
       char[] charExpression = expression.toCharArray();
        for (char example: new char[] {'*','+','/','-','.',','}){
            if(charExpression[charExpression.length-1] == example){
                Error.singError();
            }
        }

    }

    public static String expressionMinus (String expression ){
        return expression.replace("--", "");
    }

    public static void parentheses (String expression){
        int countParentheses = 0;

        for(int i = 0; i < expression.length(); i++) {

            if(expression.charAt(i) == '(') countParentheses++;
            else if(expression.charAt(i) == ')') countParentheses--;
            if(countParentheses < 0)
                Error.parenthesesError();
        }
        if(countParentheses != 0) {
            Error.parenthesesError();
        }
            if(expression.contains("()")){
                Error.parenthesesError();
            }
        }


    public static String expressionZero (String expression ){
        char[] charExpression = expression.toCharArray();
        expression = "";
        boolean dot = false;
        for (int x = 0; x < charExpression.length; x++){

            if (charExpression[x] == '.'){
                expression += charExpression[x];
                dot = true;
            } else
            if (charExpression[x] == ' ' && isDigit(charExpression[x-1])){
                if (dot == false) {
                    expression += ".0 ";
                } else {
                    expression += " ";
                    dot = false;
                }
            } else {
                if (x == charExpression.length - 1 && charExpression[x] != ')'){
                    expression += charExpression[x];
                    expression += ".0";
                }else {
                    expression += charExpression[x];
                }
            }
        }

        expression = expression.replace("-0", "0");
        expression = expression.replace("-0", "0");
        expression = expression.replace("-0", "0");
        expression = expression.replace("-0", "0");

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
}
