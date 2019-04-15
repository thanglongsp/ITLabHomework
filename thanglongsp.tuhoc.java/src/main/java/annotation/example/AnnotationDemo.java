package annotation.example;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface SmartPhone {
    String os();
    int version();
}

@SmartPhone(os = "Android", version = 5)
class NokiaASeries {
    private String model;
    private int size;

    NokiaASeries(String model, int size) {
        this.model = model;
        this.size = size;
    }
}

public class AnnotationDemo {
    public static void main(String[] args) {
        NokiaASeries obj = new NokiaASeries("Fine", 5);
        Class c = obj.getClass();
        Annotation an = c.getAnnotation(SmartPhone.class);
        SmartPhone s = (SmartPhone) an;
        System.out.println(s);
    }
}
