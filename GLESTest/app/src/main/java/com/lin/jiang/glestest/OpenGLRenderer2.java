package com.lin.jiang.glestest;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.lin.jiang.glestest.mesh.Group;
import com.lin.jiang.glestest.mesh.Mesh;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jianglin on 17-4-26.
 */

public class OpenGLRenderer2 implements GLSurfaceView.Renderer {

    private static final String TAG = "OpenGLRenderer2";

    private final Group root;

    public OpenGLRenderer2() {
        Group group = new Group();
        root = group;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        LOG.i(TAG, "onSurfaceCreated");
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear the screen and depth buffer
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // Replace the current matrix with identity matrix
        gl.glLoadIdentity();
        // Translate 10 units INTO the screen
        gl.glTranslatef(0, 0, -10);
        // Draw
        root.draw(gl);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        LOG.i(TAG, "onSurfaceChanged, width=" + width + ", height=" + height);
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void addMesh(Mesh mesh) {
        root.add(mesh);
    }
}
