package com.sunny.stateMode;

/**
 * @author JiaChang
 * @date 2019-05-05
 */
public abstract class LiftState {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();

}
