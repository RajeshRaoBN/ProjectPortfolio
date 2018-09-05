import java.util.HashMap;

import static org.junit.Assert.*;

public class ParamConversionTest {
    ParamConversion pc;
    HashMap<String, String> hm;
    HashMap<String, String> hm2 = null;

    @org.junit.Before
    public void setUp() throws Exception {
        pc = new ParamConversion();
        hm = new HashMap<String, String>();
        hm.put("NAICS","181220 something    ");
        hm.put("Recompete", "yes");
        hm.put("Type of Set Aside", "something");
        hm.put("Period Expiring", "Next Fiscal Year");
        hm.put("Agency", "Any");
        hm.put("City", "Chicago");
        hm.put("PSC", "Any");
        hm.put("Zip Code", "60007");
        hm.put("State", "IL");
        hm.put("Type of Contract", "Any");
        hm.put("Contracting Office", "Any");
        hm.put("Funding Office", "Any");
        hm.put("Major Command", "Any");
        hm.put("Solicitation Procedures", "Any");
        hm.put("Offers Received", "Any");
        hm.put("Contract Value", "$25000 to $100000");
        hm.put("Business Size Selection", "Some Size");
        hm.put("Requirement Description", "Some Requirement");

        try {
            hm2 = pc.Process(hm);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void log() {
        pc.log("SomeMessage");
    }

    @org.junit.Test
    public void process() {
        assertEquals("yes", hm2.get("CanIncumbentRecompete"));
    }

    @org.junit.Test
    public void process1() {
        assertEquals("181220", hm2.get("NAICSCode"));
    }

    @org.junit.Test
    public void process2() {
        assertEquals("something", hm2.get("TypeofSetAside"));
    }

    @org.junit.Test
    public void process3() {
        assertEquals("", hm2.get("Country"));
    }

    @org.junit.Test
    public void process4() {
        assertEquals("11/01/2018", hm2.get("MinEstUltimateCompletionDate"));
    }

    @org.junit.Test
    public void process5() {
        assertEquals("10/31/2018", hm2.get("MaxEstUltimateCompletionDate"));
    }

    @org.junit.Test
    public void process6() {
        assertEquals("", hm2.get("Agency"));
    }

    @org.junit.Test
    public void process7() {
        assertEquals("Chicago", hm2.get("City"));
    }

    @org.junit.Test
    public void process8() {
        assertEquals("", hm2.get("PSC"));
    }

    @org.junit.Test
    public void process9() {
        assertEquals("60007", hm2.get("ZipCode"));
    }

    @org.junit.Test
    public void process10() {
        assertEquals("IL", hm2.get("State"));
    }

    @org.junit.Test
    public void process11() {
        assertEquals("", hm2.get("TypeofContract"));
    }

    @org.junit.Test
    public void process12() {
        assertEquals("", hm2.get("ContractingOffice"));
    }

    @org.junit.Test
    public void process13() {
        assertEquals("", hm2.get("FundingOffice"));
    }

    @org.junit.Test
    public void process14() {
        assertEquals("", hm2.get("MajorCommand"));
    }

    @org.junit.Test
    public void process15() {
        assertEquals("", hm2.get("SolicitationProcedures"));
    }

    @org.junit.Test
    public void process16() {
        assertEquals("", hm2.get("OffersReceived"));
    }

    @org.junit.Test
    public void process17() {
        assertEquals("25000", hm2.get("MinUltimateContractValue"));
    }

    @org.junit.Test
    public void process18() {
        assertEquals("100000", hm2.get("MaxUltimateContractValue"));
    }

    @org.junit.Test
    public void process19() {
        assertEquals("12/01/2012", hm2.get("startdate1"));
    }

    @org.junit.Test
    public void process20() {
        assertEquals("11/01/2017", hm2.get("enddate1"));
    }

    @org.junit.Test
    public void process21() {
        assertEquals("04/01/2013", hm2.get("startdate2"));
    }

    @org.junit.Test
    public void process22() {
        assertEquals("03/01/2018", hm2.get("enddate2"));
    }

    @org.junit.Test
    public void process23() {
        assertEquals("Some Size", hm2.get("Business Size Selection"));
    }

    @org.junit.Test
    public void process24() {
        assertEquals("Some Requirement", hm2.get("Requirement Description"));
    }

    @org.junit.Test
    public void process25() {
        hm.put("NAICS", "abcdefg something    ");
        try {
            hm2 = pc.Process(hm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("", hm2.get("Country"));
    }

    @org.junit.Test
    public void process26() {
        hm.put("Country", "US");
        try {
            hm2 = pc.Process(hm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("", hm2.get("Country"));
    }

    @org.junit.Test
    public void process27() {
        hm.put("NAICS", null);
        try {
            hm2 = pc.Process(hm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("", hm2.get("NAICSCode"));
    }

    @org.junit.Test
    public void process28() {
        hm.put("Country", null);
        try {
            hm2 = pc.Process(hm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("", hm2.get("Country"));
    }

    @org.junit.Test
    public void process29() {
        hm.put("NAICS", "abcdefg something    ");
        try {
            hm2 = pc.Process(hm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("", hm2.get("NAICSCode"));
    }
}