package com.example.dennis.studlife;

/**
 * Created by dennis on 27-5-2016.
 */
public class TimeRunner implements Runnable {
    private Time time = null;

    public TimeRunner (Time time){
        this.time = time;
    }

    @Override
    public void run() {
        while(true){
            time.updateTime();
            try {
                Thread.sleep(990);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
