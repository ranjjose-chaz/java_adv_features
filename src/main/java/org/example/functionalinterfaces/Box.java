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

        B b1 = new B() {};
        B b2 = new B() {};
        List<B> bList1 = results(b1, b2);
        List<A> bList2 = results2(new B(){}, new B(){});

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



}

interface A {}

interface B extends A {}

class C {}