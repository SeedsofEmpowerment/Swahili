package com.einbrain.swahili01.activity;

import com.einbrain.swahili01.DisplayManager;
import com.einbrain.swahili01.DragableButton;
import com.einbrain.swahili01.R;
import com.einbrain.swahili01.ResultHandle;
import com.einbrain.swahili01.SizePositionManager;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class EndActivity extends Activity {
	
	//// Private Properties ////
	private RelativeLayout CurrentLayout;
	private String _FileCurrent_ = "current_v1_1.txt";
	
	private DisplayManager IDisplay;
	private ScaleView IScale;
	private SizePositionManager ISize = new SizePositionManager();
	private ResultHandle IResult = new ResultHandle();
	
	//// Public Properties ////
	int _ScreenWidth_;
	int _ScreenHeight_;
	MediaPlayer _SndEnding_;
	DragableButton IconCelebration;
	
	//public static Activity MainActivityObject;
			
	//// Init Activity ////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_activity);
		
		//MainActivityObject = this; //MenuActivity 에서 사용해야 하니까 지우지 말 것.
		
		IDisplay = new DisplayManager(getApplicationContext()); 
		_ScreenWidth_ = IDisplay.getDisplayPixel()[0]; 
		_ScreenHeight_ = IDisplay.getDisplayPixel()[1];
		CurrentLayout = (RelativeLayout) findViewById(R.id.mainLayout);
		LayoutParams params = CurrentLayout.getLayoutParams();
		params.width = _ScreenWidth_; params.height = _ScreenHeight_;
				
		// Getting current activity's size/position variables 
		IScale = new ScaleView (_ScreenWidth_, _ScreenHeight_);
		IScale.EndActivity(); 
		
		LinearLayout playButton = (LinearLayout) findViewById (R.id.btnPlay);
		ISize.setLinearLayout (playButton, IScale.endPlayX, IScale.endPlayY, IScale.endPlayWidth, IScale.endPlayHeight);
		
		_SndEnding_ = MediaPlayer.create(this, R.raw.ending);
		_SndEnding_.start();
				
		celebration();
				        
		playButton.setOnClickListener(new View.OnClickListener() {  
		    public void onClick(View v) {
		    	_SndEnding_.stop();
		    	//엔딩 처리 로그 기록 지우기 
		    	IResult.removeFile(_FileCurrent_); // Delete current login file
		    	
		    	Intent intent = new Intent(EndActivity.this, MainActivity.class);
		        startActivity(intent);
		    }  
		}); // /playButton.setOnClickListener
	} // /onCreate
	
	//게임시작
	private void celebration() {
		//맞았을 때 아이콘 애니메이션 (뱅그르 돌면서 작아짐) 
	    Animation ani = AnimationUtils.loadAnimation(this, R.anim.tween03);
	    ani.reset();
	    
	    LinearLayout iconGoodjob = (LinearLayout) findViewById (R.id.iconGoodJob);
		ISize.setLinearLayout (iconGoodjob, IScale.endGoodjobX, IScale.endGoodjobY, IScale.endGoodjobWidth, IScale.endGoodjobHeight);
		
		iconGoodjob.clearAnimation();
		iconGoodjob.startAnimation(ani);
		
	    LinearLayout iconCongrat = (LinearLayout) findViewById (R.id.iconCelebration);
		ISize.setLinearLayout (iconCongrat, IScale.endCongratX, IScale.endCongratY, IScale.endCongratWidth, IScale.endCongratHeight);
	    
		iconCongrat.clearAnimation();
		iconCongrat.startAnimation(ani);
		
		ani.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationStart(Animation animation) {}
	        @Override
	        public void onAnimationRepeat(Animation animation) {}
	        @Override
	        public void onAnimationEnd(Animation animation) {}
	    });
	}
	
	//Back button
	@Override
	public void onBackPressed() {
		finish();
	}
	
	//프로그램 닫히면 사운드 끄자 
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    _SndEnding_.stop();
	}
	
} // /public class MainActivity


