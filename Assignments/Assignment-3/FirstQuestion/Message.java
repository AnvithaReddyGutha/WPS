import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class DisplayMessage extends SimpleTagSupport
{
	public void doTag() throws JspException, IOException
	{
		super.doTag();
		JspWriter out = getJspContext().getOut();
		out.print("Hello!");
	}

}