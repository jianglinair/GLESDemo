package com.lin.jiang.glestest;

import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.lin.jiang.glestest.mesh.SimplePlane;

public class TutorialPartI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
//        glSurfaceView.setRenderer(new OpenGLRenderer());
        OpenGLRenderer2 renderer = new OpenGLRenderer2();
        glSurfaceView.setRenderer(renderer);
        setContentView(glSurfaceView);

        SimplePlane plane = new SimplePlane(1, 1);
        plane.z = 1.7f;
        plane.rx = -65;
        plane.loadBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        renderer.addMesh(plane);
    }
}
