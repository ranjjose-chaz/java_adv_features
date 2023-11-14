package org.example.functionalinterfaces;

import java.util.ArrayList;
import java.util.List;

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

class C {

}