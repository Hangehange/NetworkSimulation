package com.hanghang.equipments;
import com.hanghang.protocols.*;

import java.util.ArrayList;

/***
 * ForwardDevice.java
 * 定义一般性的数据层转发设备
 */
public class ForwardDevice extends Device {

    DataPacket forwardCache;                    //转发数据包缓存

    /**
     * 初始化构造一个网络设备,包括但不限于
     *
     * @param _numOfPorts :  拥有的接口个数
     * @param _localMac   :    本机的mac地址
     */
    public ForwardDevice(int _numOfPorts, MacAddress _localMac) {
        super(_numOfPorts, _localMac);
    }

    @Override
    public boolean addLink(Device device) {
        return super.addLink(device);
    }

    @Override
    public boolean sendPacket(DataPacket pkt_Tx) {
        return false;
    }

    @Override
    public boolean receivePacket() {
        boolean flag = false;
        //没设备收个屁
        if(this.portList.size()==1)
            return false;
        //轮询自己各个端口有没有要发packet的
        for(int i = 0;i<=portList.size();i++){
            //第i个网口上有要发数据的flag了
            if(this.portList.get(i).isToSend) {
                //放到自己的cache里
                this.packetCache_R = portList.get(i).packetCache_T;
                //对于不同转发设备只需要重写handlePacket方法即可
                handlePacket();
                flag = true;
            }
        }
        return flag;
    }

    /**
     *  设备实现转发逻辑
     * @param Data :要转发的数据包
     */
    public boolean forward(Package Data){
        boolean flag = false;

        return flag;
    }

    /**
     * 对消息的处理：一般性处理
     * （到交换机和路由器里再具体定义）
     */
    @Override
    public void handlePacket() {
        super.handlePacket();
    }

    @Override
    public void run() {
        super.run();
    }
}
