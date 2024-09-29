//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
//
// Related Topics 数组 排序 👍 2416 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_merge {
    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        //对序列进行排序
        list.sort((x, y) -> {
            if (x[0] == y[0])
                return x[1] - y[1];
            else
                return x[0] - y[0];
        });
        //获取当前序列
        int[] curr = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int[] next = list.get(i);
            //如果序列没有重叠, 插入当前序列, 并且把当前序列用作新的比较对象
            if (curr[1] < next[0]) {
                result.add(curr);
                curr = next;
            } else {
                //如果有重叠, 把当前和下个序列两个合并
                curr[0] = Math.min(curr[0], next[0]);
                curr[1] = Math.max(curr[1], next[1]);
            }
        }
        //插入最后的序列
        result.add(curr);
        //list转换成数组
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
//        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = new int[][]{{1, 4}, {4, 5}};
        int[][] result = new int[5000][];
        result = merge(intervals);

        System.out.println(Arrays.deepToString(result));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
