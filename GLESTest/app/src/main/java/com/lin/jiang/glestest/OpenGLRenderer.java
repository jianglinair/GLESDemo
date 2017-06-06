package com.lin.jiang.glestest;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jianglin on 17-4-26.
 */

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "OpenGLRenderer";

//    private Square square = new Square();
//    private Square square = new FlatColoredSquare();
    private Square square = new SmoothColoredSquare();
    private float angle = 0;

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

        // Square A
        // Save the current matrix
        gl.glPushMatrix();
        // Rotate square A CCW
        gl.glRotatef(angle, 0, 0, 1);
        // Draw square A
        square.draw(gl);
        // Restore last matrix
        gl.glPopMatrix();

        // Square B
        // Save the current matrix
        gl.glPushMatrix();
        // Rotate square B before moving it, making it rotate around A.
        gl.glRotatef(-angle, 0, 0, 1);
        // Move square B
        gl.glTranslatef(2, 0, 0);
        // Scale it to 50% of square A
        gl.glScalef(0.5f, 0.5f, 0.5f);
        // Draw square B
        square.draw(gl);

        // Do not pop matrix now
        // Square C
        // Save the current matrix for square C
        gl.glPushMatrix();
        // Rotate around B
        gl.glRotatef(-angle, 0, 0, 1);
        // Move square C
        gl.glTranslatef(2, 0, 0);
        // Scale square C
        gl.glScalef(0.5f, 0.5f, 0.5f);
        // Rotate around it's own center
        gl.glRotatef(angle, 0, 0, 1);
        // Draw square C
        square.draw(gl);

        // Restore to the matrix as it was before C
        gl.glPopMatrix();
        // Restore to the matrix as it was before B
        gl.glPopMatrix();

        angle++;
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
}
