package com.jufeng.java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一个 Stream 只可以使用一次
 * limit 截取前limit个元素
 * skip 跳过skip前的元素
 * Stream 有三个 match 方法，从语义上说：
 *
 * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
 * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
 * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
 */
public class Test {


    public static void main(String [] a){
        mapTest();
        mapToInt();
        flatMap();
        filter();
        distinct();
        sorted();
        reduce();
        findfirst();
        gropuBy();
        groupStat();
    }


    private void createStream(){
        //第一种方式
        Stream stringStream = Stream.of("a","b","c","d");
        //2
        String [] stringArray = new String[]{"a","b","c","d"};
        Arrays.stream(stringArray);

        //3
        List<String>  list = Arrays.asList(stringArray);
        list.stream();

        //IntStream LongStream DoubleStream
    }

    private static void mapTest(){
        System.out.println("------------map------------------");
        Stream<String> stringStream = Stream.of("a","b","c","d");
        stringStream.map(String::toUpperCase).forEach(System.out::print);
        //map 可以转换成任意你想要的元素
    }

    public static void mapToInt(){
        System.out.println();
        System.out.println("------------mapToInt------------------");
        Stream<String> stringStream = Stream.of("1","2","3","4");
        stringStream.mapToInt(Integer::valueOf).forEach(System.out::print);
        // mapTo... 很多
    }

    public static void flatMap(){
        System.out.println();
        //最后会变成一个大的stream，都是数字，没有List
        System.out.println("------------flatMap------------------");
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        inputStream.flatMap(childlist-> childlist.stream()).forEach(System.out::print);
    }

    public static void filter(){
        System.out.println();
        System.out.println("------------filter------------------");
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Stream.of(sixNums).filter(x->x.intValue()%2==0).forEach(System.out::print);
    }

    public static void distinct(){
        System.out.println();
        System.out.println("------------distinct------------------");
        Stream<String> stringStream = Stream.of("1","2","3","3");
        stringStream.distinct().forEach(System.out::print);
    }
    public static void sorted(){
        System.out.println();
        System.out.println("------------sorted------------------");
        Stream<String> stringStream = Stream.of("6","3","1","2","5","4");
        stringStream.sorted(Comparator.reverseOrder()).forEach(System.out::print);
        //对象
       // stringStream.sorted(Comparator.comparing(对象排序的字段get方法).reversed()).forEach(System.out::print);
    }

    public static void findfirst(){
        System.out.println();
        System.out.println("------------findFirst------------------");
        Stream<String> stringStream = Stream.of("6","3","1","2","5","4");
      String s = stringStream.findFirst().orElse("999");
        System.out.println(s);
    }

    public static void reduce(){
        System.out.println();
        System.out.println("------------reduce------------------");
        // 累加
        Stream.of(1, 2, 3, 4).reduce(0,(x,y)-> x+y).intValue();

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
         double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
         int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
    }

    public static void gropuBy(){
        System.out.println();
        System.out.println("------------gropuBy------------------");
        Stream.of(1, 2, 3, 4).collect(Collectors.groupingBy(x->x<=2)).forEach((k,v)->{
            System.out.println(k+ " : " +v);
        });
    }

    public static void groupStat(){
        System.out.println();
        System.out.println("------------groupStat------------------");
        Stream.of(1, 2, 3, 4).collect(Collectors.groupingBy(x->x<=2,Collectors.counting())).forEach((k,v)->{
            System.out.println(k+ " : " +v);
        });
    }
}
