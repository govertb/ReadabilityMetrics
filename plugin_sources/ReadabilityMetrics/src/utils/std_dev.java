package utils;

public class std_dev {
    public static double std_dev(double[] values) {
        int count = 0;
        double mean = 0.0;
        double M2 = 0.0;
        double delta;
        
        for (double v : values) {
            count++;
            delta = v - mean;
            mean = mean + delta / count;
            M2 = M2 + delta * (v - mean);
        }
        return  Math.sqrt(M2 / (count - 1));
    }
}
