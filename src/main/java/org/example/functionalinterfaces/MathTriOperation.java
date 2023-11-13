package org.example.functionalinterfaces;

@FunctionalInterface
public interface MathTriOperation<X, Y, Z> {
    X perform(X x, Y y, Z z);
    static void staticM1(){
        System.out.println("print m1");
    }

    static void staticM2(){
        System.out.println("print m2");
    }

    default void defMethod(){
        System.out.println("print m2");
    }


}
