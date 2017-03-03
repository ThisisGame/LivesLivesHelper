using UnityEngine;
using System.Collections;
using System.Net.NetworkInformation;

namespace liveslives.com
{
    public class LivesLivesHelper
    {
        private static AndroidJavaClass mAndroidJavaClassHelper = null;

        /// <summary>
        /// 初始化
        /// </summary>
        public static void Init()
        {
            if (Application.platform == RuntimePlatform.Android)
            {
                mAndroidJavaClassHelper = new AndroidJavaClass("com.liveslives.tools.helper");
                using (AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
                {
                    object jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
                    mAndroidJavaClassHelper.CallStatic("init", jo);
                }
            }
        }

        /// <summary>
        /// 重启APP
        /// </summary>
        public static void ReStartApplication()
        {
            if (Application.platform == RuntimePlatform.Android)
            {
                mAndroidJavaClassHelper.CallStatic("restartApplication");
            }
        }

        /// <summary>
        /// 获取Mac地址
        /// </summary>
        /// <returns></returns>
        public static string GetMacAddress()
        {
            string tmpMacAddress = "";
            if (Application.platform == RuntimePlatform.Android)
            {
                tmpMacAddress= mAndroidJavaClassHelper.CallStatic<string>("getMacAddress");
            }
            else
            {
                NetworkInterface[] n = NetworkInterface.GetAllNetworkInterfaces();
                tmpMacAddress= n[0].GetPhysicalAddress().ToString();
            }

            return tmpMacAddress;
        }
    }
}


