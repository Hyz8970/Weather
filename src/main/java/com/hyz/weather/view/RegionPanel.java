package com.hyz.weather.view;

import com.hyz.weather.action.RegionAction;
import com.hyz.weather.entity.Region;

import javax.swing.*;
import java.util.List;

public class RegionPanel extends JPanel {
    private RegionAction regionAction=new RegionAction();
    public RegionPanel(){
        //初始化省级数据
        List<Region> regionList = regionAction.provinceList();

    }
}
