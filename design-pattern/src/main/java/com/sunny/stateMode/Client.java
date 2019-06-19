package com.sunny.stateMode;

/**
 * 模拟电梯的动作
 * @author JiaChang
 * @date 2019-05-06
 */
public class Client {

    public static void main(String[] args) {

        Context context = new Context();
        context.setLiftState(Context.closeingLiftState);

        context.run();
//        context.stop();

        // 开门
        context.open();

        // 关门
        context.close();

        // 运行
        context.run();

        // 停止
        context.stop();

    }

}
