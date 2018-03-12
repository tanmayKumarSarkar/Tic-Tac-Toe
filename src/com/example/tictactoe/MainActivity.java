package com.example.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {

	SharedPreferences savedData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		savedData = getSharedPreferences("myData", Context.MODE_PRIVATE);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void strtBtnClk(View v){
		Intent intent =new Intent(getApplicationContext(),SecondPage.class);
		startActivity(intent);
	}
	
	public void exitBtnClk(View v1){
		Editor savedDataEditorD = savedData.edit();
		savedDataEditorD.clear();
		savedDataEditorD.commit();
		this.finish();
	}
	
	

}
