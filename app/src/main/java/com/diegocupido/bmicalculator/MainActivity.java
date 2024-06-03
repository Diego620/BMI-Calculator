package com.diegocupido.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //Class variables are also called fields
    private TextView resultText;
    private Button calculateButton;
    private RadioButton radio_male;
    private RadioButton radio_female;
    private EditText text_age;
    private EditText text_weight;
    private EditText text_feet;
    private EditText text_inches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        radio_male = findViewById(R.id.radio_button_male);
        radio_female = findViewById(R.id.radio_button_female);
        text_age = findViewById(R.id.edit_text_age);
        text_weight = findViewById(R.id.edit_text_weight);
        text_feet = findViewById(R.id.edit_text_feet);
        text_inches = findViewById(R.id.edit_text_inches);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = text_age.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18){
                    displayResult(bmiResult);
                }
                else{
                    displayGuidance(bmiResult);
                }
            }
        });
    }


    private double calculateBmi() {
        String weightText = text_weight.getText().toString();
        String feetText = text_feet.getText().toString();
        String inchesText = text_inches.getText().toString();

        //converting integers to strings
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        //Height in meters is the inches multiplied by 0.0254
        double heightInMeters = totalInches * 0.0254;

        //BMI formula = weight in kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);
    }
        private void displayResult(double bmi){
            DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
            String bmiTextResult = myDecimalFormat.format(bmi);

            String fullResultString;
            if (bmi < 18.5) {
                //Display underweight
                fullResultString = bmiTextResult + " - You are underweight";
            } else if (bmi > 25) {
                //Display overweight
                fullResultString = bmiTextResult + " - You are overweight";
            } else {
                //Display healthy
                fullResultString = bmiTextResult + " - You are a healthy weight";
            }
            resultText.setText(fullResultString);
        }
    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormat.format(bmi);

        String fullResultString;
        if(radio_male.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for boys";
        }
        else if (radio_female.isChecked()){
            //Display girl guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for girls";
        }
        else {
            //Display general guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(fullResultString);
    }

}