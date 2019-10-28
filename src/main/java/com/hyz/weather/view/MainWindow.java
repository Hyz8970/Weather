package com.hyz.weather.view;

import com.hyz.weather.action.IpLocation;
import com.hyz.weather.action.Wallpaper;
import com.hyz.weather.action.Weather;
import com.hyz.weather.reSwing.HJButton;
import com.hyz.weather.reSwing.Icons;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 主窗体
 */
public class MainWindow extends JFrame {
    private IpLocation ipLocation=new IpLocation();
    private Wallpaper wallpaper=new Wallpaper();
    private Weather weather=new Weather();
    private int wWidth=1024,wHeight=576;

    public MainWindow() {
        //系统分辨率
        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds(screenWidth/2 - wWidth/2, screenHeight/2 - wHeight/2, wWidth,wHeight);
        this.setUndecorated(true);//去掉窗口
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//指定窗口风格
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Weather");
        this.setResizable(false);
        this.setLayout(new GridBagLayout());//相对布局
        this.setIconImage(Icons.LOGO.getImage());

        //关闭按钮
        JButton closeBt = new JButton(Icons.CLOSE);
        closeBt.setMargin(new Insets(0,0,0,0));
        closeBt.setContentAreaFilled(false);
        closeBt.setBorderPainted(false);
        closeBt.setBounds(wWidth-30,6,24,24);
        closeBt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBt.addActionListener((e)-> System.exit(0));
        closeBt.setRolloverIcon(Icons.CLOSE_FOCUS);
        getLayeredPane().add(closeBt);

        setBackgroundImg();

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
    }

    private void setBackgroundImg(){
        JLabel bg=new JLabel(Icons.BACKGROUND);
        this.getLayeredPane().add(bg,JLayeredPane.DEFAULT_LAYER);
        bg.setBounds(new Rectangle(0,0,wWidth,wHeight));
        new Thread(()->{
            //加载bing壁纸
            String uri = wallpaper.bingWallpaper();
            try {
                bg.setIcon(new ImageIcon(new URL(uri)));
                repaint();//刷新重绘
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
