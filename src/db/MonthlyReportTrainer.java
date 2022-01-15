/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author ACER
 */
public class MonthlyReportTrainer {

    String trainerName, lateTime, leaveDay;
    int fees;

    public MonthlyReportTrainer(String trainerName, String lateTime, String leaveDay, int fees) {
        this.trainerName = trainerName;
        this.lateTime = lateTime;
        this.leaveDay = leaveDay;
        this.fees = fees;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getLateTime() {
        return lateTime;
    }

    public void setLateTime(String lateTime) {
        this.lateTime = lateTime;
    }

    public String getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(String leaveDay) {
        this.leaveDay = leaveDay;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

}
