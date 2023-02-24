
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class LibrarySystemGUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// PANES
	private JPanel contentPane;
	public JPanel loginPane;
	public JPanel entrancePane;
	public JPanel adminPane;
	public JPanel userUpdatePane;
	public JPanel registrationPane;
	public JPanel userPane;
	public JPanel adminLoginPane;
	public JPanel bookProcessPane;
	public JPanel addBookPane;
	JPanel updateBookPane;
	
	public Driver dbDriver;
	public Student std;
	public Book book;
	public Admin admin;
	
	// Book Process
	public JList<String> bookBookList;
	public JLabel bookNameField;
	public JLabel bookAuthorField;
	public JLabel bookPubDateField;
	public JLabel bookPageField;
	public JScrollPane scrollPane;
	public JCheckBox inLibraryCheck;
	
	//Student Info
	public JLabel userNameField;
	public JLabel userSurnameField;	
	public JLabel userNumberField;
	public JLabel userBookField;
	
	//Admin
	JLabel adminTotalBudget;
	
	
	private JTextField registrationNameField;
	private JTextField registrationSurnameField;
	private JTextField registrationNumberField;
	private JTextField registrationMailField;
	private JTextField registrationUsernameField;
	private JPasswordField registrationPasswordField;
	private JTextField userUpdateNameField;
	private JTextField userUpdateSurnameField;
	private JTextField userUpdateNumberField;
	private JTextField userUpdateMailField;
	private JPasswordField userUpdatePasswordField;
	private JTextField adminBudgetField;
	private JTextField adminLoginUsernameField;
	private JTextField adminLoginPasswordField;
	private JTextField addBookNameField;
	private JTextField addBookAuthorField;
	private JTextField addBookPubDateField;
	private JTextField addBookPageField;
	private JTextField userStudentNumberField;
	private JTextField updateBookNameField;
	private JTextField updateBookAuthorField;
	private JTextField updateBookPubDateField;
	private JTextField updateBookPageField;
	private JTextField updateBookHealthField;
	private JTextField updateBookBarcodeNumberField;
	private JTextField adminUpdateNumberField;
	private JTextField adminUpdateBarcodeNumberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					LibrarySystemGUI frame = new LibrarySystemGUI();
					frame.setVisible(true);
					frame.setInvisible();
					frame.entrancePane.setVisible(true);
					frame.entrancePane.setEnabled(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibrarySystemGUI() {

		dbDriver = new Driver();

		int n = JOptionPane.OK_OPTION;
		
		// For Database Connection
		
		JTextField dUrl = new JTextField();
		JTextField dName = new JTextField();
		JPasswordField dPassw = new JPasswordField();
		JTextField dUserName = new JTextField();
		
		Object[] fields = {
			"Databaser Url: ", dUrl,
			"Database Schema Name: ", dName,
			"Database UserName: ", dUserName,
			"Database Password: ", dPassw
		};
		
		JTextField aUsername = new JTextField();
		JTextField aPassw = new JTextField();
		JTextField aBudget = new JTextField();
		
		Object[] secFields = {
			"Admin Kullanýcý Adý: ", aUsername,
			"Admin Þifresi: ", aPassw,
			"Admin Bütçesi: ", aBudget
			
		};
		
		
		while(! dbDriver.isConnected() && n != JOptionPane.CANCEL_OPTION ){
			n = JOptionPane.showConfirmDialog(null, fields,"header!",JOptionPane.OK_CANCEL_OPTION);
			String passw = new String(dPassw.getPassword());
			System.out.println("name: "+dName.getText() + " passw: "+ passw + " username: "+ dUserName.getText());
			dbDriver.connectToDatabase(dUrl.getText()+dName.getText(),dUserName.getText(),passw);
			
			if(!dbDriver.isConnected()) {
				JOptionPane.showMessageDialog(null, "Veri Tabanýna Baðlanýlamadý, Lütfen Bilgileri Kontrol Edin!");
			}
			else {
				dbDriver.setDbName(dName.getText());
			}
		}
		
		while(! dbDriver.isTablesSet() && n != JOptionPane.CANCEL_OPTION ){
			n = JOptionPane.showConfirmDialog(null, "Veri Tabanýndaki Bazý Tablolar Eksik Otomatik Oluþturulsun Mu?","Table Creation",JOptionPane.OK_CANCEL_OPTION);
			if(n == JOptionPane.OK_OPTION) {
				n = JOptionPane.showConfirmDialog(null, secFields,"adminCreation!",JOptionPane.OK_CANCEL_OPTION);
				System.out.println("username: " + aUsername.getText()+" passw: "+aPassw.getText()+ " budget: "+aBudget.getText());
				if(aUsername.getText().length() != 0 && aPassw.getText().length() != 0 && aBudget.getText().length() != 0) {
					try {
						dbDriver.createTables(aUsername.getText(), aPassw.getText(), Integer.parseInt(aBudget.getText()));
						if(!dbDriver.isTablesSet()) {
							JOptionPane.showMessageDialog(null, "Kapatýp bir Schema' ya Baðlanýn");
						}
						else {
							JOptionPane.showMessageDialog(null, "Tablolar Oluþturuldu");
						}
					}
					catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Bütçe Sayý Olmak Zorunda");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Bütün Alanlarý Doldurunuz");
				}
				
			}
		}
		
		if(dbDriver.isConnected() && dbDriver.isTablesSet()) {
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 550, 450);
			Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			//BOOK PROCESS PANE
			
			bookProcessPane = new JPanel();
			bookProcessPane.setLayout(null);
			bookProcessPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			bookProcessPane.setBounds(0, 0, 536, 423);
			contentPane.add(bookProcessPane);
			
			JLabel bookTitleLabel = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			bookTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			bookTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			bookTitleLabel.setBounds(60, 10, 466, 36);
			bookProcessPane.add(bookTitleLabel);
			
			JLabel bookChosenBookLabel = new JLabel("Se\u00E7ilen Kitap");
			bookChosenBookLabel.setHorizontalAlignment(SwingConstants.CENTER);
			bookChosenBookLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			bookChosenBookLabel.setBounds(10, 86, 140, 24);
			bookProcessPane.add(bookChosenBookLabel);
			
			JLabel bookBookLabel = new JLabel("Kitaplar");
			bookBookLabel.setHorizontalAlignment(SwingConstants.CENTER);
			bookBookLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			bookBookLabel.setBounds(340, 86, 102, 24);
			bookProcessPane.add(bookBookLabel);
			
			JLabel bookNameLabel = new JLabel("Ba\u015Fl\u0131k:");
			bookNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookNameLabel.setBounds(20, 143, 60, 20);
			bookProcessPane.add(bookNameLabel);
			
			JLabel bookAuthorLabel = new JLabel("Yazar:");
			bookAuthorLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookAuthorLabel.setBounds(20, 183, 60, 20);
			bookProcessPane.add(bookAuthorLabel);
			
			JLabel bookPubDateLabel = new JLabel("Bas\u0131m:");
			bookPubDateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookPubDateLabel.setBounds(20, 223, 91, 20);
			bookProcessPane.add(bookPubDateLabel);
			
			JLabel bookPagesLabel = new JLabel("Sayfa:");
			bookPagesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookPagesLabel.setBounds(20, 263, 91, 20);
			bookProcessPane.add(bookPagesLabel);
			
			bookNameField = new JLabel("New label");
			bookNameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookNameField.setBounds(90, 143, 120, 20);
			bookProcessPane.add(bookNameField);
			
			bookAuthorField = new JLabel("New label");
			bookAuthorField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookAuthorField.setBounds(90, 188, 120, 20);
			bookProcessPane.add(bookAuthorField);
			
			bookPubDateField = new JLabel("New label");
			bookPubDateField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookPubDateField.setBounds(90, 228, 120, 20);
			bookProcessPane.add(bookPubDateField);
			
			bookPageField = new JLabel("New label");
			bookPageField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookPageField.setBounds(90, 268, 120, 20);
			bookProcessPane.add(bookPageField);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(265, 130, 261, 158);
			bookProcessPane.add(scrollPane);
			scrollPane.setViewportView(bookBookList);
			
			listBooks();
			
			JButton bookReserveButton = new JButton("Rezerve Et");
			bookReserveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(book == null) {
						JOptionPane.showMessageDialog(null, "Lütfen Bir Kitap Seçiniz");
					}
					else {
						
						if(book.getIsReserved().equals("F") && book.getIsBooked().equals("F") ) {
							
							if(std.getReservedBook()==0) {
								
								//Reserve book
								Date date = Calendar.getInstance().getTime();  
								DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
								String strDate = dateFormat.format(date);  
								
								book.setIsReserved(std.getNumber());
								book.setReservedDate(strDate);
								std.setReservedBook(book.getBarcodeNumber());
								
								dbDriver.updateBook(book);
								dbDriver.updateStudent(std);
								
								updateUserLabelInfo();
								
								JOptionPane.showMessageDialog(bookReserveButton, "Rezerve Edildi");
							}
							else {
								JOptionPane.showMessageDialog(bookReserveButton, "Sadece 1 Kitap Kiralayabilir veya Rezerve Edebilirsiniz");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(bookReserveButton, "Kitap kiralanmýþ veya Rezerve edilmiþ");
						}
					}
				}
			});
			bookReserveButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookReserveButton.setBounds(31, 329, 101, 24);
			bookProcessPane.add(bookReserveButton);
			
			JButton bookReservedBookButton = new JButton("Rezerve Edilen Kitap");
			bookReservedBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(std.getReservedBook() != 0) {
						book = dbDriver.getBook(std.getReservedBook());
						bookNameField.setText(book.getName());
						bookAuthorField.setText(book.getAuthor());
						bookPubDateField.setText(book.getPubDate());
						bookPageField.setText(Integer.toString(book.getPages()));
					}
					else {
						JOptionPane.showMessageDialog(null,"Rezerve Ettiðiniz Kitap Bulunmamakta");
					}
				}
			});
			
			bookReservedBookButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookReservedBookButton.setBounds(312, 355, 181, 24);
			bookProcessPane.add(bookReservedBookButton);
			
			JButton bookBackButton = new JButton("Back");
			bookBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Go Back to Student Pane
	//				bookProcessPane.setVisible(false);
	//				bookProcessPane.setEnabled(false);
					setInvisible();
					
					userPane.setVisible(true);
					userPane.setEnabled(true);
				}
			});
			bookBackButton.setBounds(20, 20, 70, 25);
			bookProcessPane.add(bookBackButton);
	
			
			JButton bookChooseButton = new JButton("Se\u00E7");
			bookChooseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Clicked");
					if(bookBookList.getSelectedIndex() != -1) {
						String info = (String) bookBookList.getSelectedValue();
						String[] parts = info.split(": ");
						String strBarcodeNumber = parts[1];
						int barcodeNumber = Integer.parseInt(strBarcodeNumber);
						placeBookInfo(barcodeNumber);
					}
				}
			});
			
			bookChooseButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			bookChooseButton.setBounds(361, 298, 81, 24);
			bookProcessPane.add(bookChooseButton);
			
			JButton btnRezerviIptalEt = new JButton("Rezervi \u0130ptal Et");
			btnRezerviIptalEt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(book == null) {
						JOptionPane.showMessageDialog(null, "Lütfen Rezerve Ettiðiniz Kitabý <Rezerve Edilen Kitap> Butonuna Basýp Seçiniz");
					}
					else {
						
						if(book.getIsReserved().equals(std.getNumber())) {
							//Reserve book
							
							book.setIsReserved("F");
							book.setReservedDate("F");
							std.setReservedBook(0);
							
							dbDriver.updateBook(book);
							dbDriver.updateStudent(std);
							
							bookNameField.setText("");
							bookAuthorField.setText("");
							bookPubDateField.setText("");
							bookPageField.setText("");
							
							updateUserLabelInfo();
							book = null;
							
							JOptionPane.showMessageDialog(bookReserveButton, "Rezerve Ýptal Edildi");
							
						}
						else {
							JOptionPane.showMessageDialog(bookReserveButton, "Kitap Sizin Tarafýnýzdan Rezerve Edilmemiþ");
						}
						
					}
				}
			});
			btnRezerviIptalEt.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			btnRezerviIptalEt.setBounds(31, 372, 147, 24);
			bookProcessPane.add(btnRezerviIptalEt);
			
			//ADD BOOK PANE
			
			addBookPane = new JPanel();
			addBookPane.setLayout(null);
			addBookPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			addBookPane.setBounds(0, 0, 536, 413);
			contentPane.add(addBookPane);
			
			JLabel titleLabel_5 = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			titleLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			titleLabel_5.setBounds(41, 10, 466, 36);
			addBookPane.add(titleLabel_5);
			
			JLabel addBookNameLabel = new JLabel("Ba\u015Fl\u0131k: ");
			addBookNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			addBookNameLabel.setBounds(26, 118, 45, 30);
			addBookPane.add(addBookNameLabel);
			
			addBookNameField = new JTextField();
			addBookNameField.setColumns(10);
			addBookNameField.setBounds(127, 125, 120, 20);
			addBookPane.add(addBookNameField);
			
			JLabel addBookAuthorLabel = new JLabel("Yazar: ");
			addBookAuthorLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			addBookAuthorLabel.setBounds(26, 158, 65, 30);
			addBookPane.add(addBookAuthorLabel);
			
			addBookAuthorField = new JTextField();
			addBookAuthorField.setColumns(10);
			addBookAuthorField.setBounds(127, 165, 120, 20);
			addBookPane.add(addBookAuthorField);
			
			JLabel addBookPubDateLabel = new JLabel("Bas\u0131m Tarihi:");
			addBookPubDateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			addBookPubDateLabel.setBounds(26, 198, 91, 30);
			addBookPane.add(addBookPubDateLabel);
			
			addBookPubDateField = new JTextField();
			addBookPubDateField.setColumns(10);
			addBookPubDateField.setBounds(127, 205, 120, 20);
			addBookPane.add(addBookPubDateField);
			
			addBookPageField = new JTextField();
			addBookPageField.setColumns(10);
			addBookPageField.setBounds(127, 245, 120, 20);
			addBookPane.add(addBookPageField);
			
			JLabel addBookPageLabel = new JLabel("Sayfa:");
			addBookPageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			addBookPageLabel.setBounds(26, 238, 65, 30);
			addBookPane.add(addBookPageLabel);
			
			JButton addBookButton = new JButton("Kitap Ekle");
			addBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int barcodeNumber;
					String name = addBookNameField.getText();
					String author = addBookAuthorField.getText();
					String pubDate = addBookPubDateField.getText();
					
