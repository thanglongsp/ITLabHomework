package bai5.bai2;

import java.math.BigDecimal;
import java.util.*;

public class Java8Example {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("thanglong", 23, Arrays.asList("MinhHang", "NgocHa", "MyTam")),
                new User("thanglong1", 24, Arrays.asList("MinhHang1", "NgocHa1", "MyTam1")),
                new User("thanglong2", 25, Arrays.asList("MinhHang2", "NgocHa2", "MyTam2")),
                new User("thanglong3", 25, Arrays.asList("MinhHang3", "NgocHa3", "MyTam3"))
        );

        // show  users
        System.out.println("Show list user");
        System.out.println("----------------------");
        users.stream().forEach(System.out::println);

        System.out.println("\nGroup by age");
        Map<BigDecimal, List<User>> grByAge = new HashMap<>();
        for (User user : users) {
            grByAge.computeIfAbsent(BigDecimal.valueOf(user.getAge()), k -> new ArrayList<>()).add(user);
        }
        System.out.println(grByAge);

        // compare by age
        Comparator<User> comparator = Comparator.comparing(User::getAge);

        // Filter and map
        System.out.println("\nMap and Filter");
        Optional<String> optional = users.stream()
                .map(user -> user.getGirlFriends().stream())
                .flatMap(stringStream -> stringStream.filter(girlFriend -> girlFriend.equals("MinhHang")))
                .findAny();
        optional.ifPresent(System.out::println);

        // Sum
        System.out.println("\nSum age");
        int sum = users.stream()
                .mapToInt(User::getAge)
                .sum();
        System.out.println("Sum age : " + sum);

        // Count
        System.out.println("\nCount users");
        int count = (int) users.stream()
                .count();
        System.out.println("Count user : " + count);

        // Min
        System.out.println("\nMin by age");
        User minObject = users.stream().min(comparator).get();
        System.out.println(minObject.getName() + " : " + minObject.getAge());

        // Max
        System.out.println("\nMax by age");
        User maxObject = users.stream().max(comparator).get();
        System.out.println(maxObject.getName() + " : " + maxObject.getAge());
    }
}
