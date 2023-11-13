package org.example.functionalinterfaces;

public interface MathUniOperation<T> {
    public T perform(T t);

    public static void main(String[] args) {

        I1<Person> i11 = Person::printName;
        i11.perform(new Person());

    }


}


interface I1<T extends Person> {
    void perform(T t);
}

interface I2<Person> {
    void perform(Person t);
}



class Person {
    public void printName(){
        System.out.println("prints Person");
    }
}

class SpecialPerson extends Person {

    @Override
    public void printName() {
        System.out.println("prints SpecialPerson");
    }
}
