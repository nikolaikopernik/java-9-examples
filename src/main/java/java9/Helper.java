package java9;

import sun.misc.Unsafe;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by kopernik on 20/11/16.
 */
public class Helper {
    public static Unsafe UNSAFE = null;

    static{
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
