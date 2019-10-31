package com.hyz.weather.reSwing;

import javax.swing.*;
import java.awt.*;

public class HJLabel extends JLabel {
    //文本内容支持html代码
    public HJLabel(){
        super();
        this.setFont(Fonts.MSYH_PLAIN_24);
//        this.setForeground(Color.white);
    }
    public HJLabel(Icon img){
        super(img);
    }
    public HJLabel(String text){
        super(text);
        this.setFont(Fonts.MSYH_PLAIN_24);
//        this.setForeground(Color.white);
    }
    public HJLabel(String text,int align){
        super(text,align);
        this.setFont(Fonts.MSYH_PLAIN_24);
//        this.setForeground(Color.white);
    }
}
