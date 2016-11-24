package java9.cmpactstrings;

import org.openjdk.jol.info.GraphLayout;

/**
 * Created by kopernik on 20/11/16.
 */
public class CompactEx {

    public static void main(String[] args) {
        String s = new String("Hello!");
        System.out.println(String.format("String (%s) = %d bytes",
                s,
                GraphLayout.parseInstance(s).totalSize()));
    }
}
