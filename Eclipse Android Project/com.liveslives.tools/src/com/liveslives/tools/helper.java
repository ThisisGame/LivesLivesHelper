package com.liveslives.tools;

import java.io.InputStream;
import java.util.Hashtable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class helper 
{
	private static boolean isInit=false;
	private static Activity sActivity = null;
	private static AssetManager sAssetManager;
	private static Hashtable mFileTable = new Hashtable();
	
	public static void init(Object paramObject)
	{
		if(!isInit)
		{
			sActivity = (Activity)paramObject;
			sAssetManager = sActivity.getAssets();
			isInit = true;
			Log.i("liveslives", "init success");
		}
	}
	
	//restart application
	public static void restartApplication() 
	{
		new Thread() 
		{
			public void run() 
			{

				Intent launch = sActivity.getBaseContext().getPackageManager()
						.getLaunchIntentForPackage(
								sActivity.getBaseContext().getPackageName());

				launch.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				sActivity.startActivity(launch);

				android.os.Process.killProcess(android.os.Process.myPid());

			}

		}.start();

		sActivity.finish();
	}
	
	//get mac address
	public static String getMacAddress()
	{
		Log.i("liveslives", "getMacAddress");
		WifiManager wifi = (WifiManager) sActivity.getBaseContext().getSystemService(Context.WIFI_SERVICE);  
		if(wifi==null)
        {
        	return "get mac faild";
        }
        WifiInfo info = wifi.getConnectionInfo();  
        if(info==null)
        {
        	return "get mac faild";
        }
        return info.getMacAddress(); 
	}
	
	//get file exist in apk assets
	public static boolean ExistInDataPath(String paramString)
	{
		boolean bool = false;
	    if (mFileTable.containsKey(paramString))
	      return ((Boolean)mFileTable.get(paramString)).booleanValue();
	    if (sAssetManager != null)
	    {
	      InputStream localInputStream = null;
	      try
	      {
	        localInputStream = sAssetManager.open(paramString);
	        bool = true;
	        mFileTable.put(paramString, Boolean.valueOf(true));
	        localInputStream.close();
	      }
	      catch (Exception localException)
	      {
	        mFileTable.put(paramString, Boolean.valueOf(false));
	        Log.e("ReadFile exception", localException.toString());
	      }
	    }
	    return bool;
	}
}
