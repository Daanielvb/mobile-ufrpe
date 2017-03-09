package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Daniel on 25/10/2016.
 */
public class Membership {
    private Date createdAt;
    private Date expirationAt;
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
}
