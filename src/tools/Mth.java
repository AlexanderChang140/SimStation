package tools;

public class Mth {
    public static int clamp(int val, int min, int max) {
        return Math.min(Math.max(val, min), max);
    }

    public static int overflow(int val, int min, int max) {
        if (val < min) {
            return max - val;
        }
        if (val > max) {
            return min + val;
        }

        return val;
    }
}
