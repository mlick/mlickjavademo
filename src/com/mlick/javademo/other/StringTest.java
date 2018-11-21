package com.mlick.javademo.other;


//
public class StringTest {

    public static void main(String[] args) {
        String s = new String("1");
        System.out.println(s);
        System.out.println(s.intern());
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
//        System.out.println(s3.intern());
        String s4 = "11";
        System.out.println(s3.intern());
        System.out.println(s3 == s4);
    }


//    public static void main(String[] args) {
//        String s1 = new String("he") + new String("llo");
//        String s2 = new String("h") + new String("ello");
//        String s3 = s1.intern();
//        String s4 = s2.intern();
//        System.out.println(s1 == s3);// true
//        System.out.println(s1 == s4);// true
//    }

}
