package pb2_carsDealer.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class XmlBigDecimalAdapter extends XmlAdapter<String,BigDecimal> {

    @Override
    public BigDecimal unmarshal(String xmlPrice) throws Exception {
        return new BigDecimal(xmlPrice);
    }

    @Override
    public String marshal(BigDecimal price) throws Exception {
        return String.format("%.2f",price);
    }
}
