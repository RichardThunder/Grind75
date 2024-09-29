import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_employeeFreeTime{
    public static int[][] employeeFreeTime(int[][] schedule) {
        // Write your code here
        List<int[]> timeline = new ArrayList<>();
        List<int[]> result = new ArrayList<>();
        //将时间区间展开
        for(int[] item : schedule){
            for(int i=0;i< item.length;i+=2){
                timeline.add(new int[]{item[i],item[i+1]});
            }
        }

        //排序
        timeline.sort((x,y)->{
            if(x[0] == y[0]){
                return y[1]- x[1];
            }
            else
                return x[0] - y[0];
        });

        //遍历timeline,
        //比较最晚结束的一段和下一段开始是否为空
        int end = timeline.get(0)[1];
        for(int i=1;i<timeline.size();i++){

            if(end < timeline.get(i)[0]){
                result.add(new int[]{end,timeline.get(i)[0]});
                end = timeline.get(i)[1];
            }
            //当且仅当尾端更靠后时更新
            else if(end < timeline.get(i)[1]) end = timeline.get(i)[1];
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] sch = new int[][]{
                {1,3,6,7},{2,4},{2,5,9,12}
        };
        System.out.println(Arrays.deepToString(employeeFreeTime(sch)));
    }
}

/*
class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}*/
