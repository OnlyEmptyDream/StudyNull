package com.kong.tuple;

public class TowTupleDemo {
    public static void main(String[] args) {
        TowTupleDemo demo = new TowTupleDemo();
        TwoTuple<Boolean, String> tuple1 = new TwoTuple<>(false, "hello");
        TwoTuple<Boolean, String> tuple2 = new TwoTuple<>(false, "hello");
        if(tuple1.equals(tuple2)){
            System.out.println("hello");
        }else{
            System.out.println("goodbye");
        }
    }
    public TwoTuple<Boolean, String> check(){
        TwoTuple<Boolean, String> demo = new TwoTuple<>(false, "hello");
//        System.out.println(demo.frist);
//        System.out.println(demo.second);
//        boolean flag = demo.frist;
        return demo;
//       HashMap<Integer, String>;
    }
}
