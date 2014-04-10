package com.einbrain.swahili01;

import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SizePositionManager {
	//// private properties ////
	
	//// public properties ////
	
	//// Constructor ////
	public SizePositionManager () { 
    }  
	
	/////////////////////////
	//// private methods ////
	
	
	////////////////////////
	//// public methods ////
	
	
	public void setLinearLayout (LinearLayout ll, int posx, int posy, int width, int height) {
		//위치 조정 
		android.view.ViewGroup.LayoutParams param = ll.getLayoutParams();
		((MarginLayoutParams) param).setMargins(posx, posy, 10, 10);
		ll.setLayoutParams(param);
		
		//크기 조정 
		param.width = width;
		param.height = height;
		
	}// /public void visible
	

	//public void setTextView (TextView tv, int posx, int posy, int width, int height, int size) {
	public void setTextView (TextView tv, int posx, int posy, int width, int height) {
		
		// Coordination
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tv.getLayoutParams();
		params.setMargins(posx, posy, 0, 0); // left, top, right, bottom
		tv.setLayoutParams(params);
		
		// Size
		tv.setWidth(width);
		tv.setHeight(height);
		
		// Text Size
		//tv.setTextSize (TypedValue.COMPLEX_UNIT_PX, size);
		
	}// /public void visible
	
} // /public class DragableButton
