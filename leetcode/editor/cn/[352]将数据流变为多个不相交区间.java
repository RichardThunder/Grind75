//给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。 
//
// 实现 SummaryRanges 类： 
//
// 
// 
// 
// SummaryRanges() 使用一个空数据流初始化对象。 
// void addNum(int val) 向数据流中加入整数 val 。 
// int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。 
// 
// 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", 
//"addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
//[[], [1], [], [3], [], [7], [], [2], [], [6], []]
//输出：
//[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]],
// null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
//
//解释：
//SummaryRanges summaryRanges = new SummaryRanges();
//summaryRanges.addNum(1);      // arr = [1]
//summaryRanges.getIntervals(); // 返回 [[1, 1]]
//summaryRanges.addNum(3);      // arr = [1, 3]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
//summaryRanges.addNum(7);      // arr = [1, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
//summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
//summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
//summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= val <= 10⁴ 
// 最多调用 addNum 和 getIntervals 方法 3 * 10⁴ 次 
// 
//
// 
//
// 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办? 
//
// Related Topics 设计 二分查找 有序集合 👍 201 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
/*class SummaryRanges {
    List<Integer> nums;

    public SummaryRanges() {
        nums = new ArrayList<>();
    }

    public void addNum(int value) {
        //防止插入重复数字
        if(nums.contains(value)) return;
        nums.add(value);
    }

    public int[][] getIntervals() {
        //数组为空, 返回空数组
        if (nums.isEmpty()) return new int[0][];
        //就一个数字
        if (nums.size() == 1) return new int[][]{{nums.get(0), nums.get(0)}};
        //初始化结果数组
        List<int[]> result = new ArrayList<>();

        //判断向后连续的逻辑
        //先排序
        nums.sort((x, y) -> x - y);
        //左边界num[0], 有边界num[0]
        int right = nums.get(0);
        int left = nums.get(0);
        //从前向后扫描
        for (int i = 0; i < nums.size() - 1; i++) {
        // if  i - (i+1) =1 , 右边界right = i+1
            if (nums.get(i + 1) - nums.get(i) == 1){
                right = nums.get(i+1);
            }
        // not -> 将区间加入结果, 并且左边界 =i+1, 右边界= i+1
            else{
                result.add(new int[]{left,right});
                left = nums.get(i+1);
                right= nums.get(i+1);
            }
        }
        //遍历完,插入最后一个数字参与的区间
        result.add(new int[]{left,right});
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        SummaryRanges sr = new SummaryRanges();
        sr.addNum(1);
        Arrays.deepToString(sr.getIntervals());
        sr.addNum(3);
        System.out.println( Arrays.deepToString(sr.getIntervals()));

    }

}*/

class SummaryRanges {
    //使用自排序的treeSet
    //
    TreeSet<Integer> treeSet;

    public SummaryRanges() {
        this.treeSet = new TreeSet<Integer>((x, y) -> x - y);

    }

    public void addNum(int value) {
        treeSet.add(value);

    }

    public int[][] getIntervals() {
        if(treeSet.isEmpty())
            return new int[0][];
        //使用迭代器
        /*Iterator<Integer> it = treeSet.iterator();
        List<int[]> result = new ArrayList<>();
        int right = -1;
        int left = -1;

        while (it.hasNext()) {
            int num = it.next();
            //之前区间中断,开始新区间
            if (left == -1) {
                right = left = num;
            }
            //如果num是连续的, 那么将已有区间扩大
            else {
                if (num == right + 1) {
                    right = num;
                }//终端
                else {
                    //断了就加入结果,并且设置标志
                    result.add(new int[]{left, right});
                    right = left = num;
                }
            }
        }
        result.add(new int[]{left, right});
        return result.toArray(new int[0][]);*/

        //使用arrayList
        List<Integer> nums =new ArrayList<>();
        for(Integer i : treeSet){
            nums.add(i);
        }
        //就一个数字
        if (nums.size() == 1) return new int[][]{{nums.get(0), nums.get(0)}};
        //初始化结果数组
        List<int[]> result = new ArrayList<>();

        //判断向后连续的逻辑
        //先排序
        nums.sort((x, y) -> x - y);
        //左边界num[0], 有边界num[0]
        int right = nums.get(0);
        int left = nums.get(0);
        //从前向后扫描
        for (int i = 0; i < nums.size() - 1; i++) {
            // if  i - (i+1) =1 , 右边界right = i+1
            if (nums.get(i + 1) - nums.get(i) == 1){
                right = nums.get(i+1);
            }
            // not -> 将区间加入结果, 并且左边界 =i+1, 右边界= i+1
            else{
                result.add(new int[]{left,right});
                left = nums.get(i+1);
                right= nums.get(i+1);
            }
        }
        //遍历完,插入最后一个数字参与的区间
        result.add(new int[]{left,right});
        return result.toArray(new int[0][]);
    }

}


/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
//leetcode submit region end(Prohibit modification and deletion)

