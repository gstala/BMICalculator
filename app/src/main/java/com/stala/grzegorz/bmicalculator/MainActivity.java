package com.stala.grzegorz.bmicalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.resultTextView)
    TextView resultTextView;
    @BindView(R.id.adviceTextView)
    TextView adviceTextView;
    @BindView(R.id.massEditText)
    EditText massEditText;
    @BindView(R.id.heightEditText)
    EditText heightEditText;
    @BindView(R.id.KgMRadioButton)
    RadioButton kgMButton;
    @BindView(R.id.LbInRadioButton)
    RadioButton lbInButton;

    private ICountBmi countBmiForKgM;
    private ICountBmi countBmiForLbIn;

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        countBmiForKgM = new CountBmiForKgM();
        countBmiForLbIn = new CountBmiForLbIn();
        sharedPref = getApplicationContext().getSharedPreferences("MyApp", 0);
        makeTextViewsInvisible();
        readData();
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPref.edit();
        if (!String.valueOf(massEditText.getText()).equals("") && !String.valueOf(heightEditText.getText()).equals("")) {
            editor.putString("mass", String.valueOf(massEditText.getText()));
            editor.putString("height", String.valueOf(heightEditText.getText()));
            editor.apply();
            Toast.makeText(getApplicationContext(), R.string.toastSaveText, Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getApplicationContext(), R.string.toastSaveError, Toast.LENGTH_SHORT).show();
    }

    private void readData() {
        String mass = sharedPref.getString("mass", "");
        String height = sharedPref.getString("height", "");
        massEditText.setText(mass);
        heightEditText.setText(height);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.author:
                startAuthorActivity();
                break;
            case R.id.share:
                shareAction();
                break;
            case R.id.save:
                saveData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareAction() {
        if (!String.valueOf(massEditText.getText()).equals("") && !String.valueOf(heightEditText.getText()).equals("")) {
            float mass = Float.valueOf(massEditText.getText().toString());
            float height = Float.valueOf(heightEditText.getText().toString());
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "BMI");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Mass: " + mass + " height: " + height);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        }
        else Toast.makeText(getApplicationContext(), R.string.toastSaveError, Toast.LENGTH_SHORT).show();
    }

    private void startAuthorActivity() {
        Intent intent = new Intent(this, AuthorActivity.class);
        startActivity(intent);
    }

    private float calculateBmi(float mass, float height) throws IllegalArgumentException {
        float result = 0;
        if (kgMButton.isChecked()) {
            result = countBmiForKgM.countBMI(mass, height);
        } else if (lbInButton.isChecked()) {
            result = countBmiForLbIn.countBMI(mass, height);
        }
        return result;
    }

    public void onClick(View view) {
        float mass;
        float height;
        float result;
        makeTextViewsInvisible();
        try {
            mass = Float.valueOf(massEditText.getText().toString());
            height = Float.valueOf(heightEditText.getText().toString());
            result = calculateBmi(mass, height);
            resultTextView.setText(String.valueOf(result));
            setAdvice(result);
            makeTextViewsVisible();

        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), R.string.toastErrorText, Toast.LENGTH_SHORT).show();
            clearTextViews();
        }
        closeKeyboard();
    }

    private void setAdvice(float bmi) {
        if (bmi < 16) {
            adviceTextView.setText(R.string.starvation);
            setColors(R.color.bloody);
        } else if (bmi >= 16 && bmi < 17) {
            adviceTextView.setText(R.string.emaciation);
            setColors(R.color.red);
        } else if (bmi >= 17 && bmi < 18.5) {
            adviceTextView.setText(R.string.underweight);
            setColors(R.color.orange);
        } else if (bmi >= 18.5 && bmi < 25) {
            adviceTextView.setText(R.string.normalValue);
            setColors(R.color.light_green);
        } else if (bmi >= 25 && bmi < 30) {
            adviceTextView.setText(R.string.overweight);
            setColors(R.color.yellow);
        } else if (bmi >= 30 && bmi < 35) {
            adviceTextView.setText(R.string.firstDegreeOverweight);
            setColors(R.color.orange);
        } else if (bmi >= 35 && bmi < 40) {
            adviceTextView.setText(R.string.secondDegreeOverweight);
            setColors(R.color.red);
        } else if (bmi >= 40) {
            adviceTextView.setText(R.string.thirdDegreeOverweight);
            setColors(R.color.bloody);
        }
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void makeTextViewsInvisible() {
        resultTextView.setVisibility(View.INVISIBLE);
        adviceTextView.setVisibility(View.INVISIBLE);
    }

    private void makeTextViewsVisible() {
        resultTextView.setVisibility(View.VISIBLE);
        adviceTextView.setVisibility(View.VISIBLE);
    }

    private void setColors(int color) {
        adviceTextView.setTextColor(this.getResources().getColor(color));
        resultTextView.setTextColor(this.getResources().getColor(color));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String result = savedInstanceState.getString("result");
        String advice = savedInstanceState.getString("advice");
        int color = savedInstanceState.getInt("color");
        resultTextView.setText(result);
        resultTextView.setTextColor(color);
        adviceTextView.setText(advice);
        adviceTextView.setTextColor(color);
        makeTextViewsVisible();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result", String.valueOf(resultTextView.getText()));
        outState.putString("advice", String.valueOf(adviceTextView.getText()));
        outState.putInt("color", resultTextView.getCurrentTextColor());
    }

    private void clearTextViews() {
        resultTextView.setText("");
        adviceTextView.setText("");
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        MenuItem item = menu.findItem(R.id.save);
        if (String.valueOf(massEditText.getText()).equals("") || String.valueOf(heightEditText.getText()).equals("")) {
            item.setEnabled(false);
        }
        else item.setEnabled(true);
        return true;
    }
}
