package com.hanghang;

import com.hanghang.equipments.Device;
import com.hanghang.equipments.EndDevice;
import com.hanghang.equipments.Router;
import com.hanghang.equipments.Switch;
import com.hanghang.protocols.DataPacket;
import com.hanghang.protocols.IPAddress;
import com.hanghang.protocols.MacAddress;

import java.util.ArrayList;

/**
 * 定义一个拓扑结构并按照一定参数初始化
 */
public class TopoDefine {
    //todo:大规模测试时候需要把设备写到List里面
    ArrayList<Router> routerList = new ArrayList<>();
    ArrayList<Switch> switchList = new ArrayList<>();
    ArrayList<EndDevice>  pcList = new ArrayList<>();
    //todo 但是现在我们先偷个懒
    EndDevice pc1 = new EndDevice(1,new MacAddress("00-00-01"));
    EndDevice pc2 = new EndDevice(1,new MacAddress("00-00-02"));
    Switch    sw1 = new Switch   (4,new MacAddress("00-ff-01"));
    /**
     * 定义网络中设备
     */
    public TopoDefine(){

    }

    /**
     * 测试情景1：两个pc用网线直接连起来
     */
    public void pc2pc(){
        pc1.setIpAddress(new IPAddress("1.1.1.1"));
        pc2.setIpAddress(new IPAddress("1.1.1.2"));
        System.out.println("测试情景1：两个pc用网线直接连起来");
        connectDevices(pc1,pc2);
        Thread th1 = new Thread(pc1);   th1.setPriority(8); th1.start();
        Thread th2 = new Thread(pc2);   th2.setPriority(8); th2.start();
        try {
            Thread.sleep(500);  //等两个设备都开机了再说
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        pc1.sendPacket(new DataPacket(pc1,"\'Hello pc2!\'"));
        //pc2.sendPacket(new DataPacket(pc2,"\'Hello pc1!\'"));
    }

    /**
     * 测试情景2：两个pc接到同一个交换机上
     */
    public void pc2sw2pc()
    {
        pc1.setIpAddress(new IPAddress("1.1.1.1"));
        pc2.setIpAddress(new IPAddress("1.1.1.2"));
        connectDevices(pc1,sw1);
        connectDevices(pc2,sw1);
        Thread th1 = new Thread(pc1);   th1.setPriority(8); th1.start();
        Thread th2 = new Thread(pc2);   th2.setPriority(8); th2.start();
        Thread th3 = new Thread(sw1);   th3.setPriority(8); th3.start();
        try {
            Thread.sleep(500);  //等两个设备都开机了再说
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        DataPacket pkt1 = new DataPacket(pc1,
                new IPAddress("192.168.1.3"),
                new MacAddress("00-00-02"),
                80,
                "tcp",
                "Hello!pc2!"
        );
        pc1.sendPacket(pkt1);
        //pc2.sendPacket(new DataPacket(pc2,"\'Hello pc1!\'"));
    }


    public void options(){

    }

    /**
     * 把两个设备连起来的静态方法，可以从别处调用
     * @param dev1
     * @param dev2
     */
    public static void connectDevices(Device dev1,Device dev2){
        dev1.addLink(dev2);
        dev2.addLink(dev1);
    }
}
