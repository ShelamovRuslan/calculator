public class CalculatorTestDrive {
    public static void main(String[] args) {
        String str = "1+2*10";
        str = CalculatorDebugger.calculatorDebugger(str);
        System.out.println(str);
        System.out.println(Calculator.calc(str));
    }
}
