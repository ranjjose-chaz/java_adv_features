package org.example.functionalinterfaces;

public interface MathBiOperation<T1, T2> {
    public T1 perform(T1 t1, T2 t2);
}
