package com.example.zhanglanxin.password;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    EditText editTextLength;
    EditText editTextNumbers;
    EditText editTextSymbols;
    TextView myText;
    private int length;
    private int numbers;
    private int symbols;
    private int letters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView)findViewById(R.id.textView);
        //this botton is used to refresh the password
        final Button refresh = (Button) findViewById(R.id.button);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextLength = (EditText)findViewById(R.id.edit_text_length);
                editTextNumbers = (EditText)findViewById(R.id.edit_text_numbers);
                editTextSymbols = (EditText)findViewById(R.id.edit_text_symbols);
                String incorrectOutput = "Incorrect length!";
                myText.setText(incorrectOutput);
                if(editTextLength.getText().toString().length()!=0) {
                    length = Integer.parseInt(editTextLength.getText().toString());
                }
                else{
                    myText.setText(incorrectOutput);
                }
                if(editTextLength.getText().toString().length()!=0) {
                    numbers = Integer.parseInt(editTextNumbers.getText().toString());
                }
                else{
                    myText.setText(incorrectOutput);
                }
                if(editTextSymbols.getText().toString().length()!=0) {
                    symbols = Integer.parseInt(editTextSymbols.getText().toString());
                }
                else{
                    myText.setText(incorrectOutput);
                }
                letters = length-numbers-symbols;
                //if the letters is smaller than 0, then the length is incorrect
                myText.setText(PasswordGenerator.generate(numbers,symbols,letters));
            }
        });
        //copy botton to clipboard
        final Button getCopy = (Button) findViewById(R.id.button2);
        getCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sdk_Version = android.os.Build.VERSION.SDK_INT;
                if(sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(myText.getText().toString());   // Assuming that you are copying the text from a TextView
                    Toast.makeText(getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
                }
                else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Text Label", myText.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
