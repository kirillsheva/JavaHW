


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.foo();


        System.out.println("Using collection:");
        Constructor<Singleton> constructor;
        List<Singleton> collection = new ArrayList<>();
        try {
            constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        collection.add(constructor.newInstance());
        collection.add(constructor.newInstance());
        collection.add(constructor.newInstance());
            for (Singleton msl : collection) {
                msl.foo();
            }
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}