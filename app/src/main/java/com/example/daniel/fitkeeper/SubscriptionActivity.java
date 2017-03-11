package com.example.daniel.fitkeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.daniel.fitkeeper.utils.Controller;
import com.example.daniel.fitkeeper.utils.Session;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.Membership;

public class SubscriptionActivity extends AppCompatActivity implements View.OnClickListener {
    Membership m = Session.getInstance().getUser().getMembership();
    private ImageButton backBtn;
    private Button renewBtn;
    private String type;
    private String expiration;
    private TextView membershipType;
    private TextView membershipExpiration;
    private TextView finalPrice;

    DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        setUi();
        setMembership(m);

        List<String> spinnerArray = new ArrayList<String>();

        String[] options = {this.getString(R.string.annual_plan), this.getString(R.string.semester_plan),
                this.getString(R.string.monthly_plan)};
        spinnerArray.addAll(Arrays.asList(options));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.payment_spinner);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                System.out.println(position);
                updatePrice(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


    }


    public void updatePrice(int position) {
        switch (position) {
            case 0:
                setPrice("TOTAL: " + this.getString(R.string.default_price));
                break;
            case 1:
                setPrice("TOTAL: " + this.getString(R.string.semester_price));
                break;
            case 2:
                setPrice("TOTAL: " + this.getString(R.string.month_price));
                break;
        }
    }

    public void setPrice(String price) {
        finalPrice.setText(price);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.renewBtn:
                renewSubscription(((Spinner) findViewById(R.id.payment_spinner)).getSelectedItemPosition());
                break;
        }
    }

    public void setUi() {
        membershipExpiration = (TextView) findViewById(R.id.membershipLstDayEdtTxt);
        membershipType = (TextView) findViewById(R.id.membershipEdtTxt);
        finalPrice = (TextView) findViewById(R.id.totalTxtLabel);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        renewBtn = (Button) findViewById(R.id.renewBtn);
        backBtn.setOnClickListener(this);
        renewBtn.setOnClickListener(this);
    }

    public void setMembership(Membership m) {
        membershipExpiration.setText(m.getExpirationAt());
        membershipType.setText(getResources().getStringArray(R.array.plans)[m.getType()].toString());
    }

    private void renewSubscription(int plan) {
        String newDate = updateSubscriptionExpiration(setPlanDays(plan));
        Membership m = new Membership(plan);
        m.setExpiration_at(newDate);
        c.setTime(m.getCreatedAt());
        m.setCreated_at(format.format(c.getTime()));
        m.setId(Session.getInstance().getUser().membership);
        String jsonMembership = Controller.gson.toJson(m);
        if (Controller.updateMembership(jsonMembership, m.getId())) {
            System.out.println("Matricula alterada com sucesso");
            //updateUI();
        } else
            System.out.println("Erro ao alterar a senha");
    }

    private String updateSubscriptionExpiration(int days) {
        try {
            String oldDate = Session.getInstance().getUser().getMembership().getExpirationAt();
            Date date = format.parse(oldDate);
            c.setTime(date);
            c.add(Calendar.DATE,days);
            //return c.getTime();
            return format.format(c.getTime());
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    private int setPlanDays(int plan){
        if(plan == 0){
            return 365;
        }
        else if(plan == 1){
            return 180;
        }
        else if(plan == 2){
            return 30;
        }
        else
            return 1;
    }
}

