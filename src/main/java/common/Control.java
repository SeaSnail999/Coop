package common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Control {
    static Scanner scanner = new Scanner(System.in);

    static void printStackTrace() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            System.out.println(i + " : " + element.getMethodName() + " " + element.getLineNumber());
        }
    }

    static final HashSet<String> fieldsMap = new HashSet<>();
    static String fields = "city,country,last_seen";

    static {
        String[] parts = fields.split(",");
        fieldsMap.addAll(Arrays.asList(parts));
    }

    static boolean hasField(String field) {
        return fields.contains(field);
    }
}
