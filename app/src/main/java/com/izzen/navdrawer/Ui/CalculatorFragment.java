package com.izzen.navdrawer.Ui;

/*
 * Nama          : M Izzudin Wijaya
 * NIM           : 10117152
 * Latest Update : 20 July 2020, 15:20:00 WIB
 * Modified      : 20 July 2020, 15:20:00 WIB
 * */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.izzen.navdrawer.R;

public class CalculatorFragment extends Fragment {

    public EditText height;
    public EditText weight;
    public TextView result;
    public Button btn_result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_calculator, container, false);

        height = (EditText) layout.findViewById(R.id.height);
        weight = (EditText) layout.findViewById(R.id.weight);
        result = (TextView) layout.findViewById(R.id.result);
        btn_result = (Button) layout.findViewById(R.id.btn_calculate);

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float getHeight, getWeight;

                if (height.getText().toString().equals("")) {
                    getHeight = 0f;
                } else {
                    getHeight = Float.parseFloat(height.getText().toString());
                }
                if (weight.getText().toString().equals("")) {
                    getWeight = 0f;
                } else {
                    getWeight = Float.parseFloat(weight.getText().toString());
                }

                float bmi = getWeight / (getHeight * getHeight);

                result.setText("Your BMI is " + bmi);
            }
        });


        return layout;
    }


    // end class
}
