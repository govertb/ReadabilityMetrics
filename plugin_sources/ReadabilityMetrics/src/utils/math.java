package utils;

/*  Benjamin Versteeg & Govert Brinkman (2015) 
    Class for calculating:
     - standard deviation
     - variation coefficient
     - average
     - sum
*/

public class math {
    
    // Method for calculating either the standard deviation or the variation coefficient.
    private static double innerCalculateVariance(double[] values, final boolean standardDeviation, final boolean variationCoefficient) {
        // This implementation allows the variance to be calculated in one pass.
        // Source: https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance#Online_algorithm
        // Donald E. Knuth (1998). The Art of Computer Programming, volume 2: Seminumerical Algorithms, 3rd edn., p. 232. Boston: Addison-Wesley.
        
        // assert on both stadard deviation and variation coefficient
        assert !(standardDeviation && variationCoefficient);
        
        // Cannot calculate the variance over less than 2 values
        if (values.length < 2) {
            return Double.NaN;
        }
        
        // calculate the variance in one pass
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
        double variance = M2 / (count - 1);
        
        // case of return standard deviation
        if (standardDeviation) { 
            return Math.sqrt(variance);
        }
        
        // case of return variation coefficient
        if (variationCoefficient) {
            return Math.sqrt(variance) / mean;
        }
        
        // if not standard deviation or variation coefficient return variance
        return variance;
    }
    
    // not used
    // Calculate the variance over double[] array
    public static double variance(double[] values) {
        return innerCalculateVariance(values, false, false);
    }
    
    // Calculate the standard deviation over double[] array
    public static double standardDeviation(double[] values) {
        return innerCalculateVariance(values, true, false);
    }
    
    // Calculate the variation coefficient over double[] array
    public static double variationCoefficient(double[] values) {
        return innerCalculateVariance(values, false, true);
    }
    
    // Calculate the sum over double[] array
    public static double sum(double[] values) {
        double summed = 0;
        for (double v : values) {
            summed += v;
        }
        return summed;
    }
    
    // Calculate the average over double[] array
    public static double average(double[] values) {
        return sum(values) / values.length;
    }
}
