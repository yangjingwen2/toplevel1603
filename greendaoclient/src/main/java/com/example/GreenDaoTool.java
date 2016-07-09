package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * GreenDaoTool是一个GreenDao的工具类
 * 用来生成一批代码：
 * 1、包含数据库访问的类
 * 2、以及一些javabean对象
 * 3、创建数据库以及数据库中的表
 */
public class GreenDaoTool {
/*
create table user {

 _id integer primary key autoincrement,
 name text ,
 age int
}

 */
    public static void main(String[] args) {
        //创建一个模板，创建数据库中的表的模板，同时创建这些表对应的javabean对象。
        /*
        参数一：版本号
        参数二：一个包名：表示自动生成的代码放在哪一个包下面
         */
        Schema schema = new Schema(1,"com.androidxx.yangjw.dao");
        //创建一张表
        Entity userEntity = schema.addEntity("user");
        //给User表添加字段
        userEntity.addIdProperty().autoincrement();
        userEntity.addStringProperty("name");
        userEntity.addIntProperty("age");

        /*
        参数一：Schema对象
        参数二：自动生成代码放在哪一个项目中
         */
        //自定生成
        try {
            new DaoGenerator().generateAll(schema,"../TopLevel1603/greendaodemo/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
