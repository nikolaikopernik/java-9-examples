package java9.varhandle;

/**
 * Created by kopernik on 20/11/16.
 */
public class ReadOpaqueEx {
    public static int flag = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            int f = flag;
            while(true){
                if(flag!=f) break;
            }
            System.out.println(flag);
        });

        Thread t2 = new Thread(() -> {
            flag++;
        });

        t1.start();
        t2.start();
    }
}
