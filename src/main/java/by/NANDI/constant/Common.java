package by.NANDI.constant;

import java.time.Duration;

public interface Common {
    interface Data {
        String SEARCH_WORD_1 = "Иван";
    }

    interface Timeouts {
        Integer LOW = 1000;
        Integer MIN = 5000;
        Integer MID = 15000;
        Integer MAX = 50000;
    }

    interface DurationTimeout {
        Duration LOW   = Duration.ofSeconds(1);
        Duration TWO   = Duration.ofSeconds(2);
        Duration MIN   = Duration.ofSeconds(5);
        Duration MID   = Duration.ofSeconds(10);
        Duration MAX   = Duration.ofSeconds(30);
        Duration HIGH  = Duration.ofSeconds(300);
    }
}
