//给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重
//叠 。 
//
// 
//
// 示例 1: 
//
// 
//输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
//输出: 1
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: intervals = [ [1,2], [1,2], [1,2] ]
//输出: 2
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: intervals = [ [1,2], [2,3] ]
//输出: 0
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= intervals.length <= 10⁵ 
// intervals[i].length == 2 
// -5 * 10⁴ <= starti < endi <= 5 * 10⁴ 
// 
//
// Related Topics 贪心 数组 动态规划 排序 👍 1152 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutioneraseOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        /*List<int[]> list = new ArrayList(Arrays.asList(intervals));
        list.sort((x,y)->x[1]-y[1]);
        //不需要维护result数组
        *//*int[] curr = list.get(0);
        List<int[]> result = new ArrayList();
        result.add(curr);
        for(int i=1; i< list.size(); i++){
            if(curr[1]<= list.get(i)[0]){
                curr = list.get(i);
                result.add(curr);
            }
        }
        return list.size()- result.size();*//*
        int right = list.get(0)[1];
        //符合条件的区间
        int count = 1;
        for(int i=1;i<list.size();i++){
            if(right <= list.get(i)[0]) {
                count++;
                right = list.get(i)[1];
            }
        }
        return list.size()-count;*/

        //使用原生数组操作
        Arrays.sort(intervals,(x,y)->x[1]-y[1]);
//        int right = intervals[0][1];
//        int cnt = 1;
//        for(int[] i : intervals){
//            if(right <= i[0]){
//                right = i[1];
//                cnt++;
//            }
//        }
        int right = Integer.MIN_VALUE;
        int cnt = Arrays.stream(intervals).reduce(new int[]{right,0},(acc,i)->{
            if(acc[0]<= i[0]) {
                acc[0] = i[1];
                acc[1]++;
            }
            return acc;
        })[1];
        return intervals.length - cnt;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
