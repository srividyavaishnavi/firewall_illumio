

import com.opencsv.CSVReader;

import javax.naming.InsufficientResourcesException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Firewall {
    public HashMap<String[], Boolean> packets = new HashMap<String[], Boolean>();

    public Firewall(String filePath) throws FileNotFoundException, InputMismatchException, InsufficientResourcesException {
        //reading the csv file type
        FileReader fileReader = new FileReader(filePath);

        //using CSVReader class for the input file .csv
        CSVReader csvReader = new CSVReader(fileReader);

        //output of each row in the csv whether it is accepted or rejected is stored in the list of packets


        //iterate through the given file
        while (true) {
            try {
                //store records per row
                String[] setofRecord;
                //confirm the last record of the file
                if (!((setofRecord = csvReader.readNext()) != null))
                    break;
                //in case of insufficient records for the accept_packet
                if (setofRecord.length < 4) {
                    throw new InsufficientResourcesException();
                }

                // adding each row in the list
                packets.put(setofRecord, accept_packet(setofRecord[0], setofRecord[1], setofRecord[2], setofRecord[3]));


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    //Scenario 1 - where the port is an integer value
    public boolean accept_packet(String direction, String protocol, int port, String ipv4address) {
        /*
        validateDirection - function confirms whether the direction is either inbound or outbound
        validateProtocol - function confirms whether the protocol is either tcp or udp
        Port - an integer value - checking whether the value is between the range for port [1,65535]
        validateIPv4address- function confirms whether the IPv4address is in the given range
        *
        */
        return validateDirection(direction) && validateProtocol(protocol) && port >= Constants.portMin && port <= Constants.portMax && validateIPv4address(ipv4address);
    }

    //Scenario 2 - where the port is a range between two integers
    public boolean accept_packet(String direction, String protocol, String port, String ipv4address) {
        /*
        validateDirection - function confirms whether the direction is either inbound or outbound
        validateProtocol - function confirms whether the protocol is either tcp or udp
        validatePort - function fetches the two ranges and checks whether it is in the given port range
        validateIPv4address- function checking whether the IPv4address is in the given range
        *
        */
        return validateDirection(direction) && validateProtocol(protocol) && validatePort(port) && validateIPv4address(ipv4address);
    }

    //validateDirection function checks whether the Constants.directions has the mentioned direction
    public boolean validateDirection(String direction) {
        return Constants.directions.indexOf(direction) != -1;
    }

    //validateDirection function checks whether the Constants.protocols has the mentioned protocol
    public boolean validateProtocol(String protocol) {

        return Constants.protocols.indexOf(protocol) != -1;
    }

    //validate a range of port
    public boolean validatePort(String portValue) {
        //extract the two range values in an array
        if (portValue.indexOf("-") != -1) {
            String[] range = portValue.split("-");
            //check whether the check whether both the values are in the mentioned port range and the first range is less than the second range
            if (valueRange(range[1], Constants.portMax, Constants.portMin) && valueRange(range[0], Constants.portMax, Constants.portMin))
                return rangeDiff(range[1], range[0], false);
            return false;
        }
        return valueRange(portValue, Constants.portMax, Constants.portMin);

    }

    //validate a range of IPv4address
    public boolean validateIPv4address(String IPv4address) {
        //Scenario 3- the IPv4address is a set of ranges
        if (IPv4address.indexOf('-') != -1) {
            //extract both the ranges in an array
            String[] range = IPv4address.split("-");
            //check whether each range is in the mentioned ipv4 address range and the first range is less than the second range
            if (valueRange(range[1], Constants.ipv4Max, Constants.ipv4Min) && valueRange(range[0], Constants.ipv4Max, Constants.ipv4Min))
                return rangeDiff(range[1], range[0], true);
            return false;
        }
        //Scenario 4 - the IPv4address is a single input
        return valueRange(IPv4address, Constants.ipv4Max, Constants.ipv4Min);

    }

    //check whether in the input of range the first value is less than the second value
    public boolean rangeDiff(String range1, String range2, boolean type) {
        //For ipv4address
        if (type) {
            return Long.parseLong(range1.replaceAll("\\.", "")) > Long.parseLong(range2.replaceAll("\\.", ""));
        }
        //for port
        return Integer.parseInt(range1) > Integer.parseInt(range2);

    }

    //check whether the integer value is within the max and min range
    public boolean valueRange(String value, int max, int min) {
        //for ipv4address
        if (value.indexOf(".") != -1) {
            String[] ipv4 = value.split("\\.");
            for (int i = 0; i < ipv4.length; i++) {
                int x = Integer.parseInt(ipv4[i]);

                if (!(x >= min && x <= max))
                    return false;
            }
            return true;
        }
        //for port
        return Integer.parseInt(value) >= min && Integer.parseInt(value) <= max;
    }

}