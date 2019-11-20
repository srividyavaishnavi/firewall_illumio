package com.illumio;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static List<String> directions = Arrays.asList("inbound", "outbound");
    public static List<String> protocols = Arrays.asList("tcp", "udp");

    public static int portMin = 1;
    public static int portMax = 65535;
    public static int ipv4Min = 0;
    public static int ipv4Max = 255;
}
