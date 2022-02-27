package cn.jianbin.algorithm.map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author aaron.zou
 * @date 2022/1/10 10:08 下午
 */
@UtilityClass
public class MapTest {
    public static void main(String[] args) {
        Map<User, String> map = new HashMap<>();
        User u1 = new User(1);
        User u2 = new User(2);

        map.put(u1, "u1");
        map.put(u2, "u2");

        System.out.println(map.get(u1));
        System.out.println(map.get(u2));
    }

    @AllArgsConstructor
    static class User {
        private Integer age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(age, user.age);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
