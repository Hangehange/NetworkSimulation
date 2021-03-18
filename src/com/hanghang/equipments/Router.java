package com.hanghang.equipments;

import com.hanghang.protocols.DataPacket;
import com.hanghang.protocols.MacAddress;

import java.util.ArrayList;

/**
 * 定义一个路由器
 */
public class Router extends ForwardDevice {
    ArrayList Arp_List;

    public Router(int _numOfPorts, MacAddress _localMac) {
        super(_numOfPorts, _localMac);
    }

    /**
     * 路由器如果有dhcp需在addLink的时候给分配IP地址
     * @param device
     */
    @Override
    public boolean addLink(Device device) {
        return super.addLink(device);
    }

    @Override
    public boolean forward(DataPacket Data) {
        return super.forward(Data);
    }
}
