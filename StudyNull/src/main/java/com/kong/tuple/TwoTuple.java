package com.kong.tuple;

import java.util.Objects;

public class TwoTuple<A, B> {
    public A frist;
    public B second;

    public TwoTuple(A frist, B second) {
        this.frist = frist;
        this.second = second;
    }

    public A getFrist() {
        return frist;
    }

    public B getSecond() {
        return second;
    }

    public void setFrist(A frist) {
        this.frist = frist;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoTuple<?, ?> twoTuple = (TwoTuple<?, ?>) o;
        return Objects.equals(frist, twoTuple.frist) &&
                Objects.equals(second, twoTuple.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frist, second);
    }
}
