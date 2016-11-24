package java9.varhandle;

import java9.Helper;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import static java9.Helper.UNSAFE;

/**
 * Created by kopernik on 20/11/16.
 */
public class OffheapEx {
    public static void main(String[] args) {
        long address = UNSAFE.allocateMemory(1024);
        System.out.println("byte ("+address+") = " + UNSAFE.getByte(address+1));
        UNSAFE.putByte(address+1, (byte) 1);
        System.out.println("byte ("+address+") = " + UNSAFE.getByte(address+1));
        UNSAFE.freeMemory(address);

        ByteBuffer bb = ByteBuffer.allocateDirect(1024);
        System.out.println("byte(1) = " + bb.get(1));
        bb.position(1);
        bb.put((byte)1);
        System.out.println("byte(1) = " + bb.get(1));
        bb.clear();
    }
}
