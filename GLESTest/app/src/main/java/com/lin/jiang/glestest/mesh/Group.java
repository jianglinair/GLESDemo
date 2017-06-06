package com.lin.jiang.glestest.mesh;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

/**
 * Group 的主要功能是把针对 Group 的操作（如draw) 分发到 Group 中的每个成员对应的操作（如draw)。
 * <p>
 * Created by jianglin on 17-5-3.
 */

public class Group extends Mesh {

    private Vector<Mesh> children = new Vector<>();

    @Override
    public void draw(GL10 gl) {
        int size = children.size();
        for (int i = 0; i < size; i++) {
            children.get(i).draw(gl);
        }
    }

    /**
     * @param location index in Vector
     * @param mesh     instance of Mesh
     * @see java.util.Vector#add(int, Object)
     */
    public void add(int location, Mesh mesh) {
        children.add(location, mesh);
    }

    public boolean add(Mesh mesh) {
        return children.add(mesh);
    }

    /**
     * Removes all of the elements from this Group
     */
    public void clear() {
        children.clear();
    }

    public Mesh get(int location) {
        return children.get(location);
    }

    public Mesh remove(int location) {
        return children.remove(location);
    }

    public boolean remove(Mesh mesh) {
        return children.remove(mesh);
    }

    public int size() {
        return children.size();
    }
}
