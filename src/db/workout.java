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
public class workout {

    private int workout_id;
    private String workout_name;
    private String workout_video;
    private String workout_type;
    private float burnt_calorie;
    private String trainer_id;
    private String trainerName;
    private String effectedMuscle;
    private String email;
    private byte[] image;

    public workout(int id, String workout_name, String workout_video, String workout_type, float burnt_calorie, String trainerName, String effectedMuscle, String email, byte[] image) {
        this.workout_id = id;
        this.workout_name = workout_name;
        this.workout_video = workout_video;
        this.workout_type = workout_type;
        this.burnt_calorie = burnt_calorie;
        this.trainerName = trainerName;
        this.effectedMuscle = effectedMuscle;
        this.email = email;
        this.image = image;
    }

    public workout(String workout_name, String workout_video, String workout_type, float burnt_calorie, String effectedMuscle, String email, byte[] image) {
        this.workout_name = workout_name;
        this.workout_video = workout_video;
        this.workout_type = workout_type;
        this.burnt_calorie = burnt_calorie;
        this.effectedMuscle = effectedMuscle;
        this.email = email;
        this.image = image;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEffectedMuscle() {
        return effectedMuscle;
    }

    public void setEffectedMuscle(String effectedMuscle) {
        this.effectedMuscle = effectedMuscle;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public int getId() {
        return workout_id;
    }

    public void setId(int id) {
        this.workout_id = id;
    }

    public String getName() {
        return workout_name;
    }

    public void setName(String name) {
        this.workout_name = name;
    }

    public String getWorkout_video() {
        return workout_video;
    }

    public void setWorkout_video(String workout_video) {
        this.workout_video = workout_video;
    }

    public String getWorkout_type() {
        return workout_type;
    }

    public void setWorkout_type(String workout_type) {
        this.workout_type = workout_type;
    }

    public float getBurnt_calorie() {
        return burnt_calorie;
    }

    public void setBurnt_calorie(float burnt_calorie) {
        this.burnt_calorie = burnt_calorie;
    }

    public String getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(String trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "workout{" + "id=" + workout_id + ", name=" + workout_name + ", workout_video=" + workout_video + ", workout_type=" + workout_type + ", burnt_calorie=" + burnt_calorie + ", trainer_id=" + trainer_id + '}';
    }

}
