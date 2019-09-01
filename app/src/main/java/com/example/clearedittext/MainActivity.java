package com.example.clearedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyButton mybutton;
    private MyEditText myedittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mybutton= findViewById(R.id.my_button);
        myedittext= findViewById(R.id.my_edit_text);
        // setcostom button and edittext
        setMyButton();
        myedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // atur button ketika text kosong dan tidak
                setMyButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, myedittext.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setMyButton() {
        Editable re= myedittext.getText();
        // ketika edittext tidak kosong maka button enable true
        if(re!=null && !re.toString().isEmpty()){
            mybutton.setEnabled(true);
        }
        // ketika edittext tidak terselect maka enable sama dengan false
        else{
            mybutton.setEnabled(false);
        }
    }
}
