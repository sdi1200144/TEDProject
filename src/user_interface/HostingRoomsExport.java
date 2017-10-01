package user_interface;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HOSTING ROOMS")
public class HostingRoomsExport {
	List<HostingRoomExport> hostingrooms;

	@XmlElement(name = "HOSTING ROOM")
	public void setHostingrooms(List<HostingRoomExport> hrooms) {
		this.hostingrooms = hrooms;
	}

	public void add(HostingRoomExport hroom) {
		if (this.hostingrooms == null) {
			this.hostingrooms = new ArrayList<HostingRoomExport>();
		}
		this.hostingrooms.add(hroom);

	}

	public List<HostingRoomExport> getHostingrooms() {
		return hostingrooms;
	}
}
