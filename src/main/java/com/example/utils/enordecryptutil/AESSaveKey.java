package com.example.utils.enordecryptutil;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class AESSaveKey {

	public static void main(String[] args) throws Exception {

	}
	
	public String getStringCode(String oldString){
		
		String newString = "";
		
		byte[] oldStringArr = oldString.getBytes();
		
		for(int i = 0 ; i < oldStringArr.length; i++){
			newString += oldStringArr[i];
		}
		
		return newString;
		
	}
	
	public String getStringKey(String oldStringCode){
		StringBuffer StrBuf = new StringBuffer();
		
		if(oldStringCode.length()%2!=0){
			return "";
		}
		
		for(int i = 0 ; i < oldStringCode.length();i=i+2){
			
			String charCodeStr = oldStringCode.substring(i,i+2);
			System.out.println(charCodeStr+"     "+i  );
			
			char charCode = (char) Integer.parseInt(charCodeStr);
			System.out.println(charCode);
			StrBuf.append(charCode);
		}
		
		return StrBuf.toString();
	}
	

	
	public static String readDatFile() {
		try
		{
			DataInputStream in=new DataInputStream(new FileInputStream(System.getProperty("user.dir")+"/key.dat"));
	        
			StringBuffer tempNum = new StringBuffer();
			String line="";	                            
			while ((line = in.readLine()) != null){
				tempNum.append(line);
			}
			
			String filetxt = tempNum.toString().substring(0,16);
			
			in.close();
			
			return filetxt;
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
}

