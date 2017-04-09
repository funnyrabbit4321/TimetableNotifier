package com.group4.joseph.timetablenotifier;

        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.Html;
        import android.widget.TextView;

        import android.annotation.TargetApi;

        import android.content.Intent;

        import android.content.pm.PackageManager;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Build;
        import android.provider.Settings;
        import android.support.annotation.NonNull;
        import android.support.v4.app.ActivityCompat;
        import android.view.View;
        import android.widget.Button;

        import android.widget.Toast;




public class Weather extends AppCompatActivity implements View.OnClickListener{

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    Typeface weatherFont;

    private Button button;
    private TextView text1,text2;
    private LocationListener location_listener;
    private LocationManager location_manager;
    double latitude;
    double longitude;
    private int flag=0;
    Button button2;
    Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_weather);


        menuButton = (Button)findViewById(R.id.menuButton);


        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.humidity_field);
        pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);


        location_manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //requestQueue= Volley.newRequestQueue(this);

        location_listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location)
            {
                latitude=location.getLatitude();
                longitude=location.getLongitude();
                //Person user;
                //user = Person.getInstance();
                //user.lat = latitude;
                //user.long1 = longitude;
                //Toast.makeText(Weather.this, Double.toString(latitude) + " " + Double.toString(longitude), Toast.LENGTH_SHORT).show();
                //text1.append("\n"+location.getLatitude());
                //text2.append("\n"+location.getLongitude());
                //set_geocoder(location);

                Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
                    public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure/*, String weather_updatedOn*/, String weather_iconText, String sun_rise) {

                        cityField.setText(weather_city);
                        detailsField.setText(weather_description);
                        currentTemperatureField.setText(weather_temperature);
                        humidity_field.setText("Humidity: "+weather_humidity);
                        pressure_field.setText("Pressure: "+weather_pressure);
                        weatherIcon.setText(Html.fromHtml(weather_iconText));

                    }
                });
                asyncTask.execute(Double.toString(latitude), Double.toString(longitude));


            };

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {

            }

            @Override
            public void onProviderEnabled(String provider)
            {

            }

            @Override
            public void onProviderDisabled(String provider)
            {
                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        /*Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure/*, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(weather_city);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: "+weather_humidity);
                pressure_field.setText("Pressure: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        Person user;
        user = Person.getInstance();*/
        //Toast.makeText(Weather.this, Double.toString(latitude) + " " + Double.toString(longitude), Toast.LENGTH_SHORT).show();
        //Toast.makeText(Weather.this, Double.toString(user.lat) + " " + Double.toString(user.long1), Toast.LENGTH_SHORT).show();



        //asyncTask.execute(Double.toString(user.lat), Double.toString(user.long1));
        //asyncTask.execute("53.34000", "-6.27000");

//"53.34000", "-6.27000"


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case 10:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    flag=1;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v)
    {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{android.Manifest.permission.INTERNET,
                    android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},10);
            //return;
        }
        else
        {
            flag=1;
        }

        switch (flag)
        {
            case 0:
                Toast.makeText(this, "Permission Denied,require user permission", Toast.LENGTH_SHORT).show();
                break ;
            case 1:
                location_manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50000, 0, location_listener);
                break;
        }
    }

    public void goToMenu(View view){
        Person user;
        user = Person.getInstance();

        if(user.person == 1) {
            Intent lec_menu_intent = new Intent(this, MainLecActivity.class);
            startActivity(lec_menu_intent);
        }
        else if(user.person == 0){
            Intent menu_intent = new Intent(this, MainActivity.class);
            startActivity(menu_intent);
        }
    }


}