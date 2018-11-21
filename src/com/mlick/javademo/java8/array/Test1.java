package com.mlick.javademo.java8.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author mlick lixiangxin
 * @create 2018-02-06
 */
public class Test1 {


    public static void main(String[] args) {
        System.out.println(match("2342311111sdfasdfasdf12311112ffasdf1221111122111", "22"));

        System.out.println(sortCharactersInString("sdfiojisodfjsajdfjlkasdfnasdfasfklaskdfkasdkf"));
    }


    /** 正则匹配 */
    public static List<String> match(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        return matchedParts;
    }

    /** 按字母顺序排列字符串中的字符 */
    public static String sortCharactersInString(String input) {
        return Arrays.stream(input.split("")).sorted().collect(Collectors.joining());
    }


}
