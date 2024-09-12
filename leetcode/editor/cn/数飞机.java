import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Point {
    int time;
    int flag;

    Point(int x, int y) {
        this.time = x;
        this.flag = y;
    }
}



class countOfAirplane {

    //Definition of Interval:



    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public static int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
        //扫描线算法, 将set展开, 并排序, 按顺序遍历,记录最大时刻
        List<Point> list = new ArrayList<>(airplanes.size() * 2);
        for (Interval airplane : airplanes) {
            //降落是0,起飞是 1, 降落具有优先权
            list.add(new Point(airplane.start, 1));
            list.add(new Point(airplane.end, 0));
        }
        list.sort((p1, p2) -> {
            if (p1.time == p2.time)
                //同时出现, 降落有优先权
                return p1.flag - p2.flag;
            else
                return p1.time - p2.time;
        });
        int count = 0,max=0;
        for(Point p : list){
            if(p.flag==1)
                count++;
            else
                count--;
            max = Math.max(count, max);
        }
        return max;
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList(){};
        list.add(new Interval(1,10));
        list.add(new Interval(2,3));
        list.add(new Interval(5,8));
        list.add(new Interval(4,7));
        System.out.println(countOfAirplanes(list));
    }
}
