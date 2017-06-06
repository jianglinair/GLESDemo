package com.lin.jiang.glestest.mesh;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jianglin on 17-4-28.
 */

public class Mesh {
    // Translate params
    public float x = 0;
    public float y = 0;
    public float z = 0;
    // Rotate params
    public float rx = 0;
    public float ry = 0;
    public float rz = 0;
    // Our vertex buffer
    private FloatBuffer verticesBuffer = null;
    // Our index buffer
    private ShortBuffer indicesBuffer = null;
    // The number of indices
    private int numOfIndices = -1;
    // Flat color
    private float[] color = {1f, 1f, 1f, 1f};
    // Smooth colors
    private FloatBuffer colorBuffer = null;

    //========== Use texture ==========
    // UV texture buffer
    private FloatBuffer mTextureBuffer = null;
    private int mTextureId = -1;
    // The bitmap we want to load as a texture
    private Bitmap mBitmap = null;
    // If we need to load the texture
    private boolean mShouldLoadTexture = false;

    /**
     * Render the mesh
     *
     * @param gl the OpenGL Context to render to
     */
    public void draw(GL10 gl) {
        // CCW
        gl.glFrontFace(GL10.GL_CCW);
        //Enable culling face
        gl.glEnable(GL10.GL_CULL_FACE);
        // Cull back face
        gl.glCullFace(GL10.GL_BACK);
        // Enable vertex buffer pipeline of OpenGL ES
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // Render vertices
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        // Set flat color
        gl.glColor4f(color[0], color[1], color[2], color[3]);
        // Smooth color
        if (colorBuffer != null) {
            // Enable OpenGL ES color pipeline
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        }

        //=========== jianglin: render texture =============
        if(mShouldLoadTexture) {
            loadGLTexture(gl);
            mShouldLoadTexture = false;
        }
        if(mTextureId != -1 && mTextureBuffer != null) {
            gl.glEnable(GL10.GL_TEXTURE_2D);
            // Enable the texture state(pipeline of OpenGL)
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            // Point to our buffers
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
        }
        //==============================================

        gl.glTranslatef(x, y, z);
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);

        // Pointer out where the color buffer is
        gl.glDrawElements(GL10.GL_TRIANGLES, numOfIndices, GL10.GL_UNSIGNED_SHORT, indicesBuffer);
        // Disable the vertices buffer
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        //=========== jianglin: render texture =============
        if(mTextureId != -1 && mTextureBuffer != null) {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }
        //==============================================
        // Disable face culling
        gl.glDisableClientState(GL10.GL_CULL_FACE);
    }

    /**
     * setVertices 允许子类重新定义顶点坐标
     *
     * @param vertices
     */
    protected void setVertices(float[] vertices) {
        // A float is 4 bytes
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuffer = vbb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);
    }

    /**
     * setIndices 允许子类重新定义顶点的顺序
     *
     * @param indices
     */
    protected void setIndices(short[] indices) {
        // A short is 2 bytes
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indicesBuffer = ibb.asShortBuffer();
        indicesBuffer.put(indices);
        indicesBuffer.position(0);

        numOfIndices = indices.length;
    }

    /**
     * setColor /setColors 允许子类重新定义颜色
     *
     * @param red
     * @param green
     * @param blue
     * @param alpha
     */
    protected void setColor(float red, float green, float blue, float alpha) {
        // Setting the flat color
        color[0] = red;
        color[1] = green;
        color[2] = blue;
        color[3] = alpha;
    }

    /**
     * setColor /setColors 允许子类重新定义颜色
     *
     * @param colors
     */
    protected void setColors(float[] colors) {
        // A float has 4 bytes
        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    /**
     * setTextureCoordinates 支持定义 UV 坐标
     *
     * @param textureCoordinates
     */
    protected void setTextureCoordinates(float[] textureCoordinates) {
        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        mTextureBuffer = tbb.asFloatBuffer();
        mTextureBuffer.put(textureCoordinates);
        mTextureBuffer.position(0);
    }

    /**
     * Set the bitmap to load into a texture
     *
     * @param bitmap
     */
    public void loadBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mShouldLoadTexture = true;
    }

    /**
     * Load texture
     *
     * @param gl
     */
    private void loadGLTexture(GL10 gl) {
        // Generate one texture pointer
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        mTextureId = textures[0];

        // Bind the created texture to OpenGL
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);

        // Create nearest filtered texture
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        // Different possible texture parameters
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        // Bind bitmap tp OpenGL, use the Android GLUtils
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0);
    }

}
