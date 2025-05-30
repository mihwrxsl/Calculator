package com.example.helloapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    private EditText inputField;
    private String currentExpression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);

        // Обработчики для цифр и операций
        setButtonClickListener(R.id.btn0, "0");
        setButtonClickListener(R.id.btn1, "1");
        setButtonClickListener(R.id.btn2, "2");
        setButtonClickListener(R.id.btn3, "3");
        setButtonClickListener(R.id.btn4, "4");
        setButtonClickListener(R.id.btn5, "5");
        setButtonClickListener(R.id.btn6, "6");
        setButtonClickListener(R.id.btn7, "7");
        setButtonClickListener(R.id.btn8, "8");
        setButtonClickListener(R.id.btn9, "9");
        setButtonClickListener(R.id.btnAdd, "+");
        setButtonClickListener(R.id.btnSubtract, "-");
        setButtonClickListener(R.id.btnMultiply, "*");
        setButtonClickListener(R.id.btnDivide, "/");
        setButtonClickListener(R.id.btnDot, ".");

        // Кнопка "=" (вычисление)
        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());

        // Кнопка "C" (очистка)
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            currentExpression = "";
            inputField.setText("");
        });

        // Кнопка "DEL" (удаление символа)
        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            if (!currentExpression.isEmpty()) {
                currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                inputField.setText(currentExpression);
            }
        });
    }

    // Обработчик для цифр и операций
    private void setButtonClickListener(int buttonId, final String value) {
        findViewById(buttonId).setOnClickListener(v -> {
            currentExpression += value;
            inputField.setText(currentExpression);
        });
    }

    // Вычисление результата
    private void calculateResult() {
        if (!currentExpression.isEmpty()) {
            try {
                // Используем ScriptEngine для вычисления выражения
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                Object result = engine.eval(currentExpression);

                // Выводим результат
                inputField.setText(currentExpression + "=" + result);
                currentExpression = result.toString(); // Для продолжения вычислений
            } catch (ScriptException e) {
                inputField.setText("Ошибка");
                currentExpression = "";
            }
        }
    }
}