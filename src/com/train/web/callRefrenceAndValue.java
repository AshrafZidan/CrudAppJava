package com.train.web;

public class callRefrenceAndValue {

//        value Example
    int data=50;

//    void change(int data){
//        data=data+100;//changes will be in the local variable only
//        System.out.println(data);
//    }

//    refrence Example
    void change(callRefrenceAndValue op){
        op.data=op.data+100;//changes will be in the instance variable
        System.out.println(data);
    }

//    public static void main(String args[]){
//        callRefrenceAndValue op=new callRefrenceAndValue();
//
////        System.out.println("before change "+op.data);
////        op.change(500);
////        System.out.println("after change "+op.data);
//
//        System.out.println("before change "+op.data);
//        op.change(op);//passing object
//        System.out.println("after change "+op.data);
//
//
//    }

}
