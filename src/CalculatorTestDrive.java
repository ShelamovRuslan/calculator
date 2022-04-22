import javax.swing.*;

public class CalculatorTestDrive {
    public static void main(String[] args) {
        String str = JOptionPane.showInputDialog("Введіть арифметичний вираз");
        str = CalculatorDebugger.calculatorDebugger(str);
        System.out.println(str);
        System.out.println(Calculator.calculator(str));
        System.exit(0);
    }
}
