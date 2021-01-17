package basic.dynamic;
/**
 * 类似杨辉三角，不过每个位置的数据可以随意填写，经过某个数字只能到达下面一层相邻的两个数组
 * 假设你站在第一层，向下移动，我们把移动到最底层所经过的所有数字之和，定义为路径的长度
 * 请你编程求出从最高层移动到最底层的最短路径长度
 */
public class YangHuiTriangle {
    public static YangHuiTriangle main=new YangHuiTriangle();
    public static void main(String[] args) {
        int[][] params=new int[][]{{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}};
        System.out.println(main.yangHuiTriangle(params));
        System.out.println(main.yangHuiTriangle2(params));
        System.out.println(main.yangHuiTriangle3(params));
    }
    /**
     * 二维数组解法
     * params ={{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}}
     * 创建dy[params.length][params.length]动态数组，i代表当前第i行，j代表当前第j个元素
     * dy[i][j]代表走到当前元素的最短路径（包含当前元素的值）
     * @param params
     * @return
     */
    public int yangHuiTriangle(int[][] params) {
        if(params == null || params.length ==0) return 0;
        int[][] dy=new int[params.length][params.length];
        dy[0][0]=params[0][0];
        for (int i=1;i<params.length;i++) {
            for (int j=0;j<params[i].length;j++) {
                if(j == 0) {
                    dy[i][j]=dy[i-1][j]+params[i][j];
                }else if(j == params[i].length -1) {
                    dy[i][j] =dy[j-1][j-1]+params[i][j];
                }else {
                    dy[i][j] = params[i][j]+Math.min(dy[i-1][j-1],dy[i-1][j]);
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for (int j=0;j<params[params.length-1].length;j++) {
            int pathLen=dy[params.length-1][j];
            if(pathLen<min) {
                min=pathLen;
            }
        }
        return min;
    }

    /**
     * 一维数组解法
     * @param params
     * @return
     */
    public int yangHuiTriangle2(int[][] params) {
        int[] dy=new int[params.length];
        dy[0]=params[0][0];
        for (int i=1;i<params.length;i++) {
            //从后往前遍历，防止前面的值被二次更新
            for (int j=params[i].length-1;j>=0;j--) {
                if(j==params[i].length-1) {
                    dy[j]=dy[j-1]+params[i][j];
                }else if(j==0) {
                    dy[j]=dy[j]+params[i][j];
                }else {
                    dy[j]=params[i][j]+Math.min(dy[j-1],dy[j]);
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for (int i=0;i<dy.length;i++) {
            if(dy[i]<min) {
                min=dy[i];
            }
        }
        return min;
    }

    /**
     * 解法三
     * 倒置杨辉三角，从底部向上做状态迁移，代码最为简洁
     * @param params
     * @return
     */
    public int yangHuiTriangle3(int[][] params) {
        //这个地方之所以要length+1,是为了循环内处理时好处理j+1，无需额外判断
        int[] min=new int[params.length+1];
        for (int i=params.length-1;i>=0;i--) {
            int[] raws=params[i];
            for (int j=0;j<raws.length;j++) {
                //取下一层中两个元素中较小的一个，加上当前元素作为路径长度
                min[j]=Math.min(min[j],min[j+1])+raws[j];
            }
        }
        return min[0];
    }
}
