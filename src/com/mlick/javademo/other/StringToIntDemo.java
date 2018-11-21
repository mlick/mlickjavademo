package com.mlick.javademo.other;

public class StringToIntDemo {


    public static void main(String[] args) {


        String str = "1234056780";

        System.out.println(string2Int(str));


//        System.out.println(Integer.parseInt(str));
//        System.out.println(Integer.valueOf(str));

    }

    /* made by lixiangxin for 2018-11-9 13:56:31  */
    private static int string2Int(String str) {

        if (str == null || str.length() == 0) {
            throw new NullPointerException("参数为空异常");
        }

        char[] chars = str.toCharArray();
        int i = 0, nagetive = 1, len = chars.length - 1;
        long sum = 0;
        if (chars[0] == '-') {
            nagetive = -1;
            i = 1;
            len--;
        }
        if (chars[0] == '+') {
            i = 1;
            len--;
        }
        for (; i < chars.length; i++, len--) {
            int digit = chars[i] - 48;
            if (digit > 9 || digit < 0) {
                throw new NumberFormatException("字符异常");
            }
            sum += digit * (Math.pow(10, len));
            if ((nagetive * sum) > Integer.MAX_VALUE || (nagetive * sum) < Integer.MIN_VALUE) {
                throw new NumberFormatException("字符异常");
            }
        }

        return (int) sum;
    }


}
