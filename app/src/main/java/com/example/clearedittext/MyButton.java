package com.example.clearedittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import static android.view.Gravity.CENTER;

// custom buttom
public class MyButton extends AppCompatButton {
    private Drawable enabledBackground, disabledBackground;
    private int textColor;
    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //perubahan pada buttom
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // jika  isEnable maka akna enablebackground
        // selain itu disable background
        setBackground(isEnabled()?enabledBackground:disabledBackground);
        setTextColor(textColor);
        setTextSize(12.f);
        setGravity(CENTER);
        // if enbled maka text akan submit
        // jika disable maka text akan isi dulu
        setText(isEnabled()?"Submit": "ISI Dulu");
    }
    private void init (){
        textColor= ContextCompat.getColor(getContext(), android.R.color.background_light);
        enabledBackground= getResources().getDrawable(R.drawable.bg_button);
        disabledBackground= getResources().getDrawable(R.drawable.bg_button_disable);
    }
}
