package com.hanghang.equipments;

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


}
