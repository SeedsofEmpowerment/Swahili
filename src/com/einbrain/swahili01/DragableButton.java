package com.einbrain.swahili01;

import java.util.ArrayList;
import java.util.List;

import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DragableButton {
	//// private properties ////
	private LinearLayout targetLayout; 
	
	private int screenWidth;
	private int screenHeight;
	private int originalX;
	private int originalY;
	private String chosenQ;
	private String correctString;
	private String incorrectString;
	
	//// public properties ////
	public LinearLayout mLayout;  
	public TextView mTextview;
	public String mLabel;
	public android.view.ViewGroup.LayoutParams mParam;
	
	public int mCurrentLevel;
	public List<Integer> mLiOthers = new ArrayList<Integer>(); 
	
	//// Constructor ////
	public DragableButton (LinearLayout ll, TextView tv, String lb, int sw, int sh, int cl, String cq, List<Integer> ot)  {  
        mLayout = ll;  
        mTextview = tv;
        mLabel = lb;
        screenWidth = sw;
        screenHeight = sh;
        mCurrentLevel = cl;
        chosenQ = cq;
        mLiOthers = ot;
        mParam = mLayout.getLayoutParams();
    }  
	
	/////////////////////////
	//// private methods ////
	
	
	////////////////////////
	//// public methods ////
    public String checkCollision (LinearLayout ll, String c, String ic) {
    	this.targetLayout = ll;
    	this.correctString = c;
    	this.incorrectString = ic;
    	
    	int targetX = this.targetLayout.getLeft();
		int targetY = this.targetLayout.getTop();
		int targetWidth = this.targetLayout.getMeasuredWidth();
		int targetHeight = targetLayout.getMeasuredHeight();
		int currentX = mLayout.getLeft();
		int currentY = mLayout.getTop();
		int currentWidth = mLayout.getMeasuredWidth();
		int currentHeight = mLayout.getMeasuredHeight();
		
		//when it comes in to the target
		if ( currentX > targetX && currentX < (targetX+targetWidth)-currentWidth && currentY > targetY && currentY < (targetY+targetHeight)-currentHeight ) {
			//기본값은 오답 
			if (this.mLabel == this.chosenQ) {
				return this.correctString;
			} else {
				return this.incorrectString;
			}
		}
		return "false";
	}// /checkCollision
    
	public void setViewPosition (RelativeLayout mainLayout, LinearLayout view, MotionEvent event, android.view.ViewGroup.LayoutParams para) {
    	int leftdist = (int) event.getRawX();
        int topdist = (int) event.getRawY();
        
        // getRawX, getRawY는 안드로이드 툴바까지 포함한 위치를 받는다. 그래서 깎아줘야한다. 
        int[] aLocation = new int[2];
        mainLayout.getLocationOnScreen(aLocation);
        
        int leftmargin = leftdist - aLocation[0] - (view.getWidth()/2);
        int topmargin = topdist - aLocation[1] - (view.getHeight()/2);
                       
        //아이콘이 화면 밖으로 나가지 않도록!
     	if (leftmargin < 0) leftmargin = 0;
     	if (leftmargin > screenWidth - mParam.width) leftmargin = screenWidth - mParam.width;
     	if (topmargin < 0) topmargin = 0;
     	if (topmargin > screenHeight - mParam.height) topmargin = screenHeight - mParam.height;
        
     	((MarginLayoutParams) para).setMargins ( leftmargin, topmargin, 0, 0 );
        view.setLayoutParams(para);
	}// /setViewPosition
	
	public void visible (int posx, int posy, int width, int height, int textSize) {
		//reposition 위해 일단 저장 
		this.originalX = posx;
		this.originalY = posy;
		
		this.mLayout.setVisibility(LinearLayout.VISIBLE); //일단 보이게 하고 
		
		if (mLabel != null) { 
			this.mTextview.setText (mLabel);//label 넣어주고
			if (textSize != 0 ) this.mTextview.setTextSize (TypedValue.COMPLEX_UNIT_PX, textSize);
		}
		
		
		
		//위치 조정 
		((MarginLayoutParams) this.mParam).setMargins(posx, posy, 10, 10);
		this.mLayout.setLayoutParams(mParam);
		
		//크기 조정 
		if (width != 0) this.mParam.width = width;
		if (height != 0) this.mParam.height = height;
		
	}// /public void visible
	
	public void hide () {
		this.mLayout.setVisibility(LinearLayout.INVISIBLE); //
	}//hide
	
	/*
	public void onClickEvent (Uri myUri) {
		this.mLayout.setOnTouchListener (new CClickListener( myUri ));
	}// /public void onClickEvent
*/
	
	public void reposition () { //제자리로!
		((MarginLayoutParams) this.mParam).setMargins(this.originalX, this.originalY, 10, 10);
		this.mLayout.setLayoutParams(mParam);
	}
	
} // /public class DragableButton
