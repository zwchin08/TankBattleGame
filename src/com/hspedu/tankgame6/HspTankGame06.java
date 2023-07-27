package com.hspedu.tankgame6;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author 陈正伟
 * @version 5.0
 */
public class HspTankGame06 extends JFrame {

    //定义一个MyPanel
    MyPanel mp = null;//初始化为空，在构造器里面完成他的初始化
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        HspTankGame06 TankGame01 = new HspTankGame06();
    }


    public HspTankGame06(){
        System.out.println("请输入选择  1:新游戏 2:继续上局");
        String key = scanner.next();
        mp = new MyPanel(key);
        //将mp 放入到 Tread ，并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp); //把面板加进去

        this.setSize(1300,950);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //再JFrame 中增加相应的关闭窗口处理
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
//                System.out.println("监听到关闭窗口的指令了");
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
