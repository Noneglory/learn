package simple;

/**
 * describe:
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author leijiang
 * @date 2020/06/16
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String returnStr = "";
        int minlength = strs[0].length();
        String minlengthStr =strs[0];
        for(String StrUnit :strs)
        {
            if(StrUnit.length()<minlength){
                minlength=StrUnit.length();
                minlengthStr=StrUnit;
            }
        }
        System.out.println(minlengthStr);
        System.out.println(minlength);
        int i=0;
        for(;i<minlength;i++){
            char compareChar = minlengthStr.charAt(i);
            int Location=0;
            for(;Location<strs.length;Location++)
            {
                String compareStr = strs[Location];
                if(compareChar!=compareStr.charAt(i))
                {
                    break;
                }
            }
            if(Location!=strs.length){
                break;
            }
        }
        return minlengthStr.substring(0,i);
    }


    public static void main(String args[]){
        String[] stringArr ={"flower","flow","flight"};
        LongestCommonPrefix solution = new LongestCommonPrefix();
        String result = solution.longestCommonPrefix(stringArr);
        System.out.println(result);
    }
}
