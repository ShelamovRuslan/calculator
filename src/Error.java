public class Error {

    public static void singError() {
        System.out.println("В вашем выражение неверно расставлены знаки. Введите корректное выражение.");
        RunCalc.runCalc();
    }

    public static void parenthesesError() {
        System.out.println("В вашем выражение неверно раставлены скобки. Введите корректное выражение.");
        RunCalc.runCalc();
    }

    public static void incorrectSymbolError() {
        System.out.println("В вашем выражение присутствуют недопустимые символы. Введите корректное выражение.");
        RunCalc.runCalc();
    }

}
