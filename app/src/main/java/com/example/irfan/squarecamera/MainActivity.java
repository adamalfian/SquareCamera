package com.example.irfan.squarecamera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnCamerad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText)findViewById(R.id.nrp);
        btnCamerad = (Button) findViewById(R.id.btn_camerad);
        btnCamerad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nrp = editText.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, CameradActivity.class);
                intent.putExtra("nrp",nrp);
                startActivity(intent);
            }
        });
    }
}
