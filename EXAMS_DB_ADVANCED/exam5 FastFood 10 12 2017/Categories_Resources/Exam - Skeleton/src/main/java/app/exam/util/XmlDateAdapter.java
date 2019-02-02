package app.exam.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlDateAdapter extends XmlAdapter<String,Date> {
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    @Override
    public Date unmarshal(String xmlDate) throws Exception {
        return this.dateFormat.parse(xmlDate);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return this.dateFormat.format(date);
    }
}
