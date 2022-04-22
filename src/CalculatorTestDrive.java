public class CalculatorTestDrive {
    public static void main(String[] args) {
        String str = "-1*((-4*(--25634534)))*(10*-35453,45340)+(-22)";
        str = CalculatorDebugger.calculatorDebugger(str);
        System.out.println(str);
        System.out.println(Calculator.calculator(str));

    }
}
