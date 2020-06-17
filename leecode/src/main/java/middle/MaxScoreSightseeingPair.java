package middle;

/**
 * describe:最佳观光组合
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 *  
 *
 * 示例：
 *
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 *
 * 官方分析：
 * 可以将其拆分成 A[i]+i和 A[j]-j 两部分，这样对于统计景点 j 答案的时候，由于 A[j]-j是固定不变的，
 * 因此最大化 A[i]+i+A[j]-j的值其实就等价于求 [0,j-1] 中 A[i]+i 的最大值 mx，景点
 * j 的答案即为 mx+A[j]-j。而 mx 的值我们只要从前往后枚举 j的时候同时维护即可，这样每次枚举景点 j
 * 的时候，寻找使得得分最大的 i 就能从 O(n)O 降至 O(1) 的时间复杂度，总时间复杂度就能从 O(n^2) 降至 O(n)。
 *
 *
 * 个人理解：A[i]+A[j]+i-j 可以分成A[i]+i+(A[j]-j),我们以j来枚举，当j确定时，则每个A[j]-j是确定的，此时j前面的所有数据中，能让
 * A[i]+i最大的值就是能取得的最高分。
 *
 * @author leijiang
 * @date 2020/06/17
 */
public class MaxScoreSightseeingPair {
    public int maxScoreSightseeingPair(int[] A) {
        //maxStart 这个是为A[i]+i的最大值
        int maxDistance = 0; int maxStart = A[0]+0;
        for(int j=1;j<A.length;j++){
            maxDistance=Math.max(maxDistance,A[j]-j+maxStart);
            maxStart=Math.max(maxStart,A[j]+j);
        }
        return maxDistance;
    }
}
