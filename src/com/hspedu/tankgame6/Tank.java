package com.hspedu.tankgame6;

/**
 * @author 陈正伟
 * @version 5.0
 * 定义一个坦克的类，把基本属性写出来，然后让我们的坦克和敌人的坦克继承
 */
public class Tank {
    private  int x; //坦克的横坐标
    private  int y;
    private  int direct;
    private  int speed = 1;
    boolean isLive = true;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //上右下左移动方法
    public void moveUp(){
        y -= speed;
    }
    public void moveRight(){
        x += speed;
    }
    public void moveDown(){
        y += speed;
    }
    public void moveLift(){
        x -= speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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
}
