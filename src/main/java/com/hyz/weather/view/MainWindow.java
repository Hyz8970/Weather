package com.hyz.weather.view;

import com.hyz.weather.action.IpLocation;
import com.hyz.weather.action.Wallpaper;
import com.hyz.weather.action.Weather;
import com.hyz.weather.entity.root.HeWeather6Now;
import com.hyz.weather.reSwing.HJLabel;
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
    private int wWidth = 1024, wHeight = 576, tHeight = 36;
    private String appTitle = "和风天气";
    private Point loc = null;
    private Point tmp = null;
    private boolean isDragged = false;
    private NowPanel nowPanelP;
    private AirNowPanel airNowPanelP;
    private ForecastPanel forecastPanelP;
    private HourlyPanel hourlyPanelP;
    private LifestylePanel lifestylePanelP;
    private String location = "beihai";

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
        this.setIconImage(Icons.LOGO.getImage());

        //标题栏
        JLabel wIcon = new JLabel(new ImageIcon(
                Icons.LOGO.getImage().getScaledInstance(30, 30, Image.SCALE_FAST)
        ));
        wIcon.setBounds(10, tHeight / 2 - 30 / 2, 30, 30);
        HJLabel wTitle = new HJLabel(appTitle);
        wTitle.setBounds(50, 0, wWidth - 40 - Icons.CLOSE.getIconWidth(), tHeight);
        wTitle.setForeground(Color.white);
//        this.getLayeredPane().add(wIcon);
//        this.getLayeredPane().add(wTitle);
        wTitle.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        isDragged = false;
                    }

                    public void mousePressed(MouseEvent e) {
                        tmp = new Point(e.getX(), e.getY());
                        isDragged = true;
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
        closeBt.setMargin(new Insets(0, 0, 0, 0));
        closeBt.setContentAreaFilled(false);
        closeBt.setBorderPainted(false);
        closeBt.setBounds(wWidth - Icons.CLOSE.getIconWidth(), 0,
                Icons.CLOSE.getIconWidth(), Icons.CLOSE.getIconHeight());
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
//        this.getLayeredPane().add(closeBt);
//        setBackgroundImg();
//        int cWidth = wWidth / 2 - 10;//组件宽度
//        int osHeight = wHeight - tHeight;//单侧面板高度
        nowPanelP = new NowPanel();
        airNowPanelP = new AirNowPanel();
        hourlyPanelP = new HourlyPanel();
        forecastPanelP = new ForecastPanel();
        lifestylePanelP = new LifestylePanel();
        flushData();
        this.getContentPane().setLayout(new BorderLayout());

        GridBagConstraints leftGBC = new GridBagConstraints();
        leftGBC.fill=GridBagConstraints.BOTH;
        leftGBC.gridx=0;
        //左面板
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new GridBagLayout());
        leftGBC.gridheight=2;
        leftPanel.add(nowPanelP, leftGBC);
        leftGBC.gridheight=1;
        leftPanel.add(airNowPanelP, leftGBC);
        leftPanel.add(hourlyPanelP, leftGBC);
        //右面板
        GridBagConstraints rightGBC = new GridBagConstraints();
        rightGBC.fill=GridBagConstraints.EAST;
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new GridBagLayout());
        rightGBC.gridheight=1;
        rightPanel.add(forecastPanelP, rightGBC);
        rightGBC.gridheight=4;
        rightPanel.add(lifestylePanelP, rightGBC);

        this.getContentPane().add(leftPanel, BorderLayout.WEST);
        this.getContentPane().add(rightPanel, BorderLayout.EAST);
        this.getLayeredPane().add(wIcon);
        this.getLayeredPane().add(wTitle);
        this.getLayeredPane().add(closeBt);
        setBackgroundImg();
    }

    private void setBackgroundImg() {
        JLabel bg = new JLabel(new ImageIcon(
                Icons.BACKGROUND.getImage().getScaledInstance(wWidth, wHeight, Image.SCALE_FAST)
        ));
        this.getLayeredPane().add(bg, JLayeredPane.DEFAULT_LAYER);
        bg.setBounds(new Rectangle(0, 0, wWidth, wHeight));
        new Thread(() -> {
            //加载bing壁纸
            String uri = wallpaper.bingWallpaper();
            try {
                ImageIcon bing = new ImageIcon(new URL(uri));
                bg.setIcon(new ImageIcon(
                        bing.getImage().getScaledInstance(wWidth, wHeight, Image.SCALE_FAST)
                ));
                repaint();//刷新重绘
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private JFrame getSelf() {
        return this;
    }

    private void flushData() {
        new Thread(() -> {
            if (location.equals("")) {
                //定位获取天气数据
                location = ipLocation.locationByIP();
            }
            HeWeather6Now now = weather.now(location);
            if (now != null) {
                nowPanelP.setData(now);
                location = now.getBasic().getCid();
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
