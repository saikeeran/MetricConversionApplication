package com.tamucc.MSD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView conversionValue1,conversionValue2,conversionValue3,conversionValue4,heading;

    String selectedType;
    EditText value;
    float input,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), this.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        String[] conversions = {"Currency", "Length", "Weight", "Temperature"};

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conversions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        conversionValue1 = findViewById(R.id.conversionValue1);
        conversionValue2 = findViewById(R.id.conversionValue2);
        conversionValue3 = findViewById(R.id.conversionValue3);
        conversionValue4 = findViewById(R.id.conversionValue4);
        heading = findViewById(R.id.textViewConversion);
        value = findViewById(R.id.value);
        hideTextViews();
        if(sharedPref.contains("ConversionType") && sharedPref.contains("Input")) {
            value.setText(sharedPref.getString("Input",""));
            if(sharedPref.getString("ConversionType","").equals("Currency")) {
                spinner.setSelection(0);
                convertCurrencies();
            } else if(sharedPref.getString("ConversionType","").equals("Length")) {
                spinner.setSelection(1);
                convertLengths();
            } else if(sharedPref.getString("ConversionType","").equals("Weight")) {
                spinner.setSelection(2);
                convertWeights();
            } else if(sharedPref.getString("ConversionType","").equals("Temperature")) {
                spinner.setSelection(3);
                convertTemperatures();
            }
        }



        findViewById(R.id.conversionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideTextViews();

                selectedType = spinner.getSelectedItem().toString();
                
                if(!value.getText().toString().isEmpty()) {
                    editor.putString("Input",value.getText().toString());
                    editor.putString("ConversionType",selectedType);
                }

                if(value.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please Enter a value to be converted", Toast.LENGTH_SHORT).show();
                } else if(selectedType.equals("Currency")) {
                    convertCurrencies();
                } else if(selectedType.equals("Length")) {
                    convertLengths();
                } else if(selectedType.equals("Weight")) {
                    convertWeights();
                } else if(selectedType.equals("Temperature")){
                    convertTemperatures();
                }
                editor.apply();
            }
        });
    }

    public void hideTextViews() {
        conversionValue1.setVisibility(View.INVISIBLE);
        conversionValue2.setVisibility(View.INVISIBLE);
        conversionValue3.setVisibility(View.INVISIBLE);
        conversionValue4.setVisibility(View.INVISIBLE);
        heading.setVisibility(View.INVISIBLE);
    }
    public void convertCurrencies() {
        heading.setText("Currency Conversions:");
        heading.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 0.91);
        conversionValue1.setText(input + " US Dollars -> " + result + " Euros");
        conversionValue1.setVisibility(View.VISIBLE);
        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 76.23);
        conversionValue2.setText(input + " US Dollars -> " + result + " Indian Rupees");
        conversionValue2.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 1.33);
        conversionValue3.setText(input + " US Dollars -> " + result + " Australian Dollars");
        conversionValue3.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 1.25);
        conversionValue4.setText(input + " US Dollars -> " + result + " Canadian Dollars");
        conversionValue4.setVisibility(View.VISIBLE);
    }

    public void convertLengths() {
        heading.setText("Length Conversions:");
        heading.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 1.60934);
        conversionValue1.setText(input + " Miles -> " + result + " Kilometers");
        conversionValue1.setVisibility(View.VISIBLE);
        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 1609.34);
        conversionValue2.setText(input + " Miles -> " + result + " Meters");
        conversionValue2.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 5280);
        conversionValue3.setText(input + " Miles -> " + result + " Feet");
        conversionValue3.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 63360);
        conversionValue4.setText(input + " Miles ->" + result + " Inches");
        conversionValue4.setVisibility(View.VISIBLE);
    }

    public void convertWeights() {
        heading.setText("Weight Conversions:");
        heading.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 0.453592);
        conversionValue1.setText(input + " Pounds -> " + result + " Kilograms");
        conversionValue1.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 453.592);
        conversionValue2.setText(input + " Pounds -> " + result + " Grams");
        conversionValue2.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) (input * 16);
        conversionValue3.setText(input + " Pounds -> " + result + " Ounces");
        conversionValue3.setVisibility(View.VISIBLE);
    }

    public void convertTemperatures() {
        heading.setText("Temperature Conversions:");
        heading.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) ((input-32) * (0.55));
        conversionValue1.setText(input + " Fahrenheit -> " + result + " °Celsius");
        conversionValue1.setVisibility(View.VISIBLE);

        input = Float.parseFloat(value.getText().toString());
        result = (float) ((input-32) * (0.55)+(273.15));
        conversionValue2.setText(input + " Fahrenheit -> " + result + " Kelvin");
        conversionValue2.setVisibility(View.VISIBLE);
    }
}