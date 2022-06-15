import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class App {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        String s = "-";
        Boo boo = new Boo();

        serialization(boo, s);
    }

    static <T> void serialization(T c, String s) throws IllegalAccessException, InstantiationException {
        Class cls = c.getClass();
        Field[] variables = cls.getDeclaredFields();

        for (Field variable : variables) {
            Annotation[] annotations = variable.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof MyAnnotation) {
                    MyAnnotation myAnn = (MyAnnotation) annotation;
                    System.out.println(s + myAnn.name() + " " + variable.get(c));
                    if (!variable.getType().isPrimitive()) {
                        serialization(variable.getType().newInstance(), "  " + s);
                    }
                }
            }
        }

    }
}

class Boo {
    @MyAnnotation(name = "test1")
    String in = "2";
    @MyAnnotation(name = "test2")
    Foo foo = new Foo();
}

class Foo {
    @MyAnnotation(name = "test3")
    String str = "rtvf";
    @MyAnnotation(name = "test4")
    int n = 3;
    @MyAnnotation(name = "test5")
    Moo moo = new Moo();
}

class Moo {
    @MyAnnotation(name = "test6")
    boolean b = true;
}