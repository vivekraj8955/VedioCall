package com.example.vediocall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Vedio extends AppCompatActivity {
    EditText code;
    Button share,join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);
        code=findViewById(R.id.secretcode);
        share=findViewById(R.id.sharemeet);
        join=findViewById(R.id.joinmeet);
        try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL("https://meet.jit.si"))
                    .setFeatureFlag("welcomepage.enabled", false)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = code.getText().toString();
                if (text.length() > 0) {

                    JitsiMeetConferenceOptions options
                            = new JitsiMeetConferenceOptions.Builder()
                            .setRoom(text)
                            .build();
                    JitsiMeetActivity.launch(Vedio.this, options);
                }
            }
        });
    }
}