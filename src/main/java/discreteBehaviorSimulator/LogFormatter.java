package discreteBehaviorSimulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Define the log to show to users
 * @author Flavien Vernier
 */
public class LogFormatter  extends Formatter {
	
	/**
	 * Constructor
	 * @param log record 
	 * @return string of log record 
	 */
	public String format(LogRecord rec) {
		StringBuffer buf = new StringBuffer();
		
		buf.append(calcDate(rec.getMillis()));
		buf.append(": ");
		buf.append(rec.getLevel());
		buf.append(System.getProperty("line.separator"));
		buf.append(formatMessage(rec));
		buf.append(System.getProperty("line.separator"));
		
		return buf.toString();
	}
	
	/**
	 * Convert a time in millisecs to a date
	 * @param millisecs 
	 * @return date
	 */
	private String calcDate(long millisecs) {
	    SimpleDateFormat date_format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SS");
	    Date resultdate = new Date(millisecs);
	    return date_format.format(resultdate);
	  }

	/**
	   *@return head
	   */
	  public String getHead(Handler h) {
		  return "";
	  }
	  
	  /**
	   *@return tail
	   */
	  public String getTail(Handler h) {
	    return "";
	  }

}
