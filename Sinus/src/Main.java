import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        out.println("Sum for x = 1 : "+taylor(1));
        out.println("Sum for x = 0.5 : "+taylor(0.5));
        out.println("Sum for x = 0 : "+taylor(0));
        out.println("Sum for x = -1 : "+taylor(-1));
        out.println("Sum for x = -0.5 : "+taylor(-0.5));
    }

   private static double factorial(int n) {
        double factorial=1;
       int i=2;
       while (i<=n) {
           factorial = factorial * i;
           i++;
       }
        return factorial;
    }

    private static double taylor(double x){
        int n=100;
        double sum = 0;
        for(int i=0;i<n;i++) sum += ((power(-1, i) * power(x, 2 * i + 1)) / factorial(2 * i + 1));
        return sum;
    }

    private static double power(double x, int n) {
        if (n >= 0) {
            double y = 1;
            if (n <= 0) {
                return y;
            }
            do {
                if (n % 2 == 0) {
                    x = x * x;
                } else {
                    y = y * x;
                    x = x * x;
                }
                n = n / 2;
            } while (n > 0);
            return y;
        } else {
            if (n != Integer.MIN_VALUE) {
                n = n * (-1);
                return 1.0 / power(x, n);
            }
            n = (n + 1) * (-1);
            return 1.0 / (power(x * x, n));
        }
    }
}

