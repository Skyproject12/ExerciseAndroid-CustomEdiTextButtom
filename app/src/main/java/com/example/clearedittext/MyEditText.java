package com.example.clearedittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class MyEditText extends AppCompatEditText {
    Drawable mClearButtonImage;
    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // mengatur tampilan
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Masukkan nama Anda");
        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }

    // mengatur fungsi
    private void init(){
        // set image clear
        mClearButtonImage= ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear,null);
        // ketika edit text di sentuh oleh pengguna
        setOnTouchListener(new OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if((getCompoundDrawablesRelative()[2]!=null)){
                    float clearButtonStart;
                    float clearButtonEnd;
                    boolean isClearButtonCliked= false;
                    if(getLayoutDirection() == LAYOUT_DIRECTION_RTL){
                        clearButtonEnd= mClearButtonImage.getIntrinsicHeight()+getPaddingStart();
                        if(motionEvent.getX()> clearButtonEnd){
                            isClearButtonCliked=true;
                        }
                    }
                    else {
                        clearButtonStart = (getWidth() -getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());
                        if(motionEvent.getX() > clearButtonStart){
                            isClearButtonCliked=true;
                        }
                    }
                    if(isClearButtonCliked){
                        if(motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                            // set imageresource drawable
                            mClearButtonImage= ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear, null);
                            showContextMenu();
                            return true;
                        }
                        else if(motionEvent.getAction()== motionEvent.ACTION_UP){
                            mClearButtonImage= ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear, null);
                            if(getText()!=null){
                                getText().clear();
                            }
                            hideClearButton();
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
                return false;
            }
        });
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    showClearButton();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    // set mclearbutton if clear button edit text
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, mClearButtonImage,null);
    }

    // set null if hide button editext
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
