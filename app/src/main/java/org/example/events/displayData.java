package org.example.events;

import static android.provider.BaseColumns._ID;
import static org.example.events.Constants.TABLE_NAME;
import static org.example.events.Constants.COURSE;
import static org.example.events.Constants.PUTTS;
import static org.example.events.Constants.PENNALTIES;
import static org.example.events.Constants.SCORE;
import static org.example.events.Constants.PLAYER;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class displayData extends Activity {
	
	private EventsData events;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_data);
		
		events = new EventsData(this);
        try{
        	
        	Cursor cursor = getEvents();
        	
        		showEvents(cursor);
        	
        }finally{
        	events.close();
        }
	}
	
    private static String[] FROM = {COURSE, PUTTS, PENNALTIES, SCORE, PLAYER };
    private static String ORDER_BY = SCORE + " DESC";
   
    private Cursor getEvents(){
    	SQLiteDatabase db = events.getReadableDatabase();
    	Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
    	startManagingCursor(cursor);
    	return cursor;
    }
    //private static int[] TO = {R.id.rowid, R.id.time, R.id.title};
    
    private void showEvents(Cursor cursor){
    	/**SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, FROM, TO);
    	setListAdapter(adapter);*/
    	
    	
    	StringBuilder builder = new StringBuilder();
    	while (cursor.moveToNext()){
    		//String id = cursor.getString(0);
    		String course = cursor.getString(0);
    		String putts = cursor.getString(1);
    		String pennalties = cursor.getString(2);
    		String score = cursor.getString(3);
    		String player = cursor.getString(4);
    		
    		//builder.append(id).append(": ");
    		builder.append(course).append(" | ");
    		builder.append(putts).append(" | ");
    		builder.append(pennalties).append(" | ");
    		builder.append(score).append(" | ");
    		builder.append(player);
    		builder.append("\n");
    	}
    	TextView info_bar = (TextView)findViewById(R.id.info_bar);
    	TextView text = (TextView)findViewById(R.id.text);
    	
    	if(builder != null){
    	info_bar.setText("Course Name | Putts | Pennalties | Score | Player");
    	text.setText(builder);
    	}else{
    		text.setText("No Golf Managment Information");
    	}
    }
    


}
