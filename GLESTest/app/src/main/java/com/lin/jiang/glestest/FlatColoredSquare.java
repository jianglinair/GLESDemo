package com.lin.jiang.glestest;

import javax.microedition.khronos.opengles.GL10;

/**
 * 单色矩形
 * Flat coloring
 * Created by jianglin on 17-4-26.
 */

public class FlatColoredSquare extends Square {

    @Override
    public void draw(GL10 gl) {
        gl.glColor4f(0.5f, 0.5f, 1.0f, 1.0f);
        super.draw(gl);
    }
}
