package org.opencv.samples.puzzle15;

import java.util.Random;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener;

import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Puzzle15Activity extends Activity implements CvCameraViewListener2, View.OnTouchListener {

    private static final String  TAG = "Object Recognition";

    private CameraBridgeViewBase mOpenCvCameraView;
    private boolean grabImage = false,recognizeVar = false;
    private Mat grabbedImage;
    private int minHeight = 0, minWidth = 0, maxHeight = 0, maxWidth = 0;
    private int objectHeight = 0, objectWidth = 0;
    private double ratio = 1.0d;
    private double[] k;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
    
 
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    /* Now enable camera view to start receiving frames */
                    mOpenCvCameraView.enableView();
                    k =  new double[10];
                    
                } break;
                
                default:
                {
                    super.onManagerConnected(status);
                } break;
                
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_puzzle15);

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.puzzle_activity_surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
       
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, mLoaderCallback);
        //OpenCVLoader.initDebug();
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_puzzle15, menu);
        return true;
    }

    
    public boolean onOptionsItemSelected(MenuItem item) {
   
    	 Log.i(TAG, "Menu Item selected " + item);
         if (item.getItemId() == R.id.captureImage) {
             /* Capture Image */
             captureFrame();
         } else if (item.getItemId() == R.id.recognizeImage) {
             /* Recognize Image */
        	

        	 	ratio = (ratio+1)%6;
 	    	    
 	    	    //Recognize a object of size of bottle
 	    	    if(ratio == 1)
 	    	    	Toast.makeText(this, "Bottle", Toast.LENGTH_SHORT).show();

 	    	    //Recognize a object of size of can
 	    	    else  if(ratio == 2)
 	    			Toast.makeText(this, "Can", Toast.LENGTH_SHORT).show();

 	    	    //Recognize a object of size of watch
 	    	    else if(ratio == 3)
 	    			Toast.makeText(this, "Watch", Toast.LENGTH_SHORT).show();

 	    	    //Recognize a object of size of lock
 	    	    else if(ratio == 4 )
 	    			Toast.makeText(this, "Lock", Toast.LENGTH_SHORT).show();

 	    	  //Recognize a object of size of pen
 	    	    else if(ratio == 5)
 	    			Toast.makeText(this, "Pen", Toast.LENGTH_SHORT).show();
 	    	  
 	    	    //Cannot recognize a object
 	    	    else 
 	    			Toast.makeText(this, "Cannot Recognize", Toast.LENGTH_SHORT).show();



             
         }
    	return true;
    }
    
    public void captureFrame()
    {
    	//grabImage = true;
    }
    
  

    public void onCameraViewStarted(int width, int height) {
       
      
    }

    public void onCameraViewStopped() {
    	
    }

    public boolean onTouch(View view, MotionEvent event) {
          	
        return false;
    }


	@Override
	public Mat onCameraFrame(CvCameraViewFrame inputFrame) {

			return inputFrame.rgba();
	}

  
}
