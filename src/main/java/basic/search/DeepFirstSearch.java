package basic.search;
import java.util.ArrayList;
import java.util.List;
public class DeepFirstSearch {
    // 定义best(i,j) 和 M N
    private int[][] best = null;
    private int M = 0;
    private int N = 0;

    // 定义方向常量
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    // 当前搜索的方向数组
    private List<Integer> curPath = null;
    // 记录最大值对应的方向数组
    private Integer[] bestPath = null;

    // 当前搜索点
    private int curX = 0;
    private int curY = 0;
    // 当前搜索累计值
    private int value = 0;
    // 记录搜索到的最大值
    private int maxValue = 0;

    // 往某个方向前进
    private void goDir(int dir, int[][] matrix) {
        if( dir == DOWN) {
            curX++;
        }else {
            curY++;
        }
        curPath.add(dir);
        value+=matrix[curX][curY];
    }

    // 往某个方向回退
    private void goBackDir(int dir, int[][] matrix) {
        value-=matrix[curX][curY];
        curPath.remove(curPath.size()-1);
        if( dir == DOWN ) {
            curX--;
        }else {
            curY--;
        }
    }

    // 深搜
    private void search(int dir, int[][] matrix) {
        goDir(dir, matrix);
        //到达终点
        if(curX == M-1 && curY == N-1) {
            if(value>maxValue) {
                //记录最大值，最佳路径
                maxValue=value;
                bestPath=new Integer[curPath.size()];
                curPath.toArray(bestPath);
            }
        }else if(value <= best[curX][curY]) {

        }else {
            //替换best路径
            best[curX][curY]=value;
            if(curX<M-1) {
                search(DOWN, matrix);
            }
            if(curY<N-1) {
                search(RIGHT, matrix);
            }
        }
        //搜完一条路径之后需要回退，改搜其他路径
        goBackDir(dir, matrix);
    }

    // 获取最大值
    public int getMaxAward(int[][] matrix) {
        value=matrix[0][0];
        M=matrix.length;
        N=matrix[0].length;
        best=new int[M][N];
        curPath=new ArrayList<Integer>();
        if(M>1) {
            search(DOWN,matrix);
        }
        if(N>1) {
            search(RIGHT,matrix);
        }
        return maxValue;
    }

    // 打印最佳路径
    public void printBestPath() {
        // 打印路径
        for(int i = 0; i < bestPath.length; i++) {
            if(bestPath[i] == RIGHT) {
                System.out.print("右 ");
            } else {
                System.out.print("下 ");
            }
        }
        System.out.println();
    }
}
