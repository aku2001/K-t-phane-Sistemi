
public class Book {
	
	private String name;
	private String author;
	private String pubDate;
	private int	pages;
	private String  isBooked;
	private String isReserved;
	private String reservedDate;
	private String bookedDate;
	private int barcodeNumber;
	private int health;
	
	
//	Hasar yeri ekle
	
	
	public Book(String name, String author, String pubDate, int pages, String isBooked, String isReserved,
			String reservedDate, String bookedDate, int barcodeNumber, int health) {
		this.name = name;
		this.author = author;
		this.pubDate = pubDate;
		this.pages = pages;
		this.isBooked = isBooked;
		this.isReserved = isReserved;
		this.reservedDate = reservedDate;
		this.bookedDate = bookedDate;
		this.barcodeNumber = barcodeNumber;
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Book(String name, String author, String pubDate, int pages) {
		super();
		this.name = name;
		this.author = author;
		this.pubDate = pubDate;
		this.pages = pages;
		this.isBooked = "F";
		this.isReserved = "F";
		this.reservedDate = "F";
		this.bookedDate = "F";
		this.health = 100;
	}
	
	public Book(String name, String author, String pubDate, int pages, int barcodeNumber) {
		super();
		this.name = name;
		this.author = author;
		this.pubDate = pubDate;
		this.pages = pages;
		this.isBooked = "F";
		this.isReserved = "F";
		this.reservedDate = "F";
		this.bookedDate = "F";
		this.barcodeNumber = barcodeNumber;
		
	}

	public int getBarcodeNumber() {
		return barcodeNumber;
	}

	public void setBarcodeNumber(int barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(String isBooked) {
		this.isBooked = isBooked;
	}

	public String getIsReserved() {
		return isReserved;
	}

	public void setIsReserved(String isReserved) {
		this.isReserved = isReserved;
	}

	public String getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(String reservedDate) {
		this.reservedDate = reservedDate;
	}

	public String getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}

	
	
	
	
}
