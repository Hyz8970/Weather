package com.hyz.weather.view;

import com.hyz.weather.action.Wallpaper;
import com.hyz.weather.reSwing.MDColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class BGJPanel extends JPanel {
    private JFrame jFrame;
    private Point loc = null;
    private Point tmp = null;
    private boolean isDragged = false;
    private Wallpaper wallpaper=new Wallpaper();
    private Image bg;

    public BGJPanel(JFrame jFrame) {
        super();
        this.jFrame=jFrame;
        this.setOpaque(true);
        setDragable();
        this.setBackground(MDColor.blueDark);
        //默认背景
        bg = new ImageIcon(BGJPanel.class.getClassLoader().getResource("./img/bg.jpg")).getImage();
//        this.repaint();

        new Thread(()->{
            //加载bing壁纸
            String uri = wallpaper.bingWallpaper();
            try {
                bg = new ImageIcon(new URL(uri)).getImage();
//                this.repaint();//刷新重绘
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        System.out.println("BG loading");
        g.drawImage(bg,0,0,jFrame.getWidth(),jFrame.getHeight(),this);
    }
    /**
     * 拖拽
     * */
    private void setDragable() {
        this.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        isDragged = false;
                        jFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    }
                    public void mousePressed(MouseEvent e) {
                        tmp = new Point(e.getX(), e.getY());
                        isDragged = true;
                        jFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    }
                });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDragged) {
                    loc = new Point(jFrame.getLocation().x + e.getX() - tmp.x,
                            jFrame.getLocation().y + e.getY() - tmp.y);
                    jFrame.setLocation(loc);
                }
            }
        });
    }
}
