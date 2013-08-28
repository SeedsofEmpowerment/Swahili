package com.einbrain.swahili01;

import java.util.Random;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;

public class DisplayManager {
	private View background;  
	private Context mContext;
        
	//Constructor 
	public DisplayManager (Context context) {  
		mContext = context;
    } 
	
	public int[] getDisplayPixel(){ // display width, height return 
		
		int[] displayPixel = new int[2];
		
		DisplayMetrics metrics = new DisplayMetrics();
		metrics = mContext.getResources().getDisplayMetrics();
			
		//getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		//// Display 기본 320 * 480 으로 맞춰라 ////  
		int deviceWidth = metrics.widthPixels;
		int deviceHeight = metrics.heightPixels;
				
		if( deviceWidth*480/320 <= deviceHeight) { // 위로 길쭉이 
			displayPixel[0] = deviceWidth;
			displayPixel[1] = displayPixel[0]*480/320;
		} else { //옆으로 넙쭉이 
			displayPixel[1] = deviceHeight;
			displayPixel[0] = displayPixel[0]*320/480;
		}

		return displayPixel;
	}
	
    public void insertBackgroundView(View b){
    	this.background = b;    	
    }
    
    public void changeBackground() {  
    	//Random RGB
    	int min = 1;
    	int max = 255;
    	
    	Random r = new Random();
    	int red = r.nextInt(max - min + 1) + min;
    	int green = r.nextInt(max - min + 1) + min;
    	int blue = r.nextInt(max - min + 1) + min;
    	
    	background.setBackgroundColor(Color.rgb(red, green, blue));
    };  

}


