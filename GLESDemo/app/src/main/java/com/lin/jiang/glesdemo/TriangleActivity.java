package com.lin.jiang.glesdemo;

import android.os.Bundle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class TriangleActivity extends MainActivity {

    private int index = 0;

    private float vertexArray[] = {
            -0.8f, -0.4f * 1.732f, 0.0f,
            0.0f, -0.4f * 1.732f, 0.0f,
            -0.4f, 0.4f * 1.732f, 0.0f,
            0.0f, -0.0f * 1.732f, 0.0f,
            0.8f, -0.0f * 1.732f, 0.0f,
            0.4f, 0.4f * 1.732f, 0.0f,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void drawScene(GL10 gl) {
        super.drawScene(gl);

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexArray.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer vertex = vbb.asFloatBuffer();
        vertex.put(vertexArray);
        vertex.position(0);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -4);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
        index++;
        /*index%=10;
        switch(index){
            case 0:
            case 1:
            case 2:
                gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
                gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
                break;
            case 3:
            case 4:
            case 5:
                gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 6);
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
                gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 6);
                break;
        }*/

        if (index < 100) {
            gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
            gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
        } else if (index < 200) {
            gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 6);
        } else if (index < 300) {
            gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
            gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 6);
        } else {
            index = 0;
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
