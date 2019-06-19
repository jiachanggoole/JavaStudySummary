package com.sunny.stateMode;

/**
 * 电梯运行中可以做哪些事情
 * @author JiaChang
 * @date 2019-05-05
 */
public class RunningLiftState extends LiftState{
    @Override
    public void open() {
        // do nothing 电梯运行时不能开门
    }

    @Override
    public void close() {
        // 肯定是关门状态
    }

    @Override
    public void run() {
        System.out.println("电梯运行中...");
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingLiftState);
        super.context.getLiftState().stop();
    }
}
