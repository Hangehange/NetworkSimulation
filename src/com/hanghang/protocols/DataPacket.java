package com.hanghang.protocols;

import com.hanghang.equipments.Device;

public class DataPacket {
    TCPData         tcpData = new TCPData();
    IpData          ipdata  = new IpData();
    EthernetData    macData = new EthernetData();
    String          payload = "";

    /**
     *  数据报的规定格式
     * @param Sip       source  IP
     * @param Tip       target  IP
     * @param Smac      source  mac
     * @param Tmac      target  mac
     * @param portNum   Port number
     * @param proName   Protocol name
     * @param info      payload
     */
    public DataPacket(IPAddress Sip,IPAddress Tip,
                      MacAddress Smac,MacAddress Tmac,
                      int portNum,String proName,
                      String info){
        this.ipdata.ip_target=Tip;
        this.ipdata.ip_source=Sip;
        this.macData.targetMac=Tmac;
        this.macData.sourceMac=Smac;
        this.tcpData.portNumber=portNum;
        this.tcpData.protocolName=proName;
        this.payload=info;

    }

    /**
     * 构造广播包
     * @param info
     */
    public DataPacket(Device sDev,String info){
        this.ipdata.ip_target=new IPAddress("255.255.255.255");
        this.ipdata.ip_source=sDev.getIPAddress();
        this.macData.targetMac=new MacAddress("ff-ff-ff");
        this.macData.sourceMac=sDev.getMacAddress();
        //this.tcpData.portNumber=portNum;
        //this.tcpData.protocolName=proName;
        this.payload=info;
    }

    /**
     * 啥也没有的构造方法
     */
    public DataPacket(){

    }
    public String showPacket() {
        String message="\n\t\t++++++++++++++++++++++++++++++++++++";
        message += "\n\t\tsource mac \t= "+this.macData.sourceMac.macAddr_str;
        message += "\n\t\ttarget mac \t= "+this.macData.targetMac.macAddr_str;
        message += "\n\t\tsource ip \t= "+this.ipdata.ip_source.ipAddress;
        message += "\n\t\ttarget ip \t= "+this.ipdata.ip_target.ipAddress;
        message += "\n\t\tpayload \t= "+this.payload;
        message +=     "\n\t\t------------------------------------";



        return message;
    }
}
