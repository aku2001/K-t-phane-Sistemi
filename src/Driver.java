
import java.sql.*;

public class Driver {
	
	//Database info
//	private String dbName = "librarySystem";
	private String dbName = "trial";
	private String dbUrl = "jdbc:mysql://localhost:3306/"+dbName;
	private String dbUserName = "root";
	private String dbPassw = "<Aku2001/>";
	
	//Tables
	private String stdTableName = "students";
	private String bookTableName = "books";
	private String adminTable = "admin";
	
	
	//
	public Connection myConn;
	private boolean isConnected;
	private boolean isTablesSet;
	
	//Query Commands
	// Functions	
	
	//Students: insert, find, delete, update
	//Book: update(taken), delete, insert
	


	public Driver() {
		connectToDatabase();
	}
	
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public boolean isTablesSet() {
		return isTablesSet;
	}


	public void setTablesSet(boolean isTablesSet) {
		this.isTablesSet = isTablesSet;
	}


	public String getDbUserName() {
		return dbUserName;
	}


	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}


	public String getDbPassw() {
		return dbPassw;
	}


	public void setDbPassw(String dbPassw) {
		this.dbPassw = dbPassw;
	}


	public String getstdTableName() {
		return stdTableName;
	}


	public void setstdTableName(String stdTableName) {
		this.stdTableName = stdTableName;
	}


	public String getBookTableName() {
		return bookTableName;
	}


	public void setBookTableName(String bookTableName) {
		this.bookTableName = bookTableName;
	}


	public String getAdminTable() {
		return adminTable;
	}


	public void setAdminTable(String adminTable) {
		this.adminTable = adminTable;
	}


	public boolean isConnected() {
		return isConnected;
	}


	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}


	public boolean connectToDatabase() {
		
		try {	
			this.myConn = DriverManager.getConnection(dbUrl,dbUserName,dbPassw);
			this.isConnected = true;
		}
		catch(Exception exc) {
			exc.printStackTrace();
			this.isConnected = false;
			return false;
		}
		
		try {
			String selectStatement = "select * from "+ this.bookTableName;
			PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
			ResultSet myRes = myStmt.executeQuery();
			
			selectStatement = "select * from "+ this.stdTableName;
			myStmt = myConn.prepareStatement(selectStatement);
			myRes = myStmt.executeQuery();

			selectStatement = "select * from "+ this.adminTable;
			myStmt = myConn.prepareStatement(selectStatement);
			myRes = myStmt.executeQuery();
			
			System.out.println("Tables Set True");
			this.isTablesSet = true;
			return true;

		}
		catch (Exception er) {
			// TODO: handle exception
			this.isTablesSet = false;
			System.out.println("Problemmmmmm");
			return false;
		}
	}
	
	public void createTables(String username, String password, int budget) {
		
//		Create Student Table
		try {
			String createStudentTable = "CREATE TABLE "+ dbName+"."+stdTableName+"(\n"+
					  "`idstudents` INT NOT NULL AUTO_INCREMENT,\n"+
					  "`username` VARCHAR(45) NOT NULL,\n"+
					  "`password` VARCHAR(45) NOT NULL,\n"+
					  "`name` VARCHAR(45) NOT NULL,\n"+
					  "`surname` VARCHAR(45) NOT NULL,"+
					  "`number` VARCHAR(45) NOT NULL,\n"+
					  "`mail` VARCHAR(45) NOT NULL,\n"+
					  "`isInLibrary` VARCHAR(1) NOT NULL,\n"+
					  "`reservedBook` INT NOT NULL,\n"+
					  "PRIMARY KEY (`idstudents`),\n"+
					  "UNIQUE INDEX `idstudents_UNIQUE` (`idstudents` ASC) VISIBLE,\n"+
					  "UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,\n"+
					  "UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE)\n"+
					  "ENGINE = InnoDB;";
			
			
			
			PreparedStatement myStmt = myConn.prepareStatement(createStudentTable);
			myStmt.execute();
			System.out.println("Student Table Created");
			isTablesSet = true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("studennttttt");

		}
		
		
//		Create BOOK Table
		try {
			
			String createBookTable = "CREATE TABLE " + dbName+"."+bookTableName+ "(\n`idbooks` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL, `author` VARCHAR(45) NOT NULL,"+
					  "`pubDate` VARCHAR(45) NOT NULL,`pages` INT NOT NULL,`isBooked` VARCHAR(45) NOT NULL,`isReserved` VARCHAR(45) NOT NULL,"+
					  "`reservedDate` VARCHAR(45) NOT NULL,`bookedDate` VARCHAR(45) NOT NULL,`barcodeNumber` INT NOT NULL,`health` VARCHAR(45) NOT NULL,"+
					  "PRIMARY KEY (`idbooks`),UNIQUE INDEX `idbooks_UNIQUE` (`idbooks` ASC) VISIBLE,UNIQUE INDEX `barcodeNumber_UNIQUE` (`barcodeNumber` ASC) VISIBLE);";
			
			
			
			PreparedStatement myStmt = myConn.prepareStatement(createBookTable);
			myStmt.execute();
			System.out.println("Book Table Created");
			isTablesSet = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("boooook");

		}
		
//		
		
//		Create Admin Table
		try {
			
			
			String createAdminTable = "CREATE TABLE " + dbName+"."+adminTable+ "(\n`username` VARCHAR(45) NOT NULL,"+ "`idadmin` INT NOT NULL AUTO_INCREMENT,"+
					  "`password` VARCHAR(45) NOT NULL,`budget` INT NOT NULL,  PRIMARY KEY (`idadmin`), UNIQUE INDEX `username_UNIQUE` (`username` ASC));";

			PreparedStatement myStmt = myConn.prepareStatement(createAdminTable);

			myStmt.execute();
			
			Admin admin = new Admin(username,password,budget);
			insertAdmin(admin);
			
			System.out.println("Admin Table Created");
			
			isTablesSet = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("adminnnnnnn");
		}
		
	}
	
	public boolean connectToDatabase(String dName, String dUserName, String dPassw) {
		
		try {	
			this.myConn = DriverManager.getConnection(dName,dUserName,dPassw);
			this.isConnected = true;
			System.out.println("Connected");
		}
		
		catch(Exception exc) {
			exc.printStackTrace();
			this.isConnected = false;
			return false;
		}
		
		try {
			String selectStatement = "select * from "+ this.bookTableName;
			PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
			ResultSet myRes = myStmt.executeQuery();
			
			selectStatement = "select * from "+ this.stdTableName;
			myStmt = myConn.prepareStatement(selectStatement);
			myRes = myStmt.executeQuery();

			selectStatement = "select * from "+ this.adminTable;
			myStmt = myConn.prepareStatement(selectStatement);
			myRes = myStmt.executeQuery();
			
			System.out.println("Tables Set True");
			this.isTablesSet = true;
			return true;

		}
		catch (Exception er) {
			// TODO: handle exception
			this.isTablesSet = false;
			System.out.println("Problemmmmmm");
			return false;
		}
		
		
	}
	

	//Books Module
	
	public int generateBarcode() {
		String selectStatement = "select * from "+ this.bookTableName;
		PreparedStatement myStmt;
		int barcodeNumber = 0;
		
		try {
			myStmt = myConn.prepareStatement(selectStatement);
			ResultSet myRes = myStmt.executeQuery();
			while(myRes.next()) {
				barcodeNumber = myRes.getInt("barcodeNumber");
			}
			return barcodeNumber + 1;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int insertBook(Book book) {
		String insertStatement = "insert into "+ this.bookTableName+ " (name,author,pubDate,pages,isBooked,isReserved,barcodeNumber,health,reservedDate,bookedDate) values (?, ?, ?, ?, ?, ?, ?,?,?,?)";
		PreparedStatement myStmt;
		int barcodeNumber;
		
		try {
			barcodeNumber = generateBarcode();
			myStmt = myConn.prepareStatement(insertStatement);
			
			myStmt.setString(1, book.getName());
			myStmt.setString(2, book.getAuthor());
			myStmt.setString(3, book.getPubDate());
			myStmt.setInt(4, book.getPages());
			myStmt.setString(5, book.getIsBooked());
			myStmt.setString(6, book.getIsReserved());
			myStmt.setInt(7, barcodeNumber );
			myStmt.setInt(8, book.getHealth());
			myStmt.setString(9, book.getReservedDate());
			myStmt.setString(10, book.getBookedDate());
			
			myStmt.executeUpdate();
			return barcodeNumber;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			System.out.println("The username is already exist\n");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public boolean deleteBook(int barcodeNumber) {
		String deleteStatement = "delete from "+ this.bookTableName+ " where barcodeNumber = ?";
		PreparedStatement myStmt;
		
		try {
			myStmt = myConn.prepareStatement(deleteStatement);
			myStmt.setInt(1, barcodeNumber);
			myStmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void updateBook(Book book) {
		String updateStatement = "update " + this.bookTableName+ " set name = ?, author= ?, pubDate = ?, pages= ?,"
				+ " isBooked = ?, isReserved = ?, reservedDate = ?, bookedDate= ?, health = ? where barcodeNumber = ?";
		
		try {
			PreparedStatement myStmt = myConn.prepareStatement(updateStatement);
			myStmt = myConn.prepareStatement(updateStatement);
			
			myStmt.setString(1, book.getName());
			myStmt.setString(2, book.getAuthor());
			myStmt.setString(3, book.getPubDate());
			myStmt.setInt(4, book.getPages());
			myStmt.setString(5, book.getIsBooked());
			myStmt.setString(6, book.getIsReserved());
			myStmt.setString(7, book.getReservedDate());
			myStmt.setString(8, book.getBookedDate());
			myStmt.setInt(9, book.getHealth());
			myStmt.setInt(10, book.getBarcodeNumber());
			
			
			myStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Book getBook(int barcodeNumber) {
		String name,author,pubDate,isBooked,isReserved,reservedDate,bookedDate;
		int pages,health;
		String selectStatement = "select * from "+ this.bookTableName + " where barcodeNumber = ?";
		
		try {
			PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
			myStmt.setInt(1, barcodeNumber);
			
			ResultSet myRes = myStmt.executeQuery();
			
			if(myRes.next()) {
				name = myRes.getString("name");
				author = myRes.getString("author");
				pubDate = myRes.getString("pubDate");
				isBooked = myRes.getString("isBooked");
				isReserved = myRes.getString("isReserved");
				reservedDate = myRes.getString("reservedDate");
				bookedDate = myRes.getString("bookedDate");
				health = myRes.getInt("health");
				
				pages = myRes.getInt("pages");
				Book book = new Book(name,author,pubDate,pages, isBooked,isReserved,reservedDate,bookedDate,barcodeNumber,health) ;
				
				return book;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ResultSet getAllBooks() {
		
		String selectStatement = "select * from "+ this.bookTableName;
		
		try {
			PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
			
			
			ResultSet myRes = myStmt.executeQuery();
			return myRes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ResultSet getBook(String bookName) {
			
		String selectStatement = "select * from "+ this.bookTableName + " where name = ?";
		
		try {
			PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
			myStmt.setString(0, bookName);
			
			
			ResultSet myRes = myStmt.executeQuery();
			return myRes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	
	//Student Modules
		public boolean insertStudent(Student std) {
			String insertStatement = "insert into "+ this.stdTableName+ " (username, password, name, surname, number, mail, isInLibrary,reservedBook) values (?, ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement myStmt;
			
			try {
				myStmt = myConn.prepareStatement(insertStatement);
				
				myStmt.setString(1, std.getUsername());
				myStmt.setString(2, std.getPassword());
				myStmt.setString(3, std.getName());
				myStmt.setString(4, std.getSurname());
				myStmt.setString(5, std.getNumber());
				myStmt.setString(6, std.getMail());
				myStmt.setString(7, std.getIsInLibrary());
				myStmt.setInt(8, std.getReservedBook());
				
							
				
				myStmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("The username or number is already in use");
//				e.printStackTrace();
			}
			return false;
			
		}
		
		public Student getStudent(String username) {
			String number,password,name,surname,mail,isInLibrary;
			int reservedBook;
			
			String selectStatement = "select * from "+ this.stdTableName + " where username = ?";
			
			try {
				PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
				myStmt.setString(1, username);
				
				ResultSet myRes = myStmt.executeQuery();
				
				if(myRes.next()) {
					number = myRes.getString("number");
					password = myRes.getString("password");
					name = myRes.getString("name");
					surname = myRes.getString("surname");
					mail= myRes.getString("mail");
					isInLibrary = myRes.getString("isInLibrary");
					reservedBook = myRes.getInt("reservedBook");
					
					Student std = new Student(username,password,name,surname,number,mail,isInLibrary,reservedBook) ;
					
					return std;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		public Student getStudentFromNumber(String number) {
			String username,password,name,surname,mail,isInLibrary;
			int reservedBook;
			
			String selectStatement = "select * from "+ this.stdTableName + " where number = ?";
			
			try {
				PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
				myStmt.setString(1, number);
				
				ResultSet myRes = myStmt.executeQuery();
				
				if(myRes.next()) {
					username = myRes.getString("username");
					password = myRes.getString("password");
					name = myRes.getString("name");
					surname = myRes.getString("surname");
					mail= myRes.getString("mail");
					isInLibrary = myRes.getString("isInLibrary");
					reservedBook = myRes.getInt("reservedBook");
					
					Student std = new Student(username,password,name,surname,number,mail,isInLibrary,reservedBook) ;
					
					return std;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		
		
		public ResultSet getAllStudents() {
			
			String selectStatement = "select * from "+ this.stdTableName;
			
			try {
				PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
				
				
				ResultSet myRes = myStmt.executeQuery();
				return myRes;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		public void deleteStudent(Student std) {
			String deleteStatement = "delete from "+ this.stdTableName+ " where username = ?";
			PreparedStatement myStmt;
			
			try {
				myStmt = myConn.prepareStatement(deleteStatement);
				myStmt.setString(1, std.getUsername());
				myStmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void updateStudent(Student std) {
			String updateStatement = "update " + this.stdTableName+ " set password = ?, name = ?, surname = ?,"
					+ " mail = ?, isInLibrary = ?, reservedBook = ? where username = ?";
			
			try {
				PreparedStatement myStmt = myConn.prepareStatement(updateStatement);
				myStmt = myConn.prepareStatement(updateStatement);
				
				myStmt.setString(1, std.getPassword());
				myStmt.setString(2, std.getName());
				myStmt.setString(3, std.getSurname());
				myStmt.setString(4, std.getMail());
				myStmt.setString(5, std.getIsInLibrary());
				myStmt.setInt(6, std.getReservedBook());
				myStmt.setString(7, std.getUsername());
				
				myStmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		
		//Admin Modules
				public boolean insertAdmin(Admin admin) {
					System.out.println("Inserting admim");
					String insertStatement = "insert into "+ this.adminTable+ " (username, password, budget) values (?, ?, ?)";
					PreparedStatement myStmt;
					
					try {
						myStmt = myConn.prepareStatement(insertStatement);
						
						myStmt.setString(1, admin.getUsername());
						myStmt.setString(2, admin.getPassword());
						myStmt.setInt(3, admin.getBudget());

						
						myStmt.executeUpdate();
						return true;
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("The username or number is already in use");
//						e.printStackTrace();
					}
					return false;
					
				}
				
				public Admin getAdmin(String username) {
					String password;
					int budget;
					
					String selectStatement = "select * from "+ this.adminTable + " where username = ?";
					
					try {
						PreparedStatement myStmt = myConn.prepareStatement(selectStatement);
						myStmt.setString(1, username);
						
						ResultSet myRes = myStmt.executeQuery();
						
						System.out.println(myStmt);
						if(myRes.next()) {
							
							password = myRes.getString("password");
							budget = myRes.getInt("budget");
							
							
							Admin admin = new Admin(username,password,budget) ;
							
							return admin;
							
						}
						else {
							System.out.println("NONE");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
					
				}
				
				
				
				
				public void deleteAdmin(Admin admin) {
					String deleteStatement = "delete from "+ this.adminTable+ " where username = ?";
					PreparedStatement myStmt;
					
					try {
						myStmt = myConn.prepareStatement(deleteStatement);
						myStmt.setString(1, admin.getUsername());
						myStmt.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				public void updateAdmin(Admin admin) {
					String updateStatement = "update " + this.adminTable+ " set password = ?, budget = ? where username = ?";
					
					try {
						PreparedStatement myStmt = myConn.prepareStatement(updateStatement);
						myStmt = myConn.prepareStatement(updateStatement);
						
						myStmt.setString(1, admin.getPassword());
						myStmt.setInt(2, admin.getBudget());
						myStmt.setString(3, admin.getUsername());
						
						myStmt.execute();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	
}
