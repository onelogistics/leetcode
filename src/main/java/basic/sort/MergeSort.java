package basic.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 4, 1, 4, 6};
        new MergeSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
    /**
     * time complexity:O(NlogN)
     * space complexity:O(N)
     * stable:true
     * @param array
     */
    public void sort(int[] array) {
        //提前创建临时数组，避免迭代/递归过程中频繁开辟空间
        int[] temp = new int[array.length];
        sort(array, 0, array.length - 1, temp);
    }

    /**
     * 不断分组，直到left=right，然后递归合并
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    public void sort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(array, left, mid, temp);
            sort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * 合并两个有序数组，不断比较两个数组的头指针，将较小的一个放到临时数组中，同时不断移动数组指针
     * @param array
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    public void merge(int[] array, int left, int mid, int right, int[] temp) {
        //左序列指针
        int i = left;
        //右序列指针
        int j = mid + 1;
        //临时序列指针
        int t = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j])
                temp[t++] = array[i++];
            else
                temp[t++] = array[j++];
        }
        //序列剩余元素填充
        while (i <= mid)
            temp[t++] = array[i++];
        while (j <= right)
            temp[t++] = array[j++];
        //将临时数组的元素拷贝到array中
        t = 0;
        while (left <= right)
            array[left++] = temp[t++];
    }
}
