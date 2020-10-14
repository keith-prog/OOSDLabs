
public class HotelRoom{
	
	// Varibles
	private int roomNumber;
	private String roomType;
	private boolean vacant;
	private double rate;

	//Constructors
	
	public HotelRoom() {
		setRoomNumber(0);
		setRoomType("");
		setVacant(false);
		setRate(0);
	}
	
	public HotelRoom(int roomNumber, String roomType) {
		setRoomNumber(roomNumber);
		setRoomType(roomType);
	}
	
	public HotelRoom(int roomNumber, String roomType, boolean vacant, double rate) {
		setRoomNumber(roomNumber);
		setRoomType(roomType);
		setVacant(vacant);
		setRate(rate);
	}

	
	// Getter and Setter
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(int roomNumber) {
		if(roomNumber>0 && roomNumber < 500) {
			this.roomNumber = roomNumber;
		}else {
			System.out.println("the Room number is not in range");
		}
		
	}
	
	public String getRoomType() {
		return roomType;
	}
	
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the vacant
	 */
	public boolean getVacant() {
		return vacant;
	}

	/**
	 * @param vacant the vacant to set
	 */
	public void setVacant(boolean vacant) {
		this.vacant = vacant;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "HotelRoom [roomNumber=" + roomNumber + ", roomType=" + roomType + ", vacant=" + vacant + ", rate="
				+ rate + "]";
	}
	
	public void isOccupied() {
		if(vacant) {
			System.out.println("the room is occupied!!!");
		}else {
			setVacant(true);
			System.out.println("the room is now occupied!!!");
		}
	}

	
	
}
