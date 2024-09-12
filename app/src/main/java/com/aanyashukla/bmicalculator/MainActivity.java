package com.aanyashukla.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //  Class Variables; also are called 'Fields'
//    to make a local variable global use ctrl+alt+f
    private Button calculate;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private TextView resulttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setupButtonClickListener();
//      Toast.makeText(this,"Wow, I can make an alert pop up!",Toast.LENGTH_LONG).show();
    }

    private void findView() {
        resulttext = findViewById(R.id.text_view_result);
//      resulttext.setText("Wohoo I can update my Textview using programming code");
        maleButton = findViewById(R.id.radio_id_male);
        femaleButton = findViewById(R.id.radio_id_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculate = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Toast.makeText(MainActivity.this,"Hehee",Toast.LENGTH_LONG).show();
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);    // parse means to analyze

                if(age >= 18){
                    displayResult(bmiResult);
                }else{
                    displayGuidance(bmiResult);
                }

            }
        });
    }

    private double calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

//      converting String into Int
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        double heightinMeters = totalInches * 0.0254;

        double bmi = weight / (heightinMeters * heightinMeters);
        return bmi;
    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
//      we must convert int/double into string for our TextView
        String fullResultString;
        if(bmi < 18.5){
            fullResultString = bmiTextResult + " - You are underweight";
        }else if(bmi > 25){
            fullResultString = bmiTextResult + " - You are overweight";
        }else{
            fullResultString = bmiTextResult + " - You are a healthy weight";
        }
        resulttext.setText(fullResultString);
    }
    
    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(maleButton.isChecked()){
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for boys";
        }else if(femaleButton.isChecked()){
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for girls";
        }else{
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range";
        }
        resulttext.setText(fullResultString);
    }
}