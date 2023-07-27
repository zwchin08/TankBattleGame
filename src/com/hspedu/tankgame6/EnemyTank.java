package com.hspedu.tankgame6;

import java.util.Vector;

/**
 * @author 陈正伟
 * @version 5.0
 * 敌人的坦克
 */
public class EnemyTank extends Tank implements Runnable {
    //在敌人坦克类，使用Vector保存多个Shot
    Vector<Shot> shots = new Vector<>();
    // 增加一个成员，敌人的坦克  可以得到敌人坦克的Vector
    //分析  因为enemyTanks是在 MyPanel这个类里边的
    Vector<EnemyTank> enemyTanks = new Vector<>();

    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //这里提供一个方法可以将 MyPanel 成员的Vector<EnemyTank> enemyTanks = new Vector<>();
    //设置到 EnemyTank 的成员  enemyTanks
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法，判断当前的这个敌人坦克是否和Vector<EnemyTank>  的enemyTanks 中的其他坦克发生了重叠或者是碰撞
    public boolean isTouchEnemyTank() {

        //判断当前敌人坦克(this)方向
        switch (this.getDirect()) {
            case 0:
                //让当前的坦克(this)去和敌人的所有的坦克去比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从集合中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人的其他坦克是上下方向
                        //敌人坦克的坐标  x的范围   [enemyTank.getX(),enemyTank.getX() + 40]
                        //敌人坦克的坐标  y的范围   [enemyTank.getY(),enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 如果this的坦克的左上角或者右上角和敌人的比较多其他坦克的区域有接触就是碰撞
                            //  当前坦克 即this坦克  左上角的坐标是[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //  当前坦克 即this坦克  右上角的坐标是[this.getX() +40 ,this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //如果敌人的其他坦克是左右方向
                        //敌人坦克的坐标  x的范围   [enemyTank.getX(),enemyTank.getX() + 60]
                        //敌人坦克的坐标  y的范围   [enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // 如果this的坦克的左上角或者右上角和敌人的比较多其他坦克的区域有接触就是碰撞
                            //  当前坦克 即this坦克  左上角的坐标是[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //  当前坦克 即this坦克  右上角的坐标是[this.getX() +40 ,this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }

                    }

                }
                break;
            case 1:
                //让当前的坦克(this)去和敌人的所有的坦克去比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从集合中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人的其他坦克是上下方向
                        //敌人坦克的坐标  x的范围   [enemyTank.getX(),enemyTank.getX() + 40]
                        //敌人坦克的坐标  y的范围   [enemyTank.getY(),enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 如果this的坦克的左上角或者右上角和敌人的比较多其他坦克的区域有接触就是碰撞
                            //  当前坦克 即this坦克 方向是向右的 坦克的左上角的坐标是[this.getX() + 60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //  当前坦克 即this坦克  右上角的坐标是[this.getX() +60 ,this.getY() +40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //如果敌人的其他坦克是左右方向
                        //敌人坦克的坐标  x的范围   [enemyTank.getX(),enemyTank.getX() + 60]
                        //敌人坦克的坐标  y的范围   [enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // 如果this的坦克的左上角或者右上角和敌人的比较多其他坦克的区域有接触就是碰撞
                            //  当前坦克 即this坦克 方向是向右的 坦克的左上角的坐标是[this.getX() + 60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //  当前坦克 即this坦克  右上角的坐标是[this.getX() +60 ,this.getY() +40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }

                    }

                }

                break;
            case 2:
                //让当前的坦克(this)去和敌人的所有的坦克去比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从集合中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人的其他坦克是上下方向
                        //敌人坦克的坐标  x的范围   [enemyTank.getX(),enemyTank.getX() + 40]
                        //敌人坦克的坐标  y的范围   [enemyTank.getY(),enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 如果this的坦克的左上角或者右上角和敌人的比较多其他坦克的区域有接触就是碰撞
                            //  当前坦克 即this坦克 方向是向下的 坦克的左上角的坐标是[this.getX() + 40,this.getY() +60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //  当前坦克 即this坦克 方向是向下  右上角的坐标是[this.getX() ,this.getY() +60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //如果敌人的其他坦克是左右方向
                        //敌人坦克的坐标  x的范围   [enemyTank.getX(),enemyTank.getX() + 60]
                        //敌人坦克的坐标  y的范围   [enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // 如果this的坦克的左上角或者右上角和敌人的比较多其他坦克的区域有接触就是碰撞
                            //  当前坦克 即this坦克 方向是向下的 坦克的左上角的坐标是[this.getX() + 40,this.getY() +60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //  当前坦克 即this坦克 方向是向下  坦克的右上角的坐标是[this.getX() ,this.getY() +60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }

                    }

                }
                break;

            case 3:
                //让当前的坦克(this)去和敌人的所有的坦克去比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从集合中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人的其他坦克是上下方向
                        //敌人坦克的坐标  x的范围   [enemyTank.getX(),enemyTank.getX() + 40]
                        //敌人坦克的坐标  y的范围   [enemyTank.getY(),enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // 如果this的坦克的左上角或者右上角和敌人的比较多其他坦克的区域有接触就是碰撞
                            //  当前坦克 即this坦克 方向是向左的 坦克的左上角的坐标是[this.getX() ,this.getY() +40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40<= enemyTank.getY() + 60) {
                                return true;
                            }
                            //  当前坦克 即this坦克 方向是向左  坦克的右上角的坐标是[this.getX() ,this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY()  >= enemyTank.getY()
                                    && this.getY()  <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //如果敌人的其他坦克是左右方向
                        //敌人坦克的坐标  x的范围   [enemyTank.getX(),enemyTank.getX() + 60]
                        //敌人坦克的坐标  y的范围   [enemyTank.getY(),enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // 如果this的坦克的左上角或者右上角和敌人的比较多其他坦克的区域有接触就是碰撞
                            //  当前坦克 即this坦克 方向是向左的 坦克的左上角的坐标是[this.getX() ,this.getY() +40]
                            if (this.getX()  >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 60
                                    && this.getY() + 40>= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //  当前坦克 即this坦克 方向是向左  坦克的右上角的坐标是[this.getX() ,this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY()  >= enemyTank.getY()
                                    && this.getY()  <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }

                    }

                }
                break;
        }
        return false;
    }

    @Override
    public void run() {

        while (true) {

            //这里我们判断 如果shots size() ==0了说明没有子弹了
            if (isLive && shots.size() < 2) {  //把敌人的坦克控制一次只能打两个子弹
                //判断坦克的方向创建对相应的子弹
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                //启动
                new Thread(s).start();

            }
            //根据坦克的方向一直移动
            switch (getDirect()) {
                case 0:
                    //让坦克保持一个方向走30步
                    for (int i = 0; i < 30; i++) {

                        if (getY() > 0 && !isTouchEnemyTank()) {
                            moveUp();
                        }
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    //让坦克保持一个方向走30步
                    for (int i = 0; i < 30; i++) {
                        if (getX() +60< 1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    //让坦克保持一个方向走30步
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750 && !isTouchEnemyTank()) {
                            moveDown();
                        }
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    //让坦克保持一个方向走30步
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0 && !isTouchEnemyTank()) {
                            moveLift();
                        }
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

            }
            
            //然后随机的改变坦克的方向  0-3
            setDirect((int) (Math.random() * 4));//[0-4)

            //一旦写并发程序，一定要考虑清楚什么时候该线程结束
            if (!isLive) {//在创建坦克的时候启动线程
                break;//退出线程
            }
        }
    }
}
