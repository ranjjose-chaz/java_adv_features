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

        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Tiger> tigers = new ArrayList<>();
        List<SiberianTiger> siberianTigers = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        List<Dingo> dingos = new ArrayList<>();
        List<CreamyWhiteDingo> creamyWhiteDingos = new ArrayList<>();
        List<? extends Animal> AnimalExts= new ArrayList<Cat>();


        //ext(animals);
        ext(cats); // works because List<Cat> conforms to List<? extends Cat>
        ext(tigers); // works because List<Tiger> conforms to List<? extends Cat>
        ext(siberianTigers); // works because List<SiberianTiger>
                             // conforms to List<? extends Cat>
        //ext(dogs);        // fails because List<Dog> doesn't confirm to
                            // List<? extends Cat>

        sup(animals);   // works because Animal in List<Animal> is a super class of
                        // Tiger in List<? super Tiger>
        sup(cats);      // works because Cat in List<Cat> is a super class of
                        // Tiger in List<? super Tiger>
        sup(tigers);    // works because Tiger in List<Tiger> is a super class of
                        // Tiger in List<? super Tiger>. This is a special case.
        // sup(siberianTigers);    // fails because SiberianTiger in List<SiberianTiger> isn't a super class of
                                // Tiger in List<? super Tiger>
        // sup(dogs);   // fails because Dog in List<Dogs> isn't a super class of
                        // Tiger in List<? super Tiger>


    }
    static <T extends Number> List<T> getList(T[] tArr) {
        return List.of(tArr);
    }

    /**
     * This method can take any List of type Cat or its subclasses.
     * @param catExts List<? extends Cat > any <code>List<code/> with a type that <code>is-a-Cat<code/>
     */
    static void ext(List<? extends Cat > catExts) {
        List<? extends Tiger> cats1 = new ArrayList<>();
        //catExts.add(new Cat());

    }

    /**
     * This method can take any List of type Tiger  or its super classes.
     * @param tigerSuprs List<? super Tiger> any List with a type that's Tiger
     *                   or any of its super classes
     */
    static void sup(List<? super Tiger> tigerSuprs) {
        tigerSuprs.add(new Tiger());
        tigerSuprs.add(new SiberianTiger());

    }

}

interface C extends B {}

interface D extends C {}

class Animal {}
class Cat extends Animal {}
class Tiger extends Cat {}
class SiberianTiger extends Tiger {}



class Dog extends Animal{}
class Dingo extends Dog {}
class CreamyWhiteDingo extends Dingo {}




