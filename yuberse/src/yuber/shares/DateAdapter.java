package yuber.shares;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.*;

public class DateAdapter extends XmlAdapter<String,Date>{
	private SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public String marshal(Date v) throws Exception{
		return dateFormate.format(v);
	}
	
	@Override
	public Date unmarshal(String v) throws Exception{
		return dateFormate.parse(v);
	}
}
