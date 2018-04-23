package org.example.events;

import static org.example.events.Constants.TABLE_NAME;
import static org.example.events.Constants.COURSE;
import static org.example.events.Constants.PUTTS;
import static org.example.events.Constants.PENALTIES;
import static org.example.events.Constants.SCORE;
import static org.example.events.Constants.PLAYER;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsData extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "CourseManagement.db";
	private static final int DATABASE_VERSION = 1;
	
	public EventsData(Context ctx){
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		
		String s = "CREATE TABLE " + TABLE_NAME + " (" 
				+ COURSE + " TEXT NOT NULL, " 
				+ PUTTS + " TEXT NOT NULL, "
				+ PENALTIES + " TEXT NOT NULL, "
				+ SCORE + " TEXT NOT NULL, " 
				+ PLAYER + " TEXT NOT NULL);";
		
		db.execSQL(s);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
