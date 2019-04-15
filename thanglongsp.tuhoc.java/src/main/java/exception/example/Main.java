package exception.example;

public class Main {
    public static void main(String[] args) {
        try {
            divide2number(1,0);
        } catch (ExceptionExample e) {
            System.out.println(e.getError());
        }
    }

    public static void divide2number(int a, int b) throws ExceptionExample {
        try {
            int result = a / b;
            System.out.println(result);
        }catch (Exception e){
            throw new ExceptionExample("Err divide by 0");
        }
    }
}

