import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    private String[] elements;
    private int i;
    private static String expression;
    private static double result;


    public static void core(String massage) {
        expression = Debugger.core(console(massage));
        Calculator calculator = new Calculator(expression);
        result = calculator.calculate();
        Data data = new Data(expression,
                "" + result);
        ArrayList<Data> list = Data.dataList();
        list.add(data);
        Data.dataFile(list);
        System.out.println(result);
        expression = "";
        result = 0.0;
        RunCalc.runCalc();
    }

    public static double coreEditeExpression(String expression) {
        expression = Debugger.core(expression);
        Calculator calculator = new Calculator(expression);
        result = calculator.calculate();
        return result;
    }

    public static String console (String message){
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        //return JOptionPane.showInputDialog(message);
        return console.nextLine();
    }

    public Calculator(String expr) {
        this.elements = expr.split(" ");
        this.i = 0;
    }

    public double calculate () {
        double first = multiply ();

        while (i < elements.length){
            String operator = elements[i];
            if (!operator.equals("+") && !operator.equals("-")){
                break;
            } else {
                i++;
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
        double first = parentheses ();

        while (i < elements.length){
            String operator = elements[i];
            if (!operator.equals("*") && !operator.equals("/")){
                break;
            } else {
                i++;
            }
            double second = parentheses ();
            if (operator.equals("*")){
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }

    public double parentheses () {
        String next = elements[i];
        double result;
        if (next.equals("(")) {
            i++;
            result = calculate();
            String closeBracket = "";
            if (i < elements.length){
                closeBracket = elements[i];
            }
            if (closeBracket.equals(")")){
                i++;
                return result;
            }
        }
        i++;
        return Double.parseDouble(next);
    }

}
