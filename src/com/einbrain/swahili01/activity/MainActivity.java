package com.einbrain.swahili01.activity;

import com.einbrain.swahili01.DisplayManager;
import com.einbrain.swahili01.R;
import com.einbrain.swahili01.ResultHandle;
import com.einbrain.swahili01.SizePositionManager;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	
	//// Private Properties ////
	private RelativeLayout CurrentLayout;
	private String _FileUsers_ = "users_v1_1.txt", _FileCurrent_ = "current_v1_1.txt";
	
	private DisplayManager IDisplay;
	private ScaleView IScale;
	private SizePositionManager ISize = new SizePositionManager();
	private ResultHandle IResult = new ResultHandle();
	
	//// Public Properties ////
	int _ScreenWidth_;
	int _ScreenHeight_;
	MediaPlayer _SndBackground_;
	public static Activity MainActivityObject;
			
	//// Init Activity ////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		MainActivityObject = this; //MenuActivity 에서 사용해야 하니까 지우지 말 것.
		
		IDisplay = new DisplayManager(getApplicationContext()); 
		_ScreenWidth_ = IDisplay.getDisplayPixel()[0]; 
		_ScreenHeight_ = IDisplay.getDisplayPixel()[1];
		CurrentLayout = (RelativeLayout) findViewById(R.id.mainLayout);
		LayoutParams params = CurrentLayout.getLayoutParams();
		params.width = _ScreenWidth_; params.height = _ScreenHeight_;
				
		// Getting current activity's size/position variables 
		IScale = new ScaleView (_ScreenWidth_, _ScreenHeight_);
		IScale.MainActivity(); 
		
		LinearLayout playButton = (LinearLayout) findViewById (R.id.btnPlay);
		ISize.setLinearLayout (playButton, IScale.mainPlayX, IScale.mainPlayY, IScale.mainPlayWidth, IScale.mainPlayHeight);
		
		_SndBackground_ = MediaPlayer.create(this, R.raw.background);
		_SndBackground_.start();
		        
		playButton.setOnClickListener(new View.OnClickListener() {  
		    public void onClick(View v) {
		    	_SndBackground_.stop();
		    	
		    	// Read a login file to check logged-in 
			    String loadedUsers = null;
				String loadedData = null;
			    
			    loadedUsers = IResult.getData(_FileUsers_); //최근상태 저장해 놓은 파일을 불러오고
			    loadedData = IResult.getData(_FileCurrent_); //최근상태 저장해 놓은 파일을 불러오고 
				
				if (loadedData != null && loadedUsers != null) { // Already logged-in 
					// Go to the Game Activity
					Intent intent = new Intent(MainActivity.this, GameActivity.class);
			        startActivity(intent);
					
				} else {// Not logged-in 
					// Go to the Menu Activity
					Intent intent = new Intent(MainActivity.this, MenuActivity.class);
			        startActivity(intent);
				}
		    }  
		}); // /playButton.setOnClickListener
	} // /onCreate
	
	//Back button
	@Override
	public void onBackPressed() {
		finish();
	}
	
	//프로그램 닫히면 사운드 끄자 
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    _SndBackground_.stop();
	}
	
} // /public class MainActivity


