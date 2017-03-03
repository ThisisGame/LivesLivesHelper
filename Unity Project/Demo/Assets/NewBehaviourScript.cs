using UnityEngine;
using System.Collections;
using System.Net.NetworkInformation;
using liveslives.com;

public class NewBehaviourScript : MonoBehaviour {

    string mStr = "aaa";

    void Awake()
    {
        LivesLivesHelper.Init();
    }

	// Use this for initialization
	void Start ()
    {
        mStr = LivesLivesHelper.GetMacAddress();
    }



    void OnGUI()
    {
        GUILayout.Label(mStr);
    }
}
