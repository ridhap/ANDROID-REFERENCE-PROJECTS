package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstNumber;
    EditText secondNumber;
    Button btnAdd,btnSub,btnMul,btnDiv;

    double num1,num2,sum,sub,mul,div;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNumber = findViewById(R.id.edit_text1);
        secondNumber = findViewById(R.id.edit_text2);
        btnAdd =findViewById(R.id.button_Add);
        btnSub=findViewById(R.id.button_Sub);
        btnMul=findViewById(R.id.button_Mul);
        btnDiv=findViewById(R.id.button_Div);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    num1 = Double.parseDouble(firstNumber.getText().toString());
                    num2 = Double.parseDouble(secondNumber.getText().toString());
                    sum = num1 + num2;
                Toast.makeText(MainActivity.this,Double.toString(sum), Toast.LENGTH_SHORT).show();
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(firstNumber.getText().toString());
                num2 = Double.parseDouble(secondNumber.getText().toString());
                sub = num1 -num2;
                Toast.makeText(MainActivity.this,Double.toString(sub), Toast.LENGTH_SHORT).show();
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(firstNumber.getText().toString());
                num2 = Double.parseDouble(secondNumber.getText().toString());
                mul = num1*num2;
                Toast.makeText(MainActivity.this,Double.toString(mul), Toast.LENGTH_SHORT).show();
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(firstNumber.getText().toString());
                num2 = Double.parseDouble(secondNumber.getText().toString());
                div = num1/num2;
                Toast.makeText(MainActivity.this,Double.toString(div), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
