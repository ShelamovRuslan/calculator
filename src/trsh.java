public class trsh {
    public static void main(String[] args) {
        String exp = "10*20";
        String[] expressionArray = exp.split("\\*");

        for (String x: expressionArray){
            System.out.println(x);
        }
    }
}
