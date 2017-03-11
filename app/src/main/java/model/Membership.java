package model;

import com.google.gson.annotations.Expose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Daniel on 25/10/2016.
 */
public class Membership {

    @Expose
    private int id;

    private Date createdAt;

    private Date expirationAt;

    @Expose
    private String creationAt;

    @Expose
    private String expireAt;

    private int type;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Membership(int type){
        this.createdAt = new Date();
        this.type = type;
        this.expirationAt = addYearPlan(this.createdAt);
    }

    public Date addYearPlan(Date createdAt){
        Calendar c = Calendar.getInstance();
        c.setTime(createdAt);
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }

    public Date addDays(Date initialDate, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(initialDate);
        c.add(Calendar.DATE,days);
        return c.getTime();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getExpirationAt() {
        return sdf.format(expirationAt);
    }

    public int getType() {
        return type;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpirationAt(Date expirationAt) {
        this.expirationAt = expirationAt;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcreationAt() {
        return creationAt;
    }

    public void setCreated_at(String creationAt) {
        this.creationAt = creationAt;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }
}
