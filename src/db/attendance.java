/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.time.LocalDate;

public class attendance {
    private int id;
    private LocalDate attendance_date;

    public attendance(int id, LocalDate attendance_date) {
        this.id = id;
        this.attendance_date = attendance_date;
    }

    public attendance(LocalDate attendance_date) {
        this.attendance_date = attendance_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(LocalDate attendance_date) {
        this.attendance_date = attendance_date;
    }

    @Override
    public String toString() {
        return "attendance{" + "id=" + id + ", attendance_date=" + attendance_date + '}';
    }
    
    
    
}
