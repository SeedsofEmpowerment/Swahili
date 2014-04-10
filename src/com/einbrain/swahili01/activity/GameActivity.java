package com.einbrain.swahili01.activity;

import com.einbrain.swahili01.DisplayManager;
import com.einbrain.swahili01.DragableButton;
import com.einbrain.swahili01.R;
import com.einbrain.swahili01.ResultHandle;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends Activity {
	
	private RelativeLayout CurrentLayout;
	
	//// 전역변수들 ////
	int _LevelScoreRate_ = 3;
	int _CorrectScoreRate_ = 2;
	
	String _FileResult_ = "result_v1_1.txt", _FileCurrent_ = "current_v1_1.txt";
	
	int _ScreenWidth_, _ScreenHeight_;
	int _1SetPhonicNumber_ = 5;
		
	MediaPlayer _SndPhonic_, _SndCorrect_, _SndIncorrect_;
		
	String _CorrectString_ = "correct";
	String _IncorrectString_ = "incorrect";
	
	//기본음운들을 모아놓자. 
	String _PhonicList_[] = {
			"a", "e", "i", "o", "u", // level 1
			"ba", "be", "bi", "bo", "bu", // level 2
			"ma", "me", "mi", "mo", "mu", // level 3
			"ka", "ke", "ki", "ko", "ku", // level 4
			"ta", "te", "ti", "to", "tu", // level 5
			"da", "de", "di", "do", "du", // level 6
			"wa", "we", "wi", "wo", "wu", // level 7
			"pa", "pe", "pi", "po", "pu", // level 8
			"na", "ne", "ni", "no", "nu", // level 9
			"fa", "fe", "fi", "fo", "fu", // level 10
			"va", "ve", "vi", "vo", "vu", // level 11
			"ga", "ge", "gi", "go", "gu", // level 12
			"la", "le", "li", "lo", "lu", // level 13
			"sa", "se", "si", "so", "su", // level 14
			"ja", "je", "ji", "jo", "ju", // level 15
			"ha", "he", "hi", "ho", "hu", // level 16
			"ra", "re", "ri", "ro", "ru", // level 17
			"za", "ze", "zi", "zo", "zu", // level 18
			"ya", "ye", "yi", "yo", "yu", // level 19
			"cha", "che", "chi", "cho", "chu", // level 20
			"sha", "she", "shi", "sho", "shu", // level 21
			"dha", "dhe", "dhi", "dho", "dhu", // level 22
			"mwa", "mwe", "mwi", "mwo", "mwu", // level 23
			"nya", "nye", "nyi", "nyo", "nyu", // level 24
			"nga", "nge", "ngi", "ngo", "ngu", // level 25
			"ng'a", "ng'e", "ng'i", "ng'o", "ng'u", // level 26
			"1", "2", "3", "4", "5", // level 27
			"6", "7", "8", "9", "10", // level 28
			"11", "12", "13", "14", "15", // level 29
			"16", "17", "18", "19", "20" // level 30
	};
	List<Integer> LiLevelCriteria = new ArrayList<Integer>(); //
	
	LinearLayout ListeningKid;
	
	DragableButton ArrIconSmall[] = new DragableButton[5];
	DragableButton IconCorrect;
	DragableButton IconIncorrect;
	
	private DisplayManager IDisplay; 
	private ScaleView IScale;
	ResultHandle IResult = new ResultHandle();
	public static Activity GameActivityObject;
	
	
	//// 프로그램 시작 ////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
		
		GameActivityObject = this; //MenuActivity 에서 사용해야 하니까 지우지 말 것.
		
		// Getting device display size & Setting display size
		IDisplay = new DisplayManager(getApplicationContext()); 
		_ScreenWidth_ = IDisplay.getDisplayPixel()[0]; 
		_ScreenHeight_ = IDisplay.getDisplayPixel()[1];
		CurrentLayout = (RelativeLayout) findViewById(R.id.gameLayout);
		LayoutParams params = CurrentLayout.getLayoutParams();
		params.width = _ScreenWidth_; params.height = _ScreenHeight_;
		
		// Getting current activity's size/position variables 
		IScale = new ScaleView (_ScreenWidth_, _ScreenHeight_);
		IScale.GameActivity(); 
		
		initGame();
		
	} // /onCreate
	

	//게임시작
	private boolean initGame() {
		LiLevelCriteria.add(5); //레벨업 기준 초기화: 5개 연속으로 맞춰야..
		LiLevelCriteria.add(3); //레벨다운 기준 초기화: 3개 연속으로 틀리면..
		
		initImages(); //이미지 초기화
		
		LinearLayout menubox = (LinearLayout) findViewById (R.id.llMenuBox);
		LayoutParams menuParam = (LayoutParams) menubox.getLayoutParams();
		
		//위치 조정 
		((MarginLayoutParams) menuParam).setMargins(IScale.gameMenuboxX, IScale.gameMenuboxY, 0, 0);
		menubox.setLayoutParams(menuParam);
		
		//크기 조정
		menuParam.width = IScale.gameMenuboxWidth;
		menuParam.height = IScale.gameMenuboxHeight;
		
		//컬러 조정
	    menubox.setBackgroundColor(Color.argb(255, 255, 255, 153));
	    
		LinearLayout menu = (LinearLayout) findViewById (R.id.Menu);
	    DragableButton iMenu = new DragableButton (menu, null, null, _ScreenWidth_, _ScreenHeight_, 0, null, null );
	    iMenu.visible (IScale.gameMenuX, IScale.gameMenuY, IScale.gameMenuWidth, IScale.gameMenuHeight, 0);
	    
	    TextView menuText = (TextView) findViewById(R.id.menuText);
	    menuText.setTextSize (TypedValue.COMPLEX_UNIT_PX, IScale.gameMenuTextSize);
	    	    
	    menu.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				Intent intent = new Intent(GameActivity.this, MenuActivity.class);
		        startActivity(intent);
			}
		});
		       
		//점수들 초기화. 기존에 저장된 정보 있으면 불러온다
	    String loadedData = null;
		loadedData = IResult.getData(_FileCurrent_); //최근상태 저장해 놓은 파일을 불러오고 
			
		int currentLevel = 1; // 초기값은 1 
		int currentScore = 0;
		int currentStraightc = 0;
		int totalStraightc = 0;
		
		if (loadedData != null) { // File exists
			System.out.println(loadedData);
			try {
				JSONObject oData = new JSONObject(loadedData);
				
				IResult.mUserName = oData.getString("who");
				IResult.mUserPass = oData.getString("userPass");
				IResult.mSaveTimes = oData.getInt("savetimes");
				
				currentLevel = oData.getInt("level");
				currentScore = oData.getInt("score");
				currentStraightc = oData.getInt("straightc");
				currentStraightc = oData.getInt("tstraightc");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			//메뉴에서 레벨 건너 뛰기를 한 녀석들
			Bundle extras = getIntent().getExtras(); // 전달된 변수 찾아보자 
			if (extras != null) { // 전달된 변수 있으면 
				currentLevel = extras.getInt("gotoLevel"); // 해당 레벨로 집어 넣자  
			}
			
		} else { // No current file. Serious error 
			System.out.println("No current file found");
			finish();
		}
		
		IResult.scoreInit (currentScore, currentStraightc, totalStraightc);// 점수 초기화
		
		//현재 레벨 표시 
		LinearLayout levelTextBox = (LinearLayout) findViewById(R.id.LevelTextBox);
		levelTextBox.setVisibility(LinearLayout.VISIBLE); //일단 보이게 하고 
		LayoutParams paramLevelText = (LayoutParams) levelTextBox.getLayoutParams();
		((MarginLayoutParams) paramLevelText).setMargins(IScale.gameLevelTextX, IScale.gameLevelTextY, 10, 10);
		levelTextBox.setLayoutParams(paramLevelText);
		paramLevelText.width = IScale.gameLevelTextWidth;
		paramLevelText.height = IScale.gameLevelTextHeight;
		
		//현재 스코어 표시 
		LinearLayout scoreTextBox = (LinearLayout) findViewById(R.id.ScoreTextBox);
		scoreTextBox.setVisibility(LinearLayout.VISIBLE); //일단 보이게 하고 
		LayoutParams paramScoreText = (LayoutParams) scoreTextBox.getLayoutParams();
		((MarginLayoutParams) paramScoreText).setMargins(IScale.gameScoreTextX, IScale.gameScoreTextY, 10, 10);
		scoreTextBox.setLayoutParams(paramScoreText);
		paramScoreText.width = IScale.gameScoreTextWidth;
		paramScoreText.height = IScale.gameScoreTextHeight;
		
		//현재 상태 표시 
		LinearLayout currentStatusTextBox = (LinearLayout) findViewById(R.id.CurrentStatusTextBox);
		currentStatusTextBox.setVisibility(LinearLayout.VISIBLE); //일단 보이게 하고
		
		LayoutParams paramStatusText = (LayoutParams) currentStatusTextBox.getLayoutParams();
		((MarginLayoutParams) paramStatusText).setMargins(IScale.gameStatusTextX, IScale.gameStatusTextY, 10, 10);
		currentStatusTextBox.setLayoutParams(paramStatusText);
		paramStatusText.width = IScale.gameStatusTextWidth;
		paramStatusText.height = IScale.gameStatusTextHeight;
		
		
		TextView levelText = (TextView) findViewById(R.id.LevelText);
		levelText.setText ("Level: " + currentLevel);
		levelText.setTextSize (TypedValue.COMPLEX_UNIT_PX, IScale.gameMenuTextSize);
		
		TextView scoreText = (TextView) findViewById(R.id.ScoreText);
		scoreText.setText ("Score: " + currentScore);
		scoreText.setTextSize (TypedValue.COMPLEX_UNIT_PX, IScale.gameMenuTextSize);
		
		TextView statusText = (TextView) findViewById(R.id.StatusText);
		int straightCorrect = IResult.mStraightCorrectLog;
		String straightCorrectText = "☆☆☆☆☆";
		
		switch(straightCorrect) {
		    case 0:
		        break;
		    case 1:
		    	straightCorrectText = "★☆☆☆☆";
		        break;
		    case 2:
		    	straightCorrectText = "★★☆☆☆";
		        break;
		    case 3:
		    	straightCorrectText = "★★★☆☆";
		        break;
		    case 4:
		    	straightCorrectText = "★★★★☆";
		        break;
		    case 5:
		    	straightCorrectText = "★★★★★";
		        break;
		    default:
	    	break;
		}
		statusText.setText (straightCorrectText);
		statusText.setTextSize (TypedValue.COMPLEX_UNIT_PX, IScale.gameMenuTextSize);
		
		
		initButtons(1, currentLevel);
		return false;
	} // /initGame()
	
	
	//드래그할 음운 버튼들 초기화 
	private void initButtons(int sl, int cl) {
		
		int startLevel = sl;
		int currentLevel = cl;
		
		//Show Level & Score
		TextView levelText = (TextView) findViewById(R.id.LevelText);
		levelText.setText ("Level: " + currentLevel);
		TextView scoreText = (TextView) findViewById(R.id.ScoreText);
		scoreText.setText ("Score: " + IResult.mScore);
		
		TextView statusText = (TextView) findViewById(R.id.StatusText);
		int straightCorrect = IResult.mStraightCorrectLog;
		String straightCorrectText = "☆☆☆☆☆";
		
		switch(straightCorrect) {
		    case 0:
		        break;
		    case 1:
		    	straightCorrectText = "★☆☆☆☆";
		        break;
		    case 2:
		    	straightCorrectText = "★★☆☆☆";
		        break;
		    case 3:
		    	straightCorrectText = "★★★☆☆";
		        break;
		    case 4:
		    	straightCorrectText = "★★★★☆";
		        break;
		    case 5:
		    	straightCorrectText = "★★★★★";
		        break;
		    default:
	    	break;
		}
		statusText.setText (straightCorrectText);
					
		//pick 5 numbers from the start level to current level without repetition and ordering
		Random r = new Random(); // Ideally just create one instance globally
		List<Integer> li1setRandom = new ArrayList<Integer>(); //current question options
			
		for (int i = 0; i < (_1SetPhonicNumber_ - 1); i++) { //choose four options (distractors)
		    while(true) {
		    	int min = (startLevel-1) * _1SetPhonicNumber_;
		    	int max = currentLevel * _1SetPhonicNumber_;
		    	Integer next = r.nextInt(max-min) + min; //min (inclusive) ~ max (exclusive)
		        if (!li1setRandom.contains(next)) {
		        	li1setRandom.add(next);
		            break;
		        }
		    }
		}
		
		int qNum = r.nextInt(5); //(0~4) for positioning of correct answer
		int question = -1; //correct answer number in the current level
		
		while (question == -1) {
			int min = (currentLevel-1) * _1SetPhonicNumber_;
	    	int max = currentLevel * _1SetPhonicNumber_;
	    	question = r.nextInt(max - min) + min; //min (inclusive) ~ max (exclusive)
	    	for (int i=0; i<li1setRandom.size(); i++){
	    		if (li1setRandom.get(i)==question) {
	    			question = -1;
	    		}
	    	}
		}
		li1setRandom.add(qNum, question); //insert correct answer into the distractors
				
		String chosenQ = _PhonicList_[ li1setRandom.get(qNum) ]; // li1setRandom[qNum]이 뽑힌녀석
		System.out.println("Cur.Lv: " + currentLevel + " " + li1setRandom + " Q: " + question + " cQ: " + chosenQ );
		
		//Listening Kid Image
	    ListeningKid = (LinearLayout) findViewById (R.id.ListeningKid);
	    DragableButton iListeningKid = new DragableButton (ListeningKid, null, null, _ScreenWidth_, _ScreenHeight_, 0, null, null );
	    iListeningKid.visible (IScale.gameKidX, IScale.gameKidY, IScale.gameKidWidth, IScale.gameKidHeight, 0);
	    
	    //ClickToListen 버튼 
	    LinearLayout clickToListen = (LinearLayout) findViewById (R.id.ClickToListen);
	    DragableButton iClickToListen = new DragableButton (clickToListen, null, null, _ScreenWidth_, _ScreenHeight_, 0, null, null );
	    iClickToListen.visible (IScale.gameClickX, IScale.gameClickY, IScale.gameClickWidth, IScale.gameClickHeight, 0);
	    
	    //사운드 파일 불러와라. 이름 안되는 것들은 이름 바꿔주자. 
	    String sndStr = chosenQ;
	    if (chosenQ == "do") sndStr = "do_";
		else if (chosenQ == "ng'a") sndStr = "ng_a";
		else if (chosenQ == "ng'e") sndStr = "ng_e";
		else if (chosenQ == "ng'i") sndStr = "ng_i";
		else if (chosenQ == "ng'o") sndStr = "ng_o";
		else if (chosenQ == "ng'u") sndStr = "ng_u";
	    
	    for (int i=1; i<21; i++){ //숫자 사운드 파일이 n1, n2 식으로 되어 있다. 
	    	String t = String.valueOf(i);
	    	if ( chosenQ.equals(t) ) sndStr = "n" + t;
	    }
	    
	    Uri myUri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + sndStr);
	    _SndPhonic_ = MediaPlayer.create(this, myUri);

	    System.out.println("_SndPhonic_: " + _SndPhonic_);
	    
	    iClickToListen.mLayout.setOnTouchListener (new CClickListener( )); //이벤트 리스너 
	    
	    //이미지와 텍스트 결합된 녀석 불러오고 
	    LinearLayout arrImgPhonicSmallRed[] = new LinearLayout[5];
	    arrImgPhonicSmallRed[0] = (LinearLayout) findViewById(R.id.mimgPhonicSmall01);
	    arrImgPhonicSmallRed[1] = (LinearLayout) findViewById(R.id.mimgPhonicSmall02);
	    arrImgPhonicSmallRed[2] = (LinearLayout) findViewById(R.id.mimgPhonicSmall03);
	    arrImgPhonicSmallRed[3] = (LinearLayout) findViewById(R.id.mimgPhonicSmall04);
	    arrImgPhonicSmallRed[4] = (LinearLayout) findViewById(R.id.mimgPhonicSmall05);
	    
		//텍스트 집어 넣는 곳 불러오고  
		TextView arrTextView[] = new TextView[5];
		arrTextView[0] = (TextView) findViewById(R.id.mimgPhonicSmalltext01);
		arrTextView[1] = (TextView) findViewById(R.id.mimgPhonicSmalltext02);
		arrTextView[2] = (TextView) findViewById(R.id.mimgPhonicSmalltext03);
		arrTextView[3] = (TextView) findViewById(R.id.mimgPhonicSmalltext04);
		arrTextView[4] = (TextView) findViewById(R.id.mimgPhonicSmalltext05);
		
		//phonics 아이콘 폰트 바꾸기 
		Typeface font = Typeface.createFromAsset(getAssets(), "a.ttf");  
		arrTextView[0].setTypeface(font);
		arrTextView[1].setTypeface(font);
		arrTextView[2].setTypeface(font);
		arrTextView[3].setTypeface(font);
		arrTextView[4].setTypeface(font);
			
	    //텍스트네모에 phonics 집어넣고 드래그 가능한 아이콘으로 만들기 
	    for (int i=0; i<arrImgPhonicSmallRed.length; i++){
	    	ArrIconSmall[i] = new DragableButton(arrImgPhonicSmallRed[i], arrTextView[i], _PhonicList_[li1setRandom.get(i)], _ScreenWidth_, _ScreenHeight_, currentLevel, chosenQ, li1setRandom );
	    	ArrIconSmall[i].visible (IScale.gameArrIconSmallX[i], IScale.gameIconSmallY, IScale.gameIconSmallWidth, IScale.gameIconSmallHeight, IScale.gameIconSmallTextSize);
	    	ArrIconSmall[i].mLayout.setOnTouchListener (new CMoveListener(i)); //이벤트 리스너 
		}
		
	    LinearLayout iconCorrect = (LinearLayout) findViewById(R.id.IconCorrect);
	    IconCorrect = new DragableButton(iconCorrect, null, null, _ScreenWidth_, _ScreenHeight_, 0, null, null);
	    LinearLayout iconIncorrect = (LinearLayout) findViewById(R.id.IconIncorrect);
	    IconIncorrect = new DragableButton(iconIncorrect, null, null, _ScreenWidth_, _ScreenHeight_, 0, null, null);
	    
	}// /initButtons
	
	
	private class CClickListener implements OnTouchListener {
				
		public boolean onTouch (View v, MotionEvent event) {
			switch (event.getAction()) {
	        	case MotionEvent.ACTION_DOWN:
	        		//play sound
	        		//stopPlaying(_SndPhonic_);
	        		if ( _SndPhonic_!=null ) {
	        			//_SndPhonic_.setVolume(1.0f, 1.0f);
	        			_SndPhonic_.start();
	        		}
	        		break; 
	            case MotionEvent.ACTION_MOVE:
	            	//do nothing
	                break;
	            case MotionEvent.ACTION_UP:
	                //do nothing
	                break;
			}
			return true;
		}
	}// /CClickListener
	
	
	//이미지 초기화 
	private void initImages() {
		IDisplay.insertBackgroundView(CurrentLayout); 
		IDisplay.changeBackground();
	}// /initImages

		
	//// 이벤트 리스너  //// 
	public class CMoveListener implements OnTouchListener {
		
		DragableButton self;
		public CMoveListener(int i) {
			self = ArrIconSmall[i];
		}
		
		// Event handler 
		public boolean onTouch (View v, MotionEvent event) {
			
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //do nothing
                    break; 
                case MotionEvent.ACTION_MOVE:
                	self.setViewPosition(CurrentLayout, self.mLayout, event, self.mParam); //움직여 주
                    break;
                case MotionEvent.ACTION_UP:
                	CollisionHandler( self, self.checkCollision(ListeningKid, _CorrectString_, _IncorrectString_), self.mCurrentLevel, self.mLiOthers );
                    break;
            }
            return true;
      }

	}// /CMoveListener
	
	
	public void CollisionHandler(final DragableButton self, String result, final int currentLevel, List<Integer> liOthers) {
		
		String options =  _PhonicList_[liOthers.get(0)]; //첫번째로 초기화 시키고 
		for (int i=1; i < liOthers.size(); i++ ){ //두번째꺼 부터 집어 넣자 
			options = options + "/" + _PhonicList_[liOthers.get(i)];
		}
		
		String correctPhonic = null;
		if (result == _CorrectString_) {
			correctPhonic = self.mLabel; 
		}
		
		IResult.scoreManage (result, correctPhonic, _CorrectString_, _IncorrectString_); //점수 기록을 위해 결과와 맞은 phonic 을 (중복으로 맞은 것은 연속 점수에 넣지 않는다) 넣자 
		int straightCorrect = IResult.mStraightCorrectLog;
		int straightIncorrect = IResult.mStraightIncorrectLog;
		int totalStraightCorrect = IResult.mTotalStraightCorrectLog;

		//점수 기준
		int score = IResult.mScore;
		//score = score + (currentLevel-1)*_LevelScoreRate_ + totalStraightCorrect*_CorrectScoreRate_; //레벨 1은 가중치 없다~ 
		//System.out.println("total:" + totalStraightCorrect + "/score:"+score);
		
		//IResult.mScore = score; //점수 수정
		
		Date date = new Date();
		SimpleDateFormat sformat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timestamp = sformat.format(date);
		
		// Save game info
		JSONObject currentBasicInfo =new JSONObject();
		try {
			currentBasicInfo.put("who", IResult.mUserName);
			currentBasicInfo.put("userPass", IResult.mUserPass);
			currentBasicInfo.put("savetimes", IResult.mSaveTimes);
			
			currentBasicInfo.put("when", timestamp);
			currentBasicInfo.put("level", currentLevel);
			currentBasicInfo.put("phonic", self.mLabel);
			currentBasicInfo.put("result", result);
			currentBasicInfo.put("options", options);
			
			currentBasicInfo.put("straightc", straightCorrect);
			currentBasicInfo.put("straightinc", straightIncorrect);
			currentBasicInfo.put("tstraightc", totalStraightCorrect);
			currentBasicInfo.put("score", score);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
								
		IResult.inputData (currentBasicInfo.toString(), _FileCurrent_, "overwrite"); // Current status saved
		IResult.inputData (getString(R.string.split_char) + currentBasicInfo.toString(), _FileResult_, "append"); // Add the current status to the result file
		        		
		Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha01);
		
		if ( result == _CorrectString_ ) { //정답
			if ( _SndPhonic_!=null ) {
				_SndPhonic_.stop();
				_SndPhonic_.release();
				_SndPhonic_ = null;
			}
			//stopPlaying(_SndCorrect_);
			if ( _SndCorrect_!=null ) {
				_SndCorrect_.stop();
				_SndCorrect_.release();
				_SndCorrect_ = null;
			}
			_SndCorrect_ = MediaPlayer.create(this, R.raw.correct); //맞았을 때 나는 소리 
			
			System.out.println("_SndCorrect_: " + _SndCorrect_);
			_SndCorrect_.start();
			System.out.println("straightCorrect: " + straightCorrect);
			System.out.println("straightIncorrect: " + straightIncorrect);
			
			//Scoring
			//int score = IResult.mScore;
			score = score + (currentLevel-1)*_LevelScoreRate_ + totalStraightCorrect*_CorrectScoreRate_; //레벨 1은 가중치 없다~ 
			System.out.println("total:" + totalStraightCorrect + "/score:"+score);
			
			IResult.mScore = score; //점수 수정
			
			
			//레벌업/다운 할 것인지 체크 
			final String statusCheck = IResult.statusCheck (LiLevelCriteria, currentLevel, straightCorrect, straightIncorrect);
			
			System.out.println("statusCheck: " + statusCheck);
			//System.out.println(straightCorrect);
			
			//Correct Icon 애니메이션 (나왔다가 그냥 사라지는 것)
			IconCorrect.visible (IScale.gameIconCorrectX, IScale.gameIconCorrectY, IScale.gameIconCorrectWidth, IScale.gameIconCorrectHeight, 0);
			alpha.reset();
		    IconCorrect.mLayout.clearAnimation();
		    IconCorrect.mLayout.startAnimation(alpha);
		    alpha.setAnimationListener(new AnimationListener() {
		        @Override
		        public void onAnimationStart(Animation animation) {}
		        @Override
		        public void onAnimationRepeat(Animation animation) {}
		        @Override
		        public void onAnimationEnd(Animation animation) {
		        	IconCorrect.hide();
		        }
		    });
			
			//맞았을 때 음운 아이콘 애니메이션 
		    Animation ani = AnimationUtils.loadAnimation(this, R.anim.tween02);
		    ani.reset();
		    self.mTextview.clearAnimation();
		    self.mTextview.startAnimation(ani);
		    ani.setAnimationListener(new AnimationListener() {
		        @Override
		        public void onAnimationStart(Animation animation) {}
		        @Override
		        public void onAnimationRepeat(Animation animation) {}
		        @Override
		        public void onAnimationEnd(Animation animation) {
		        	if ( statusCheck=="up" ) {
						IResult.mStraightCorrectLog = 0;
						IResult.mStraightIncorrectLog = 0;
						nextLevel(currentLevel + 1); //잘했어 레벨업! 
					} else {
						IResult.mStraightIncorrectLog = 0;
						nextLevel(currentLevel); //Need more straight
					}
		        }
		    });
		    
		} else if( result == _IncorrectString_ ){ //오답
			final String statusCheck = IResult.statusCheck (LiLevelCriteria, currentLevel, straightCorrect, straightIncorrect);
			
			if ( _SndIncorrect_!=null ) {
				_SndIncorrect_.stop();
				_SndIncorrect_.release();
				_SndIncorrect_ = null;
			}
			_SndIncorrect_ = MediaPlayer.create(this, R.raw.incorrect); //틀렸을때 나는 소리 
			System.out.println("_SndIncorrect_: " + _SndIncorrect_);
			_SndIncorrect_.start();
			
			System.out.println("straightCorrect: " + straightCorrect);
			System.out.println("straightIncorrect: " + straightIncorrect);
			System.out.println("statusCheck: " + statusCheck);
						
			IconIncorrect.visible (IScale.gameIconCorrectX, IScale.gameIconCorrectY, IScale.gameIconCorrectWidth, IScale.gameIconCorrectHeight, 0);
		    alpha.reset();
		    IconIncorrect.mLayout.clearAnimation();
		    IconIncorrect.mLayout.startAnimation(alpha);
		    alpha.setAnimationListener(new AnimationListener() {
		        @Override
		        public void onAnimationStart(Animation animation) {}
		        @Override
		        public void onAnimationRepeat(Animation animation) {}
		        @Override
		        public void onAnimationEnd(Animation animation) {
		        	IconIncorrect.hide();
		        }
		    });
		    
			Animation ani = AnimationUtils.loadAnimation(this, R.anim.tween02);
		    ani.reset();
		    self.mLayout.clearAnimation();
		    self.mLayout.startAnimation(ani);
		    ani.setAnimationListener(new AnimationListener() {
		        @Override
		        public void onAnimationStart(Animation animation) {}
		        @Override
		        public void onAnimationRepeat(Animation animation) {}
		        @Override
		        public void onAnimationEnd(Animation animation) {
		        	//self.reposition(); //제자리로
		        	if (statusCheck=="down"){
						IResult.mStraightCorrectLog = 0;
						IResult.mStraightIncorrectLog = 0;
						if(currentLevel == 1) nextLevel(currentLevel); //현재 레벨이 1이면 뒤로 갈 것도 없다..
						else nextLevel(currentLevel - 1); //강등! 레벨다운!
					} else {
						IResult.mStraightCorrectLog = 0;
						nextLevel(currentLevel); //Need more straight
					}
		        }
		    }); 
		} else {
			//do nothing
		}
	}
	
	private void nextLevel(int i) {
		if(i>0 && i<31) {
			IDisplay.changeBackground();
			initButtons(1, i);	
		} else if (i==31) { //마지막 레벨... 
			//Go to the Game Activity
			Intent intent = new Intent(GameActivity.this, EndActivity.class);
	        startActivity(intent);
	        finish();
		} else { //Error
			
		}
	}
	
	
} // /public class MainActivity


