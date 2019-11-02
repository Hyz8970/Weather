package com.hyz.weather.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyz.weather.action.HistoryAction;
import com.hyz.weather.action.IpLocation;
import com.hyz.weather.action.Wallpaper;
import com.hyz.weather.action.Weather;
import com.hyz.weather.entity.*;
import com.hyz.weather.entity.root.*;
import com.hyz.weather.reSwing.HJButton;
import com.hyz.weather.reSwing.HJLabel;
import com.hyz.weather.reSwing.Icons;
import com.hyz.weather.reSwing.MDColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 主窗体
 */
public class MainWindow extends JFrame {
    private String appTitle = "和风天气";
    private IpLocation ipLocation = new IpLocation();
    private Wallpaper wallpaper = new Wallpaper();
    private Weather weather = new Weather();
    private HistoryAction historyAction = new HistoryAction();
    private int wWidth = 1024, wHeight = 576, tHeight = 36;
    private Point loc = null;
    private Point tmp = null;
    private boolean isDragged = false;
    private String location = "";
    private RegionPanel regionPanel;
    private NowPanel nowPanelP;
    private AirNowPanel airNowPanelP;
    private ForecastPanel forecastPanelP;
    private HourlyPanel hourlyPanelP;
    private LifestylePanel lifestylePanelP;
    private LocationListPanel locationListPanel;

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
        HJLabel wIcon = new HJLabel(new ImageIcon(
                Icons.LOGO.getImage().getScaledInstance(30, 30, Image.SCALE_FAST)
        ));
        wIcon.setBounds(10, tHeight / 2 - 30 / 2, 30, 30);
        HJLabel wTitle = new HJLabel(appTitle);
        wTitle.setBounds(50, 0, wWidth - 40 - Icons.CLOSE.getIconWidth(), tHeight);
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
//        JButton closeBt = new HJButton(Icons.CLOSE);
        HJButton closeBt = new HJButton(Icons.CLOSE_BLACK);
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
        regionPanel = new RegionPanel();
        locationListPanel = new LocationListPanel();
        nowPanelP = new NowPanel();
        airNowPanelP = new AirNowPanel();
        hourlyPanelP = new HourlyPanel();
        forecastPanelP = new ForecastPanel();
        lifestylePanelP = new LifestylePanel();

        locationListPanel.setVisible(false);
        locationListPanel.getClose().addActionListener((e)-> {
            //获取选中地区并刷新数据
            location=locationListPanel.getSelectResult();
            this.refreshData();
        });
        regionPanel.getList().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //弹出地区选择面板
                locationListPanel.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                regionPanel.getList().setBackground(MDColor.BLUE_LIGHT);
                regionPanel.getList().setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                regionPanel.getList().setContentAreaFilled(false);
            }
        });
        regionPanel.getRefresh().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshData();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                regionPanel.getRefresh().setBackground(MDColor.BLUE_LIGHT);
                regionPanel.getRefresh().setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                regionPanel.getRefresh().setContentAreaFilled(false);
            }
        });
        loadingData();

        GridBagConstraints leftGBC = new GridBagConstraints();
        leftGBC.fill = GridBagConstraints.BOTH;
        leftGBC.gridx = 1;
        leftGBC.gridy = 0;
        leftGBC.gridwidth = 1;
        leftGBC.insets = new Insets(20, 10, 0, 0);
        //左面板
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(new Rectangle(0, tHeight, wWidth / 2, wHeight));
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new GridBagLayout());
        leftGBC.gridheight = 1;
        leftPanel.add(regionPanel, leftGBC);
        leftGBC.gridy = 1;
        leftGBC.gridheight = 10;
        leftPanel.add(nowPanelP, leftGBC);
        leftGBC.gridy = 11;//间隔2格
        leftGBC.gridheight = 1;
        leftPanel.add(airNowPanelP, leftGBC);
        leftGBC.gridy = 13;
        leftGBC.gridheight = 5;
        leftPanel.add(hourlyPanelP, leftGBC);
        //右面板
        GridBagConstraints rightGBC = new GridBagConstraints();
        rightGBC.fill = GridBagConstraints.BOTH;
        rightGBC.gridx = 0;
        rightGBC.gridy = 0;
        rightGBC.gridwidth = 1;
        rightGBC.insets = new Insets(10, 10, 0, 10);
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new GridBagLayout());
        rightGBC.gridheight = 4;
        rightPanel.add(forecastPanelP, rightGBC);
        rightGBC.gridy = 4;
        rightGBC.gridheight = 15;
        rightPanel.add(lifestylePanelP, rightGBC);

        this.getLayeredPane().add(wIcon);
        this.getLayeredPane().add(wTitle);
        this.getLayeredPane().add(closeBt);
