package com.example.dennis.studlife;


/**
 *       Android App - StudLife
 *     Cervisia Technologies Inc.
 *
 *      Dennis Kleverwal S4598164
 *     Richard van Ginkel S4599047
 *      Roeland Hoefsloot S4629388
 *
 * Time class. Used to implement time into our app.
 * Which is fairly crucial, seeing to that you want your stud to actually
 * be able to die. When you don't feed him for long enough.
 */
public class Time{
    public static long time;
    private Student student;
    private int currentProgressbarEnergy = 0;
    private int currentProgressbarHealth = 0;
    private int currentProgressbarHappiness = 0;
    private float currentMoneyGiven = 0;

    /**
     * Gets our stud from the ApplicationClass.
     */
    public Time(){
        student = ApplicationClass.student;
    }

    /**
     * Gets the current time from the system, and calls to the makeProgressBarsValues method.
     */
    public void updateTime(){
        time = System.currentTimeMillis();
        makeProgressBarsValues();
    }

    /**
     * Calculates the, possibly new, value for the progressbars. Test if there's a difference
     * with the current ones, and if so updates these.
     */
    public void makeProgressBarsValues(){
        int progressbarEnergy = (int) ((time - student.getStartedWithLife()) / student.getMsPerEnergy());
        int progressbarHealth = (int) ((time - student.getStartedWithLife()) / student.getMsPerHealth());
        int progressbarHappiness = (int) ((time - student.getStartedWithLife()) / student.getMsPerHappiness());
        float money = (float) ((((time - student.getStartedWithLife()) / student.getMsPerStufi())+ 1)*30);
        boolean changed = testForUpdate(progressbarEnergy, progressbarHealth, progressbarHappiness, money);

        if (changed){
            if(student.getContext() != null) {
                student.updateProgressbars();
            }
        }
    }

    /**
     * Tests if the calculated value is different from the one our stud currently has.
     * If this is the case, updates it with the new values.
     *
     * @param progressbarEnergy Progress bar showing our stud's energy level.
     * @param progressbarHealth Progress bar showing our stud's health.
     * @param progressbarHappiness Progress bar showing our stud's happiness.
     * @param money Showing the stud's financial position.
     * @return
     */
    public boolean testForUpdate(int progressbarEnergy, int progressbarHealth, int progressbarHappiness, float money){
        boolean changed = false;
        if(currentProgressbarEnergy < progressbarEnergy){
            currentProgressbarEnergy = progressbarEnergy;
            student.setEnergyFromTime(currentProgressbarEnergy);
            changed = true;
        }
        if(currentProgressbarHealth < progressbarHealth){
            currentProgressbarHealth = progressbarHealth;
            student.setHealthFromTime(currentProgressbarHealth);
            changed = true;
        }
        if(currentProgressbarHappiness < progressbarHappiness){
            currentProgressbarHappiness = progressbarHappiness;
            student.setHappinessFromTime(currentProgressbarHappiness);
            changed = true;
        }
        if(currentMoneyGiven < money){
            currentMoneyGiven = money;
            student.setMoneyFromStufi(currentMoneyGiven);
            changed = true;
        }
        return changed;
    }
}