//					System.out.println("Name register: "+ name+"hello");
					
					try {
						int pages = Integer.parseInt(addBookPageField.getText());
						if(name.length() != 0 && author.length() != 0 && pubDate.length() != 0) {
							
							book = new Book(name,author,pubDate,pages);
							if((barcodeNumber = dbDriver.insertBook(book)) != 0) {
								JOptionPane.showMessageDialog(addBookButton, "Kitap Kaydedildi. Barkod Numarasý: "+ barcodeNumber);
								listBooks();				}
							else {
								JOptionPane.showMessageDialog(addBookButton, "Kitap Kaydý Yapýlamadý Lütfen Alanlarý Düzgün Doldurunuz");
							}
						}
						else {
							JOptionPane.showMessageDialog(addBookButton, "Lütfen Bütün Alanlarý Doldurunuz");
						}
					}
					catch (Exception re) {
							JOptionPane.showMessageDialog(null, "Sayfa Sayýsýný Düzgün Giriniz");
					}
						
					
					
				
					
				}
			});
			addBookButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			addBookButton.setBounds(311, 323, 130, 30);
			addBookPane.add(addBookButton);
			
			JLabel logo_2 = new JLabel("");
			logo_2.setIcon(new ImageIcon(img));
			logo_2.setBounds(311, 112, 150, 153);
			addBookPane.add(logo_2);
			
			JButton adminBookBackButton = new JButton("Back");
			adminBookBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Return to admin Panel
	//				addBookPane.setVisible(false);
	//				addBookPane.setEnabled(false);
					setInvisible();
					
					
					adminPane.setVisible(true);
					adminPane.setEnabled(true);
				}
			});
			adminBookBackButton.setBounds(18, 20, 73, 25);
			addBookPane.add(adminBookBackButton);
			
			
			// USER UPDATE PANE
			userUpdatePane = new JPanel();
			userUpdatePane.setLayout(null);
			userUpdatePane.setBorder(new EmptyBorder(5, 5, 5, 5));
			userUpdatePane.setBounds(0, 0, 536, 413);
			contentPane.add(userUpdatePane);
			
			JLabel userUpdateTitleLabel = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			userUpdateTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			userUpdateTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			userUpdateTitleLabel.setBounds(60, 10, 466, 36);
			userUpdatePane.add(userUpdateTitleLabel);
			
			JLabel userUpdateNameLabel = new JLabel("Ad:");
			userUpdateNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userUpdateNameLabel.setBounds(50, 118, 45, 30);
			userUpdatePane.add(userUpdateNameLabel);
			
			userUpdateNameField = new JTextField();
			userUpdateNameField.setColumns(10);
			userUpdateNameField.setBounds(124, 125, 120, 20);
			userUpdatePane.add(userUpdateNameField);
			
			JLabel userUpdateSurnameLabel = new JLabel("Soyad:");
			userUpdateSurnameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userUpdateSurnameLabel.setBounds(50, 158, 65, 30);
			userUpdatePane.add(userUpdateSurnameLabel);
			
			userUpdateSurnameField = new JTextField();
			userUpdateSurnameField.setColumns(10);
			userUpdateSurnameField.setBounds(124, 165, 120, 20);
			userUpdatePane.add(userUpdateSurnameField);
			
			JLabel userUpdateNumberLabel = new JLabel("Numara:");
			userUpdateNumberLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userUpdateNumberLabel.setBounds(50, 198, 65, 30);
			userUpdatePane.add(userUpdateNumberLabel);
			
			userUpdateNumberField = new JTextField();
			userUpdateNumberField.setEnabled(false);
			userUpdateNumberField.setColumns(10);
			userUpdateNumberField.setBounds(124, 205, 120, 20);
			userUpdatePane.add(userUpdateNumberField);
			
			JLabel userUpdateMailLabel = new JLabel("Mail:");
			userUpdateMailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userUpdateMailLabel.setBounds(50, 238, 65, 30);
			userUpdatePane.add(userUpdateMailLabel);
			
			userUpdateMailField = new JTextField();
			userUpdateMailField.setColumns(10);
			userUpdateMailField.setBounds(125, 245, 120, 20);
			userUpdatePane.add(userUpdateMailField);
			
			JLabel userUpdatePasswordLabel = new JLabel("\u015Eifre:");
			userUpdatePasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userUpdatePasswordLabel.setBounds(50, 278, 65, 30);
			userUpdatePane.add(userUpdatePasswordLabel);
			
			userUpdatePasswordField = new JPasswordField();
			userUpdatePasswordField.setBounds(124, 285, 120, 20);
			userUpdatePane.add(userUpdatePasswordField);
			
			JButton userUpdateUpdateButton = new JButton("G\u00FCncelle");
			userUpdateUpdateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//Update Student Object
					if(userUpdateNameField.getText().length() != 0) {						
						std.setName(userUpdateNameField.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Ad Boþ Olamaz");
					}
					
					if(userUpdateSurnameField.getText().length() != 0) {						
						std.setSurname(userUpdateSurnameField.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Soyad Boþ Olamaz");
					}
					
					if(userUpdateMailField.getText().length() != 0) {						
						std.setMail(userUpdateMailField.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Mail Boþ Olamaz");
					}
					
					String updatePassw = new String(userUpdatePasswordField.getPassword());
					
					if(updatePassw != null && updatePassw.length() != 0) {						
						std.setPassword(new String(userUpdatePasswordField.getPassword()));
					}
					else {
						JOptionPane.showMessageDialog(null, "Þifre Boþ Olamaz");
					}
					
					//Update Database
					dbDriver.updateStudent(std);
					JOptionPane.showMessageDialog(null, "Güncellendi");
					updateUserLabelInfo();
					
				}
			});
			userUpdateUpdateButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			userUpdateUpdateButton.setBounds(338, 323, 120, 30);
			userUpdatePane.add(userUpdateUpdateButton);
			
			JLabel logo_1 = new JLabel("");
			logo_1.setIcon(new ImageIcon(img));
			logo_1.setBounds(314, 126, 150, 153);
			userUpdatePane.add(logo_1);
			
			JButton userUpdateBackButton = new JButton("Back");
			userUpdateBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
	//				//Go Back To User Panel
	//				userUpdatePane.setVisible(false);
	//				userUpdatePane.setEnabled(false);
					setInvisible();
					
					userPane.setVisible(true);
					userPane.setEnabled(true);
				}
			});
			userUpdateBackButton.setBounds(30, 20, 65, 25);
			userUpdatePane.add(userUpdateBackButton);
			
			
			// REGISTRATION PANE
			
			registrationPane = new JPanel();
			registrationPane.setLayout(null);
			registrationPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			registrationPane.setBounds(0, 0, 536, 413);
			contentPane.add(registrationPane);
			
			JLabel titleLabel_1 = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			titleLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			titleLabel_1.setBounds(60, 10, 466, 36);
			registrationPane.add(titleLabel_1);
			
			JLabel nameLabel = new JLabel("Ad:");
			nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			nameLabel.setBounds(25, 140, 45, 30);
			registrationPane.add(nameLabel);
			
			JLabel surnameLabel = new JLabel("Soyad:");
			surnameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			surnameLabel.setBounds(25, 180, 65, 30);
			registrationPane.add(surnameLabel);
			
			JLabel usernameLabel_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
			usernameLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			usernameLabel_1.setBounds(260, 140, 102, 30);
			registrationPane.add(usernameLabel_1);
			
			JLabel passwordLabel_1 = new JLabel("\u015Eifre:");
			passwordLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			passwordLabel_1.setBounds(260, 200, 65, 30);
			registrationPane.add(passwordLabel_1);
			
			JLabel numberLabel = new JLabel("Numara:");
			numberLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			numberLabel.setBounds(25, 220, 65, 30);
			registrationPane.add(numberLabel);
			
			JLabel mailLabel = new JLabel("Mail:");
			mailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			mailLabel.setBounds(25, 260, 65, 30);
			registrationPane.add(mailLabel);
			
			registrationNameField = new JTextField();
			registrationNameField.setColumns(10);
			registrationNameField.setBounds(99, 147, 120, 20);
			registrationPane.add(registrationNameField);
			
			registrationSurnameField = new JTextField();
			registrationSurnameField.setColumns(10);
			registrationSurnameField.setBounds(99, 187, 120, 20);
			registrationPane.add(registrationSurnameField);
			
			registrationNumberField = new JTextField();
			registrationNumberField.setColumns(10);
			registrationNumberField.setBounds(99, 227, 120, 20);
			registrationPane.add(registrationNumberField);
			
			registrationMailField = new JTextField();
			registrationMailField.setColumns(10);
			registrationMailField.setBounds(100, 267, 120, 20);
			registrationPane.add(registrationMailField);
			
			registrationUsernameField = new JTextField();
			registrationUsernameField.setColumns(10);
			registrationUsernameField.setBounds(366, 147, 120, 20);
			registrationPane.add(registrationUsernameField);
			
			JButton registrationRegisterButton = new JButton("Kay\u0131t Ol");
			registrationRegisterButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Add student to database
					
					String name = registrationNameField.getText();
					String surname = registrationSurnameField.getText();
					String number = registrationNumberField.getText();
					String mail = registrationMailField.getText();
					String username = registrationUsernameField.getText();
					String password = new String(registrationPasswordField.getPassword());
					
					if(name.length() != 0 && surname.length() != 0 && number.length() != 0 && mail.length() != 0  && username.length()!=0 && password.length()!=0) {
						std = new Student(username,password, name, surname, number, mail);
						//If succesfull turn back to entrance
						if(dbDriver.insertStudent(std)) {
							JOptionPane.showMessageDialog(registrationPane, "Registered Successfully");
							std = null;
							
							setInvisible();
							
							entrancePane.setVisible(true);
							entrancePane.setEnabled(true);
						}
						else {
							JOptionPane.showMessageDialog(registrationPane, "Username or Number is Already in Use");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Lütfen Bütün ALanlarý Doldurunuz");
					}
					
				
				}
			});
			registrationRegisterButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			registrationRegisterButton.setBounds(346, 323, 130, 30);
			registrationPane.add(registrationRegisterButton);
			
			registrationPasswordField = new JPasswordField();
			registrationPasswordField.setBounds(366, 207, 120, 20);
			registrationPane.add(registrationPasswordField);
			
			JButton registrationBackButton = new JButton("Back");
			registrationBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Get back to entrance
	//				registrationPane.setVisible(false);
	//				registrationPane.setEnabled(false);
					setInvisible();
					
					entrancePane.setVisible(true);
					entrancePane.setEnabled(true);
				}
			});
			registrationBackButton.setBounds(28, 20, 80, 25);
			registrationPane.add(registrationBackButton);
			
			
			// ADMIN  LOGIN
			adminLoginPane = new JPanel();
			adminLoginPane.setLayout(null);
			adminLoginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			adminLoginPane.setBounds(0, 0, 536, 413);
			contentPane.add(adminLoginPane);
			
			JLabel titleLabel_4 = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			titleLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			titleLabel_4.setBounds(60, 10, 466, 36);
			adminLoginPane.add(titleLabel_4);
			
			JLabel adminLoginUsernameLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
			adminLoginUsernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			adminLoginUsernameLabel.setBounds(44, 178, 85, 30);
			adminLoginPane.add(adminLoginUsernameLabel);
			
			adminLoginUsernameField = new JTextField();
			adminLoginUsernameField.setColumns(10);
			adminLoginUsernameField.setBounds(161, 182, 200, 25);
			adminLoginPane.add(adminLoginUsernameField);
			
			adminLoginPasswordField = new JTextField();
			adminLoginPasswordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			adminLoginPasswordField.setColumns(10);
			adminLoginPasswordField.setBounds(161, 232, 200, 25);
			adminLoginPane.add(adminLoginPasswordField);
			
			JLabel adminLoginPasswordLabel = new JLabel("\u015Eifre:");
			adminLoginPasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			adminLoginPasswordLabel.setBounds(44, 234, 90, 21);
			adminLoginPane.add(adminLoginPasswordLabel);
			
			JButton adminLoginLoginButton = new JButton("Giri\u015F Yap");
			adminLoginLoginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//get username and password 
					
					String username = adminLoginUsernameField.getText();
					String password = adminLoginPasswordField.getText();
					
					//find matcing student
					//if passw true go to user pane
					
					admin = dbDriver.getAdmin(username);
					
					if(admin == null) {
						JOptionPane.showMessageDialog(loginPane, "Username is incorrect");
					}
					else if(admin.getPassword().equals(password)) {
	//					loginPane.setVisible(false);
	//					loginPane.setEnabled(false);
						setInvisible();
						
						adminPane.setVisible(true);
						adminPane.setEnabled(true);
						
						updateAdminLabelInfo();
					}
					else {
						JOptionPane.showMessageDialog(loginPane, "Password is incorrect");
					}
				}
			});
			
			adminLoginLoginButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			adminLoginLoginButton.setBounds(350, 313, 109, 25);
			adminLoginPane.add(adminLoginLoginButton);
			
			JButton adminLoginBackButton = new JButton("Back");
			adminLoginBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					
					setInvisible();
					entrancePane.setVisible(true);
					entrancePane.setEnabled(true);
				}
			});
			adminLoginBackButton.setBounds(20, 20, 75, 25);
			adminLoginPane.add(adminLoginBackButton);
			
			JLabel lblNewLabel = new JLabel("Admin");
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(44, 101, 103, 30);
			adminLoginPane.add(lblNewLabel);
			
			
			
	//		LOGIN PANE
			loginPane = new JPanel();
			loginPane.setLayout(null);
			loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			loginPane.setBounds(0, 0, 536, 413);
			contentPane.add(loginPane);
			
			JTextField loginUsernameField = new JTextField();
			loginUsernameField.setColumns(10);
			loginUsernameField.setBounds(161, 182, 200, 25);
			loginPane.add(loginUsernameField);
			
			JTextField loginPasswordField = new JTextField();
			loginPasswordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			loginPasswordField.setColumns(10);
			loginPasswordField.setBounds(161, 232, 200, 25);
			loginPane.add(loginPasswordField);
			
			JLabel usernameLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
			usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			usernameLabel.setBounds(44, 178, 85, 30);
			loginPane.add(usernameLabel);
			
			JLabel passwordLabel = new JLabel("\u015Eifre:");
			passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			passwordLabel.setBounds(44, 234, 90, 21);
			loginPane.add(passwordLabel);
			
			JButton loginButton = new JButton("Giri\u015F Yap");
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//get username and password 
					
					String username = loginUsernameField.getText();
					String password = loginPasswordField.getText();
					
					//find matcing student
					//if passw true go to user pane
					
					std = dbDriver.getStudent(username);
					if(std == null) {
						JOptionPane.showMessageDialog(loginPane, "Username is incorrect");
					}
					else if(std.getPassword().equals(password)) {

						setInvisible();
						updateUserLabelInfo();
						userPane.setVisible(true);
						userPane.setEnabled(true);
					}
					else {
						JOptionPane.showMessageDialog(loginPane, "Password is incorrect");
	//					System.out.println("password: "+ password+ " given: "+std.getPassword());
					}
					
					
				}
			});
			loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			loginButton.setBounds(350, 313, 109, 25);
			loginPane.add(loginButton);
			
			JLabel titleLabel = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			titleLabel.setBounds(60, 10, 466, 36);
			loginPane.add(titleLabel);
			
			JButton loginBackButton = new JButton("Back");
			loginBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//get back to entrance
	//				loginPane.setVisible(false);
	//				loginPane.setEnabled(false);
					setInvisible();
					
					entrancePane.setVisible(true);
					entrancePane.setEnabled(true);
				}
			});
			loginBackButton.setBounds(24, 20, 78, 25);
			loginPane.add(loginBackButton);
			
			
			// ADMIN PANE
			adminPane = new JPanel();
			adminPane.setLayout(null);
			adminPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			adminPane.setBounds(0, 0, 536, 413);
			contentPane.add(adminPane);
			
			JLabel titleLabel_3 = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			titleLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			titleLabel_3.setBounds(99, 10, 361, 36);
			adminPane.add(titleLabel_3);
			
			JLabel adminNumberLabel = new JLabel("\u00D6\u011Frenci Numaras\u0131:");
			adminNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
			adminNumberLabel.setBounds(10, 149, 156, 25);
			adminPane.add(adminNumberLabel);
			
			JLabel adminBarcodeLabel = new JLabel("Kitap Barkodu:");
			adminBarcodeLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
			adminBarcodeLabel.setBounds(10, 193, 156, 25);
			adminPane.add(adminBarcodeLabel);
			
			JButton adminBookButton = new JButton("Kirala/\u0130ade");
			adminBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						int barcodeNumber = Integer.parseInt(adminUpdateBarcodeNumberField.getText());
						String number = adminUpdateNumberField.getText();
						
						std = dbDriver.getStudentFromNumber(number);
						book = dbDriver.getBook(barcodeNumber);
						
						if(book != null) {
							
							if(std != null) {
								
								if(book.getIsBooked().equals("F")) {
									
									if(book.getIsReserved().equals("F") || book.getIsReserved().equals(number) ) {
										//						If Book is not reserved give the book
										
										if(std.getReservedBook() == 0) {
											
											Date date = Calendar.getInstance().getTime();  
											DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
											String strDate = dateFormat.format(date);  
											
											book.setIsBooked(number);
											book.setBookedDate(strDate);
											std.setReservedBook(barcodeNumber);
											
											dbDriver.updateBook(book);
											dbDriver.updateStudent(std);
											
											JOptionPane.showMessageDialog(adminBookButton, "Kiralama Ýþlemi Baþarýlý");
										}
										else {
											JOptionPane.showMessageDialog(adminBookButton, "Sadece Bir Kitap Kiralayabilir veya Rezerve Edebilirsiniz!");
										}
										
									}
									else {
										JOptionPane.showMessageDialog(adminBookButton, "Kitap Rezerve Edilmiþ");
									}
									
								}
								else {
									
									if(book.getIsBooked().equals(number)) {
										//Return book
										book.setIsBooked("F");
										book.setBookedDate("F");
										std.setReservedBook(0);
										
										dbDriver.updateBook(book);
										dbDriver.updateStudent(std);
										
										JOptionPane.showMessageDialog(adminBookButton, "Geri Ýade Baþarýlý");
									}
									else {
										JOptionPane.showMessageDialog(adminBookButton, "Kitap Bu Öðrenci Tarafýndan Kiralanmamýþ");
									}
									
									
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Girilen Öðrenci Bulunamadý!");
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Girilen Kitap Bulunamadý!");
						}
						
						
					}
					catch (Exception er) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Lütfen Barkod Numarasýný Sayý Olarak Giriniz!");
					}
					
				}
			});
			
			adminBookButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
			adminBookButton.setBounds(10, 275, 144, 25);
			adminPane.add(adminBookButton);
			
			JLabel adminBudgetLabel = new JLabel("B\u00FCt\u00E7e");
			adminBudgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
			adminBudgetLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
			adminBudgetLabel.setBounds(342, 103, 156, 25);
			adminPane.add(adminBudgetLabel);
			
			adminTotalBudget = new JLabel("120000");
			adminTotalBudget.setHorizontalAlignment(SwingConstants.CENTER);
			adminTotalBudget.setFont(new Font("Times New Roman", Font.BOLD, 15));
			adminTotalBudget.setBounds(342, 149, 156, 25);
			adminPane.add(adminTotalBudget);
			
			JLabel adminBookProcessLabel = new JLabel("Kitap Kirala/\u0130ade");
			adminBookProcessLabel.setHorizontalAlignment(SwingConstants.CENTER);
			adminBookProcessLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
			adminBookProcessLabel.setBounds(40, 103, 167, 25);
			adminPane.add(adminBookProcessLabel);
			
			adminBudgetField = new JTextField();
			adminBudgetField.setColumns(10);
			adminBudgetField.setBounds(352, 195, 131, 23);
			adminPane.add(adminBudgetField);
			
			JButton adminAddButton = new JButton("Ekle");
			adminAddButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						int val = Integer.parseInt(adminBudgetField.getText());
						admin.setBudget(admin.getBudget() + val);
						dbDriver.updateAdmin(admin);
						updateAdminLabelInfo();
					}
					catch (Exception er) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Lütfen Bütçeyi Sayý Olarak Giriniz");
					}
				}
			});
			adminAddButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
			adminAddButton.setBounds(330, 246, 79, 36);
			adminPane.add(adminAddButton);
			
			JButton adminReduceButton = new JButton("Azalt");
			adminReduceButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						int val = Integer.parseInt(adminBudgetField.getText());
						admin.setBudget(admin.getBudget() - val);
						dbDriver.updateAdmin(admin);
						updateAdminLabelInfo();
					}
					catch (Exception er) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Lütfen Bütçeyi Sayý Olarak Giriniz");
					}
					
				}
			});
			adminReduceButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
			adminReduceButton.setBounds(419, 246, 79, 36);
			adminPane.add(adminReduceButton);
			
			JButton adminBackButton = new JButton("Back");
			adminBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Go Back to Entrance
					setInvisible();

					
					entrancePane.setVisible(true);
					entrancePane.setEnabled(true);
				}
			});
			adminBackButton.setBounds(10, 20, 79, 25);
			adminPane.add(adminBackButton);
			
			JButton adminAddBookButton = new JButton("Kitap Ekle");
			adminAddBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	//				adminPane.setVisible(false);
	//				adminPane.setEnabled(false);
					setInvisible();
					book = null;
					std = null;
					
					addBookPane.setVisible(true);
					addBookPane.setEnabled(true);
					
				}
			});
			
			adminAddBookButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
			adminAddBookButton.setBounds(342, 311, 144, 42);
			adminPane.add(adminAddBookButton);
			
			JButton adminUpdateBookButton = new JButton("Kitap G\u00FCncelle");
			adminUpdateBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	//				adminPane.setVisible(false);
	//				adminPane.setEnabled(false);
					setInvisible();
					
					updateBookPane.setVisible(true);
					updateBookPane.setEnabled(true);
				}
			});
			adminUpdateBookButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
			adminUpdateBookButton.setBounds(10, 331, 144, 25);
			adminPane.add(adminUpdateBookButton);
			
			adminUpdateNumberField = new JTextField();
			adminUpdateNumberField.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			adminUpdateNumberField.setColumns(10);
			adminUpdateNumberField.setBounds(152, 153, 131, 23);
			adminPane.add(adminUpdateNumberField);
			
			adminUpdateBarcodeNumberField = new JTextField();
			adminUpdateBarcodeNumberField.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			adminUpdateBarcodeNumberField.setColumns(10);
			adminUpdateBarcodeNumberField.setBounds(152, 197, 131, 23);
			adminPane.add(adminUpdateBarcodeNumberField);
			
			
			
			// USER PANE
			userPane = new JPanel();
			userPane.setLayout(null);
			userPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			userPane.setBounds(0, 0, 536, 413);
			contentPane.add(userPane);
			
			JLabel titleLabel_2 = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			titleLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			titleLabel_2.setBounds(48, 10, 466, 36);
			userPane.add(titleLabel_2);
			
			JButton bookProcessButton = new JButton("Kitap \u0130\u015Flemleri");
			bookProcessButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Go to book process panel
	//				userPane.setVisible(false);
	//				userPane.setEnabled(false);
					
					setInvisible();
					listBooks();
					bookProcessPane.setVisible(true);
					bookProcessPane.setEnabled(true);
				}
			});
			bookProcessButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			bookProcessButton.setBounds(44, 265, 175, 30);
			userPane.add(bookProcessButton);
			
			JButton updateButton = new JButton("Bilgi G\u00FCncelleme");
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Go to Update Panel
	//				userPane.setVisible(false);
	//				userPane.setEnabled(false);
					
					setInvisible();
					
					userUpdatePane.setVisible(true);
					userUpdatePane.setEnabled(true);
					updateUserLabelInfo();
				}
			});
			
			updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			updateButton.setBounds(286, 265, 175, 30);
			userPane.add(updateButton);
			
			JLabel userNameLabel = new JLabel("Ad:");
			userNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			userNameLabel.setBounds(44, 134, 70, 25);
			userPane.add(userNameLabel);
			
			JLabel userSurnameLabel = new JLabel("Soyad:");
			userSurnameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			userSurnameLabel.setBounds(44, 169, 70, 25);
			userPane.add(userSurnameLabel);
			
			JLabel userNumberLabel = new JLabel("Numara:");
			userNumberLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			userNumberLabel.setBounds(306, 134, 70, 25);
			userPane.add(userNumberLabel);
			
			userNameField = new JLabel("qty");
			userNameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userNameField.setBounds(124, 135, 70, 25);
			userPane.add(userNameField);
			
			userSurnameField = new JLabel("qty");
			userSurnameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userSurnameField.setBounds(124, 170, 70, 25);
			userPane.add(userSurnameField);
			
			userNumberField = new JLabel("qty");
			userNumberField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userNumberField.setBounds(391, 135, 70, 25);
			userPane.add(userNumberField);
			
			inLibraryCheck = new JCheckBox("K\u00FCt\u00FCphanedeyim");
			inLibraryCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(inLibraryCheck.isSelected()) {
						std.setIsInLibrary("T");
						dbDriver.updateStudent(std);
						updateUserLabelInfo();
					}
					else {
						std.setIsInLibrary("F");
						dbDriver.updateStudent(std);
						updateUserLabelInfo();
					}
				}
			});
			inLibraryCheck.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			inLibraryCheck.setBounds(181, 66, 135, 30);
			userPane.add(inLibraryCheck);
			
			JLabel userBookLabel = new JLabel("Kitap:");
			userBookLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			userBookLabel.setBounds(306, 169, 70, 25);
			userPane.add(userBookLabel);
			
			userBookField = new JLabel("qty");
			userBookField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userBookField.setBounds(391, 170, 70, 25);
			userPane.add(userBookField);
			
			JButton userBackButton = new JButton("Back");
			userBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Go Back to entrance
					std = null;
	//				userPane.setVisible(false);
	//				userPane.setEnabled(false);
					setInvisible();
					
					entrancePane.setVisible(true);
					entrancePane.setEnabled(true);
				}
			});
			userBackButton.setBounds(10, 20, 70, 25);
			userPane.add(userBackButton);
			
			userStudentNumberField = new JTextField();
			userStudentNumberField.setEditable(false);
			userStudentNumberField.setHorizontalAlignment(SwingConstants.CENTER);
			userStudentNumberField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userStudentNumberField.setText("300");
			userStudentNumberField.setBounds(417, 56, 59, 23);
			userPane.add(userStudentNumberField);
			userStudentNumberField.setColumns(10);
			
			
			
			// UPDATE BOOK PANE
			updateBookPane = new JPanel();
			updateBookPane.setLayout(null);
			updateBookPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			updateBookPane.setBounds(0, 0, 536, 413);
			contentPane.add(updateBookPane);
			
			JLabel titleLabel_6 = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			titleLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			titleLabel_6.setBounds(42, 10, 466, 36);
			updateBookPane.add(titleLabel_6);
			
			JLabel updateBookNameLabel = new JLabel("Ba\u015Fl\u0131k: ");
			updateBookNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			updateBookNameLabel.setBounds(26, 93, 45, 30);
			updateBookPane.add(updateBookNameLabel);
			
			updateBookNameField = new JTextField();
			updateBookNameField.setColumns(10);
			updateBookNameField.setBounds(127, 100, 120, 20);
			updateBookPane.add(updateBookNameField);
			
			JLabel updateBookAuthorLabel = new JLabel("Yazar: ");
			updateBookAuthorLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			updateBookAuthorLabel.setBounds(26, 133, 65, 30);
			updateBookPane.add(updateBookAuthorLabel);
			
			updateBookAuthorField = new JTextField();
			updateBookAuthorField.setColumns(10);
			updateBookAuthorField.setBounds(127, 140, 120, 20);
			updateBookPane.add(updateBookAuthorField);
			
			JButton updateBookBackButton = new JButton("Back");
			updateBookBackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Go Back To admin
	//				updateBookPane.setVisible(false);
	//				updateBookPane.setEnabled(false);
					
					setInvisible();
					adminPane.setVisible(true);
					adminPane.setEnabled(true);
					
				}
			});
			updateBookBackButton.setBounds(10, 20, 71, 25);
			updateBookPane.add(updateBookBackButton);
			
			updateBookPubDateField = new JTextField();
			updateBookPubDateField.setColumns(10);
			updateBookPubDateField.setBounds(127, 180, 120, 20);
			updateBookPane.add(updateBookPubDateField);
			
			JLabel updateBookPubDateLabel = new JLabel("Bas\u0131m Tarihi:");
			updateBookPubDateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			updateBookPubDateLabel.setBounds(26, 173, 91, 30);
			updateBookPane.add(updateBookPubDateLabel);
			
			JLabel updateBookPageLabel = new JLabel("Sayfa:");
			updateBookPageLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			updateBookPageLabel.setBounds(26, 213, 65, 30);
			updateBookPane.add(updateBookPageLabel);
			
			updateBookPageField = new JTextField();
			updateBookPageField.setColumns(10);
			updateBookPageField.setBounds(127, 220, 120, 20);
			updateBookPane.add(updateBookPageField);
			
			JButton updateBookButton = new JButton("G\u00FCncelle");
			updateBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						int barcodeNumber = Integer.parseInt(updateBookBarcodeNumberField.getText());
						
						book = dbDriver.getBook(barcodeNumber);
						
						if(book != null) {
							
							if(updateBookHealthField.getText().length()!= 0) {
								book.setHealth(Integer.parseInt(updateBookHealthField.getText()));
							}
							else {
								JOptionPane.showMessageDialog(null, "Saðlýk Kýsmý Boþ Olamaz");
							}
							
							if(updateBookNameField.getText().length() != 0) {								
								book.setName(updateBookNameField.getText());
							}
							else {
								JOptionPane.showMessageDialog(null, "Ad Kýsmý Boþ Olamaz");
							}
							
							if(updateBookAuthorField.getText().length() != 0) {								
								book.setAuthor(updateBookAuthorField.getText());
							}
							else {
								JOptionPane.showMessageDialog(null, "Yazar Kýsmý Boþ Olamaz");
							}
							
							
							if(updateBookPageField.getText().length() != 0) {			
								try {		
									int num = Integer.parseInt(updateBookPageField.getText());
									book.setPages(num);
								}
								catch (Exception er) {
									// TODO: handle exception
									JOptionPane.showMessageDialog(null, "Sayfa Sayýsýný Sayý Olarak Giriniz");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Sayfa Sayýsý Kýsmý Boþ Olamaz");
							}
							
							if(updateBookPubDateField.getText().length() != 0) {								
								book.setPubDate(updateBookPubDateField.getText());
							}
							else {
								JOptionPane.showMessageDialog(null, "Basým Tarihi Kýsmý Boþ Olamaz");
							}
							
							dbDriver.updateBook(book);
							
							JOptionPane.showMessageDialog(updateBookButton, "Güncellendi");
						}
						else {
							JOptionPane.showMessageDialog(null, "Barkod Numarasý Yanlýþ!");
						}
					}
					catch (Exception er) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Lütfen Sayý Gelmesi Gereken Yerleri (Saðlýk & Barkod NUmarasý) Sayý Olarak Giriniz!");
					}
				}
			});
			updateBookButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			updateBookButton.setBounds(180, 302, 130, 30);
			updateBookPane.add(updateBookButton);
			
			JLabel updateBookHealtLabel = new JLabel("Sa\u011Fl\u0131k:");
			updateBookHealtLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			updateBookHealtLabel.setBounds(286, 93, 45, 30);
			updateBookPane.add(updateBookHealtLabel);
			
			JLabel updateBookBarcodeNumberLabel = new JLabel("Barkod Numaras\u0131");
			updateBookBarcodeNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
			updateBookBarcodeNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
			updateBookBarcodeNumberLabel.setBounds(352, 223, 124, 30);
			updateBookPane.add(updateBookBarcodeNumberLabel);
			
			updateBookHealthField = new JTextField();
			updateBookHealthField.setColumns(10);
			updateBookHealthField.setBounds(356, 100, 120, 20);
			updateBookPane.add(updateBookHealthField);
			
			updateBookBarcodeNumberField = new JTextField();
			updateBookBarcodeNumberField.setColumns(10);
			updateBookBarcodeNumberField.setBounds(356, 263, 120, 20);
			updateBookPane.add(updateBookBarcodeNumberField);
			
			JButton updateBookDeleteButton = new JButton("Sil");
			updateBookDeleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						int barcodeNumber = Integer.parseInt(updateBookBarcodeNumberField.getText());
						Book delBook = dbDriver.getBook(barcodeNumber);
						if(dbDriver.deleteBook(barcodeNumber) && delBook != null) {
							updateBookHealthField.setText("");
							updateBookNameField.setText("");
							updateBookAuthorField.setText("");
							updateBookPageField.setText("");
							updateBookPubDateField.setText("");
							
							JOptionPane.showMessageDialog(updateBookButton, "Silindi");
							
						}
						else {
							
							updateBookHealthField.setText("");
							updateBookNameField.setText("");
							updateBookAuthorField.setText("");
							updateBookPageField.setText("");
							updateBookPubDateField.setText("");
							JOptionPane.showMessageDialog(null, "Barkod Numarasý Girilen Kitap Bulunamadý");
						}
						
					}
					catch (Exception er) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Lütfen Barkod Kýsmýna Sayý Giriniz");
					}
				}
			});
			updateBookDeleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			updateBookDeleteButton.setBounds(26, 302, 130, 30);
			updateBookPane.add(updateBookDeleteButton);
			
			JButton updateBookSearchButton = new JButton("Ara");
			updateBookSearchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						int barcodeNumber = Integer.parseInt(updateBookBarcodeNumberField.getText());
						
						book = dbDriver.getBook(barcodeNumber);
						if(book != null) {
							
							updateBookHealthField.setText(Integer.toString(book.getHealth()));
							updateBookNameField.setText(book.getName());
							updateBookAuthorField.setText(book.getAuthor());
							updateBookPageField.setText(Integer.toString(book.getPages()));
							updateBookPubDateField.setText(book.getPubDate());
						}
						else {
							JOptionPane.showMessageDialog(null, "Bu Barkod Numaralý Kitap Bulunamadý");
						}
					}
					catch (Exception er) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Lütfen Barkod Kýsmýna Sayý Giriniz");
					}
				
				}
			});
			
			updateBookSearchButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			updateBookSearchButton.setBounds(379, 293, 79, 42);
			updateBookPane.add(updateBookSearchButton);
			listBooks();
			
			
			
			
			
	//		ENTRANCE PANE
			entrancePane = new JPanel();
			entrancePane.setLayout(null);
			entrancePane.setBorder(new EmptyBorder(5, 5, 5, 5));
			entrancePane.setBounds(0, 0, 536, 413);
			contentPane.add(entrancePane);
			
			JLabel entranceLabel = new JLabel("Y\u0131ld\u0131z K\u00FCt\u00FCphane Bilgi Sistemi");
			entranceLabel.setHorizontalAlignment(SwingConstants.CENTER);
			entranceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			entranceLabel.setBounds(10, 173, 476, 47);
			entrancePane.add(entranceLabel);
			
			JButton userLoginButton = new JButton("\u00D6\u011Frenci Giri\u015F");
			userLoginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Disable entrance pane, enable user login pane
	//				entrancePane.setVisible(false);
	//				entrancePane.setEnabled(false);
					
					setInvisible();
					loginPane.setVisible(true);
					loginPane.setEnabled(true);
	
				}
			});
			
			userLoginButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			userLoginButton.setBounds(200, 230, 120, 30);
			entrancePane.add(userLoginButton);
			
			JButton adminLoginButton = new JButton("Admin Giri\u015F");
			adminLoginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Disable entrance pane, enable adminLogin pane
	//				entrancePane.setVisible(false);
	//				entrancePane.setEnabled(false);
					
					setInvisible();
					adminLoginPane.setVisible(true);
					adminLoginPane.setEnabled(true);
				}
			});
			adminLoginButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			adminLoginButton.setBounds(200, 270, 120, 30);
			entrancePane.add(adminLoginButton);
			
			JButton registerButton = new JButton("Kay\u0131t");
			registerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Disable Entrance pane, enable registration pane
	//				entrancePane.setVisible(false);
	//				entrancePane.setEnabled(false);
					setInvisible();
					
					registrationPane.setVisible(true);
					registrationPane.setEnabled(true);
				}
			});
			
			registerButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			registerButton.setBounds(200, 310, 120, 30);
			entrancePane.add(registerButton);
			
			JLabel logo = new JLabel("");
			logo.setIcon(new ImageIcon(img));
			logo.setBounds(180, 10, 150, 153);
			entrancePane.add(logo);
		
		}
	}
	
	public void updateAdminLabelInfo() {
		adminTotalBudget.setText(Integer.toString(admin.getBudget()));
	}
	
	public void updateUserLabelInfo() {
		int counter = 0;
		userNameField.setText(std.getName());
		userSurnameField.setText(std.getSurname());	
		userNumberField.setText(std.getNumber());
		System.out.println("Updating user Info");
		
		Book bk = dbDriver.getBook(std.getReservedBook());
		if(bk != null) {
			userBookField.setText(bk.getName());
		}
		else {
			userBookField.setText("");
		}
		
		//Find Student Number in Library
		ResultSet myRes = dbDriver.getAllStudents();
		try {
			while(myRes.next()) {
				if(myRes.getString("isInLibrary").equals("T")) {
					counter ++;
//					System.out.println("there is person");
				}
			}
			System.out.println("Counted num: "+ counter);
			userStudentNumberField.setText(Integer.toString(counter));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(std.getIsInLibrary().equals("T")) {
			inLibraryCheck.setSelected(true);
		}
		else {
			inLibraryCheck.setSelected(false);
		}
		
		
		userUpdateNameField.setText(std.getName());
		userUpdateSurnameField.setText(std.getSurname());
		userUpdateNumberField.setText(std.getNumber());
		userUpdateMailField.setText(std.getMail());
		userUpdatePasswordField.setText(std.getPassword());
	}
	
	public void listBooks() {
		ResultSet myRes = dbDriver.getAllBooks();
		DefaultListModel<String> myList = new DefaultListModel<>();
		
		try {
			while(myRes.next()) {
				String name = myRes.getString("name");
				int barcodeNumber = myRes.getInt("barcodeNumber");
				System.out.println("Adding "+"adý: " +name+" barkod numarasý: "+barcodeNumber);
				myList.addElement(name+"/ barkod: "+barcodeNumber);
			}
			
			bookBookList = new JList<String>(myList);
			bookBookList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(bookBookList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void placeBookInfo(int barcodeNumber) {
		System.out.println("barcodeNumber: "+barcodeNumber);
		book = dbDriver.getBook(barcodeNumber);
		bookNameField.setText(book.getName());
		bookAuthorField.setText(book.getAuthor());
		bookPubDateField.setText(book.getPubDate());
		bookPageField.setText(Integer.toString(book.getPages()));
	}
	
	public void setInvisible() {
		
		entrancePane.setVisible(false);
		entrancePane.setEnabled(false);
		
		loginPane.setVisible(false);
		loginPane.setEnabled(false);
		
		adminPane.setVisible(false);
		adminPane.setEnabled(false);
		
		userUpdatePane.setVisible(false);
		userUpdatePane.setEnabled(false);
		
		registrationPane.setVisible(false);
		registrationPane.setEnabled(false);
		
		userPane.setVisible(false);
		userPane.setEnabled(false);
		
		adminLoginPane.setVisible(false);
		adminLoginPane.setEnabled(false);
		
		bookProcessPane.setVisible(false);
		bookProcessPane.setEnabled(false);
		
		addBookPane.setVisible(false);
		addBookPane.setEnabled(false);
		
		updateBookPane.setVisible(false);
		updateBookPane.setEnabled(false);

	}
}
