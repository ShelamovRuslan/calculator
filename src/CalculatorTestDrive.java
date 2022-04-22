public class CalculatorTestDrive {
    public static void main(String[] args) {
        String str = "-1*((-4*(--2)))*(10*-3545345340)+(-22)";
        str = CalculatorDebugger.calculatorDebugger(str);
        System.out.println(str);
        System.out.println(Calculator.calc(str));
    }
}
