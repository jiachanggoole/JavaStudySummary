package com.sunny.stateMode;

/**
 * @author JiaChang
 * @date 2019-05-05
 */
public class Context {

    //定义出所有的电梯状态

    public static final OpeningLiftState openingLiftState = new OpeningLiftState();
    public static final ClosingLiftState closeingLiftState = new ClosingLiftState();
    public static final RunningLiftState runningLiftState = new RunningLiftState();
    public static final StoppingLiftState stoppingLiftState = new StoppingLiftState();

    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        liftState.setContext(this);
    }

    public void open(){
        this.liftState.open();

    }

    public void close(){
        this.liftState.close();
    }

    public void run(){
        this.liftState.run();
    }

    public void stop(){
        this.liftState.stop();
    }
}
