public class Test {
    public static void main(String[] args) {
        method(null);
    }
    public static void method(String param){
        switch (param){
            case "sth":
                System.out.println("it's sth");
                break;
            case "null":
                System.out.println("it's null");
                break;
            default:
                System.out.println("default");
        }
    }
}
