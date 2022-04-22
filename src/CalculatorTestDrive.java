public class CalculatorTestDrive {
    public static void main(String[] args) {
        String str = "-1*((-4gfdgdfg*(3--         2)))*(10*-35453s45)+(-22)";
        str = CalculatorDebugger.calculatorDebugger(str);
        System.out.println(Calculator.calculator(str));

    }
}
