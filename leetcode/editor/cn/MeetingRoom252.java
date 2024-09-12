import java.util.Arrays;
/*class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}*/
public class MeetingRoom252 {

    public static boolean canAttendMeetings(Interval[] intervals){
        Arrays.sort(intervals,(x,y)->x.start - y.start);
        for(int  i; i<intervals.length-2;i++){
            if(intervals[i].end < intervals[i+1].start) continue;
            else return false;
        }
        return true;
    }
}
