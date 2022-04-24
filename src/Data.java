import java.io.*;
import java.util.ArrayList;

public class Data {
    public String number;
    public  String expression;
    public  String result;

    public Data(String number, String expression, String result) {
        this.number = number;
        this.expression = expression;
        this.result = result;
    }
    public Data(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }
    public static ArrayList<Data> dataList() {
        ArrayList<Data> list = new ArrayList<Data>();
        BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("data.txt"));
                String line;

                while ((line = br.readLine()) != null) {
                    if (!line.equals("")) {
                        String[] arr = line.split("\t");
                        String[] array = arr[1].split(" = ");
                        Data data = new Data(arr[0], array[0], array[1]);
                        list.add(data);
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return list;
    }

    public static void dataFile(ArrayList<Data> dataList) {
        try {
            File file = new File("data.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            int i = 1;
            PrintWriter pw = new PrintWriter(file);
            for (Data x: dataList) {
                pw.println(String.format("%d\t%s = %s", i, x.expression, x.result));
                i++;
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void report() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("data.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    String[] arr = line.split("\t");
                    String[] array = arr[1].split(" = ");
                    System.out.println(String.format("%s\t%s = %s", arr[0], array[0], array[1]));
                }
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RunCalc.runCalc();
    }
    public static void editeData () {
        report();
        String number = Calculator.console("Введите номер выражение которое желаете изменить.");
        String expression = Calculator.console("Введите арифметичное выражение.");

        ArrayList<Data> list = dataList();
        for (Data x: list){
            if (x.number.equals(number)){
                x.expression = Debugger.core(x.expression);
                x.result = "" + Calculator.coreEditeExpression(x.expression);
                x.expression = expression;
                Data.dataFile(list);
            }
        }
        RunCalc.runCalc();
    }
    public static void searchData () {
        String number = Calculator.console("Введите искомое значение выражения");
        ArrayList<Data> list = dataList();
        int digit = Integer.parseInt(number);
        for (Data x: list){
            double result = Double.parseDouble(x.result);
            if (x.result.equals(number)){
                System.out.println(x.expression + " = " + x.result);
            } else
            if (result == digit){
                System.out.println(x.expression + " = " + x.result);
            }
        }
        RunCalc.runCalc();
    }
    public static void searchGreaterData () {
        String number = Calculator.console("Введите искомое значение выражения");
        ArrayList<Data> list = dataList();
        double result;
        double digit = Double.parseDouble(number);
        for (Data x: list){
            result = Double.parseDouble(x.result);
            if (result > digit){
                System.out.println(x.expression + " = " + x.result);
            }
        }
        RunCalc.runCalc();
    }
    public static void searchLowerData () {
        String number = Calculator.console("Введите искомое значение выражения");
        ArrayList<Data> list = dataList();
        double result;
        double digit = Double.parseDouble(number);
        for (Data x: list){
            result = Double.parseDouble(x.result);
            if (result < digit){
                System.out.println(x.expression + " = " + x.result);
            }
        }
        RunCalc.runCalc();
    }





}
