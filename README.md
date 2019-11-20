# firewall_illumio

The solution to the given challenge Illumio Coding Assignment 2019-­‐2020, PCE teams is implemented using a maven project in Java 8.

TESTING

FirewallTest.java - The JUnit class which tests all the common cases and the edge cases
1)BeforeClass - The static method to initalize the object of the class Firewall
2)A set of 7 testcases testAcceptPacket1-7 - These test cases check for common and edge cases with the 4 arguments of direction,protocol,port,and ipv4address
3)AfterClass- Gives the output of the csv file which was initalized in the BeforeClass
************************************************************************************************************************

IMPLEMENTATION


Firewall.java


1)Firewall(String filepath)- constructor to the class which takes the csv file as the input argument
Scenario 1 - the port is an integer
2)boolean accept_packet(String direction, String protocol, int port, String ipv4address) - This takes the arguments for a packet and returns whether to allow or reject
 Scenario 2 - the port is a range of integers
3)boolean accept_packet(String direction, String protocol, String port, String ipv4address)
4)boolean validateDirection(String direction) - check whether the Direction given is valid as per the requirements
5)boolean validateProtocol(String protocol)- check whether the protocol given is valid as per the requirements
6)boolean validatePort(String portValue) - check whether the port given is valid as per the requirements
7)boolean validateIPv4address(String IPv4address) - check whether the IPv4address given is valid as per the requirements
8)boolean rangeDiff(String range1, String range2, boolean type) - confirms whether the values in a given range are mentioned properly ie range1<range2 .The type attribute is the numeric types long or integer
9)boolean valueRange(String value, int max, int min) - confirms whether the value is within the min and max range


Constants.java

To maintain the validation rules, this interface stores those values
List<String> direction - list of acceptable direction values
List<String> port- list of acceptable port values
int portMax,portMin,ipv4Min,ipv4Max - Maximum and minimum values of port and ipv4address

************************************************************************************************************************

TEAM

I would like to join the platform team and explore the various functionalities in that field.
