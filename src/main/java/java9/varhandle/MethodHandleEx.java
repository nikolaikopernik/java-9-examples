package java9.varhandle;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kopernik on 20/11/16.
 */
@State(Scope.Thread)
public class MethodHandleEx {
    Method method;
    MethodHandle mh;
    static final MethodHandle smh;

    static {
        MethodHandle mm  = null;
        try {
            mm  = MethodHandles.lookup()
                    .findStatic(MethodHandleEx.class, "run",
                            MethodType.methodType(void.class));
        }catch (Exception e){
        }
        smh = mm;
    }

    ;

    @Setup
    public void init() throws NoSuchMethodException, IllegalAccessException {
        method  = MethodHandleEx.class.getDeclaredMethod("run", null);
        mh = MethodHandles.lookup()
                .findStatic(MethodHandleEx.class, "run",
                        MethodType.methodType(void.class));
    }

    @Benchmark
    public void callViaReflection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        method.invoke(null);
    }

    @Benchmark
    public void callViaMethodHandlers() throws Throwable {
        mh.invoke();
    }

    @Benchmark
    public void callViaStaticMethodHandlers() throws Throwable {
        smh.invoke();
    }

    @Benchmark
    public static void run(){
        //System.out.println("executed");
    }

    /**
     * Benchmark                                    Mode  Cnt           Score           Error  Units
     * MethodHandleEx.callViaReflection            thrpt    5   173197702.856 ±   6248820.422  ops/s
     * MethodHandleEx.callViaMethodHandlers        thrpt    5   238163997.342 ±   6559254.905  ops/s
     * MethodHandleEx.callViaStaticMethodHandlers  thrpt    5  2888417022.488 ± 314492040.427  ops/s
     * MethodHandleEx.run                          thrpt    5  2807143766.111 ± 290090963.700  ops/s
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(MethodHandleEx.class.getSimpleName()).threads(1)
                .forks(1).shouldFailOnError(true).shouldDoGC(true).warmupIterations(5).measurementIterations(5)
                .jvmArgs("-server").build();
        new Runner(options).run();
    }
}
