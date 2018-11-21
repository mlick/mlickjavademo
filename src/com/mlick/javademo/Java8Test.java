package com.mlick.javademo;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mlick on 2017-11-10.
 */

public class Java8Test {


    @Test
    public void test0() {
        Arrays.asList("p", "k", "u", "f", "o", "r", "k").forEach(System.out::println);
        System.out.println("----------------------------------------------------------");
        Stream.of("p", "k", "u", "f", "o", "r", "k").forEach(System.out::println);
        System.out.println("----------------------------------------------------------");
        Arrays.asList("p", "k", "u", "f", "o", "r", "k").parallelStream().forEach(System.out::println);
    }

    @Test
    public void test1() {
        Arrays.asList("p", "k", "u", "f", "o", "r", "k").forEach(e -> {
            System.err.println("===========1==================");
            System.err.println(e);
            System.err.println("===========2==================");
        });
    }


    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

    @Test
    public void test2() {//Java8 Stream流操作
        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8)
        );

        // Calculate total points of all active tasks using sum()
        final long totalPointsOfOpenTasks = tasks
                .stream()
                .filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();
        System.out.println("Total points: " + totalPointsOfOpenTasks);


        // Calculate total points of all tasks
        final double totalPoints = tasks
                .stream()
                .parallel()//并行处理数据
                .map(Task::getPoints) // or map( (task -> task.getPoints() )
                .reduce(0, Integer::sum);

        System.out.println("Total points (all tasks): " + totalPoints);


        //按照某种准则来对集合中的元素进行分组
        // Group tasks by their status
        final Map<Status, List<Task>> map = tasks
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);


        //计算整个集合中每个task分数（或权重）的平均值来结束task的例子
        // Calculate the weight of each tasks (as percent of total points)
        final Collection<String> result = tasks
                .stream()                                        // Stream< String >
                .mapToInt(Task::getPoints)                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble(points -> points / totalPoints)   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong(weigth -> (long) (weigth * 100)) // LongStream
                .mapToObj(percentage -> percentage + "%")      // Stream< String>
                .collect(Collectors.toList());                 // List< String >

        System.out.println(result);
    }


    @Test
    public void test3() {//stream 文件流的支持

        final Path path = new File("D:\\road2.txt").toPath();
        //try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
        try (Stream<String> lines = Files.lines(path)) {
            lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {// Date/Time API (JSR 310) 时间的支持
        // Get the system clock as UTC offset
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        // Get the local date and local time
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);

        System.out.println(date);
        System.out.println(dateFromClock);

        // Get the local date and local time
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);

        System.out.println(time);
        System.out.println(timeFromClock);

        // Get the local date/time
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);

        System.out.println(datetime);
        System.out.println(datetimeFromClock);

        // Get the zoned date/time
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);


        // Get duration between two dates
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
        System.out.println("Duration in hours: " + duration.toMinutes());

    }

    @Test
    public void test5() {//JavaScript引擎Nashorn
        //ScriptEngineManager manager = new ScriptEngineManager();
        //ScriptEngine engine = manager.getEngineByName("JavaScript");
        //
        //System.out.println(engine.getClass().getName());
        //System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;"));
    }

    @Test
    public void test6() {
        final String text = "Base64 finally in Java 8!";

        final String encoded = Base64
                .getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        final String decoded = new String(
                Base64.getDecoder().decode(encoded),
                StandardCharsets.UTF_8);
        System.out.println(decoded);
    }

    @Test
    public void test7() {
        long[] arrayOfLong = new long[20000];

        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.print(i + " "));
        System.out.println();
    }

    @Test
    public void test8() {

        Task task = new Task(Status.OPEN, 2);

        String str = Optional.ofNullable(task).map(task1 -> task1.getPoints() + "").orElse("");

        System.out.println(str);
    }

    @Test
    public void test9() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("statue", "1");
        hashMap = null;

        String s = Optional.ofNullable(hashMap).map(stringStringHashMap -> stringStringHashMap.get("statue")).orElse("-1");

        System.out.println(s);

        //String s2 = Optional.ofNullable(hashMap).flatMap(new Function<HashMap<String, String>, Optional<String>>() {
        //    @Override
        //    public Optional<String> apply(HashMap<String, String> stringStringHashMap) {
        //        return android.graphics.Path.Op;
        //    }
        //})
    }

    @Test
    public void test10() {
    }

}
