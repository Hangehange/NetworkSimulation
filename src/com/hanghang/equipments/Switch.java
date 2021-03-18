package com.hanghang.equipments;

import com.hanghang.protocols.DataPacket;
import com.hanghang.protocols.MacAddress;

/**
 * Switch.java
 * 定义一个交换机
 */
public class Switch extends ForwardDevice {
    //

    public Switch(int _numOfPorts, MacAddress _localMac) {
        super(_numOfPorts, _localMac);
    }

    @Override
    public boolean addLink(Device device) {
        return super.addLink(device);
    }

    @Override
    public boolean sendPacket(DataPacket pkt_Tx) {
        return super.sendPacket(pkt_Tx);
    }

    @Override
    public boolean receivePacket() {
        return super.receivePacket();
    }

    @Override
    public boolean forward(Package Data) {
        return super.forward(Data);
    }

    @Override
    public void handlePacket() {
        super.handlePacket();
    }

    @Override
    public void run() {
        super.run();
    }
}
