package com.einbrain.swahili01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import android.os.Environment;

public class ResultHandle {
	List<String> liCorrectPhonic = new ArrayList<String>();
	
	public String mUserName = null;
	public String mUserPass = null;
	public int mSaveTimes = 0;
	public int mStraightCorrectLog = 0;
	public int mStraightIncorrectLog = 0;
	public int mTotalStraightCorrectLog = 0;
	public int mTotalIncorrectLog =  0;
	public int mScore = 0;
	
	public ResultHandle(){

	}
	
	private void writeF (String data, String filename, String modename) {
		String eol = System.getProperty("line.separator");
		boolean mode = false; //일단 지우고 쓰
		if ( modename == "append" ){
			data = data + eol;
			mode = true; //덧붙여서 쓰기 모드 
		} 
		try {
	        //---Internal Storage--- FileOutputStream fOut = openFileOutput("textfile.txt", MODE_WORLD_READABLE);
			
			File sdCard = Environment.getExternalStorageDirectory();
	        File directory = new File(sdCard.getAbsolutePath() + "/SwahiliResult");
	        directory.mkdirs();
	        
	        File file = new File(directory, filename);
	        FileOutputStream fOut = new FileOutputStream(file, mode);
	        OutputStreamWriter osw = new OutputStreamWriter(fOut);
	        osw.write(data);
	        osw.flush();
	        osw.close();
	        } catch (IOException ioe) {
	        	ioe.printStackTrace();
	    }
	} 
	
	private String readF (String filename) {
		String data = null;
		try {
			File sdCard = Environment.getExternalStorageDirectory();
	        File directory = new File(sdCard.getAbsolutePath() + "/SwahiliResult");
	        
	        File file = new File(directory, filename);
			FileInputStream fIn = new FileInputStream(file);
			BufferedReader myReader = new BufferedReader( new InputStreamReader(fIn) );
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null) {
				aBuffer += aDataRow + "\n";
			}
			data = aBuffer;
			myReader.close();
		} catch (Exception e) {
			//e.getMessage()
		}
		return data;
	} 
	
	private boolean deleteF (String filename){
		File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/SwahiliResult");
		File file = new File(directory, filename);
		return file.delete();
	}
	
	private boolean renameF (String filenameFrom, String filenameTo){
		File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/SwahiliResult");
		File from = new File(directory, filenameFrom);
		File to = new File(directory, filenameTo);
		return from.renameTo(to);
	}
	
	////////////////////////
	//// public methods ////
	public String statusCheck ( List<Integer> liLevelCriteria, int currentLevel, int straightCorrect, int straightIncorrect) {
		String statusResult = "stay";
		
		if ( straightCorrect >= liLevelCriteria.get(0) ) {
			statusResult = "up";
			//return true;
		} else if (straightIncorrect >= liLevelCriteria.get(1)) {
			statusResult = "down";
		} 
		
		return statusResult;
		
	}

	public void scoreManage(String result, String correctPhonic, String correctString, String incorrectString) {
		if (result == correctString) { //정답이면 
			mTotalStraightCorrectLog ++;
			mTotalIncorrectLog = 0; //연속 틀린 수 초기화 
			
			int worthScoring = 1; //일단 중복으로 뽑히지 않았다고 가정한 후
			
			for (int i=0; i<liCorrectPhonic.size(); i++){
				if (liCorrectPhonic.get(i) == correctPhonic) { // 중복으로 뽑힌 것이 하나라도 있으면 "연속으로 맞았다고 하지 않는다"
					worthScoring = 0;
				}
			}
			if (worthScoring == 1) { // 중복없이 새로운 것 연속으로 맞았으니 
				mStraightCorrectLog ++; //연속으로 맞은 수 저장  
			} 
			liCorrectPhonic.add(correctPhonic); //맞은 음운을 테이블에 저장
		
		} else if (result == incorrectString) { //틀렸으면 
			liCorrectPhonic.clear();
			mStraightIncorrectLog ++; //틀렸으면 연속 틀린 수 저장 
			mTotalIncorrectLog ++; //틀렸으면 틀린 수 저장
			
			mStraightCorrectLog = 0; //틀렸으면 연속 맞은 수 초기화
			mTotalStraightCorrectLog = 0; //틀렸으면 Total 연속 맞은 수 초기화
		} else {
			// error
		}
	}// /public void scoreManage

	public void inputData(String data, String filename, String type) {
		this.writeF(data, filename, type);
	}

	public String getData(String filename) {
		String data = null;
		data = this.readF(filename);
		return data;
	}

	public void scoreInit(int currentScore, int currentStraightc, int totalStraightc) {
		this.mScore = currentScore;
		this.mStraightCorrectLog = currentStraightc;
		this.mTotalStraightCorrectLog = totalStraightc;
	}
	
	public boolean renameFile (String filenameFrom, String filenameTo){
		return this.renameF(filenameFrom, filenameTo);
	}
	
	public boolean removeFile (String filename){
		return this.deleteF(filename);
	}

}// /class ResultHandle
