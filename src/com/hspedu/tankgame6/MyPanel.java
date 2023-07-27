package com.hspedu.tankgame6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

/**
 * @author 陈正伟
 * @version 5.0
 * 坦克大战的绘图区域
 */
// 为了监听 键盘事件，实现KeyListener
//为了让Panel 不停的重回子弹，需要将MyPanel 实现Runnable ,当作一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    //定义敌人的坦克，放入到Vector的集合中
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个存放Node对象集合用于回复敌人的坦克坐标和方向
    Vector<Node> nodes = new Vector<>();
    // 定义一个Vector用于存放炸弹
    //说明 ，当击中敌人，或者被敌人击中的时候就加入一个Bomb对象
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;

    //定义三张炸弹的图片用于显示爆炸的效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        //先判断我们的记录文件是否存在 如果文件不存在提示只能开启新游戏，key =1
        File file = new File(Recorder.getRecordFile());
        if(file.exists()) {
            nodes = Recorder.getNodeAndEnmyTankRec();
        }else{
            System.out.println("文件不存在，只能开启新游戏！");
            key = "1";
        }
        //将MyPanel对象的 enemyTanks 集合  设置给 Recorder  的enemyTanks
        Recorder.setEnemyTanks(enemyTanks);

        hero = new Hero(500, 100); //初始化自己的坦克
