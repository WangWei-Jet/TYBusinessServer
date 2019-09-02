/**
 * 
 */
package com.whty.example.utils;

public class FileTransfer {

	static {
		System.loadLibrary("/bmpInfo_news");
	}

	public native int GetBmp(String filename, int t);
}
