package cz.jiripinkas.jba.entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TestVideo {

    public int i;

    public TestVideo(int i) {
        this.i = i;
    }

    public TestVideo() {
    }

    public static void main(String[] args) throws Exception {
        Consumer<TestVideo> createLog1 = new TestVideo()::createLog1;
        Consumer<? super TestVideo> log = createLog();
        Optional<TestVideo> testVideo = Optional.ofNullable(new TestVideo());
        testVideo.ifPresent(createLog1);
        testVideo.orElse(new TestVideo(123));
        Integer integer = Optional.<TestVideo>ofNullable(null).map(testVideo1 -> testVideo1.i).orElse(123);
        System.out.println("\nString.valueOf(integer): " + String.valueOf(integer));

        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(4,5,6));
        List<List<Integer>> listOfLists = new ArrayList<>(Arrays.asList(l1, l2));
        List<Integer> collect = listOfLists.stream().flatMap(x -> x.stream()).collect(Collectors.toList());

        System.out.printf(collect.toString());

    }

    private static Consumer<? super TestVideo> createLog() {
        return x -> System.out.printf(String.valueOf(x));
    }

    private void createLog1(TestVideo testVideo) {
        System.out.printf(String.valueOf(testVideo));
    }
}