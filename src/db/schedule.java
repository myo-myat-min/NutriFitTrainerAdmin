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
public class schedule extends trainer{
    
    private String sche_id;
    private LocalDate schedule_date;
    private String content;
    private String trainer_id;

    public schedule(String id, LocalDate schedule_date, String content, String trainer_id) {
        this.sche_id = id;
        this.schedule_date = schedule_date;
        this.content = content;
        this.trainer_id = trainer_id;
    }

    public schedule(LocalDate schedule_date, String content, String trainer_id) {
        this.schedule_date = schedule_date;
        this.content = content;
        this.trainer_id = trainer_id;
    }

    public schedule(String sche_id, LocalDate schedule_date, String content, String id, String name) {
        super(id, name);
        this.sche_id = sche_id;
        this.schedule_date = schedule_date;
        this.content = content;
    }

    public String getSche_id() {
        return sche_id;
    }

    
    
  
    public void setSche_id(String id) {
        this.sche_id = id;
    }

    public LocalDate getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(LocalDate schedule_date) {
        this.schedule_date = schedule_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(String trainer_id) {
        this.trainer_id = trainer_id;
    }


    
    
    

    @Override
    public String toString() {
        return "schedule{" + "id=" + sche_id + ", schedule_date=" + schedule_date + ", content=" + content + ", trainer_id=" + trainer_id + '}';
    }

    
}
