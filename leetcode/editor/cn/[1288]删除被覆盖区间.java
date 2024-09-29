//给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。 
//
// 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。 
//
// 在完成所有删除操作后，请你返回列表中剩余区间的数目。 
//
// 
//
// 示例： 
//
// 
//输入：intervals = [[1,4],[3,6],[2,8]]
//输出：2
//解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 1000 
// 0 <= intervals[i][0] < intervals[i][1] <= 10^5 
// 对于所有的 i != j：intervals[i] != intervals[j] 
// 
//
// Related Topics 数组 排序 👍 116 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution_removeCoveredIntervals {
    /*public int removeCoveredIntervals(int[][] intervals) {
        //结果数组
        //对intervals 按end 排序从大到小, 从前向后遍历, 如果next.start > curr.start 那么删除区间(不计数)
        Arrays.sort(intervals, (x,y)->{if (x[1] ==y[1]) return x[0]-y[0];
            else return y[1]-x[1];});
        int[] curr = intervals[0];
        int cnt = 1;
        for(int i=1;i<intervals.length;i++){
            //完全不重合
            if(curr[0]>= intervals[i][1]){
                cnt++;
                curr = intervals[i];
            }
            else if(intervals[i][0]< curr[0] ){
                //扩大区间
                curr[0]= Math.min(intervals[i][0],curr[0]);
                cnt++;
            }
        }
        return cnt;
    }*/
    public static int removeCoveredIntervals(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, (x, y) -> {
            if (x[0] == y[0])
                return y[1] - x[1];
            else return x[0] - y[0];
        });
        int[] curr = intervals[0];
        int cnt = 1;
        for (int i = 1; i < intervals.length; i++) {
            /*//因为尾端降序,所以首段相同的后面直接省略
            if(curr[0]== intervals[i][0])
                continue;
            //向后遍历, 后面的区间开始已经在当前区间之后了,不可能合并,替换当前区间
            else if(curr[1] <= intervals[i][0]){
                curr = intervals[i];
                cnt++;
            }
            else if(curr[1]< intervals[i][1]){
                curr = intervals[i];
                cnt++;
            }
            //包含,直接合并
            else if (curr[1] >= intervals[i][1]) {
                continue;
            }*/
            //向后遍历,因为已经排序过, 后面添加新区间的情况只有一种, 那就是next尾端大于curr尾端(等于不需要替换).这个时候需要替换curr
            if(curr[1]< intervals[i][1]){
                curr = intervals[i];
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,4},{3,6},{2,8}};
        System.out.println( removeCoveredIntervals(intervals));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
