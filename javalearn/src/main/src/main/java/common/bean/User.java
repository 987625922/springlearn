package common.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String userName;

    public static User create(Supplier<User> supplier) {
        return supplier.get();
    }

    public static void collide(User user) {
        System.out.println(user);
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    @Override
    public String toString() {
        return "User1{" +
                "userName='" + userName + '\'' +
                '}';
    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }
}
