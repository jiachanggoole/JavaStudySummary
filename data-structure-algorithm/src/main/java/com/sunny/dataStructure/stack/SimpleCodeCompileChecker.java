package com.sunny.dataStructure.stack;

/**
 * 代码编译检查器
 *
 * @author JiaChang
 * @date 2019/1/25
 */
public class SimpleCodeCompileChecker {

    /**
     * 存储待检查的字符串
     */
    private String input;

    /**
     * 构造方法，接受待检查的字符串
     *
     * @param in
     */
    public SimpleCodeCompileChecker(String in) {
        this.input = in;
    }

    /**
     * 检查分隔符匹配的方法
     */
    public void check() {
        int strLength = input.length();
        MyStack stack = new MyStack(strLength);

        for (int i = 0; i < strLength; i++) {
            //一次获取串中的单个字符
            char ch = input.charAt(i);

            switch (ch) {
                case '{':
                case '[':
                case '(':
                    //如果为左分隔符，压入栈
                    stack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    //如果为右分隔符，与栈顶元素进行匹配
                    if (!stack.isEmpty()) {
                        char chx = stack.pop();
                        boolean flag = (ch == '{' && chx != '}') ||
                                (ch == '(' && chx != ')') ||
                                (ch == '[' && chx != ']');
                        if (flag) {
                            System.out.println("匹配出错！字符：" + ch + ",下标：" + i);
                        }
                    } else {
                        System.out.println("匹配出错！字符：" + ch + ",下标：" + i);
                    }

                default:
                    break;
            }

        }

        if (!stack.isEmpty()) {
            //匹配结束时如果栈中还有元素，证明右分隔符缺失
            System.out.println("有括号没有关闭！");
        }
    }


    public static void main(String[] args) {
        SimpleCodeCompileChecker simpleCodeCompileChecker = new SimpleCodeCompileChecker("a{b(c[d]e)f");
        simpleCodeCompileChecker.check();
    }


}
