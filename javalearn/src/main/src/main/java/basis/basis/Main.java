package basis.basis;

import common.bean.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * java基础的使用
 * 1.接口内实现方法
 * 2.可变参数String...
 * 3.Optional判空
 */
public class Main {

    public static void main(String[] args) {
        //可变参数的使用
        print("输出", "可变参数");
    }

    /**
     * 可变参数使用
     * <p>
     * 即string。。。 out的使用
     */
    public static void print(String... out) {
        StringBuffer sb = new StringBuffer();
        for (String str : out) {
            sb.append(str);
        }
        System.out.println("可变参数使用：" + sb.toString());
    }

    /**
     * java8
     * 函数式接口
     */
    @Test
    public void testFunctionalInterface() {
        Converter<String, Integer> converter = i -> i + "";
        System.out.println(converter.run(123));
    }

    /**
     * 方法引用
     * 方法引用使用一对冒号 ::
     */
    @Test
    public void testMethodReference() {
        //构造器引用 User.create(() -> new User())
        User user = User.create(User::new);
        //静态方法引用
        List<User> users = new ArrayList<>();
        users.add(user);
        //users.forEach(user1 -> User.collide(user1));
        users.forEach(User::collide);
        //方法的引用 users.forEach(user1 -> user1.repair());
        users.forEach(User::repair);
    }

    /**
     * Stream接口(Collection的子类,map不支持)
     */
    @Test
    public void testStream() {
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");
        //排序 sort 不会影响原有数据源
        //过滤器 filter 只保留符合条件的元素
        //转换成其他类型的Stream
        Stream<String> stream = list.stream()
                .sorted(String::compareTo)
                .filter(str -> str.equals("11"))
                .map(String::toUpperCase);//转成大写
//      List list = stream.collect(Collectors.toList());
        stream.forEach(System.out::println);

        //Match匹配  判断的条件里，任意一个元素成功，返回true
        boolean anyStartsWith = stream.anyMatch(s -> s.startsWith("1"));
        //判断条件里的元素，所有的都是，返回true
        boolean allMatchWith = stream.allMatch(s -> s.startsWith("1"));
        //判断条件里的元素，所有的都不是，返回true
        boolean noneMatch = stream.noneMatch(s -> s.startsWith("1"));
        System.out.println(anyStartsWith);

        //count 符合Stream中元素的个数
        System.out.println(stream.count());

        //Reduce 规约 把所有的item合成一个Optional
        Optional<String> reduced = list.stream().reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

        //parallelStream 并行的流
        Stream parallelStream = list.parallelStream();

        /** ========= Map类型不支持stream，不过Map提供了一些新的有用的方法来处理
         * 一些日常任务  ============== */
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, "val" + i);
        }
        map.forEach((id, value) -> System.out.println("id:" + id +
                ",value:" + value));
        //替换指定key的value
        map.computeIfPresent(3, (num, val) -> val + val);
        //判断指定key的value是否为空
        map.containsKey(9);
        //若key对应的value为空，会将返回值存入并返回
        map.computeIfAbsent(23, num -> "val" + num);
        //根据key删除一个键值
        map.remove(3);
        //根据key和value删除一个键值
        map.remove(3, "value");
        //有就返回value，没有就返回后面的值
        map.getOrDefault(42, "not found");
        //map元素合并 键名不存在则插入，否则则对原键对应的值做合并操作并重新插入到map
        map.merge(9, "val9",
                (value, newValue) -> value.concat(newValue));
        map.get(9);
    }


}
