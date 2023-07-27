package com.hspedu.tankgame6;

/**
 * @author 陈正伟
 * @version 5.0
 * 射击子弹
 */
public class Shot implements Runnable{
    int x; //子弹的x坐标
    int y; //子弹的y坐标
    int direct = 0;//子弹的方向
    int speed = 2;//子弹的速度
    boolean isLive = true;  //设计一个变量,子弹是否还存活


    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() { //就是一个射击行为
        while(true){

            //让线程休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //根据方向来改变x,y坐标
            switch (direct){
                case 0://向上
                    y -= speed;
                    break;
                case 1://向右
                    x += speed;
                    break;
                case 2://向下
                    y += speed;
                    break;
                case 3://向左
                    x -= speed;
                    break;

            }

            //为了测试这里输出下，xy坐标
            System.out.println("x坐标" + x + "y坐标" + y);
            //如果子弹不在显示的边框范围以内，线程终止
            //挡子弹打到敌人坦克时线程终止
            if( !( x >= 0 && x <= 1000 && y >= 0 && y<=750 && isLive)){
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }
        }
    }
}
