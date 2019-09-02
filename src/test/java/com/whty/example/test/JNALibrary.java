package com.whty.example.test;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface JNALibrary extends Library {

	// 加载libhello.so链接库
	JNALibrary INSTANTCE = (JNALibrary) Native.loadLibrary("nativeLib/GetInfo", JNALibrary.class);

	// 此方法为链接库中的方法
	int GetBmp(String filename, int t);

}
