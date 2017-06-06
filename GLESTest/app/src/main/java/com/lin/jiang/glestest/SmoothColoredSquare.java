package com.lin.jiang.glestest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * 平滑颜色过度
 * Created by jianglin on 17-4-26.
 */

public class SmoothColoredSquare extends Square {

    private FloatBuffer colorBuffer = null;

    private float[] colors = {
            1f, 0f, 0f, 1f, // vertex0 red
            0f, 1f, 0f, 1f, // vertex1 green
            0f, 0f, 1f, 1f, // vertex2 blue
            1f, 0f, 1f, 1f  // vertex3 magenta
    };

    public SmoothColoredSquare() {
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    @Override
    public void draw(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        // Enable OpenGLES color buffer pipeline
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        // Pointer out where the color buffer is.
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        super.draw(gl);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
