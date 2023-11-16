package org.example.functionalinterfaces;

import org.apache.commons.math3.fraction.Fraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.math3.fraction.Fraction.*;
import static org.apache.commons.math3.fraction.Fraction.ONE_FIFTH;
import static org.apache.commons.math3.fraction.Fraction.THREE_FIFTHS;

public class Box<T> {

    private T t;          

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {

        B b1 = new B() {
            @Override
            public void m1() {

            }
        };
        B b2 = () -> System.out.println("blah blah");

        List<B> bList1 = results(b1, b2);
        List<A> bList2 = results2(b1, b2);

        System.out.println("test");

    }


    public static <T extends A> List<T> results(T t1, T t2){
        List<T> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        return list;


    }




    public static List<A> results2(A t1, A t2){
        List<A> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        return list;
    }


    public static <T extends A> T results3(T t){
        t.m1();
        return t;
    }



}

interface A {
    void m1();
}

interface B extends A {

}


class Utility {

    public static void main(String[] args) {

        //All these works as all these Lists are made up of Objects extending java.lang.Number
        System.out.println(Collections.max(getList(new Integer[] {1,2,3,4,5})));
        System.out.println(Collections.max(getList(new Double[] {1.1, 2.2, 3.3,4.4,5.5})));
        System.out.println(Collections.max(getList(new Fraction[]{ONE_FIFTH, THREE_FIFTHS, ONE_THIRD})));

        //This is invalid because Object A is not a 'Number'
        //List<A> aList = getList(new A[] {() -> {}, () -> {}})



    }
    static <T extends Number> List<T> getList(T[] tArr) {
        return List.of(tArr);
    }

    /*static <T> List<? super C> lowerBoundSample(T[] tArr) {
        return List.of(tArr);
    }*/
}

interface C extends B {}

interface D extends C {}

