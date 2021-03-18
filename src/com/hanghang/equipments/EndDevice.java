package com.hanghang.equipments;
import com.hanghang.protocols.*;

/**
 * 终端设备
 * 可以像pc一样做各种操作
 */
public class EndDevice extends Device{

    //设定默认网关
    public IPAddress myGateway = new IPAddress("192.168.1.1");

    /**
     * 初始化构造一个网络设备,包括但不限于
     *
     * @param _numOfPorts :  拥有的接口个数
     * @param _localMac   :    本机的mac地址
     */

    public EndDevice(int _numOfPorts, MacAddress _localMac) {
        super(_numOfPorts, _localMac);
        this.NumOfPorts=2;

    }

    @Override
    public boolean addLink(Device device) {
        return super.addLink(device);
    }

    /**todo
     * 连通性测试：要分层次，类似于一个ping指令
     * @param dev   目标设备
     */
    public boolean connectTest(Device dev){
        boolean flag = false;
        //mac层检查
        if(this==dev){
            flag = true;
        }
        else if(this.portList.get(1).macAddress.macAddr_str
                == dev.macAddress.macAddr_str){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean sendPacket(DataPacket pkt_Tx) {
        boolean flag = false;
        isToSend = true;
        packetCache_T = pkt_Tx;
        System.out.println(getHeadString()+":发送消息"+packetCache_T.showPacket());
        int TTL = 1;  //发送后过TTL时间修改isToSend
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
        boolean flag = false;
        //没设备
        if(this.portList.size()==1)
            return flag;
        //自己唯一的网口上有要发数据的flag了
        if(this.portList.get(1).isToSend) {
            //放到自己的cache里
            this.packetCache_R = portList.get(1).packetCache_T;
            //判断是不是发给自己的
            if(packetCache_R.getMacData().targetMac.macAddr_str
                    ==this.macAddress.macAddr_str){
                //针对不同设备的处理逻辑
                handlePacket();
            }
            flag = true;
        }
        return flag;
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
