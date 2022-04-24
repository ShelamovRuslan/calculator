public class Error {

    public static void singError() {
        Calculator.core("В вашем выражение неверно расставлены знаки. Введите корректное выражение.");
    }

    public static void parenthesesError() {
        Calculator.core("В вашем выражение неверно раставлены скобки. Введите корректное выражение.");
    }

    public static void incorrectSymbolError() {
        Calculator.core("В вашем выражение присутствуют недопустимые символы. Введите корректное выражение.");
    }

}
