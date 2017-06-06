package com.lin.jiang.glesdemo;

import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jianglin on 17-5-8.
 */

public class SolarSystemActivity extends MainActivity {

    private Star sun = new Star();
    private Star earth = new Star();
    private Star moon = new Star();

    private int angle = 0;

    @Override
    public void drawScene(GL10 gl) {
        super.drawScene(gl);
        gl.glLoadIdentity();
        // Set modelview matrix(uses virtual world coordinates system)
        GLU.gluLookAt(gl, 0f, 0f, 15f, 0f, 0f, 0f, 0f, 1f, 0f);

        // Transformation uses object's local coordinates system!
        // Transformation uses object's local coordinates system!
        // Transformation uses object's local coordinates system!

        // Sun
        gl.glPushMatrix();// save matrix I
        gl.glRotatef(angle, 0, 0, 1);
        gl.glColor4f(1f, 0f, 0f, 1f);
        gl.glLineWidth(6f);
        sun.draw(gl);
        gl.glPopMatrix();// restore matrix I

        // Earth
        gl.glPushMatrix();// save matrix I
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(3, 0, 0);
        gl.glScalef(0.5f, 0.5f, 0.5f);
        gl.glColor4f(0f, 0f, 1f, 1f);
        gl.glLineWidth(4f);
        earth.draw(gl);

        // Moon
//        gl.glPushMatrix();//save matrix RSTR, current matrix is RSTRI
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(2, 0, 0);
        gl.glScalef(0.5f, 0.5f, 0.5f);
        gl.glRotatef(angle * 10, 0, 0, 1);
        gl.glColor4f(0f, 1f, 0f, 1f);
        gl.glLineWidth(2f);
        moon.draw(gl);

//        gl.glPopMatrix();// current matrix is RSTRRSTRI,  restore matrix RSTRI
        gl.glPopMatrix();// current matrix is RSTRI,  restore matrix I
        angle++;
    }
}
