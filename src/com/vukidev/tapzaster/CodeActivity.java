package com.vukidev.tapzaster;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.os.Handler;
import android.media.MediaPlayer;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.content.Intent;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.content.SharedPreferences;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class CodeActivity extends Activity { // start of evil codes menu

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codes);
		
		final Button SaveMBtn = (Button) findViewById(R.id.codebtn);
		SaveMBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText myEditText = (EditText) findViewById(R.id.codeinput);
				String userInput = myEditText.getText().toString();
				
				if (userInput.equalsIgnoreCase("catto boi")) {
					dialog("meow :3");
				} else if (userInput.equalsIgnoreCase("tapzaster") | userInput.equalsIgnoreCase("techzaster")) {
					dialog("Thats what im saying");
				} else if (userInput.equalsIgnoreCase("rayan") | userInput.equalsIgnoreCase("rayteam")) {
					dialog("Funny rayan guy");
				} else if (userInput.equalsIgnoreCase("67")) {
					dialog("Fuck you");
					dialog("I would have wiped all of ur data but i was lazy to implement it");
				} else if (userInput.equalsIgnoreCase("Fuck you")) {
					dialog("You too");
				} else if (userInput.equalsIgnoreCase("meow") | userInput.equalsIgnoreCase(":3")) {
					dialog("cat :3");
				} else if (userInput.equalsIgnoreCase("VukiDev")) {
					Intent i = new Intent(CodeActivity.this, Vukiowo.class);
					startActivity(i);
				} else if (!userInput.equalsIgnoreCase("")) {
					dialog("wut");
				}
				if (userInput.isEmpty()) {
					dialog("Put something");
				}
			}
		});
    }
	public void dialog(String Messages) {
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

		builder.setTitle("Message")
			   .setMessage(Messages)
			   .setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
				   public void onClick(android.content.DialogInterface dialog, int id) {
					   dialog.dismiss();
				   }
			   });

		android.app.AlertDialog dialog = builder.create();
		dialog.show();
	}
}