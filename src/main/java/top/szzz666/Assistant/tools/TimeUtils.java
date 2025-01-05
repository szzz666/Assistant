package top.szzz666.Assistant.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static top.szzz666.Assistant.AssistantMain.nkServer;
import static top.szzz666.Assistant.AssistantMain.plugin;

public class TimeUtils {
    public static class checkExpiredTask {
        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class ExpiredTask {
            private long time;
            private Runnable runnable;
            private int id;
        }

        public static final HashMap<Integer, ExpiredTask> ets = new HashMap<>();


        public static int addExpiredTask(long time, Runnable runnable) {
            int id = ets.size() + 1;
            ets.put(id, new ExpiredTask(time, runnable, id));
            return id;
        }
        public checkExpiredTask() {
            nkServer.getScheduler().scheduleRepeatingTask(plugin, () -> {
                for (ExpiredTask et : ets.values()) {
                    if (nowTime() > et.getTime()) {
                        et.getRunnable().run();
                        ets.remove(et.getId());
                    }
                }
            }, 20, true);
        }
    }

    public static long nowTime() {
        return Instant.now().getEpochSecond();
    }

    public static String intTimeToFormattedTime(long time) {
        Instant instant = Instant.ofEpochSecond(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static String intTimeToFormattedTime(long time, String pattern) {
        Instant instant = Instant.ofEpochSecond(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static long formattedTimeToIntTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static long formattedTimeToIntTime(String time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }
}
