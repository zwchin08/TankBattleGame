package com.hspedu.tankgame6;

import java.util.Vector;

/**
 * @author 陈正伟
 * @version 5.0
 * 自己的坦克
 */
public class Hero extends Tank {
    //定义一个Shot对象,表示一个设计行为(线程)
    Shot shot = null;
    //可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {

        //发射多颗子弹怎么办？控制在我们的面板上最多只有5颗子弹
        if (shots.size() == 5){
            return;
        }
        //创建 Shot对象，要根据当前Hero这个汤克的位置创建Shot
        switch (getDirect()){  //这里是得到Hero的对象
            case 0:
                shot = new Shot(getX() + 20, getY(),0);
                break;
            case 1:
            shot = new Shot(getX() +60, getY() + 20 ,1);
            break;
            case 2:
            shot = new Shot(getX() + 20 , getY() + 60 ,2);
            break;
            case 3:
            shot = new Shot(getX() + 20 , getY() + 20 ,3);
            break;

        }
        //把新创建的shot放入到shots集合中
        shots.add(shot);
        //启动shot线程
        new Thread(shot).start();
    }
}
