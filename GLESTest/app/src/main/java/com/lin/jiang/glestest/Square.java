package com.lin.jiang.glestest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jianglin on 17-4-26.
 */

public class Square {
    private static final String TAG = "Square";

    public FloatBuffer vertexBuffer = null;
    public ShortBuffer indexBuffer = null;

    private float[] vertices = {
            -1.0f, 1.0f, 0.0f,   // v0
            -1.0f, -1.0f, 0.0f,  // v1
            1.0f, -1.0f, 0.0f,   // v2
            1.0f, 1.0f, 0.0f     // v3
    };

    private short[] indices = {0, 1, 2, 0, 2, 3};

    public Square() {
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    /**
     * This function draws our square on screen.
     *
     * @param gl
     */
    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        // Enable vertex pipeline
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Render vertex
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        // Render face
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);

        //LOG.i(TAG, "Square drawn");
    }
}
