package com.group4.joseph.timetablenotifier;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button mondayButton, tuesdayButton, wednesdayButton, thursdayButton, fridayButton;
    Button settingsButton, cancellationsButton, modLocButton;

    //Weather stuff
    TextView currentTemperatureField, weatherIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mondayButton = (Button)findViewById(R.id.inputMondayBtn);
        mondayButton.setOnClickListener(MainActivity.this);

        tuesdayButton = (Button)findViewById(R.id.inputTuesdayBtn);
        tuesdayButton.setOnClickListener(MainActivity.this);

        wednesdayButton = (Button)findViewById(R.id.inputWednesdayBtn);
        wednesdayButton.setOnClickListener(MainActivity.this);

        thursdayButton = (Button)findViewById(R.id.inputThursdayBtn);
        thursdayButton.setOnClickListener(MainActivity.this);

        fridayButton = (Button)findViewById(R.id.inputFridayBtn);
        fridayButton.setOnClickListener(MainActivity.this);*/

        settingsButton = (Button)findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(MainActivity.this);

        cancellationsButton = (Button)findViewById(R.id.cancellationsButton);
        cancellationsButton.setOnClickListener(MainActivity.this);

        modLocButton = (Button)findViewById(R.id.logOutStudentButton);
        modLocButton.setOnClickListener(MainActivity.this);

        //*** Stuff for listView ***
        final String[] items = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        ListAdapter listAdapter = new CustomAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String item = String.valueOf(parent.getItemAtPosition(position));
                        //Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();

                        switch(position){
                            case 0:
                                Intent monday_intent = new Intent(MainActivity.this, Monday.class);
                                startActivity(monday_intent);
                                break;

                            case 1:
                                Intent tuesday_intent = new Intent(MainActivity.this, Tuesday.class);
                                startActivity(tuesday_intent);
                                break;

                            case 2:
                                Intent wednesday_intent = new Intent(MainActivity.this, Wednesday.class);
                                startActivity(wednesday_intent);
                                break;

                            case 3:
                                Intent thursday_intent = new Intent(MainActivity.this, Thursday.class);
                                startActivity(thursday_intent);
                                break;

                            case 4:
                                Intent friday_intent = new Intent(MainActivity.this, Friday.class);
                                startActivity(friday_intent);
                                break;
                        }

                    }
                }

        );
        //*** End of listView stuff ***

        Typeface weatherFont;
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");

        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);

        //FOR WEATHER ICON AND TEMPERATURE IN THE CORNER
        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure/*, String weather_updatedOn*/, String weather_iconText, String sun_rise) {

                //cityField.setText(weather_city);
                currentTemperatureField.setText(weather_temperature);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        asyncTask.execute("53.34000", "-6.27000");




    }

    @Override
    public void onClick(View v){

        switch(v.getId()){

            /*case R.id.inputMondayBtn:
                Intent monday_intent = new Intent(this, Monday.class);
                startActivity(monday_intent);
                break;

            case R.id.inputTuesdayBtn:
                Intent tuesday_intent = new Intent(this, Tuesday.class);
                startActivity(tuesday_intent);
                break;

            case R.id.inputWednesdayBtn:
                Intent wednesday_intent = new Intent(this, Wednesday.class);
                startActivity(wednesday_intent);
                break;

            case R.id.inputThursdayBtn:
                Intent thursday_intent = new Intent(this, Thursday.class);
                startActivity(thursday_intent);
                break;

            case R.id.inputFridayBtn:
                Intent friday_intent = new Intent(this, Friday.class);
                startActivity(friday_intent);
                break;*/

            case R.id.settingsButton:
                Intent settings_intent = new Intent(this, Settings.class);
                startActivity(settings_intent);
                break;

            case R.id.cancellationsButton:
                Intent cancellations_intent = new Intent(MainActivity.this, Cancellations.class);
                startActivity(cancellations_intent);
                break;

            case R.id.logOutStudentButton:
                Intent modules_intent = new Intent(MainActivity.this, AddStringActivity.class);
                startActivity(modules_intent);
                break;


        }
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
