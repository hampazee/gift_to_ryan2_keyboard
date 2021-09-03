package com.example.hidesoftkeyboard2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout ;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.relative);
        editText = findViewById(R.id.click);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                showKeyBoard(editText);
                //get value from edittext
                String s = editText.getText().toString().trim();
                //check condition
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    //when action is equal to action done
                    //hide keyboard
                    hideKeyBoard(editText);
                    //display toast

                    return true;
                }

                return false;
            }
        });

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("TouchEvents","Touch is detected");

                int eventType = event.getActionMasked();

                switch (eventType){
                    case MotionEvent.ACTION_DOWN:
                        hideKeyBoard(editText);
                        break;
                }

                return true;
            }
        });

    }
    private void hideKeyBoard(EditText editText) {
        //initialize input manager
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //hide soft keyboard
        manager.hideSoftInputFromWindow(editText.getApplicationWindowToken(),0);
    }

    private void showKeyBoard(EditText editText) {
        //initialize input manager
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //show soft keyboard
        manager.showSoftInput(editText.getRootView(),InputMethodManager.SHOW_IMPLICIT);
        //focus on edittext
        editText.requestFocus();
    }

}