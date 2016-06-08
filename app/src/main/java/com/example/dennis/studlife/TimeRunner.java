package com.example.dennis.studlife;

/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Timerunner class. Implements the Runnable class.
 * This such that the time can run on different thread.
 * For performancy purposes.
 */
public class TimeRunner implements Runnable {
    private Time time = null;
    private boolean go;


    /**
     * Sets go to true, such that time is always running.
     * @param time
     */
    public TimeRunner (Time time){
        this.time = time;
        go = true;
    }

    /**
     * Loops the time functions every second, so that our stud keeps 'living'
     * even when there's no user interaction.
     */
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

    /**
     * Stop method used to stop the time.
     */
    public void stop(){
        go = false;
    }
}
