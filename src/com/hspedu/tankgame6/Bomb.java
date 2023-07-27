package com.hspedu.tankgame6;

/**
 * @author 陈正伟
 * @version 5.0
 *
 * 爆炸效果
 */
public class Bomb {
    int x,y;
    int life = 9;//炸弹的生命周期
    boolean isLive = true; //是否还存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void  lifeDown(){//为了配合图片的爆炸效果
        if(life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
}
