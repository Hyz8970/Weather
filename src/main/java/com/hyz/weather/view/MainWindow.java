package com.hyz.weather.view;

import com.hyz.weather.action.IpLocation;
import com.hyz.weather.action.Wallpaper;
import com.hyz.weather.action.Weather;
import com.hyz.weather.entity.root.HeWeather6Now;
import com.hyz.weather.reSwing.Icons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 主窗体
 */
public class MainWindow extends JFrame {
    private IpLocation ipLocation = new IpLocation();
    private Wallpaper wallpaper = new Wallpaper();
    private Weather weather = new Weather();
    private int wWidth = 1024, wHeight = 576;
    private String appTitle = "天气预报";
    private Point loc = null;
    private Point tmp = null;
    private boolean isDragged = false;
    private NowPanel nowPanelP;
    private AirNowPanel airNowPanelP;
    private ForecastPanel forecastPanelP;
    private HourlyPanel hourlyPanelP;
    private LifestylePanel lifestylePanelP;
    private String location="";

    public MainWindow() {
        //系统分辨率
        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds(screenWidth / 2 - wWidth / 2, screenHeight / 2 - wHeight / 2, wWidth, wHeight);
        this.setUndecorated(true);//去掉窗口
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//指定窗口风格
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(appTitle);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setIconImage(Icons.LOGO.getImage());

        //标题栏
        JLabel wTitle = new JLabel(appTitle);
        wTitle.setBounds(10, 0, wWidth-40, 30);
        wTitle.setFont(new Font("微软雅黑",Font.PLAIN,24));
        wTitle.setForeground(Color.white);
        getLayeredPane().add(wTitle);
        wTitle.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        isDragged = false;
                        getSelf().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    }

                    public void mousePressed(MouseEvent e) {
                        tmp = new Point(e.getX(), e.getY());
                        isDragged = true;
                        getSelf().setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    }
                });
        wTitle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDragged) {
                    loc = new Point(getSelf().getLocation().x + e.getX() - tmp.x,
                            getSelf().getLocation().y + e.getY() - tmp.y);
                    getSelf().setLocation(loc);
                }
            }
        });

        //关闭按钮
        JButton closeBt = new JButton(Icons.CLOSE);
        closeBt.setMargin(new Insets(10, 10, 10, 10));
        closeBt.setContentAreaFilled(false);
        closeBt.setBorderPainted(false);
        closeBt.setBounds(wWidth - 30, 6, 24, 24);
        closeBt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBt.addActionListener((e) -> System.exit(0));
        closeBt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeBt.setBackground(Color.red);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                closeBt.setBackground(Color.red);
                closeBt.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeBt.setContentAreaFilled(false);
            }
        });
        getLayeredPane().add(closeBt);

        setBackgroundImg();
        nowPanelP = new NowPanel();
        airNowPanelP = new AirNowPanel();
        forecastPanelP = new ForecastPanel();
        hourlyPanelP = new HourlyPanel();
        lifestylePanelP = new LifestylePanel();
//        flushData();
        //左面板
        JPanel leftPanel=new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBounds(0,30,wWidth/2,wHeight-30);
        leftPanel.add(nowPanelP,BorderLayout.NORTH);
        leftPanel.add(airNowPanelP,BorderLayout.CENTER);
        leftPanel.add(hourlyPanelP,BorderLayout.SOUTH);
        //右面板
        JPanel rightPanel=new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBounds(wWidth/2,30,wWidth/2,wHeight-30);
        rightPanel.add(forecastPanelP,BorderLayout.NORTH);
        rightPanel.add(lifestylePanelP,BorderLayout.SOUTH);
        this.add(leftPanel,BorderLayout.WEST);
        this.add(rightPanel,BorderLayout.EAST);
    }

    private void setBackgroundImg() {
        JLabel bg = new JLabel(new ImageIcon(
                Icons.BACKGROUND.getImage().getScaledInstance(wWidth,wHeight,Image.SCALE_FAST)
        ));
        this.getLayeredPane().add(bg, JLayeredPane.DEFAULT_LAYER);
        bg.setBounds(new Rectangle(0, 0, wWidth, wHeight));
        new Thread(() -> {
            //加载bing壁纸
            String uri = wallpaper.bingWallpaper();
            try {
                ImageIcon bing = new ImageIcon(new URL(uri));
                bg.setIcon(new ImageIcon(
                        bing.getImage().getScaledInstance(wWidth,wHeight,Image.SCALE_FAST)
                ));
                repaint();//刷新重绘
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }).start();
    }
    private JFrame getSelf(){
        return this;
    }
    private void flushData(){
        new Thread(() -> {
            if (location.equals("")){
                //定位获取天气数据
                location = ipLocation.locationByIP();
            }
            HeWeather6Now now = weather.now(location);
            if (now != null){
                nowPanelP.setData(now);
                location=now.getBasic().getCid();
                airNowPanelP.setData(weather.airNow(location));
                forecastPanelP.setData(weather.forecast(location));
                hourlyPanelP.setData(weather.hourly(location));
                lifestylePanelP.setData(weather.lifestyle(location));
            }
        }).start();
    }
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
