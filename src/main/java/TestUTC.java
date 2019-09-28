import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class TestUTC {


    static public void main (String[] args){
        String pattern = "MM-dd-yyyy hh:mm:ss a";
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.now(), TimeZone.getDefault().toZoneId());
        System.out.println("LOCAL TIME: " + zdt.format(DateTimeFormatter.ofPattern(pattern)));
        Timestamp zdtTimestamp = Timestamp.from(zdt.toInstant());
        System.out.println("LOCAL TIME ISO-8601: " + zdtTimestamp);


        ZonedDateTime zdtUTC = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
        System.out.println("UTC TIME: " + zdtUTC.format(DateTimeFormatter.ofPattern(pattern)));

        LocalDateTime utcLDT = zdtUTC.toLocalDateTime();
        System.out.println("UTC TIME ISO-8601 LocalDateTime: " + utcLDT);

        Timestamp utcTimestamp = Timestamp.valueOf(zdtUTC.toLocalDateTime());
        System.out.println("UTC TIME ISO-8601 SQL Timestamp: " + utcTimestamp);

    }
}
