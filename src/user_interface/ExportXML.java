package user_interface;

import java.io.File;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import database.BookingDAO;
import database.HostingRoomDAO;
import entities.Booking;
import entities.Hostingroom;


@ManagedBean(name = "exportxml")
@SessionScoped
public class ExportXML {
	
	public void export() {
		JAXBContext jaxbContext;
		
		BookingDAO bookDB = new BookingDAO();
		List listBookings = bookDB.getAllBookings();
		BookingsExport bookings = new BookingsExport();
		
		HostingRoomDAO roomDB = new HostingRoomDAO();
		List listRooms = roomDB.getAllHostingRooms();
		HostingRoomsExport rooms = new HostingRoomsExport();

		if (listRooms != null) {
			

			for (Object o : listRooms) {
				HostingRoomExport rb = new HostingRoomExport();

				rb.setId(((Hostingroom) o).getId());
				
				
				rb.setFrontPhoto(((Hostingroom) o).getFrontPhoto());
				rb.setCostPerNight(((Hostingroom) o).getCostPerNight());

				rb.setReviewsNum(((Hostingroom) o).getReviewsNum());
				rb.setAverageRatingStars(((Hostingroom) o).getAverageRatingStars());
				rb.setBedsNumber(((Hostingroom) o).getBedsNumber());
				rb.setBathroomsNumber(((Hostingroom) o).getBathroomsNumber());
				rb.setRoomType(((Hostingroom) o).getRoomType());
				rb.setBedroomsNumber(((Hostingroom) o).getBedroomsNumber());
				rb.setLivingRoomNumber(((Hostingroom) o).getLivingRoomNumber());
				rb.setArea(((Hostingroom) o).getArea());
				rb.setDescription(((Hostingroom) o).getDescription());
				rb.setSmokingRule(((Hostingroom) o).getSmokingRule());
				rb.setPetsRule(((Hostingroom) o).getPetsRule());
				rb.setEventsRule(((Hostingroom) o).getEventsRule());
				rb.setMinHostingDays(((Hostingroom) o).getMinHostingDays());
				rb.setLatitude(((Hostingroom) o).getLatitude());
				rb.setLongitude(((Hostingroom) o).getLongitude());
				rb.setAddress(((Hostingroom) o).getAddress());
				rb.setNeighborhood(((Hostingroom) o).getNeighborhood());
				rb.setPublicTransit(((Hostingroom) o).getPublicTransit());
				rb.setMaxGuestsNumber(((Hostingroom) o).getMaxGuestsNumber());
				rb.setMinCost(((Hostingroom) o).getMinCost());
				rb.setExtraPeopleCost(((Hostingroom) o).getExtraPeopleCost());
				rb.setWifiAmenity(((Hostingroom) o).getWifiAmenity());
				rb.setCoolingAmenity(((Hostingroom) o).getCoolingAmenity());
				rb.setHeatingAmenity(((Hostingroom) o).getHeatingAmenity());
				rb.setKitchenAmenity(((Hostingroom) o).getKitchenAmenity());
				rb.setTVAmenity(((Hostingroom) o).getTVAmenity());
				rb.setParkingAmenity(((Hostingroom) o).getParkingAmenity());
				rb.setElevatorAmenity(((Hostingroom) o).getElevatorAmenity());
				rb.setHostId(((Hostingroom) o).getUser().getId());
				
				rooms.add(rb);
			}
		}
		
		if (listBookings != null) {
			

			for (Object o : listBookings) {
				BookingExport rb = new BookingExport();
				
				rb.setId(((Booking) o).getId());
				rb.setHostingroomId(((Booking) o).getHostingroom().getId());
				rb.setUserId(((Booking) o).getUser().getId());
				rb.setCheckInDate(((Booking) o).getCheckInDate());
				rb.setCheckOutDate(((Booking) o).getCheckOutDate());
				rb.setBookingDate(((Booking) o).getBookingDate());

				
				bookings.add(rb);
				
				
				
				
				
			}
		}
		
		

		
		
		try {
		jaxbContext = JAXBContext.newInstance( HostingRoomsExport.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	String newFileName = servletContext.getRealPath("")+ "uploaded\\" + "HostingRoomsExported.xml" ;
    	
		jaxbMarshaller.marshal( rooms, new File( newFileName ) );
		jaxbMarshaller.marshal( rooms, System.out );
	} catch (JAXBException e) {
		e.printStackTrace();
	}
		
		try {
		jaxbContext = JAXBContext.newInstance( BookingsExport.class );
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	String newFileName = servletContext.getRealPath("")+ "uploaded\\" + "BookingsExported.xml" ;

		jaxbMarshaller.marshal( bookings, new File( newFileName ) );
		jaxbMarshaller.marshal( bookings, System.out );
	} catch (JAXBException e) {
		e.printStackTrace();
	}
		
		//return "/restricted/admin/exportxmldata?faces-redirect=true";
		
	}
	
	public void testing() {
		System.out.println("PASSED");
		HostingroomBean host = new HostingroomBean();
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance( HostingroomBean.class );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Marshaller jaxbMarshaller = null;
		try {
			 jaxbMarshaller = jaxbContext.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		}
		
		try {
			jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		
		try {
			jaxbMarshaller.marshal( host, new File( "simple.xml" ) );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			jaxbMarshaller.marshal( host, System.out );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	

/*	jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true);
	jaxbMarshaller.marshal( simpleHostingroom, new File( "simple.xml" ) );
	jaxbMarshaller.marshal( simpleHostingroom, System.out );*/


}
