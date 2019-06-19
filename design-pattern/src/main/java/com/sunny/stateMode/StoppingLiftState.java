package com.sunny.stateMode;

/**
 * 电梯停止中可以做哪些事情
 * @author JiaChang
 * @date 2019-05-05
 */
public class StoppingLiftState extends LiftState{
    @Override
    public void open() {
        super.context.setLiftState(Context.openingLiftState);
        super.context.getLiftState().open();
    }

    @Override
    public void close() {
        // 停止的时候，门本身就是关闭的 do nothing
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.runningLiftState);
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停止中...");
    }
}
