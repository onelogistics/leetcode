package leetcode;

/**
 * 数字转换
 */
public class ConvertNum {
    static ConvertNum convertNum = new ConvertNum();
    public static void main(String[] args) {
        System.out.println(convertNum.toBinary(15));
    }
    /**
     * 405. Convert a Number to Hexadecimal
     * 十进制转十六进制
     * 基本思想：四位二进制对应一位十六进制，使用num和0x1111进行位与运算，最终保留下来的一定是最末尾四个二进制位
     * 根据最末尾的值映射到相应的字符，然后num右移四位
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String result = "";
        while (num != 0) {
            result = map[(num & 15)] + result;
            num = num >>> 4;
        }
        return result;
    }

    public String toBinary(int num) {
        if (num == 0) return "0";
        String result = "";
        while (num!=0) {
            result = num%2+result;
            num=num/2;
        }
        return result;
    }

}
