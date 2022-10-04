package libs;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Now {
    private String time;
    private String date;

    public String createNow() {
        time = new SimpleDateFormat("HH:mm:ss").format(new Date().getTime());
        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date + " " + time;
    }
}
