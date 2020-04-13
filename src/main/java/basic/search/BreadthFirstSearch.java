package basic.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BreadthFirstSearch {
    private int M;
    private int N;
    private int DOWN=1;
    private int RIGHT=2;
    private SearchIteam bestIteam;
    private List<SearchIteam> queue=new ArrayList<SearchIteam>();
    class SearchIteam {
        private int curX;
        private int curY;
        private int dir;
        private int value;
        private SearchIteam lastIteam;

        public SearchIteam(int curX, int curY, int dir, int value) {
            this.curX = curX;
            this.curY = curY;
            this.dir = dir;
            this.value = value;
        }
    }

    /**
     * 如果找到且队列中的value较小，替换
     * @param newIteam
     * @return 是否在搜索队列中找到相应Iteam
     */
    private boolean replaceIteam(SearchIteam newIteam) {
        boolean find=false;
        for (int i=0;i<queue.size();i++) {
            SearchIteam searchIteam=queue.get(i);
            if(searchIteam.curX==newIteam.curX && searchIteam.curY == newIteam.curY) {
                find=true;
                if(searchIteam.value<newIteam.value) {
                    queue.remove(i);
                    queue.add(newIteam);
                }
                break;
            }
        }
        return find;
    }

    /**
     * 观察了下，似乎重复的元素都是位于搜索队列最后一个元素，是否可以只比对最后一个元素
     * @param newIteam
     * @return
     */
    private boolean replaceIteam2(SearchIteam newIteam) {
        boolean find=false;
        if(queue.size()!=0) {
            SearchIteam searchIteam=queue.get(queue.size()-1);
            if(searchIteam.curX==newIteam.curX && searchIteam.curY == newIteam.curY) {
                find=true;
                if(searchIteam.value<newIteam.value) {
                    queue.remove(queue.size()-1);
                    queue.add(newIteam);
                }
            }
        }
        return find;
    }
    private void search(int[][] matrix) {
        while (queue.size()>0) {
            SearchIteam searchIteam=queue.remove(0);
            int curX=searchIteam.curX;
            int curY=searchIteam.curY;
            int curValue=searchIteam.value;
            if(curX==M-1 && curY==N-1) {
                bestIteam=searchIteam;
            }
            if(curX<M-1) {
                SearchIteam newItem=new SearchIteam(curX+1,curY,DOWN,curValue+matrix[curX+1][curY]);
                newItem.lastIteam=searchIteam;
                if(!replaceIteam2(newItem)) {
                    queue.add(newItem);
                }
            }
            if(curY<N-1) {
                SearchIteam newItem=new SearchIteam(curX,curY+1,RIGHT,curValue+matrix[curX][curY+1]);
                newItem.lastIteam=searchIteam;
                if(!replaceIteam2(newItem)) {
                    queue.add(newItem);
                }
            }
        }
    }
    public int getMaxAward(int[][] matrix) {
        M=matrix.length;
        N=matrix[0].length;
        SearchIteam searchIteam=new SearchIteam(0,0,0,matrix[0][0]);
        queue.add(searchIteam);
        search(matrix);
        return bestIteam.value;
    }
    public void printBestPath() {
        List<Integer> dirList=new ArrayList<>();
        SearchIteam curIteam=bestIteam;
        while (curIteam!=null) {
            dirList.add(curIteam.dir);
            curIteam=curIteam.lastIteam;
        }
        //从dirList.size()-2开始输出，不输出起始点的dir
        for (int i=dirList.size()-2;i>=0;i--) {
            if(dirList.get(i) == DOWN) {
                System.out.print("下 ");
            } else if(dirList.get(i) == RIGHT) {
                System.out.print("右 ");
            }
        }
        System.out.println();
    }
}
