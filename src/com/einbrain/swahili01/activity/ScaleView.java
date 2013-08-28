package com.einbrain.swahili01.activity;

public class ScaleView {
	//// private properties ////
	private int mScreenWidth;
	private int mScreenHeight;
	
	//// public properties ////
	
	// MainActivity
	public int mainPlayX, mainPlayY, mainPlayWidth, mainPlayHeight;
		
	// MenuActivity
	public int menuLoggedNameX, menuLoggedNameY, menuLoggedNameWidth, menuLoggedNameHeight, 
		menuKeepPlayX, menuKeepPlayY, menuKeepPlayWidth, menuKeepPlayHeight,	
		menuUserChangeX, menuUserChangeY, menuUserChangeWidth, menuUserChangeHeight, 
		menuLoginTextX, menuLoginTextY, 	menuLoginTextWidth,	menuLoginTextXHeight,
		menuPlayX, menuPlayY, menuPlayWidth, menuPlayHeight,
		menuAdminLoginX, menuAdminLoginY, menuAdminLoginWidth, menuAdminLoginHeight,
		menuWrongX, menuWrongY, menuWrongWidth, menuWrongHeight;
		
	// GameActivity
	public int gameMenuboxX, gameMenuboxY, gameMenuboxWidth, gameMenuboxHeight, gameMenuWidth, gameMenuHeight, gameMenuX, gameMenuY, gameMenuTextSize,
		gameLevelTextWidth,	gameLevelTextHeight, gameLevelTextX, gameLevelTextY,
		gameScoreTextWidth,	gameScoreTextHeight, gameScoreTextX, gameScoreTextY,
		gameKidWidth, gameKidHeight, gameKidX, gameKidY, gameClickWidth, gameClickHeight, gameClickX, gameClickY,
		gameIconCorrectWidth, gameIconCorrectHeight, gameIconCorrectX, gameIconCorrectY,
		gameIconIncorrectWidth, gameIconIncorrectHeight, gameIconIncorrectX, gameIconIncorrectY,
		gameIconSmallWidth, gameIconSmallHeight, gameIconSmallTextSize, gameIconSmallY;
	public int[] gameArrIconSmallX = new int[5];
	
	// ResultActivity
	public int resultTextSize;
	
	//// Constructor ////
	public ScaleView (int sw, int sh)  {  
		mScreenWidth = sw;
		mScreenHeight = sh;
		//SetCommon();
    }  
	
	/////////////////////////
	//// private methods ////
	
	
	
	////////////////////////
	//// public methods ////
	public void MainActivity (){
		mainPlayX = mScreenWidth*70/100;
		mainPlayY = mScreenHeight*75/100;
		mainPlayWidth = mScreenWidth*30/100;
		mainPlayHeight = mainPlayWidth;
	}
	
	public void MenuActivity (){
		menuLoggedNameX = mScreenWidth*10/100;
		menuLoggedNameY = mScreenHeight*5/100;
		menuLoggedNameWidth = mScreenWidth*80/100;
		menuLoggedNameHeight = mScreenHeight*8/100;
		
		menuKeepPlayX = mScreenWidth*28/100;
		menuKeepPlayY = menuLoggedNameY + menuLoggedNameHeight*130/100;
		menuKeepPlayWidth = mScreenWidth*20/100;
		menuKeepPlayHeight = menuKeepPlayWidth;
						
		menuUserChangeX = menuKeepPlayX + menuKeepPlayWidth*130/100;
		menuUserChangeY = menuKeepPlayY;
		menuUserChangeWidth = mScreenWidth*20/100;
		menuUserChangeHeight = menuUserChangeWidth;
		
		
		menuLoginTextX = mScreenWidth*2/100;
		menuLoginTextY = mScreenHeight*20/100;
		menuLoginTextWidth = mScreenWidth*65/100;
		menuLoginTextXHeight = mScreenHeight*15/100;
		
		menuPlayX = menuLoginTextX + menuLoginTextWidth*105/100;
		menuPlayY = menuLoginTextY;
		menuPlayWidth = mScreenWidth*20/100;
		menuPlayHeight = menuPlayWidth;
		
		menuAdminLoginX = mScreenWidth*26/100;
		menuAdminLoginY = mScreenHeight*70/100;
		menuAdminLoginWidth = mScreenWidth*70/100;
		menuAdminLoginHeight = menuLoggedNameHeight;
		
		menuWrongX = mScreenWidth*45/100;
		menuWrongY = mScreenHeight*45/100;;
		menuWrongWidth = mScreenWidth*20/100;
		menuWrongHeight = menuWrongWidth;
		
		
		
	} // /public void MenuActivity

	
	public void GameActivity (){
		gameMenuboxX = 0;
		gameMenuboxY = 0;
		gameMenuboxWidth = mScreenWidth;
		gameMenuboxHeight = mScreenHeight*8/100;
		
		gameMenuWidth = mScreenWidth*20/100;
		gameMenuHeight = mScreenHeight*5/100;
		gameMenuX = mScreenWidth*5/100;
		gameMenuY = mScreenHeight*17/1000;
		gameMenuTextSize = mScreenHeight*3/100;
		
		gameLevelTextWidth = mScreenWidth*20/100;
		gameLevelTextHeight = mScreenHeight*5/100;
		gameLevelTextX = mScreenWidth*45/100;
		gameLevelTextY = mScreenHeight*25/1000;
		
		gameScoreTextWidth = mScreenWidth*25/100;
		gameScoreTextHeight = mScreenHeight*5/100;
		gameScoreTextX = mScreenWidth*70/100;
		gameScoreTextY = mScreenHeight*25/1000;
				
		gameKidWidth = mScreenWidth*88/100;
		gameKidHeight = mScreenHeight*38/100;
		gameKidX = (mScreenWidth-gameKidWidth)/2; //센터링
		gameKidY = mScreenHeight*12/100;
		
		gameClickWidth = mScreenWidth*25/100;
		gameClickHeight = gameClickWidth;
		gameClickX = (mScreenWidth-gameClickWidth)/2; //센터링 
		gameClickY = mScreenHeight*42/100;
		
		gameIconCorrectWidth = mScreenWidth*30/100;
		gameIconCorrectHeight = gameIconCorrectWidth;
		gameIconCorrectX = (mScreenWidth-gameIconCorrectWidth)/2; //센터링
		gameIconCorrectY = mScreenHeight*50/100;
		
		gameIconIncorrectWidth = gameIconCorrectWidth;
		gameIconIncorrectHeight = gameIconCorrectHeight;
		gameIconIncorrectX = gameIconCorrectX; //센터링
		gameIconIncorrectY = gameIconCorrectY;
			    
		gameIconSmallWidth = mScreenWidth*17/100;
		gameIconSmallHeight = mScreenWidth*17/100;
		gameIconSmallTextSize = mScreenHeight*5/100;
		
		gameIconSmallY = mScreenHeight*70/100;
		
		//positioning small icons
		for (int i=0; i < 5; i++) {
			gameArrIconSmallX[i] = gameIconSmallWidth *i + (gameIconSmallWidth/7)*(i+1);
		}
	}
	
	
	public void ResultActivity (){
		resultTextSize = mScreenHeight*3/100;		
		
	} // /public void MenuActivity

	
	
} // /public class DragableButton
