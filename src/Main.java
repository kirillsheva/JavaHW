public class Main {


    private static double[] multiMatrix(double[][] g, double[] p){
        double[] multiResult = new double[p.length];
        for(int i=0; i<g.length; i++){
            double rowResult = 0.0f;
            for(int j=0; j<g.length; j++){
                rowResult+=g[i][j]*p[j];
            }
            multiResult[i] = rowResult;
        }
        return multiResult;
    }


    private static void getGoogleMatrix(double[][] transitionMatrix, double weight){


        for(int i=0; i<transitionMatrix.length; i++){
            for(int j=0; j<transitionMatrix.length; j++){
                transitionMatrix[i][j] *= weight;
                transitionMatrix[i][j] += (1-weight)/transitionMatrix.length;
            }
        }
    }


    private static boolean compareMatrix(double[] pageRankN, double[] pageRankN_1){
        for(int i=0; i<pageRankN.length; i++){
            if(pageRankN[i]-pageRankN_1[i]>0.0000001){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        double[][] transitionMatrix={
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1, 0}
        };
        double[] p={1,1,1,1,1,1,1};
        double weight = 0.85f;

        getGoogleMatrix(transitionMatrix, weight);

        double[] pageRank = multiMatrix(transitionMatrix, p);
        while(!compareMatrix(pageRank, p)){
            p = pageRank;
            pageRank = multiMatrix(transitionMatrix, p);
        }

        for(int i=0; i<pageRank.length; i++){
            System.out.println(pageRank[i]);
        }
    }

}