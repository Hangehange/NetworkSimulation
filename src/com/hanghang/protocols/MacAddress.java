package com.hanghang.protocols;

//MAC地址类的typedef
public class MacAddress{
    public String macAddr_str;
    public boolean isBroadcast = false;     //是否为广播帧？


    public MacAddress(String _macAddr){
        this.macAddr_str =_macAddr;
        if (_macAddr=="ff-ff-ff-ff-ff-ff"){
            isBroadcast = true;
        }
    }

    /**
     * todo 没用上,也没写完
     * 按格子读写mac地址
     * @param _macAddr
     * @param _readFlag     1读0写
     */
    public void writeMacAddress(String _macAddr,boolean _readFlag){
        for(int i = 0;i<_macAddr.length();i+=3)
            System.out.println(_macAddr.substring(i,i+2));
    }
}
