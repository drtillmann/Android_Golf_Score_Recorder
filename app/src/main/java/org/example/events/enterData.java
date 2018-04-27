package org.example.events;

import static org.example.events.Constants.TABLE_NAME;
import static org.example.events.Constants.COURSE;
import static org.example.events.Constants.PUTTS;
import static org.example.events.Constants.PENALTIES;
import static org.example.events.Constants.SCORE;
import static org.example.events.Constants.PLAYER;
import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class enterData extends Activity implements OnClickListener {
	
	private EditText course;
	private EditText putts;
	private EditText pennalties;
	private EditText score;
	private EditText player;
	//private Context context = getApplicationContext();

	
	private EventsData events = new EventsData(this);
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_data);
		
		//events = new EventsData(this);
		
		View enter = findViewById(R.id.enter_data_form);
		enter.setOnClickListener(this);
		View exit = findViewById(R.id.exit_button_form);
		exit.setOnClickListener(this);
	}
	
	
	
	public void onClick(View v) {
		
		course = (EditText)findViewById(R.id.course);
		putts = (EditText)findViewById(R.id.putts);
		pennalties = (EditText)findViewById(R.id.pennalties);
		score = (EditText)findViewById(R.id.score);
		player = (EditText)findViewById(R.id.player);

		String name = course.getText().toString(), putt = putts.getText().toString(), penalty = pennalties.getText().toString(),
                myScore = score.getText().toString(), pName = player.getText().toString();


        switch (v.getId()){
			case R.id.enter_data_form:
				checkMusic();
				boolean valid = checkInput(name, putt, penalty, myScore, pName);
				if(valid){
					try{

						addEvent(name, putt, penalty,myScore, pName);

					}catch(Exception e){
						e.printStackTrace();
					}
					events.close();
					db.close();
				}
				break;
			case R.id.exit_button_form:
				checkMusic();
				finish();
				break;
		}
		
	}

	public boolean checkInput(String courseName, String myPutts, String myPennalties, String myScore, String playerName ){
        Context context = getApplicationContext();
	    CharSequence invalid = "Invalid Information Entered", valid = "Information Saved";
        Toast invalidToast, validToast;

		if(courseName.isEmpty() || myPutts.isEmpty() || myPennalties.isEmpty() || myScore.isEmpty() || playerName.isEmpty() ||
				!isDigit(myPutts) || !isDigit(myPennalties) || !isDigit(myScore) || myPutts.contains(".") || myPennalties.contains(".") || myScore.contains(".") ||
				!(convertToInt(myPutts) >= 0 && convertToInt(myPutts) < 100) ||
				!(convertToInt(myPennalties) >= 0 && convertToInt(myPennalties) < 50) ||
				!(convertToInt(myScore) > 53 && convertToInt(myScore) < 150))
		{
            invalidToast = Toast.makeText(context, invalid, Toast.LENGTH_LONG);
            invalidToast.show();

			return false;
		}else {

		    validToast = Toast.makeText(context, valid, Toast.LENGTH_LONG);
		    validToast.show();
			return true;

		}
	}
	
	public void addEvent(String c, String pu, String pe, String s, String player){
    	connectSQLiteTable();
		insertRecords(c, pu, pe, s, player);
    }

    public boolean connectSQLiteTable(){
		try {
			db = events.getWritableDatabase();
		}catch(SQLException sqlE){
			Log.d("SQLite error", sqlE.toString());
			return false;
		}
		return true;
	}

	public boolean insertRecords(String cN, String putt, String penn, String score, String playerName){
		ContentValues values = new ContentValues();
		values.put(COURSE, cN);
		values.put(PUTTS, putt);
		values.put(PENALTIES, penn);
		values.put(SCORE, score);
		values.put(PLAYER, playerName);
		try {
			db.insertOrThrow(TABLE_NAME, null, values);
		}catch(SQLException sqlE){
			Log.d("SQL Insert Error", sqlE.toString());
			return false;
		}
		return true;
	}

    public boolean isDigit(String str){
		try{
			int i = Integer.parseInt(str);
		}catch(NumberFormatException nfe){
			return false;

		}
		return true;
	}

	public int convertToInt(String str){
		int i = 0;
		try{
			i = Integer.parseInt(str);

		}catch(NumberFormatException nfe){
			return -1;
		}

		return i;
	}

	public void checkMusic(){
		if(MainActivity.mp == null){
			if(this.isFinishing()){
				MainActivity.mp.stop();
			}
		}else /*if(MainActivity.mp != null)*/{
			if(this.isFinishing()){
				MainActivity.mp.stop();
			}
		}
	}
}
