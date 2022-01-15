/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.time.LocalDate;

/**
 *
 * @author Soe min hein
 */
public class monthly_progress {
    private int id;
    private LocalDate progress_date;
    private int member_id;
    private int workout_id;

    public monthly_progress(int id, LocalDate progress_date, int member_id, int workout_id) {
        this.id = id;
        this.progress_date = progress_date;
        this.member_id = member_id;
        this.workout_id = workout_id;
    }

    public monthly_progress(LocalDate progress_date, int member_id, int workout_id) {
        this.progress_date = progress_date;
        this.member_id = member_id;
        this.workout_id = workout_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getProgress_date() {
        return progress_date;
    }

    public void setProgress_date(LocalDate progress_date) {
        this.progress_date = progress_date;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
    }

    @Override
    public String toString() {
        return "monthly_progress{" + "id=" + id + ", progress_date=" + progress_date + ", member_id=" + member_id + ", workout_id=" + workout_id + '}';
    }
    
    
}
