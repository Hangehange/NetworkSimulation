package com.hanghang;

import com.hanghang.equipments.EndDevice;
import com.hanghang.protocols.DataPacket;
import com.hanghang.protocols.MacAddress;

public class Main {
    private static void test(){
        //Switch sw1 = new Switch(4,new MacAddress("00-f0-01"));

    }

    public static void main(String[] args) {
        TopoDefine myTopo = new TopoDefine();
        //myTopo.pc2pc();
        myTopo.pc2sw2pc();
    }
}
