package leetcode;

/**
 * 题目概述：
 * 请在 n × n 的棋盘上，实现一个判定井字棋（Tic-Tac-Toe）胜负的神器，判断每一次玩家落子后，是否有胜出的玩家。
 * 在这个井字棋游戏中，会有 2 名玩家，他们将轮流在棋盘上放置自己的棋子。
 * 在实现这个判定器的过程中，你可以假设以下这些规则一定成立：
 *
 * 1 . 每一步棋都是在棋盘内的，并且只能被放置在一个空的格子里；
 * 2 . 一旦游戏中有一名玩家胜出的话，游戏将不能再继续；
 * 3 . 一个玩家如果在同一行、同一列或者同一斜对角线上都放置了自己的棋子，那么他便获得胜利。
 *
 * 示例:
 * 给定棋盘边长 n = 3, 玩家 1 的棋子符号是 "X"，玩家 2 的棋子符号是 "O"。
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> 函数返回 0 (此时，暂时没有玩家赢得这场对决)
 * |X| | |
 * | | | |    // 玩家 1 在 (0, 0) 落子。
 * | | | |
 *
 * toe.move(0, 2, 2); -> 函数返回 0 (暂时没有玩家赢得本场比赛)
 * |X| |O|
 * | | | |    // 玩家 2 在 (0, 2) 落子。
 * | | | |
 *
 * toe.move(2, 2, 1); -> 函数返回 0 (暂时没有玩家赢得比赛)
 * |X| |O|
 * | | | |    // 玩家 1 在 (2, 2) 落子。
 * | | |X|
 *
 * toe.move(1, 1, 2); -> 函数返回 0 (暂没有玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 2 在 (1, 1) 落子。
 * | | |X|
 *
 * toe.move(2, 0, 1); -> 函数返回 0 (暂无玩家赢得比赛)
 * |X| |O|
 * | |O| |    // 玩家 1 在 (2, 0) 落子。
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> 函数返回 0 (没有玩家赢得比赛)
 * |X| |O|
 * |O|O| |    // 玩家 2 在 (1, 0) 落子.
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> 函数返回 1 (此时，玩家 1 赢得了该场比赛)
 * |X| |O|
 * |O|O| |    // 玩家 1 在 (2, 1) 落子。
 * |X|X|X|
 * 题解参考：https://blog.csdn.net/hebtu666/article/details/104103177/
 */
public class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonalLine;
    private int backDiagonalLine;
    /** 解法2所需变量*/
    private int[][] array;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows=new int[n];
        cols=new int[n];
        diagonalLine=0;
        backDiagonalLine=0;
        array=new int[n][n];
    }
    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        return solution1(row, col, player);
    }

    /**
     * 解法1：基本思路，n*n的井字棋，只有当某个玩家占据了一整行、一整列、或者对角线的时候才能获胜。
     * 因此，我们可以记录每一行、每一列和两个对角线的累计和，玩家1下一颗棋，棋子所在行列对角线加1，玩家2减1
     * 只有当某一行、某一列或者对角线累加和为n或者-n时，才代表有玩家胜出
     * @param row
     * @param col
     * @param player
     * @return
     */
    private int solution1(int row, int col, int player) {
        int toAdd=player==1 ? 1 : -1;
        int target=player==1?rows.length:-rows.length;
        rows[row]+=toAdd;
        cols[col]+=toAdd;
        if(row==col) {
            diagonalLine++;
        }
        if(row==rows.length-col-1) {
            backDiagonalLine++;
        }
        if(rows[row]==target || cols[col]==target || diagonalLine==target || backDiagonalLine==target) {
            return player;
        }
        return 0;
    }

    /**
     * 解法2：建立一个n*n的棋盘，玩家1落子设为1，玩家2落子设为2，分别统计行、列和对角线，检查是否胜出
     * @param row
     * @param col
     * @param player
     * @return
     */
    private int solution2(int row, int col, int player) {
        array[row][col]=player;
        if(checkRow(row,player,array.length) || checkCol(col,player,array.length)
            || checkDiagonalLine(array.length,player) || checkBackDiagonalLine(array.length,player)) {
            return player;
        }
        return 0;
    }
    private boolean checkRow(int row,int player,int n) {
        for (int i=0;i<n;i++) {
            if(array[row][i]!=player) {
                return false;
            }
        }
        return true;
    }
    private boolean checkCol(int col,int player,int n) {
        for (int i=0;i<n;i++) {
            if(array[i][col]!=player) {
                return false;
            }
        }
        return true;
    }
    private boolean checkDiagonalLine(int n,int player) {
        for (int i=0;i<n;i++) {
            if(array[i][i]!=player) {
                return false;
            }
        }
        return true;
    }
    private boolean checkBackDiagonalLine(int n, int player) {
        for (int i=0;i<n;i++) {
            if(array[i][n-1-i]!=player) {
                return false;
            }
        }
        return true;
    }





}
