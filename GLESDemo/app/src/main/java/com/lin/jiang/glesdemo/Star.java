package com.lin.jiang.glesdemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Our star
 * Created by jianglin on 17-5-8.
 */

public class Star {

    protected float[] vertecies = null;
    protected FloatBuffer vertexBuffer = null;

    public Star() {
        float a = (float) (1f / (2f - 2f * Math.cos(72f * Math.PI / 180f)));
        float bx = (float) (a * Math.cos(18 * Math.PI / 180f));
        float by = (float) (a * Math.sin(18 * Math.PI / 180f));
        float cy = (float) (-a * Math.cos(18 * Math.PI / 180f));

        vertecies = new float[]{
                // One vertex has 2 coordinates here
                0, a, 0.5f, cy, -bx, by, bx, by, -0.5f, cy // 5 vertices
        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertecies.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertecies);
        vertexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        // Set front face is CCW
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling
        gl.glEnable(GL10.GL_CULL_FACE);
        // Cull back face
        gl.glCullFace(GL10.GL_BACK);

        // Enable OpenGL ES vertex pipeline
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Push vertex buffer to OpenGL ES
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
        // Draw lines(draw the star)
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 5);
        // Disable vertices buffer
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        // Disable face culling
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
