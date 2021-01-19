package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都
 * 表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
 * </pre>
 */
class StringToNumber {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * https://assets.leetcode-cn.com/solution-static/jianzhi_20/jianzhi_20_fig1.png
     */
    class Solution {
        Map<State, Map<CharType, State>> transferMap = new HashMap<>();

        {
            Map<CharType, State> initialMap = new HashMap<CharType, State>() {
                {
                    // 允许字符串首有一些额外的空格
                    put(CharType.CHAR_SPACE, State.STATE_INITIAL);
                    put(CharType.CHAR_SIGN, State.STATE_INITIAL_SIGN);
                    put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                    put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
                }
            };
            transferMap.put(State.STATE_INITIAL, initialMap);

            Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
                // 如果符号位存在，其后面必须跟着数字或小数点
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            }};
            transferMap.put(State.STATE_INITIAL_SIGN, intSignMap);

            Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_POINT, State.STATE_POINT);
                put(CharType.CHAR_EXP, State.STATE_EXP);
                put(CharType.CHAR_SPACE, State.STATE_END); // 合法结束
            }};
            transferMap.put(State.STATE_INTEGER, integerMap);

            Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
                put(CharType.CHAR_EXP, State.STATE_EXP);
                put(CharType.CHAR_SPACE, State.STATE_END); // 合法结束，形如 "38. " 的字符串也是合法的
            }};
            transferMap.put(State.STATE_POINT, pointMap);

            Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
                // 小数点的前后两侧，至少有一侧是数字
                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            }};
            transferMap.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);

            Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
                put(CharType.CHAR_EXP, State.STATE_EXP);
                put(CharType.CHAR_SPACE, State.STATE_END); // 合法结束
            }};
            transferMap.put(State.STATE_FRACTION, fractionMap);

            // 科学记数法的指数部分必须存在，形如 "1.2E" 或者 "1.7e+" 的都是不合法的
            Map<CharType, State> expMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            }};
            transferMap.put(State.STATE_EXP, expMap);

            Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            }};
            transferMap.put(State.STATE_EXP_SIGN, expSignMap);

            Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
                put(CharType.CHAR_SPACE, State.STATE_END); // 合法结束
            }};
            transferMap.put(State.STATE_EXP_NUMBER, expNumberMap);

            Map<CharType, State> endMap = new HashMap<CharType, State>() {{
                // 允许字符串末尾有一些额外的空格
                put(CharType.CHAR_SPACE, State.STATE_END);
            }};
            transferMap.put(State.STATE_END, endMap);
        }

        public boolean isNumber(String s) {
            int length = s.length();
            State state = State.STATE_INITIAL;

            for (int i = 0; i < length; i++) {
                CharType type = getCharType(s.charAt(i));
                if (!transferMap.get(state).containsKey(type)) {
                    return false;
                } else {
                    state = transferMap.get(state).get(type);
                }
            }
            return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION
                    || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
        }

        public CharType getCharType(char ch) {
            if (ch >= '0' && ch <= '9') {
                return CharType.CHAR_NUMBER;
            } else if (ch == 'e' || ch == 'E') {
                return CharType.CHAR_EXP;
            } else if (ch == '.') {
                return CharType.CHAR_POINT;
            } else if (ch == '+' || ch == '-') {
                return CharType.CHAR_SIGN;
            } else if (ch == ' ') {
                return CharType.CHAR_SPACE;
            } else {
                return CharType.CHAR_ILLEGAL;
            }
        }
    }

    enum State {
        /**
         * 初始状态
         */
        STATE_INITIAL,
        STATE_INITIAL_SIGN,
        /**
         * 整数部分、小数的整数部分或者科学记数法的有效数字部分的整数部分
         */
        STATE_INTEGER,
        /**
         * 自动机接收了小数点后转变的状态，后面是一个小数的小数部分，此状态是从 STATE_INTEGER 接收小数点后转变而来。
         */
        STATE_POINT,
        /**
         * 自动机接收了小数点后转变的状态，后面是一个小数的小数部分，和 STATE_POINT 不同的是，此状态接收小数点时，小数点的前面没有
         * 整数，类似于 "+.7"，因此此状态后面的小数部分必须存在。
         */
        STATE_POINT_WITHOUT_INT,
        /**
         * 小数的小数点后面的部分
         */
        STATE_FRACTION,
        /**
         * 科学计数法的指数部分起始状态，后面可以接受一个整数（可以有符号位）
         */
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END,
    }

    enum CharType {
        CHAR_NUMBER, // 0 ~ 9
        CHAR_EXP, // E e
        CHAR_POINT, // .
        CHAR_SIGN, // + -
        CHAR_SPACE,
        CHAR_ILLEGAL
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class NormalSolution {
        public boolean isNumber(String s) {
            if (s == null || s.length() == 0) return false;
            //去掉首尾空格
            s = s.trim();

            boolean numFlag = false;
            boolean dotFlag = false;
            boolean eFlag = false;

            char[] chs = s.toCharArray();
            int n = s.length();

            for (int i = 0; i < n; i++) {
                char ch = chs[i];
                if (ch >= '0' && ch <= '9') {
                    // 标记已出现过 0 ~ 9 的数字
                    numFlag = true;
                } else if (ch == '.' && !dotFlag && !eFlag) {
                    // 小数点出现时，不能已出现 . E e 这三个字符
                    dotFlag = true;
                } else if ((ch == 'e' || ch == 'E') && !eFlag && numFlag) {
                    // 出现 e 或者 E 时，需要没出现过 E 和 e，并且科学记数法的有效数字部分不能为空
                    eFlag = true;
                    // 出现了 E 或者 e 之后，必须接收指数部分，因此这里设置 numFlag 为 false
                    numFlag = false;
                } else if ((ch == '+' || ch == '-')
                        && (i == 0 || (chs[i - 1] == 'e' || chs[i - 1] == 'E'))) {
                    // 判定为 + - 符号，只能出现在第一位或者紧接 e 后面，其他情况，都是非法的
                } else {
                    return false;
                }
            }
            return numFlag;
        }
    }
}