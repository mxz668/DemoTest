package com.dripping.EasyRule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpTest {
    public static List<String> getIps(String ipString){
        String regEx="((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        List<String> ips = new ArrayList<String>();
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ipString);
        while (m.find()) {
            String result = m.group();
            ips.add(result);
        }
        return ips;
    }


    public static void main(String[] args) {
        String ipString="!254.254.254.254 127.0.0.1localhost192.168.2.1localhost";
        System.out.println(getIps(ipString));
    }
}
