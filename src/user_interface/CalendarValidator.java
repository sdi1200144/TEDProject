package user_interface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("calendarValidator")
public class CalendarValidator implements Validator 
{
	

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
	{

		try
		{

			UIInput calendar1Component = (UIInput) component.getAttributes().get("calendar1Component");
			String calendar1 = (String) calendar1Component.getValue();
			String calendar2 = (String) value;

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date i_checkInDate = format.parse(calendar1);
			Date i_checkOutDate = format.parse(calendar2);

			Date todayDate = new Date();

			boolean validCheckIn = i_checkInDate.compareTo(todayDate)>=0;
			boolean validCheckOut = i_checkOutDate.compareTo(i_checkInDate)>0;

			if (!validCheckIn || !validCheckOut) 
			{
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert check in date after today and check out date after check in date", null));
			}
			
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}