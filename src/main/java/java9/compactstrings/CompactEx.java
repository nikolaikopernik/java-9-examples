package java9.compactstrings;

import org.openjdk.jol.info.GraphLayout;

/**
 * To disable CompactStrings
 * feature use -XX:-CompactStrings
 */
public class CompactEx {

    public static void main(String[] args) {
        String s = new String("Hello!");
        System.out.println(String.format("String (%s) = %d bytes",
                s,
                GraphLayout.parseInstance(s).totalSize()));
    }
}
