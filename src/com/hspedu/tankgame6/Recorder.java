package com.hspedu.tankgame6;

import java.io.*;
import java.util.Vector;

/**
 * @author 陈正伟
 * @version 5.0
 *
 * 该类用与记录相关的信息 和 文件交互
 */
public class Recorder {

    //定义变量，记录我方击毁敌方坦克数量
    private static  int allEnemyTankNum = 0;
    //定义IO对象,用于写数据到文件中
    private  static BufferedWriter bw = null;
    private static BufferedReader br = null;
//    private  static String recordFile = "C:\\Users\\students\\Desktop\\wuyong\\myRecord.txt";
    private  static String recordFile = "src\\myRecord.txt";
    //定义一个集合，执行MyPanel对象的敌人的坦克集合
    private  static Vector<EnemyTank> enemyTanks = null;
    //返回记录文件的记录
    public static String getRecordFile(){
        return recordFile;
    }
    //定义一个Node的集合，用保存敌人的信息
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
    //增加一个方法用于读取文件恢复信息
    //该方法时继续上局游戏的时候调用
    public static Vector<Node> getNodeAndEnmyTankRec(){

        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取生成nodes集合
            String line = "";
            while((line = br.readLine()) != null){
                String[] xyd = line.split(" ");//切割后生成一个数组  xyd  表示坐标和方向
                Node node = new Node(Integer.parseInt(xyd[0]),
                        Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return nodes;
    }

    //增加一个方法，当游戏退出时，将allEnemyTankNum 保存到recordFile
    //对这个方法进行升级保存
    public static  void keepRecord(){
        try {
             bw = new BufferedWriter(new FileWriter(recordFile));
            //   "\r\n"代表换行
             bw.write(allEnemyTankNum +"\r\n");
             //遍历敌人坦克的几个，然后根据情况保存
            //OOP，定义一个属性，然后通过setXXX得到敌人坦克的集合
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出敌人坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive){
                    //保存该坦克信息
                    String record = enemyTank.getX() + " "+ enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入到文件
                    bw.write(record +"\r\n");
                    //或者使用bw.newLine()
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bw !=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方机会一辆敌方坦克时，就要对allEnemyTankNum ++
    public static  void  addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
