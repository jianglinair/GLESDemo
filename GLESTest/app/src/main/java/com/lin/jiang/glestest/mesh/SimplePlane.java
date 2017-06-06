package com.lin.jiang.glestest.mesh;

/**
 * SimplePlane is a setup class for Mesh that creates a plane mesh with texture rendering.
 * <p>
 * Created by jianglin on 17-5-3.
 */

public class SimplePlane extends Mesh {

    public SimplePlane() {
        this(1, 1);
    }

    public SimplePlane(float width, float height) {
        // Texture mapping coordinates for the vertices
        float[] textureCoordinates = {
                0f, 2f,
                2f, 2f,
                0f, 0f,
                2f, 0f
        };

        short[] indices = {
                0, 1, 2, 1, 3, 2
        };

        float[] vertices = {
                -0.5f, -0.5f, 0.0f,
                 0.5f, -0.5f, 0.0f,
                -0.5f,  0.5f, 0.0f,
                 0.5f,  0.5f, 0.0f
        };

        setIndices(indices);
        setVertices(vertices);
        setTextureCoordinates(textureCoordinates);
    }
}
