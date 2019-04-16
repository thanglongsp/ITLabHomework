package bai15;

import org.springframework.stereotype.Component;

@Component
public class MySum {
    private int first;
    private int second;

    public String sum(String first, String second) {
        try {
            this.first = Integer.parseInt(first);
            this.second = Integer.parseInt(second);
            return String.valueOf(this.first + this.second);
        } catch (NumberFormatException e) {
            return "";
        }
    }
}