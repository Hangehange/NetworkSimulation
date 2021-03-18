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
        boolean flag = false;
        isToSend = true;
        packetCache_T = pkt_Tx;
        System.out.println(getHeadString()+":发送消息"+packetCache_T.showPacket());
        int TTL = 2;  //发送后过TTL时间修改isToSend
        try {
            Thread.sleep(TTL);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        isToSend = false;
        return flag;
    }

    @Override
    public boolean receivePacket() {
        return super.receivePacket();
    }

    @Override
    public boolean forward(DataPacket Data) {
        packetCache_T = packetCache_R;
        return super.forward(Data);
    }

    @Override
    public void handlePacket() {
        boolean foundFlag = false;
        super.handlePacket();
        //挨着找自己mac表中有没有和包中target mac匹配的设备
        for(int i=0;i<portList.size();i++){
            if(packetCache_R.getMacData().targetMac.macAddr_str
                    ==portList.get(i).macAddress.macAddr_str){
                //找到了就实现转发逻辑
                System.out.println(getHeadString()+"向端口<"+i+">转发");
                forward(packetCache_R);
                foundFlag = true;
                break;
            }
        }
        //没找到，就交给ip层
        // todo 现在还没搞好ip层怎么向上传
        if(!foundFlag)
            System.out.println(getHeadString()+"找不到mac地址为"+
                packetCache_R.getMacData().targetMac.macAddr_str+"的设备");

    }

    @Override
    public void run() {
        super.run();
    }
}
