package app.retake.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlDateAdapter extends XmlAdapter<String,Date> {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    @Override
    public Date unmarshal(String xmlDate) throws Exception {
        return this.dateFormat.parse(xmlDate);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return this.dateFormat.format(date);
    }
}
