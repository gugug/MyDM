package com.java.dm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteByTXT {
	public void writeTxt(String data,String address,String fileName){ //将得到的数据存入文本
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File saveDir = new File(address);    
            if(!saveDir.exists()){    
                saveDir.mkdir();    
            }    
			fw = new FileWriter(saveDir+File.separator+fileName,true);//是否追加
			bw = new BufferedWriter(fw);
			bw.write(data); //将data存储位置到address;
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
