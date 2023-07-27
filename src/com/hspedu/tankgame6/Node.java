package com.hspedu.tankgame6;

/**
 * @author 陈正伟
 * @version 5.0
 *
 * 一个Node的对象表示一个坦克的敌人信息
 */
public class Node {
    private int x ;
    private int y ;
    private int direct;

    public Node(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
}
