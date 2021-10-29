
public class Matrixes {
    private int n;
    private int m;
    private double [][] array;


    //Creates an n-by-m matrix given a certain array
    public Matrixes(int n, int m, double[][] array) {
        this.n = n;
        this.m = m;
        this.array = array;
    }

    //Creates an n-by-m matrix of zeros
    public Matrixes(int n, int m) {
        this.n = n;
        this.m = m;
        this.array = new double[n][m];
    }

    //Creates an n-by-m matrix filled with a scalar h
    public Matrixes(int n, int m, double h) {
        this.n = n;
        this.m = m;

        double[][] arrayEqual = new double [n][m];

        for(int i = 0; i < n;i++) {
            for(int j = 0; j < m; j++) {
                arrayEqual[i][j] = h;
            }
        }

        this.array = arrayEqual;
    }

    //Basic get and set commands
    public int getRowDimension() {
        return this.n;
    }

    public int getColumnDimension() {
        return this.m;
    }

    public double[][] getArray() {
        return this.array;
    }

    public void setArray(double [][] array) {
        this.array = array;
    }

    //Now we define a few matrixes operations

    //Sum
    public Matrixes plus(Matrixes B) throws InvalidOperationException {
        if(this.m == B.getColumnDimension() && this.n == B.getRowDimension()) {

            double[][] arrayC = new double[this.n][this.m];

            for(int i = 0; i < this.n; i++) {
                for(int j = 0; j< this.m; j++) {
                    arrayC[i][j] = this.array[i][j] + B.getArray()[i][j];
                }
            }

            Matrixes C = new Matrixes(this.n, this.m, arrayC);

            return C;

        }else {
            throw new InvalidOperationException();
        }
    }

    //Subtraction
    public Matrixes minus(Matrixes B) throws InvalidOperationException {
        if(this.m == B.getColumnDimension() && this.n == B.getRowDimension()) {

            double[][] arrayC = new double[this.n][this.m];

            for(int i = 0; i < this.n; i++) {
                for(int j = 0; j< this.m; j++) {
                    arrayC[i][j] = this.array[i][j] - B.getArray()[i][j];
                }
            }

            Matrixes C = new Matrixes(this.n, this.m, arrayC);

            return C;

        }else {
            throw new InvalidOperationException();
        }
    }

    //Multiplication by constant
    public Matrixes timesConstant(double k) {

        double[][] arrayC = new double[this.n][this.m];

        for(int i = 0; i < this.n; i++) {
            for(int j = 0; j< this.m; j++) {
                arrayC[i][j] = k*this.array[i][j];
            }
        }

        Matrixes C = new Matrixes(this.n, this.m, arrayC);

        return C;
    }

    //Matrix multiplication
    public Matrixes timesMatrix(Matrixes B) throws InvalidOperationException {

        if(this.m == B.getRowDimension()) {
            double[][] arrayC = new double[this.n][B.getColumnDimension()];

            for(int i = 0; i < this.n; i++) {
                for(int j = 0; j< B.getColumnDimension(); j++) {

                    double sumCk = 0;

                    for(int k = 0; k <this.m; k++) {
                        sumCk = sumCk + this.array[i][k]*B.getArray()[k][j];
                    }

                    arrayC[i][j] = sumCk;
                }
            }

            Matrixes C = new Matrixes(this.n, B.getColumnDimension(), arrayC);

            return C;

        } else {
            throw new InvalidOperationException();
        }
    }

    //Other useful functions

    //Takes the vector norm
    public double norm() throws InvalidOperationException {
        if (this.m == 1) {
            double sum = 0;
            for (int i = 0; i < this.n; i++) {
                sum += Math.pow(array[i][0], 2);
            }
            return Math.sqrt(sum);
        }else {
            throw new InvalidOperationException();
        }
    }

    //Normalizes the vector
    public void normalize() throws InvalidOperationException {
        for (int i = 0; i < this.n; i++) {
            array[i][0] /= this.norm();
        }
    }

    //Multiply column by constant
    public void column_by_constat(int column, double k) {
        for(int j = 0; j < this.n; j++) {
            this.array[j][column] *= k;
        }
    }
}
