//给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi] 表示第 i 个区
//间的开始和结束，并且 intervals 按照 starti 升序排列。同样给定一个区间 newInterval = [start, end] 表示另一个区间的
//开始和结束。 
//
// 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，
//可以合并区间）。 
//
// 返回插入之后的 intervals。 
//
// 注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁵ 
// intervals 根据 starti 按 升序 排列 
// newInterval.length == 2 
// 0 <= start <= end <= 10⁵ 
// 
//
// Related Topics 数组 👍 920 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        boolean inserted = false;
        for (int[] cur : intervals) {
            //新区间在当前的区间之后或者新区间已经被插入, 可以直接将当前区间插入
            if(inserted==true||cur[1]<newInterval[0])
                result.add(cur);
            //新区间在当前区间之前, 插入新区间和当前区间, 并标记为已插入
            else if(newInterval[1]<cur[0]) {
            inserted = true;
            result.addAll(List.of(newInterval,cur));
            }
            //新区间与当前区间有重叠,将两个区间合并成新区间,继续向后遍历
            else{
                newInterval[0] = Math.min(newInterval[0], cur[0]);
                newInterval[1] = Math.max(newInterval[1], cur[1]);
            }
        }
        //如果到最后都没有插入新区间, 那么在最后直接插入
        if (!inserted)
            result.add(newInterval);
        //将ArrayList直接转型为array,需要用占位数组指明数组结构,空间大小可以随意指定,构造时会重新调整
        return result.toArray(new int[0][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
