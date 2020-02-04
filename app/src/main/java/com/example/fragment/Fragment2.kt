package com.example.fragment

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment2:Fragment(){
    val TAG="Fragment Two"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG,"OnCreateView")
        return inflater.inflate(R.layout.layout_two,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG,"OnActivityCreated")
    }

    override fun onStart() {
        Log.i(TAG,"OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG,"OnResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG,"OnPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG,"OnStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i(TAG,"OnDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i(TAG,"OnDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.i(TAG,"OnDetach")
        super.onDetach()
    }
}