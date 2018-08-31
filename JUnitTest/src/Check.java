import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Check {
    /*public static void main(String[] args) {
        String download_location= System.getenv("download_location");
        System.out.println(download_location);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
//        System.out.println(cal.toString());
        Calendar prevYear = cal;
        prevYear.add(Calendar.MONTH, -69); // previous 5 yrs and 9 months
        String startdate1 = dateFormat.format(prevYear.getTime());
        System.out.println(startdate1);
    }*/

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.toString());
        java.util.Date date= new Date();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        System.out.println("Calendar month: " + month);
        if(month>8) {//sepetmber is month 8
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+2);
            System.out.println("Calendar year: " + cal.get(Calendar.YEAR));
        }
        cal.set(Calendar.MONTH, 9);
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime()));
    }
}
