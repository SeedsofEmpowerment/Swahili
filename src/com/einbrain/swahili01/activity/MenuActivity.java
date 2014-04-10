package com.einbrain.swahili01.activity;

import com.einbrain.swahili01.DisplayManager;
import com.einbrain.swahili01.R;
import com.einbrain.swahili01.ResultHandle;
import com.einbrain.swahili01.SizePositionManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MenuActivity extends Activity {
	
	private RelativeLayout CurrentLayout;
	
	private int _ScreenWidth_, _ScreenHeight_;
	
	private String _FileUsers_ = "users_v1_1.txt", _FileCurrent_ = "current_v1_1.txt", _FileResult_ = "result_v1_1.txt";
	
	private DisplayManager IDisplay; 
	private ScaleView IScale;
	private SizePositionManager ISize = new SizePositionManager();
	private ResultHandle IResult = new ResultHandle();
	
	////프로그램 시작 ////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
	
		// Getting device display size & Setting display size
		IDisplay = new DisplayManager(getApplicationContext()); 
		_ScreenWidth_ = IDisplay.getDisplayPixel()[0]; 
		_ScreenHeight_ = IDisplay.getDisplayPixel()[1];
		CurrentLayout = (RelativeLayout) findViewById(R.id.menuLayout);
		LayoutParams params = CurrentLayout.getLayoutParams();
		params.width = _ScreenWidth_; params.height = _ScreenHeight_;
		
		// Getting current activity's size/position variables 
		IScale = new ScaleView (_ScreenWidth_, _ScreenHeight_);
		IScale.MenuActivity(); 
		
		initBackground();
		initButtons();
		
	} // /onCreate
	
	
	private void initBackground() {
		IDisplay.insertBackgroundView(CurrentLayout); 
		IDisplay.changeBackground();
	}// /initBackground
	
	
	private void initButtons() {
		final Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha01);
		
		// Logged-in Name
		LinearLayout menuLoggedNameLayout = (LinearLayout) findViewById(R.id.menuLoggedNameLayout);
		ISize.setLinearLayout (menuLoggedNameLayout, IScale.menuLoggedNameX, IScale.menuLoggedNameY, IScale.menuLoggedNameWidth, IScale.menuLoggedNameHeight);
		LinearLayout menuKeepPlayLayout = (LinearLayout) findViewById(R.id.menuKeepPlay);
		ISize.setLinearLayout (menuKeepPlayLayout, IScale.menuKeepPlayX, IScale.menuKeepPlayY, IScale.menuKeepPlayWidth, IScale.menuKeepPlayHeight);
		TextView userNameText = (TextView) findViewById(R.id.userNameText);
				
		// User change button
		LinearLayout menuUserChange = (LinearLayout) findViewById(R.id.menuUserChange);
		ISize.setLinearLayout (menuUserChange, IScale.menuUserChangeX, IScale.menuUserChangeY, IScale.menuUserChangeWidth, IScale.menuUserChangeHeight);
				
		/*
		// 원하는 레벨로 보내기 레이아웃 
	    LinearLayout menuGoLevelLayout = (LinearLayout) findViewById(R.id.menuGoLevelLayout);
	    ISize.setLinearLayout (menuGoLevelLayout, IScale.menuGoLevelLayoutX, IScale.menuGoLevelLayoutY, IScale.menuGoLevelLayoutWidth, IScale.menuGoLevelLayoutHeight);
	    TextView menuGoLevelText = (TextView) findViewById(R.id.menuGoLevelText);
		*/
		
		// Login input text box		
		LinearLayout menuLoginTextLayout = (LinearLayout) findViewById(R.id.menuLoginTextLayout);
		ISize.setLinearLayout (menuLoginTextLayout, IScale.menuLoginTextX, IScale.menuLoginTextY, IScale.menuLoginTextWidth, IScale.menuLoginTextHeight);
		TextView textID = (TextView) findViewById(R.id.textID);
		TextView textPW = (TextView) findViewById(R.id.textPW);
		textID.setText ("ID");
		textPW.setText ("PW");
		
		
		// Play button
		LinearLayout menuPlayLayout = (LinearLayout) findViewById(R.id.menuPlay);
		ISize.setLinearLayout (menuPlayLayout, IScale.menuPlayX, IScale.menuPlayY, IScale.menuPlayWidth, IScale.menuPlayHeight);
		TextView menuTextLoginPlay = (TextView) findViewById(R.id.textLoginPlay);
		
		menuTextLoginPlay.setText ("Login");   
		ISize.setTextView(menuTextLoginPlay, IScale.menuTextLoginPlayX, IScale.menuTextLoginPlayY, IScale.menuTextLoginPlayWidth, IScale.menuTextLoginPlayHeight);
		
		// Admin 
		/*
	    LinearLayout menuAdminLoginLayout = (LinearLayout) findViewById(R.id.menuAdminLoginLayout);
	    ISize.setLinearLayout (menuAdminLoginLayout, IScale.menuAdminLoginX, IScale.menuAdminLoginY, IScale.menuAdminLoginWidth, IScale.menuAdminLoginHeight);
		TextView adminSubmitText = (TextView) findViewById(R.id.adminSubmitText);
		*/
		// Wrong Sign
		final LinearLayout menuWrongLayout = (LinearLayout) findViewById(R.id.menuWrong);
		ISize.setLinearLayout (menuWrongLayout, IScale.menuWrongX, IScale.menuWrongY, IScale.menuWrongWidth, IScale.menuWrongHeight);
		
	    
	    // Read a login file to check logged-in 
	    String loadedUsers = null;
		String loadedData = null;
	    
	    loadedUsers = IResult.getData(_FileUsers_); //최근상태 저장해 놓은 파일을 불러오고
	    loadedData = IResult.getData(_FileCurrent_); //최근상태 저장해 놓은 파일을 불러오고 
			
		String userName = null; 
		
		if (loadedData != null && loadedUsers != null) { // Already logged-in 
			try {
				JSONObject oData = new JSONObject(loadedData);
				userName = oData.getString("who");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			// Login input text box & Play button: Invisible
			menuLoginTextLayout.setVisibility(View.INVISIBLE);
			menuPlayLayout.setVisibility(View.INVISIBLE);
			menuTextLoginPlay.setVisibility(View.INVISIBLE);
			
			// Login name show
			userNameText.setText (userName);
		} else {// Not logged-in 
			// userNameText & User Change Invisible  
			menuLoggedNameLayout.setVisibility(View.INVISIBLE);
			menuUserChange.setVisibility(View.INVISIBLE);
			menuKeepPlayLayout.setVisibility(View.INVISIBLE);
			//menuGoLevelLayout.setVisibility(View.INVISIBLE);
		}
		
		
		/////////////////////////
		//// Button Handlers ////
		
		// Keep Playing 
		menuKeepPlayLayout.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				finish();
			}
		});
		
		/*
		// 레벨 세팅: 원하는 레벨로 보내주마
		menuGoLevelText.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {

				EditText menuGoLevelNumber = (EditText) findViewById(R.id.menuGoLevelNumber); // 레벨 입력 박스에서 
				int level = Integer.parseInt(menuGoLevelNumber.getText().toString()); // 받아온 레벨을 숫자로 바꾸고 
 
				if( level>0 && level<31 ){ //제대로 된 레벨 입력 1~30 
					// Go to the Game Activity
					Intent intent = new Intent(MenuActivity.this, GameActivity.class);
					intent.putExtra("gotoLevel", level);
			        startActivity(intent);

				} else { //레벨 입력 잘못하면 ~ 
					alpha.reset();
					menuWrongLayout.clearAnimation();
					menuWrongLayout.startAnimation(alpha);
				    alpha.setAnimationListener(new AnimationListener() {
				        @Override
				        public void onAnimationStart(Animation animation) {}
				        @Override
				        public void onAnimationRepeat(Animation animation) {}
				        @Override
				        public void onAnimationEnd(Animation animation) {
				        	menuWrongLayout.setVisibility(View.INVISIBLE);
				        }
				    });
				}
			}
		});
		*/
				
		// Sign-up OR sign-in and Play
	    menuPlayLayout.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				
				EditText userNameField = (EditText)findViewById(R.id.userName);
				EditText userPassField = (EditText)findViewById(R.id.userPass);
				
				String userName = userNameField.getText().toString();
				String userPass = userPassField.getText().toString();
				
				String userNameTrimmed = userName.replace(" ", "");
				String userPassTrimmed = userPass.replace(" ", "");
				
				boolean userNameSpaceCheck = false;
				boolean userPassSpaceCheck = false;
				
				if (userName.equals(userNameTrimmed) ) userNameSpaceCheck = true;
				if (userPass.equals(userPassTrimmed) ) userPassSpaceCheck = true;
				
				if ( userNameField.getText().length() != 0 && userPassField.getText().length() != 0 
						&& userNameSpaceCheck && userPassSpaceCheck ){ //Name and Pass typed
					
					// Read a login file to check sign-up or sign-in 
				    String loadedUsers = null;
				    loadedUsers = IResult.getData(_FileUsers_); 
					
				    Date date = new Date();
					SimpleDateFormat sformat = new SimpleDateFormat("yyyyMMdd_HHmmss");
					String timestamp = sformat.format(date);
					
					if (loadedUsers != null) { // File exists
						
						String signUpStatus = "new";
						
						String loginUserName = null;
						String loginUserPass = null;
						int loginUserCurrentLevel = 1;
						int loginUserCurrentStraightC = 0;
						int loginUserTotalStraightC = 0;
						int loginUserScore = 0;
						int loginUserSaveTimes = 0;
						
						try {
							String[] separated = loadedUsers.split( getString(R.string.split_char) );
														
							JSONArray usersArr = new JSONArray();
							
							for (String item : separated){
								JSONObject usersDataTmp = new JSONObject(item);
								usersArr.put(usersDataTmp);
							}
							
							for(int i=0; i<usersArr.length(); i++){
								JSONObject userProperties = usersArr.getJSONObject(i);
								
								if ( userNameField.getText().toString().equals( userProperties.getString("who") ) 
										&& userPassField.getText().toString().equals( userProperties.getString("userPass") )
										&& userProperties.getInt("savetimes") >= loginUserSaveTimes ) {
									
									// Get game info from the users file
									loginUserSaveTimes = userProperties.getInt("savetimes");
									signUpStatus = "loggin";
									
									loginUserName = userProperties.getString("who");
									loginUserPass = userProperties.getString("userPass");
									loginUserCurrentLevel = userProperties.getInt("level");
									loginUserCurrentStraightC = userProperties.getInt("straightc");
									loginUserTotalStraightC = userProperties.getInt("tstraightc");
									loginUserScore = userProperties.getInt("score");
									
								} else if (  userNameField.getText().toString().equals( userProperties.getString("who") ) 
										&& !userPassField.getText().toString().equals( userProperties.getString("userPass") ) ) {
									
									signUpStatus = "wrongPass";
									
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
							signUpStatus = "error";
						}
						
						System.out.println("signUpStatus: " + signUpStatus);
						
						// Login Process Start
						if ( signUpStatus == "loggin" ){ // Already signed-up and right password  
							
							// Save game info got from the users file
							JSONObject loginUserBasicInfo =new JSONObject();
							try {
								loginUserBasicInfo.put("who", loginUserName);
								loginUserBasicInfo.put("userPass", loginUserPass);
								loginUserBasicInfo.put("when", timestamp);
								loginUserBasicInfo.put("level", loginUserCurrentLevel);
								loginUserBasicInfo.put("straightc", loginUserCurrentStraightC);
								loginUserBasicInfo.put("tstraightc", loginUserTotalStraightC);
								loginUserBasicInfo.put("score", loginUserScore);
								loginUserBasicInfo.put("savetimes", loginUserSaveTimes + 1);
								
							} catch (JSONException e) {
								e.printStackTrace();
							}
													
							IResult.inputData (loginUserBasicInfo.toString(), _FileCurrent_, "overwrite"); // Sign-in Log
																
							// Go to the Game Activity
							Intent intent = new Intent(MenuActivity.this, GameActivity.class);
					        startActivity(intent);
					        
						} else if (signUpStatus == "wrongPass" ){ // Already signed-up but wrong password  
							
							alpha.reset();
							menuWrongLayout.clearAnimation();
							menuWrongLayout.startAnimation(alpha);
						    alpha.setAnimationListener(new AnimationListener() {
						        @Override
						        public void onAnimationStart(Animation animation) {}
						        @Override
						        public void onAnimationRepeat(Animation animation) {}
						        @Override
						        public void onAnimationEnd(Animation animation) {
						        	menuWrongLayout.setVisibility(View.INVISIBLE);
						        }
						    });
						    
						} else if ( signUpStatus == "new") { // New sign-up and sign-in
							
							int currentLevel = 1; // 초기값은 1 
							int currentScore = 0;
							int currentStraightc = 0;
							int totalStraightc = 0;
							int savetimes = 0;
														
							// Add to the existing user list
							JSONObject newUserBasicInfo =new JSONObject();
							
							try {
								newUserBasicInfo.put("who", userName);
								newUserBasicInfo.put("userPass", userPass);
								newUserBasicInfo.put("when", timestamp);
								newUserBasicInfo.put("level", currentLevel);
								newUserBasicInfo.put("straightc", currentStraightc);
								newUserBasicInfo.put("tstraightc", totalStraightc);
								newUserBasicInfo.put("score", currentScore);
								newUserBasicInfo.put("savetimes", savetimes);
							} catch (JSONException e) {
								e.printStackTrace();
							}
								
							IResult.inputData (getString(R.string.split_char) + newUserBasicInfo.toString(), _FileUsers_, "append");// Login Log
							IResult.inputData (newUserBasicInfo.toString(), _FileCurrent_, "overwrite"); // Sign-in Log
							
							// Go to the Game Activity
							Intent intent = new Intent(MenuActivity.this, GameActivity.class);
					        startActivity(intent);
					        
						} else { // Error 
							System.out.println("Error: " + signUpStatus);
						}// /if ( signUpStatus == "loggin" )

					} else { // Users file doesn't exist, Sign-up and sign-in
						
						int currentLevel = 1; // 초기값은 1 
						int currentScore = 0;
						int currentStraightc = 0;
						int totalStraightc = 0;
						int savetimes = 0;
						
						JSONObject firstUserBasicInfo =new JSONObject();
						try {
							firstUserBasicInfo.put("who", userName);
							firstUserBasicInfo.put("userPass", userPass);
							firstUserBasicInfo.put("when", timestamp);
							firstUserBasicInfo.put("level", currentLevel);
							firstUserBasicInfo.put("straightc", currentStraightc);
							firstUserBasicInfo.put("tstraightc", totalStraightc);
							firstUserBasicInfo.put("score", currentScore);
							firstUserBasicInfo.put("savetimes", savetimes);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
						IResult.inputData (firstUserBasicInfo.toString(), _FileUsers_, "overwrite"); // Users (Sign-up) file
						IResult.inputData (firstUserBasicInfo.toString(), _FileResult_, "overwrite"); // Users (Sign-up) file
						IResult.inputData (firstUserBasicInfo.toString(), _FileCurrent_, "overwrite"); // Sign-in Log
															
						// Go to the Game Activity
						Intent intent = new Intent(MenuActivity.this, GameActivity.class);
				        startActivity(intent);

					}
			        
				} else if ( userNameSpaceCheck || userPassSpaceCheck ) { // Contains space 
					alpha.reset();
					menuWrongLayout.clearAnimation();
					menuWrongLayout.startAnimation(alpha);
				    alpha.setAnimationListener(new AnimationListener() {
				        @Override
				        public void onAnimationStart(Animation animation) {}
				        @Override
				        public void onAnimationRepeat(Animation animation) {}
				        @Override
				        public void onAnimationEnd(Animation animation) {
				        	menuWrongLayout.setVisibility(View.INVISIBLE);
				        }
				    });
				} else { //Name or Pass NOT typed 
					alpha.reset();
					menuWrongLayout.clearAnimation();
					menuWrongLayout.startAnimation(alpha);
				    alpha.setAnimationListener(new AnimationListener() {
				        @Override
				        public void onAnimationStart(Animation animation) {}
				        @Override
				        public void onAnimationRepeat(Animation animation) {}
				        @Override
				        public void onAnimationEnd(Animation animation) {
				        	menuWrongLayout.setVisibility(View.INVISIBLE);
				        }
				    });
					
				}
			}
		});
	    		    
	    // User Change Button Handler
		menuUserChange.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				MainActivity.MainActivityObject.finish();//이전 activity 지우고 
				
				if ( GameActivity.GameActivityObject != null ) {
					GameActivity.GameActivityObject.finish();//이전 activity 지우고 
				}

				Date date = new Date();
				SimpleDateFormat sformat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				String timestamp = sformat.format(date);
 
				// Add current info from the current file to the users file
				String loadedCurrent = null;
				loadedCurrent = IResult.getData(_FileCurrent_); //최근상태 저장해 놓은 파일을 불러오고 
				
				try {
					JSONObject oData = new JSONObject(loadedCurrent);
					oData.put("when", timestamp);
					oData.put("savetimes", oData.getInt("savetimes") + 1);
					
					IResult.inputData (getString(R.string.split_char) + oData.toString(), _FileUsers_, "append");// Update current status into the users file
						
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				IResult.removeFile(_FileCurrent_); // Delete current login file
				
				Intent intent = new Intent(MenuActivity.this, MainActivity.class);
		        startActivity(intent);
			}
		});
	    
		/*
		// Admin Button Handler
	    adminSubmitText.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				// Password show
				EditText adminPassField = (EditText) findViewById(R.id.adminPass);
			    
			    if ( adminPassField.getText().toString().equals("seeds")) { // Admin Login Success
			    	// Go to the Result Activity
			    	Intent intent = new Intent(MenuActivity.this, ResultActivity.class);
			        startActivity(intent);
			    	
			    } else { // Admin Login Fail
			    	alpha.reset();
					menuWrongLayout.clearAnimation();
					menuWrongLayout.startAnimation(alpha);
				    alpha.setAnimationListener(new AnimationListener() {
				        @Override
				        public void onAnimationStart(Animation animation) {}
				        @Override
				        public void onAnimationRepeat(Animation animation) {}
				        @Override
				        public void onAnimationEnd(Animation animation) {
				        	menuWrongLayout.setVisibility(View.INVISIBLE);
				        }
				    });
			    	
			    }
				
			}
		});
		*/
	    
	}// /initButtons
		
	
} // /class MenuActivity
