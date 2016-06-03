package com.example.dennis.studlife;

/**
 * Created by dennis on 27-5-2016.
 */
public class TimeRunner implements Runnable {
    private Time time = null;
    private boolean go;

    public TimeRunner (Time time){
        this.time = time;
        go = true;
    }

    @Override
    public void run() {
        while(go){
            time.updateTime();
            try {
                Thread.sleep(990);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void stop(){
        go = false;
    }
}
