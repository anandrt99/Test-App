package com.tcs.testapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextSubject;
    private EditText editTo;
    private EditText editTextContent;
    private Button buttonSendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextSubject = (EditText)findViewById(R.id.editTextSubject);
        editTextContent = (EditText)findViewById(R.id.editTextContent);
        editTo = (EditText)findViewById(R.id.editTo);
        buttonSendMail = (Button)findViewById(R.id.buttonSendMail);
        buttonSendMail.setOnClickListener(this);
    }

    public void composeEmail(String[] addresses, String subject, String content) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSendMail:
                composeEmail(getAdresses(), editTextSubject.getText().toString(), editTextContent.getText().toString());
                break;
            default:
                break;
        }
    }

    private String[] getAdresses() {
        String address = editTo.getText().toString();
        return address.split(";");
    }
}