//        setBackgroundImg();
        locationListPanel.setBounds(0,0,wWidth/4,leftPanel.getHeight());
        this.getLayeredPane().add(locationListPanel, JLayeredPane.MODAL_LAYER);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(leftPanel, BorderLayout.WEST);
        this.getContentPane().add(rightPanel, BorderLayout.EAST);
    }

    private void setBackgroundImg() {
        HJLabel bg = new HJLabel(new ImageIcon(
                Icons.BACKGROUND.getImage().getScaledInstance(wWidth, wHeight, Image.SCALE_FAST)
        ));
        this.getLayeredPane().add(bg);
        bg.setBounds(new Rectangle(0, 0, wWidth, wHeight));
//        new Thread(() -> {
//            //加载bing壁纸
//            String uri = wallpaper.bingWallpaper();
//            try {
//                ImageIcon bing = new ImageIcon(new URL(uri));
//                bg.setIcon(new ImageIcon(
//                        bing.getImage().getScaledInstance(wWidth, wHeight, Image.SCALE_FAST)
//                ));
//                repaint();//刷新重绘
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    private JFrame getSelf() {
        return this;
    }

    private void loadingData() {
        Gson gson = new Gson();
        History history = historyAction.getHistory();
        if (history != null) {
            location = history.getCid();
            regionPanel.setData(history.getName());
            try {
                nowPanelP.setData((Now) gson.fromJson(history.getNow(), new TypeToken<Now>() {
                }.getType()));
                airNowPanelP.setData((Air_now_city) gson.fromJson(history.getAirNow(), new TypeToken<Air_now_city>() {
                }.getType()));
                forecastPanelP.setData((List<Daily_forecast>) gson.fromJson(history.getForecast(), new TypeToken<List<Daily_forecast>>() {
                }.getType()));
                hourlyPanelP.setData((List<Hourly>) gson.fromJson(history.getHourly(), new TypeToken<List<Hourly>>() {
                }.getType()));
                lifestylePanelP.setData((List<Lifestyle>) gson.fromJson(history.getLifestyle(), new TypeToken<List<Lifestyle>>() {
                }.getType()));
            } catch (Exception e) {
                //解析炸了
            }
        }
        refreshData();
    }

    private void refreshData() {
        HistoryAction historyAction = new HistoryAction();
        new Thread(() -> {
            if (location.equals("")) {
                //定位获取天气数据
                location = ipLocation.locationByIP();
            }
            HeWeather6Now now = weather.now(location);
            if (now != null) {
                regionPanel.setData(now.getBasic().getLocation());
                nowPanelP.setData(now);
                location = now.getBasic().getCid();
                List<Daily_forecast> daily_forecast = weather.forecast(location).getDaily_forecast();
                forecastPanelP.setData(daily_forecast);
                List<Hourly> hourly = weather.hourly(location).getHourly();
                hourlyPanelP.setData(hourly);
                List<Lifestyle> lifestyle = weather.lifestyle(location).getLifestyle();
                lifestylePanelP.setData(lifestyle);

                HeWeather6AirNow airNow = weather.airNow(location);
                //出现请求权限问题，区级不可用？
                Air_now_city air_now_city = airNow.getAir_now_city();
                airNowPanelP.setData(air_now_city);

                historyAction.updateCurrent(now, air_now_city, hourly, daily_forecast, lifestyle);
            }
            repaint();
        }).start();
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
