package com.hyz.weather.view;

import com.hyz.weather.action.IpLocation;
import com.hyz.weather.action.Wallpaper;
import com.hyz.weather.action.Weather;
import com.hyz.weather.entity.root.HeWeather6Now;
import com.hyz.weather.reSwing.HJButton;

import javax.swing.*;
import java.awt.*;

/**
 * 主窗体
 */
public class MainWindow extends JFrame {
    private IpLocation ipLocation=new IpLocation();
    private Wallpaper wallpaper=new Wallpaper();
    private Weather weather=new Weather();

    public MainWindow() {
        //系统分辨率
        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        int wWidth=1024,wHeight=576;
        this.setBounds(screenWidth/2 - wWidth/2, screenHeight/2 - wHeight/2, wWidth,wHeight);
        this.setUndecorated(true);//去掉窗口
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//指定窗口风格
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Weather");
        this.setResizable(false);
        this.setLayout(new GridBagLayout());//相对布局
        ImageIcon imageIcon = new ImageIcon(MainWindow.class.getClassLoader().getResource("./img/logo.ico"));
        this.setIconImage(imageIcon.getImage());
        HJButton closeBt = new HJButton("X");
        closeBt.setBounds(10,10,1000,570);
        closeBt.addActionListener((e)-> System.exit(0));//关闭按钮
        GridBagConstraints closeBtGBC=new GridBagConstraints();
        closeBtGBC.gridx=1;
        closeBtGBC.gridy=0;
        closeBtGBC.gridwidth=GridBagConstraints.RELATIVE;
        closeBtGBC.gridheight=1;
        closeBtGBC.weightx=1;
        closeBtGBC.weighty=0;
        closeBtGBC.anchor=GridBagConstraints.CENTER;
        closeBtGBC.fill=GridBagConstraints.HORIZONTAL;
        closeBtGBC.insets=new  Insets( 5 , 5 , 5 , 5 );
        closeBtGBC.ipadx=0;
        closeBtGBC.ipady=0;

        JPanel bg=new BGJPanel(this);//图片背景

        NowPanel nowPanelP =new NowPanel();
        AirNowPanel airNowPanelP =new AirNowPanel();
        ForecastPanel forecastPanelP =new ForecastPanel();
        HourlyPanel hourlyPanelP =new HourlyPanel();
        LifestylePanel lifestylePanelP =new LifestylePanel();
        new Thread(()->{
            //定位获取天气数据
//            String location = ipLocation.locationByIP();
//            HeWeather6Now now = weather.now(location);
//            if (now != null){
//                nowPanelP.setData(now);
//                location=now.getBasic().getCid();
//                airNowP.setData(weather.airNow(location));
//                forecastP.setData(weather.forecast(location));
//                hourlyP.setData(weather.hourly(location));
//                lifestyleP.setData(weather.lifestyle(location));
//            }
        }).start();

//        this.add(nowJp);
//        this.add(airNowJp);
//        this.add(forecastJp);
//        this.add(hourlyJp);
//        this.add(lifestyleJp);

        bg.add(closeBt,closeBtGBC);
        this.add(bg,new GridBagConstraints());
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
