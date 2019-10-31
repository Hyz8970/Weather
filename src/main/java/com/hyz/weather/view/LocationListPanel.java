package com.hyz.weather.view;

import com.hyz.weather.action.HistoryAction;
import com.hyz.weather.action.RegionAction;
import com.hyz.weather.entity.History;
import com.hyz.weather.entity.Region;
import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJLabel;
import com.hyz.weather.reSwing.HJPanel;
import com.hyz.weather.reSwing.MDColor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LocationListPanel extends HJPanel {
    private RegionAction regionAction;
    private HistoryAction historyAction;
    private HJLabel historyTag, regionTag;

    public LocationListPanel() {
        super();
        this.setOpaque(true);
        regionAction = new RegionAction();
        historyAction = new HistoryAction();
        historyTag = new HJLabel("历史");
        regionTag = new HJLabel("地区");
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.add(historyTag, constraints);
        HJPanel historyListPanel = new HJPanel();
        List<History> historyList = historyAction.getHistoryList();
        int historySize = historyList.size();
        for (int i = 0; i < historySize; i++) {
            History history = historyList.get(i);
            HJLabel now = new HJLabel(history.getName());
            now.setOpaque(true);
            now.setFont(Fonts.MSYH_PLAIN_14);
            if (history.getIsUse() == 1) {
                //当前
                now.setBackground(MDColor.BLUE_LIGHT);
            }
            historyListPanel.add(now);
        }
        this.add(regionTag, constraints);
        HJPanel regionListPanel = new HJPanel();
        List<Region> regionList = regionAction.provinceList();
        int regionSize = regionList.size();
        String[] regionArray = new String[regionSize];
        for (int i = 0; i < regionSize; i++) {
            regionArray[i] = regionList.get(i).getName();
        }
        JList<String> regionJList = new JList<>(regionArray);
        JScrollPane scrollPane = new JScrollPane(regionJList);
        regionListPanel.add(scrollPane);
    }
}
