package com.sunny.stateMode;

/**
 * 电梯门开启状态的时候
 * @author JiaChang
 * @date 2019-05-05
 */
public class OpeningLiftState extends LiftState{

    @Override
    public void open() {
        System.out.println("电梯门开启...");
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.closeingLiftState);
        super.context.getLiftState().close();
    }

    @Override
    public void run() {
        // 开启的时候不能运行，什么都不做

    }

    @Override
    public void stop() {
        // 门开的时候就是停止状态
    }
}