//        hero.setSpeed(3);//设置坦克速度，默认为1
        switch (key){
            case "1":
                //初始化敌人的坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    //每次创建一个坦克
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    //将集合enemyTanks设置给 enemyTank对象!!!
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(2);
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //把这颗子弹加入到enemyTank的Vector 成员
                    enemyTank.shots.add(shot);
                    //启动 shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);//加到Vector的集合中
                }
                break;
            case "2": //继续上局游戏
                //初始化敌人的坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    //每次创建一个坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //将集合enemyTanks设置给 enemyTank对象!!!
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(node.getDirect());
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //把这颗子弹加入到enemyTank的Vector 成员
                    enemyTank.shots.add(shot);
                    //启动 shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);//加到Vector的集合中
                }
                break;
            default:
                System.out.println("你的输入有误...");
        }

        //初始化爆炸图片的三张
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    //音乐播放
        new AePlayWave("src\\111.wav").start();
    }


    //编写方法，显示我方击毁敌方坦克的数量
    public void showInfo(Graphics g){

        //画出玩家的总成绩
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累计击毁敌方坦克", 1020, 30);
        drawTank(1020,60,g,0,0);//画出一个敌方坦克
        g.setColor(Color.black);//这里需要重新设置笔的颜色，因为在画上面的坦克的时候颜色被改了
        g.drawString(Recorder.getAllEnemyTankNum() + "",1080,100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//默认是黑色
        showInfo(g);
        if (hero !=null && hero.isLive) {
            //画出坦克-封装到方法
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
//      drawTank(hero.getX() + 60 ,hero.getY(),g,0,0);
        }

        //画出Hero的子弹
//        if (hero.shot != null && hero.shot.isLive != false) {
//            //g.fill3DRect(hero.shot.x,hero.shot.y,2,2,false);
//            g.draw3DRect(hero.shot.x, hero.shot.y, 2, 2, false);
//        }

        //将hero的子弹集合 shots遍历取出绘制
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive ) {
            //g.fill3DRect(hero.shot.x,hero.shot.y,2,2,false);
            g.draw3DRect(shot.x, shot.y, 2, 2, false);
            }else {
                //如果该shot对象已经无效就从集合中拿掉
                hero.shots.remove(shot);
            }
        }

        // 如果集合中是有炸弹的，就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让炸弹的生命值减少
            bomb.lifeDown();
            //如果  生命值为零  就集体删除bombs的集合
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

        //画出敌人的坦克，要遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前坦克是否还存活，只有当敌人坦克是活着的时候才去画
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);//将来敌人的坦克也可以做成变量
                //画出所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出当前的子弹准备绘制
                    Shot shot = enemyTank.shots.get(j);
                    //绘制的时候要进行判断
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        //从Vector中移除
                        enemyTank.shots.remove(shot);
                    }

                }
            }
        }

    }
    //编写方法，画出坦克

    /**
     * @param x      坦克左上角的横坐标
     * @param y      坦克左上角的y坐标
     * @param g      画笔
     * @param direct 坦克的方向(上下左右)case0表示向上
     * @param type   坦克类型 0表示自己的坦克
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0: //敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //我们的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克的方向来绘制对应形式坦克
        // direct  分别用0表示向上，1表示向右，2表示向下，3表示向左边

        switch (direct) {
            case 0:  // 0表示向上
                g.fill3DRect(x, y, 10, 60, false); //画出坦克左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false); //画出坦克右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); //画出坦克的身体
                g.fillOval(x + 10, y + 20, 20, 20); //画出坦克的圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:  // 0表示向右
                g.fill3DRect(x, y, 60, 10, false); //画出坦克上边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false); //画出坦克下边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false); //画出坦克的身体
                g.fillOval(x + 20, y + 10, 20, 20); //画出坦克的圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:  // 0表示向下
                g.fill3DRect(x, y, 10, 60, false); //画出坦克左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false); //画出坦克右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); //画出坦克的身体
                g.fillOval(x + 10, y + 20, 20, 20); //画出坦克的圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:  // 0表示向左边
                g.fill3DRect(x, y, 60, 10, false); //画出坦克上边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false); //画出坦克下边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false); //画出坦克的身体
                g.fillOval(x + 20, y + 10, 20, 20); //画出坦克的圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("暂时没有处理");
        }

    }

    //如果我们的坦克可以发射多颗子弹
    //再判断我防坦克时候击中敌人的坦克时，就需要把我们的子弹集合中所有的子弹都取出来和敌人的所有坦克进行判断
    public void hitEnemyTank(){

        //遍历我们的坦克
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            // 判断是否集中了敌人坦克
            if (shot != null && shot.isLive) {
                //遍历敌人所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    //编写方法判断敌人坦克是否击中我防坦克
    public void hitHero(){
        //遍历敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历 enemyTank对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //让这颗子弹和我们的坦克相对位置进行判断   看是击中
                if(hero.isLive && shot.isLive){
                    hitTank(shot,hero);
                }
            }
        }
    }

    //编写方法，判断我方的子弹是否击中敌人
    public void hitTank(Shot s, Tank tank) {
        //判断s是否在敌人的坦克的区域内，如果在，就代表集击中了。
        //什么时候判断 我方的子弹是否集中了敌人的坦克？ run方法去判断比较好
        switch (tank.getDirect()) {
            case 0:
            case 2:
                if (s.x > tank.getX() && s.x < tank.getX() + 40
                        && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.isLive = false;
                    tank.isLive = false;
                    //当我的子弹击中敌人的坦克后，将敌人的坦克从集合中删掉
                    enemyTanks.remove(tank);

                    //当我方击落了敌方坦克  allEnemyTankNum++

                    if(tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }

                    //创建一个Bomb对象加入到集合中
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);

                }
                break;
            case 1:
            case 3:
                if (s.x > tank.getX() && s.x < tank.getX() + 60
                        && s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.isLive = false;
                    tank.isLive = false;
                    //当我的子弹击中敌人的坦克后，将敌人的坦克从集合中删掉
                    enemyTanks.remove(tank);

                    //当我方击落了敌方坦克  allEnemyTankNum++
                    if(tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }

                    //创建一个Bomb对象加入到集合中
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 处理
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {

            hero.setDirect(0);// w方向设置向上
            if (hero.getY() > 0) {
                hero.moveUp();//修改坦克坐标 y -= 1
            }

        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1); //D键设置向右
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2); ////S键设置向下
            if (hero.getY() +60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3); //S键设置向左
            if (hero.getX() > 0) {
                hero.moveLift();
            }
        }

        //如果用户按下的是J，就需要发射炮弹
        if (e.getKeyCode() == KeyEvent.VK_J) {

            System.out.println("用户按下了J,开始射击。");
            //下面的if是发射一颗子弹的情形
         //   if (hero.shot == null || !hero.shot.isLive) {   // 就是当你没有子弹的时候要创建子弹，但是当你有子弹的时候就不能再打出子弹
           //     hero.shotEnemyTank();
            //}

            //发射多颗子弹
            hero.shotEnemyTank();

        }

        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每个100毫秒重绘区域,子弹就移动起来了


        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hitEnemyTank();  //判断我们的坦克击中敌人
            hitHero(); //判断敌人的坦克是否击中我们
            this.repaint();
        }
    }
}
