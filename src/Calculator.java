

public class Calculator {

    private String[] tokens;
    private int pos;

    public static double calculator(String expr) {
        Calculator calculator = new Calculator(expr);
        return calculator.calculate();
    }

    public Calculator(String expr) {
        this.tokens = expr.split(" ");
        this.pos = 0;
    }

    public double calculate () {
        double first = multiply ();

        while (pos < tokens.length){
            String operator = tokens [pos];
            if (!operator.equals("+") && !operator.equals("-")){
                break;
            } else {
                pos ++;
            }
            double second = multiply ();
            if (operator.equals("+")){
                first += second;
            } else {
                first -= second;
            }
        }
        return first;
    }

    public double multiply () {
        double first = factor ();

        while (pos < tokens.length){
            String operator = tokens [pos];
            if (!operator.equals("*") && !operator.equals("/")){
                break;
            } else {
                pos ++;
            }
            double second = factor ();
            if (operator.equals("*")){
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }

    public double factor () {
        String next = tokens[pos];
        double result;
        if (next.equals("(")) {
            pos++;
            result = calculate();
            String closeBracket = "";
            if (pos < tokens.length){
                closeBracket = tokens[pos];
            }
            if (closeBracket.equals(")")){
                pos++;
                return result;
            }
        }
        pos++;
        return Double.parseDouble(next);
    }

}
