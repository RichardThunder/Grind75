import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class solution_lintcode {
     public static int[][] deleteInterval(int[][] intervals, int[] toBeRemoved) {
         // --- write your code here ---
         List<int[]> result = new ArrayList<>();
         int i = 0;
         while( i<intervals.length && intervals[i][1] <= toBeRemoved[0]){
             result.add(intervals[i]);
             i++;
         }
         while(i<intervals.length && intervals[i][0] < toBeRemoved[1]){
             int [] curr = intervals[i];
             //截取前面一段
             /*if(curr[1] < toBeRemoved[1]){
                 curr[1] = toBeRemoved[0];
                 if(curr[1]> curr[0])
                    result.add(curr);
             }
             else if(curr[0]> toBeRemoved[0]){
                 curr[0] = toBeRemoved[1];
                 if(curr[1]> curr[0])
                     result.add(curr);
             }
             else{
                 result.add(new int[]{curr[0],toBeRemoved[0]});
                 result.add(new int[]{toBeRemoved[1],curr[1]});
             }*/

             //这个方法比上面慢
             if(curr[0]<toBeRemoved[0])
                 result.add(new int[]{curr[0],toBeRemoved[0]});
             if(curr[1]> toBeRemoved[1])
                 result.add(new int[] {toBeRemoved[1],curr[1]});
             i++;
         }
         while(i<intervals.length){
             result.add(intervals[i]);
             i++;
         }
         return result.toArray(new int[0][]);

     }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0,2},{3,4},{5,7}};
        System.out.println( Arrays.deepToString(deleteInterval(intervals,new int[]{1,6})));
    }
}
