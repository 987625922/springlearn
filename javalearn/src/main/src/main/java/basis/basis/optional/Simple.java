package basis.basis.optional;

import common.bean.User;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author LL
 * @Description:Optional类的使用
 */
public class Simple {

    public static void main(String[] args) throws Throwable {
        Optional<User> optionalUser = Optional.ofNullable(null);
        //获取真实的值，如果没有值就抛出异常
        optionalUser.get();
        //获取真实值是否为空
        boolean ispresent = optionalUser.isPresent();
        //如果真实bean不是空就会调用accept方法,是空就不会调用
        optionalUser.ifPresent(new Consumer<User>() {
            @Override
            public void accept(User user) {

            }
        });
        //如果真实bean不为空就返回真实bean(并且会运行orElse里面的方法)，否则返回orElse传入的值
        User elseUser = optionalUser.orElse(new User());
        //如果真实bean不为空就返回真实bean(不会运行get()方法，更加节省性能)，如果为空就运行get()方法
        User elseGetUser = optionalUser.orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        });
        //如果真实bean为空就会抛出异常
        User uthrowUser = optionalUser.orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException();
            }
        });
        //转换值
        //转换Optional<User1>为Optional<Object>
        Optional<String> mapUser = optionalUser.map(new Function<User, String>() {
            @Override
            public String apply(User user) {
                user.getUserName();
                return "把user转换成String";
            }
        });
        //       和map不同的是，apply返回的是Optional，而不是对象
        Optional<String> flatMapUser = optionalUser.flatMap(new Function<User, Optional<String>>() {
            @Override
            public Optional<String> apply(User user) {
                return Optional.of("返回的是Optional<String>而不像上面的String");
            }
        });
        //过滤器filter
        //如果test的返回值是true就返回Optional，如果是false就返回空的Optional
        optionalUser.filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getUserName().equals("用户名如果相等就返回这个Optional");
            }
        });


    }

}
