package org.example;

import org.example.functionalinterfaces.MathBiOperation;
import org.example.functionalinterfaces.MathTriOperation;
import org.example.functionalinterfaces.MathUniOperation;

class H {

}

public class LambdaAndMethodParamTests {


    private static H test(H h){
        System.out.println(h);
        return h;
    }

    private static <R, S> S test1(R h) {
        S s = null;
        Integer i = null;
        i.toString();

        System.out.println(h);
        return s;
    }



    public static void main(String[] args) {

        test1(5);


        performUniOperation(2, (x) -> x*10);
        performBiOperation(2,5, (x,y) -> x*y);
        performtriOperation(2, 5,10, (x,y,z) -> x*y*z);


        new LambdaOps().performUniOperation(2, (x) -> x*10);
        new LambdaOps().performBiOperation(2,5, (x,y) -> x*y);
        new LambdaOps().performtriOperation(2, 5,10, (x,y,z) -> x*y*z);
    }


    public static <T> T performUniOperation(T t, MathUniOperation<T> op){
        T t1 = op.perform(t);
        System.out.println(t1);
        return t1;

    }


    public static <T1, T2> T1 performBiOperation(T1 t1, T2 t2, MathBiOperation<T1, T2> op) {
        T1 t11 = op.perform(t1, t2);
        System.out.println(t11);
        return t11;

    }

    public static <X, Y, Z> X performtriOperation(X x, Y y, Z z, MathTriOperation<X, Y, Z> op) {
        X x1 = op.perform(x,y,z);
        System.out.println(x1);
        return x1;

    }



}

class LambdaOps {
    public <T> T performUniOperation(T t, MathUniOperation<T> op){
        T t1 = op.perform(t);
        System.out.println(t1);
        return t1;

    }


    public <T1, T2> T1 performBiOperation(T1 t1, T2 t2, MathBiOperation<T1, T2> op) {
        T1 t11 = op.perform(t1, t2);
        System.out.println(t11);
        return t11;

    }

    public <X, Y, Z> X performtriOperation(X x, Y y, Z z, MathTriOperation<X, Y, Z> op) {
        X x1 = op.perform(x,y,z);
        System.out.println(x1);
        return x1;

    }
}


