package com.sunny.stateMode;

/**
 * 电梯门关闭以后，电梯可以做哪些事情
 * @author JiaChang
 * @date 2019-05-05
 */
public class ClosingLiftState extends LiftState{

    @Override
    public void open() {
        super.context.setLiftState(Context.openingLiftState);
        super.context.getLiftState().open();
    }

    @Override
    public void close() {
        // 具体关门的逻辑
        System.out.println("电梯门关闭...");
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.runningLiftState);
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingLiftState);
        super.context.getLiftState().stop();
    }
}
