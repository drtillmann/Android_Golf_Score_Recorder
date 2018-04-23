package org.example.events;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity implements OnClickListener {
	
	private EventsData events;
	static MediaPlayer mp;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        View enterData = findViewById(R.id.enter_data);
        enterData.setOnClickListener(this);
        
        View displayData = findViewById(R.id.display_data);
        displayData.setOnClickListener(this);

        View info = findViewById(R.id.info_button);
        info.setOnClickListener(this);
        
        View exit = findViewById(R.id.exit);
        exit.setOnClickListener(this);
        
        mp = null;
        
    }
    
	public static void Play(Context context, int resource){
		Stop(context);
		if(Options.getMusic(context)){
		mp = MediaPlayer.create(context, resource);
		mp.setLooping(true);
		mp.start();
		}
	}
	public static void Stop(Context context){
		if (mp != null){
			mp.stop();
			mp.release();
		}
	}

    
    @Override
    protected void onResume(){
    	super.onResume();
    	Play(this, R.raw.music);
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	if (this.isFinishing()){
    	Stop(this);
    	}
    	Log.d("MUSIC"," music stopped");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.options:
    		startActivity(new Intent(this, Options.class));
    		return true;
    	}
    	return false;
    }
    
    public void onClick(View v) {
    	
		switch (v.getId()){
		case R.id.enter_data:
			//onPause();
			Intent i = new Intent(this, enterData.class);
			startActivity(i);
			break;
		case R.id.display_data:
			//onPause();
			Intent j = new Intent(this, displayData.class);
			startActivity(j);
			break;
		case R.id.info_button:
			Intent k = new Intent(this, Info.class);
			startActivity(k);
			break;
		case R.id.exit:
			finish();
			break;
		
		}
		
	}
    
   
    
 }
