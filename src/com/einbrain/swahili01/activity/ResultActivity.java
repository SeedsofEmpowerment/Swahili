package com.einbrain.swahili01.activity;

import com.einbrain.swahili01.DisplayManager;
import com.einbrain.swahili01.R;
import com.einbrain.swahili01.ResultHandle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;

public class ResultActivity extends Activity {
	
	private RelativeLayout CurrentLayout;
	private int _ScreenWidth_, _ScreenHeight_;
	private String _FileResult_ = "result_v1_1.txt";
	
	private DisplayManager IDisplay; 
	private ScaleView IScale;
	private ResultHandle IResult = new ResultHandle();
	
	////프로그램 시작 ////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_activity);
	
		// Getting device display size & Setting display size
		IDisplay = new DisplayManager(getApplicationContext()); 
		_ScreenWidth_ = IDisplay.getDisplayPixel()[0]; 
		_ScreenHeight_ = IDisplay.getDisplayPixel()[1];
		CurrentLayout = (RelativeLayout) findViewById(R.id.resultLayout);
		LayoutParams params = CurrentLayout.getLayoutParams();
		params.width = _ScreenWidth_; params.height = _ScreenHeight_;
		
		// Getting current activity's size/position variables 
		IScale = new ScaleView (_ScreenWidth_, _ScreenHeight_);
		IScale.ResultActivity(); 
		
		initResult();

	} // /onCreate
	
	
	private void initResult() {
		
		TextView resultText = (TextView) findViewById(R.id.resultTextView);
		resultText.setMovementMethod(new ScrollingMovementMethod());
		
		String loadedResult = new String();
		loadedResult = IResult.getData(_FileResult_);
		
		String viewString = "Name (PW) / Time / Lev. / Score \n Straight / Total St. / Result / Q  (Options) \n ------------------------------------- \n";
		
		try {
			JSONArray resultArr = new JSONArray();
			JSONObject userProperties = new JSONObject();
			String[] separated = loadedResult.split( getString(R.string.split_char) );
						
			for (String item : separated){
				JSONObject resultDataTmp = new JSONObject(item);
				resultArr.put(resultDataTmp);
			}
			
			for(int i=1; i<resultArr.length(); i++){ // no need to get the first null 
				userProperties = resultArr.getJSONObject(i);
				
				viewString = viewString + userProperties.getString("who") + " "; 
				viewString = viewString + "("+ userProperties.getString("userPass") + ") ";
				viewString = viewString + userProperties.getString("when") + " ";
				viewString = viewString + userProperties.getString("level") + " ";
				viewString = viewString + userProperties.getString("score") + "\n";
				viewString = viewString + userProperties.getString("tstraightc") + " ";
				viewString = viewString + userProperties.getString("straightc") + " ";
				viewString = viewString + userProperties.getString("result") + " ";
				viewString = viewString + userProperties.getString("phonic") + " ";
				viewString = viewString + "("+ userProperties.getString("options") + ")";
				viewString = viewString + "\n\n";
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
				
		resultText.setText (viewString);
		resultText.setTextSize (TypedValue.COMPLEX_UNIT_PX, IScale.resultTextSize);
		
	}// /initResult()
	
	
} // /class ResultActivity
