
public class Student {
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String number;
	private String mail;
	private String isInLibrary;
	private int reservedBook;
	

	
//	Functions: 


	public Student(String username, String password, String name, String surname, String number, String mail, String isInLibrary, int reservedBook) {
		this.isInLibrary = isInLibrary;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.number = number;
		this.mail = mail;
		this.reservedBook = reservedBook;
	}
	
	public Student(String username, String password, String name, String surname, String number, String mail) {
		this.isInLibrary = "F";
		this.reservedBook = 0;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.number = number;
		this.mail = mail;
	}

	public int getReservedBook() {
		return reservedBook;
	}

	public void setReservedBook(int reservedBook) {
		this.reservedBook = reservedBook;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIsInLibrary() {
		return isInLibrary;
	}

	public void setIsInLibrary(String isInLibrary) {
		this.isInLibrary = isInLibrary;
	}
	
	
}
