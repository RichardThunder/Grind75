import java.util.ArrayList;
import java.util.List;

//lintcode免费 https://www.lintcode.com/problem/919/
public class MeetingRoomII253 {

    /*class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }*/


    //最小堆做法
        /*public int minMeetingRooms(List<Interval> intervals) {
            // Write your code here
            //按照会议开始时间进行排序, 逐个遍历, 建立最小堆获取会议最早结束时间
            intervals.sort((x,y)-> x.start-y.start);
            PriorityQueue<Interval> pq = new PriorityQueue<>((x,y)->x.end-y.end);
            if(!intervals.isEmpty()) pq.offer(intervals.get(0));
            for(int i = 1; i< intervals.size();i++){
                //取出一个进行比较
                Interval curr = pq.poll();
                //如果会议开始时间比堆顶(最早结束的时间)晚, 就不必重新开辟, 将结束时间设置为新会议,重新插入
                if(intervals.get(i).start >= curr.end)
                    //设置为最晚的,一会放回去
                    curr.end = intervals.get(i).end;
                else
                    //会议时间发生重叠,开一个新会议室
                    pq.offer(intervals.get(i));
                //刚才取出的会议室放回去
                pq.offer(curr);
            }
            //堆的大小就是会议室的数量, 最小堆一直提供最早结束的会议室, 如果会议室结束可用, 那么会把新会议室放入旧会议室中
            //如果会议室不够用, 那么将新会议放回, 刚才取出作比较的会议也一并放回,重新计算最早结束的时间
            return pq.size();
        }*/

    //扫描线做法
    class Point {
        int time;
        int flag;

        Point(int time, int flag) {
            this.flag = flag;
            this.time = time;
        }
    }

    public int minMeetingRooms(List<Interval> intervals) {
        List<Point> list = new ArrayList<>(intervals.size() * 2);
        for (Interval i : intervals) {
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, -1));
        }
        list.sort((x, y) -> {
            if (x.time == y.time) return x.flag - y.flag;
            else return x.time - y.time;
        });
        int count = 0, max = 0;
        for (Point l : list) {
            if (l.flag == 1) count++;
            else count--;
            max = Math.max(max, count);
        }
        return max;

    }

}
