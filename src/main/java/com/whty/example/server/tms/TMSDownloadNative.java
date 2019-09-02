package com.whty.example.server.tms;

public class TMSDownloadNative {
	static {
		System.loadLibrary("tmsdownloadnative");
	}

	public static native int TMS_Download(int handle, int type, TMSDownloadInfo download_info, StringBuffer ret_info);
}