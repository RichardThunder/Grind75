//城市的 天际线 是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回 由这些建筑物形成的 天际线 。 
//
// 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示： 
//
//
// 
// lefti 是第 i 座建筑物左边缘的 x 坐标。 
// righti 是第 i 座建筑物右边缘的 x 坐标。 
// heighti 是第 i 座建筑物的高度。 
// 
//
// 你可以假设所有的建筑都是完美的长方形，在高度为 0 的绝对平坦的表面上。 
//
// 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
//列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。 
//
// 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答
//案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...] 
//
// 
//
// 示例 1： 
// 
// 
//输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//解释：
//图 A 显示输入的所有建筑物的位置和高度，
//图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。 
//
// 示例 2： 
//
// 
//输入：buildings = [[0,2,3],[2,5,3]]
//输出：[[0,3],[5,0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= buildings.length <= 10⁴ 
// 0 <= lefti < righti <= 2³¹ - 1 
// 1 <= heighti <= 2³¹ - 1 
// buildings 按 lefti 非递减排序 
// 
//
// Related Topics 树状数组 线段树 数组 分治 有序集合 扫描线 堆（优先队列） 👍 853 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        /*
         * 思路:扫描线
         * 1. 维护高度变化时出现的小区间
         * 2.合并这些高度,
         *   当当高度出现变化时,将之前的区间插入结果集, 并更新现在的高度
         *
        //二维的高度表 [start,end,height], 没有的index-> height=0
        List<Integer> heightLine = new ArrayList<>();

        //首先排序, 按照left. height排序,起点递增, 高度递减
        Arrays.sort(buildings,(x,y)->{
            if(x[0] ==y[0]){
                return y[3]-x[3];
            }
            else return x[0] - y[0];
        });


        int[] curr = heightLine.get((0));
        for (int i = 1; i <heightLine.size() ; i++) {
            //记录index起点,高度
            int start = curr[0];
            int height = curr[2];
            int[] next = heightLine.get(i);
            //首先考虑最简单的断开/相连情况,简单插入之前, 然后更新就可以
            if(curr[1]<=next[0]){
                heightLine.add(new int[]{curr[0],curr[1],curr[2]});
                curr= next;
            }
            //包含,结尾不重叠
            else if(curr[1]> next[1]){
                //内部更高
                if(curr[2]<next[2]){
                  heightLine.add(new int[]{curr[0],next[0],curr[2]});
                  curr= next;
                }
            }

        }



        /*for (int i = 1; i < heightLine.size(); i++) {
            int newHeight = heightLine.get(i);
            if (height != newHeight) {
                result.add(Arrays.asList(start, height));
                start = i;
                height = newHeight;
            }
        }*/
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        for (int[] build : buildings) {
            heights.add(new int[]{build[0], build[2]});
            heights.add(new int[]{build[1], -build[2]});
        }
//        heights.sort((x, y) -> {
//            if (x[0] == y[0]) {
//                return y[1] - x[1];
//            } else return x[0] - y[0];
//        });
        //对height进行排序, 起点正序, 高度倒序,保证最高位置在最前;
        heights.sort((x, y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y)-> y-x);
        pq.offer(0);
        //记录上一次高度
        int  lastHeight = 0;
        for(int[] h:heights){
            //如果是建筑起点,加入到优先级队列
            if(h[1]>0)
                pq.offer(h[1]);
            //如果是终点, 移除队列
            else
                pq.remove(-h[1]);
            //获取当前高点
            int curr = pq.peek();
            //高度发生变化, 记录新的端点
            if(curr != lastHeight){
                result.add(Arrays.asList(h[0],curr));
                lastHeight = curr;
            }
        }
        return result;
    }
    /* 可行的做法,但是爆内存
    *  //分析开头结束,创建一个稀疏数组, 可以使用map代替 map<index.height> 因为不需要排序, 可以根据index直接遍历, 所以使用hashmap
        Map<Integer, Integer> heightLine = new HashMap<>();

        //从start遍历,end结束
        int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
        for (int[] building : buildings) {

            start = Math.min(building[0], start);
            end = Math.max(building[1], end);
        }

        for (int[] building : buildings) {
            //扫描线
            //注意结束也要包括
            for (int i = building[0]; i <= building[1]; i++) {
                if (heightLine.containsKey(i)) {
                    //逐帧扫描并放入最高的高度
//                    heightLine.put(i, Math.max(heightLine.get(i), building[2]));
                    //使用merge
                    heightLine.merge(i, building[2], Math::max);
                } else {
                    heightLine.put(i, building[2]);
                }
            }
        }

        //得到了高度线 heightLine
        List<List<Integer>> result = new ArrayList<>();
        int height = heightLine.get(start);
        //注意end也要包含
        for (int i = start; i <= end; i++) {
            int newHeight=0;
            if (heightLine.containsKey(i)) {
                newHeight = heightLine.get(i);}

                if (newHeight > height) {
                    result.add(Arrays.asList(start,height));
                    start = i;
                    height = newHeight;
                }
                else if(newHeight<height){
                    result.add(Arrays.asList(start,height));
                    start = i-1;
                    height = newHeight;
                }
        }
        result.add(Arrays.asList(start,height));
        result.add(Arrays.asList(end,0));
        return result;
       */

    public static void main(String[] args) {
        int[][] buildings = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//        int[][] buildings = new int[][]{{0,2147483647,2147483647}};
        System.out.println(getSkyline(buildings));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
