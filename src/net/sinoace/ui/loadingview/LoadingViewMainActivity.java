package net.sinoace.ui.loadingview;

import android.app.Activity;
import android.os.Bundle;

import net.sinoace.anim.R;

public class LoadingViewMainActivity extends Activity
{
    
    private LoadingView main_imageview;
    
     
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingview_main);
        main_imageview = (LoadingView)findViewById(R.id.main_imageview);
        initLoadingImages();
        
        new Thread()
        {
             
            public void run()
            {
                main_imageview.startAnim();
            }
        }.start();
        
    }
    
    private void initLoadingImages()
    {
        int[] imageIds = new int[6];
        imageIds[0] = R.drawable.loader_frame_1;
        imageIds[1] = R.drawable.loader_frame_2;
        imageIds[2] = R.drawable.loader_frame_3;
        imageIds[3] = R.drawable.loader_frame_4;
        imageIds[4] = R.drawable.loader_frame_5;
        imageIds[5] = R.drawable.loader_frame_6;
        
        main_imageview.setImageIds(imageIds);
    }
}