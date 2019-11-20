import com.illumio.Firewall;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FirewallTest {

    static Firewall fw = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        fw = new Firewall("packetrules.csv");

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        //check whether the csv file sent as input
        fw.packets.entrySet().forEach(csv_entry -> {
            System.out.println(csv_entry.getValue());
        });
    }

    @Test
    public void testAcceptPacket1() {
        try {

            boolean acceptPacket = fw.accept_packet("inbound", "tcp", 26000, "192.168.17.11");
            assertEquals("true", true, acceptPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAcceptPacket2() {
        try {

            boolean acceptPacket = fw.accept_packet("outbound", "udp", 50000, "192.167.10.11");
            assertEquals("true", true, acceptPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAcceptPacket3() {
        try {

            boolean acceptPacket = fw.accept_packet("inbound", "tcp", "10000-20000", "192.167.15.11");
            assertEquals("true", true, acceptPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAcceptPacket4() {
        try {

            boolean acceptPacket = fw.accept_packet("outbound", "tcp", 10000, "192.168.11.11-192.168.11.18");
            assertEquals("true", true, acceptPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAcceptPacket5() {
        try {

            boolean acceptPacket = fw.accept_packet("outbound", "tcp", 80000, "192.168.10.11");
            assertEquals("true", false, acceptPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAcceptPacket6() {
        try {

            boolean acceptPacket = fw.accept_packet("outbound", "tcp", 20000, "292.188.19.18");
            assertEquals("true", false, acceptPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAcceptPacket7() {
        try {

            boolean acceptPacket = fw.accept_packet("outbound", "tcp", "10000-60000", "182.138.11.14-242.188.19.18");
            assertEquals("true", true, acceptPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}