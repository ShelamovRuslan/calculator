public class RunCalc {
    public static void main(String[] args) {

        runCalc();
    }
    public static void runCalc() {
        System.out.println("Калькулятор\nВсе записанные выражения\n" +
                "Изменение выражения\nНайти выражения\nНайти все выражения со значениями больше введенного" +
                "\nНайти все выражения со значениями меньше введенного\nЗакрыть");

        String command = Calculator.console("Что сделать?");

        switch(command) {
            case "Калькулятор":
                Calculator.core("Введите арифметичное выражение.");
                break;
            case "Все записанные выражения":
                Data.report();
                break;
            case "Изменение выражения":
                Data.editeData();
                break;
            case "Найти выражения":
                Data.searchData();
                break;
            case "Найти все выражения со значениями больше введенного":
                Data.searchGreaterData();
                break;
            case "Найти все выражения со значениями меньше введенного":
                Data.searchLowerData();
                break;
            case "Закрыть":
                break;

        }
    }
}
