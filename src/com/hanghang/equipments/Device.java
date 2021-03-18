package com.hanghang.equipments;

import com.hanghang.protocols.DataPacket;
import com.hanghang.protocols.IPAddress;
import com.hanghang.protocols.MacAddress;
/**
 * 定义一般意义的网络设备，包括终端设备和转发设备
 * 可以做的事情有
 *  1.在网卡槽插网线接入设备
 *  2.todo 拔下去一个设备
 *  3.接收来自网卡上设备的packet
 *  4.向网卡上发送packet
 */
import java.util.ArrayList;

public abstract class Device implements Runnable {
    MacAddress              macAddress;          //本设备的mac地址
    IPAddress               ipAddress;           //本设备的ip地址
    public int              NumOfPorts;          //拥有端口的个数
    public ArrayList<Device>portList= new ArrayList<Device>(NumOfPorts);
    //拥有的端口上设备列表，相当于网卡槽

    //发送的缓存，接收方就是通过读取这个位置来获取Packet
    public DataPacket packetCache_T = new DataPacket();
    //接受的缓存，接收到消息就把Packet缓存到这里来
    public DataPacket packetCache_R = new DataPacket();
    public boolean isToSend = false;//要发数据的标志
    /**
     * 初始化构造一个网络设备,包括但不限于
     * @param _numOfPorts   :拥有的接口个数
     * @param _localMac     :本机的mac地址
     */
    public Device(int _numOfPorts, MacAddress _localMac){
        this.macAddress = _localMac;
        this.NumOfPorts = _numOfPorts;
        //初始化列表
        //先把自己的mac地址写到portList的第0个位置上
        portList.add(this);
    }
    public MacAddress getMacAddress(){
        return this.macAddress;
    }
    public IPAddress getIPAddress(){
        return this.ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 给设备插上一根网线，需要传递的参数
     * @param device        :插线设备(把谁插上了)
     */
    public boolean addLink(Device device){
        boolean flag = false;
        if(portList.size()<=NumOfPorts) {
            for (int i = 0; i < portList.size(); i++) {
                //判断设备上是否已经存在目的设备
                if (portList.get(i).macAddress.macAddr_str
                        == device.macAddress.macAddr_str) {
                    System.out.println("设备" + device.macAddress.macAddr_str
                            + "已经在" + this.macAddress.macAddr_str + "上面了");
                    break;
                }
            }
            portList.add(device);
            System.out.println(getHeadString()+"和设备"+device.macAddress.macAddr_str+"连接成功");
            flag = true;
        }
        return flag;

    }

    public abstract boolean sendPacket(DataPacket pkt_Tx);

    public abstract boolean receivePacket();

    /**
     * 消息处理的一般方法，默认打印出来packet
     */
    public void handlePacket() {
        if(packetCache_R.showPacket()!=null){
            System.out.println(getHeadString()+":收到消息"+packetCache_R.showPacket());
        }
    }

    @Override
    public void run() {
        System.out.println(getHeadString()+":已开机");
        while(true) {
            receivePacket();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getHeadString(){
        return "["+this.macAddress.macAddr_str+"]";
    }
}
