package basic.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 空间复杂度是O(MN)
 */
public class DynamicProgramming {
    private int M;
    private int N;
    private int[][] best;
    private int DOWN=1;
    private int RIGHT=2;
    private List<Integer> path;
    public int getMaxAward(int[][] matrix) {
        calDP(matrix);
        calBestPath(matrix);
        return best[M-1][N-1];
    }
    public void printBestPath() {
        for (int i=path.size()-1;i>=0;i--) {
            if(path.get(i)==DOWN) {
                System.out.print("下 ");
            }else {
                System.out.print("右 ");
            }
        }
        System.out.println();
    }
    public void calDP(int[][] matrix) {
        M=matrix.length;
        N=matrix[0].length;
        best=new int[M][N];
        for (int i=0;i<M;i++) {
            for (int j = 0; j <N ; j++) {
                if(i==0 && j==0) {
                    best[i][j]=matrix[i][j];
                    //历史最好的状态与当前状态相加=当前最好的状态
                }else if(i==0) {
                    best[i][j]=best[i][j-1]+matrix[i][j];
                }else if(j==0) {
                    best[i][j]=best[i-1][j]+matrix[i][j];
                }else {
                    best[i][j]=Math.max(best[i-1][j],best[i][j-1])+matrix[i][j];
                }
            }
        }
    }
    public void calBestPath(int[][] matrix) {
        path=new ArrayList<>();
        int curX=M-1;
        int curY=N-1;
        for (int i=0;i<M+N-2;i++) {
            if(curX==0) {
                curY--;
                path.add(RIGHT);
            }else if(curY==0) {
                curX--;
                path.add(DOWN);
            }else {
                if(best[curX-1][curY] > best[curX][curY-1]) {
                    curX--;
                    path.add(DOWN);
                }else {
                    curY--;
                    path.add(RIGHT);
                }
            }
        }
    }

}
