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
		menuGoLevelLayoutX, menuGoLevelLayoutY, menuGoLevelLayoutWidth, menuGoLevelLayoutHeight,
		menuLoginTextX, menuLoginTextY, menuLoginTextWidth,	menuLoginTextHeight,
		menuPlayX, menuPlayY, menuPlayWidth, menuPlayHeight,
		menuTextLoginPlayX, menuTextLoginPlayY, menuTextLoginPlayWidth, menuTextLoginPlayHeight,
		menuAdminLoginX, menuAdminLoginY, menuAdminLoginWidth, menuAdminLoginHeight,
		menuWrongX, menuWrongY, menuWrongWidth, menuWrongHeight;
		
	// GameActivity
	public int gameMenuboxX, gameMenuboxY, gameMenuboxWidth, gameMenuboxHeight, gameMenuWidth, gameMenuHeight, gameMenuX, gameMenuY, gameMenuTextSize,
		gameLevelTextWidth,	gameLevelTextHeight, gameLevelTextX, gameLevelTextY,
		gameScoreTextWidth,	gameScoreTextHeight, gameScoreTextX, gameScoreTextY,
		gameStatusTextWidth, gameStatusTextHeight, gameStatusTextX, gameStatusTextY,
		gameKidWidth, gameKidHeight, gameKidX, gameKidY, gameClickWidth, gameClickHeight, gameClickX, gameClickY,
		gameIconCorrectWidth, gameIconCorrectHeight, gameIconCorrectX, gameIconCorrectY,
		gameIconIncorrectWidth, gameIconIncorrectHeight, gameIconIncorrectX, gameIconIncorrectY,
		gameIconSmallWidth, gameIconSmallHeight, gameIconSmallTextSize, gameIconSmallY;
	public int[] gameArrIconSmallX = new int[5];
	
	// ResultActivity
	public int resultTextSize;
	
	// EndActivity
	public int endPlayX, endPlayY, endPlayWidth, endPlayHeight, endCongratX, endCongratY, endCongratWidth, endCongratHeight, endGoodjobX, endGoodjobY, endGoodjobWidth, endGoodjobHeight;
	
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
		mainPlayX = mScreenWidth*67/100;
		mainPlayY = mScreenHeight*75/100;
		mainPlayWidth = mScreenWidth*15/100;
		mainPlayHeight = mainPlayWidth;
	}
	
	public void MenuActivity (){
		menuLoggedNameX = mScreenWidth*10/100;
		menuLoggedNameY = mScreenHeight*5/100;
		menuLoggedNameWidth = mScreenWidth*80/100;
		menuLoggedNameHeight = mScreenHeight*13/100;
		
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
		menuLoginTextHeight = mScreenHeight*25/100;
		
		menuPlayX = menuLoginTextX + menuLoginTextWidth*105/100;
		menuPlayY = menuLoginTextY;
		menuPlayWidth = mScreenWidth*20/100;
		menuPlayHeight = menuPlayWidth;
		
		menuTextLoginPlayX = menuPlayX + menuPlayWidth;
		menuTextLoginPlayY = menuPlayY;
		menuTextLoginPlayWidth = mScreenWidth*6/100;
		menuTextLoginPlayHeight = menuLoggedNameHeight;
				
		menuGoLevelLayoutX = mScreenWidth*26/100;
		menuGoLevelLayoutY = menuKeepPlayY + menuKeepPlayHeight + mScreenHeight*15/100;;
		menuGoLevelLayoutWidth = mScreenWidth*50/100;
		menuGoLevelLayoutHeight = menuLoggedNameHeight;
		
		menuAdminLoginX = menuGoLevelLayoutX;
		menuAdminLoginY = mScreenHeight*85/100;
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
		gameLevelTextX = mScreenWidth*40/100;
		gameLevelTextY = mScreenHeight*25/1000;
		
		gameScoreTextWidth = mScreenWidth*25/100;
		gameScoreTextHeight = mScreenHeight*5/100;
		gameScoreTextX = mScreenWidth*57/100;
		gameScoreTextY = mScreenHeight*25/1000;
		
		gameStatusTextWidth = mScreenWidth*25/100;
		gameStatusTextHeight = mScreenHeight*5/100;
		gameStatusTextX = mScreenWidth*80/100;
		gameStatusTextY = mScreenHeight*25/1000;
						
		gameKidWidth = mScreenWidth*64/100;
		gameKidHeight = mScreenHeight*55/100;
		gameKidX = (mScreenWidth-gameKidWidth)/2; //센터링
		gameKidY = mScreenHeight*12/100;
		
		gameClickWidth = mScreenWidth*15/100;
		gameClickHeight = gameClickWidth;
		gameClickX = (mScreenWidth-gameClickWidth)/2 + gameKidWidth/2; //센터링 
		gameClickY = mScreenHeight*42/100;
		
		gameIconCorrectWidth = mScreenWidth*30/100;
		gameIconCorrectHeight = gameIconCorrectWidth;
		gameIconCorrectX = (mScreenWidth-gameIconCorrectWidth)/2; //센터링
		gameIconCorrectY = mScreenHeight*30/100;
		
		gameIconIncorrectWidth = gameIconCorrectWidth;
		gameIconIncorrectHeight = gameIconCorrectHeight;
		gameIconIncorrectX = gameIconCorrectX; //센터링
		gameIconIncorrectY = gameIconCorrectY;
			    
		gameIconSmallWidth = mScreenWidth*17/100;
		gameIconSmallHeight = mScreenWidth*17/100;
		gameIconSmallTextSize = mScreenHeight*12/100; //음운 아이콘 텍스트 사이즈 
		
		gameIconSmallY = mScreenHeight*70/100;
		
		//positioning small icons
		for (int i=0; i < 5; i++) {
			gameArrIconSmallX[i] = gameIconSmallWidth *i + (gameIconSmallWidth/7)*(i+1);
		}
	}
	
	
	public void ResultActivity (){
		resultTextSize = mScreenHeight*3/100;		
	} // /public void ResultActivity
	
	
	public void EndActivity (){
		endPlayX = mScreenWidth*67/100;
		endPlayY = mScreenHeight*75/100;
		endPlayWidth = mScreenWidth*15/100;
		endPlayHeight = endPlayWidth;
		
		endGoodjobX = mScreenWidth*20/100;;
		endGoodjobY = mScreenHeight*5/100;
		endGoodjobWidth = mScreenWidth*40/100;
		endGoodjobHeight = mScreenHeight*20/100;
		
		endCongratX = mScreenWidth*40/100;
		endCongratY = mScreenHeight*50/100;
		endCongratWidth = mScreenWidth*40/100;
		endCongratHeight = mScreenHeight*20/100;
	} // /public void EndActivity

	
	
} // /public class DragableButton
