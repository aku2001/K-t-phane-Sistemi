Tanım:

	Basit ama işlevsel bir kütüphane sistemidir. Bu sistemde ilk başta bir admin oluşturulur ve kitap işlemleri admin üzerinden gerçekleştirilir.

	İlk giriş kısmında üyelik girişi, kayıt ve admin girişi kısımları vardır. Kayıt yaptıran öğrenciler, üye girişinden giriş yapabilirler.
	Üye sayfasından kitap rezerve edip, kullanıcı bilgilerini güncelleyebilirler. 

	Admin girişinden sadece adminler girebilir. Kütüphanenin bütçe işlemlerini buradan düzenleyebilir. Öğrencilere kitap kiralama/iade, kitap ekleme/güncelleme/silme işlemleri 
	de buradan halledilir. 

	Kayıt kısmında öğrenciler kayıt yaptırabilir. Öğrenci numarası ve kullanıcı adları farklı olmak zorundadır.


Kütüphane Sisteminin Kullanımının Video Linki:
https://drive.google.com/file/d/1TxSym3uCoJ6edbQs_m0hMDgFhZbN-Jqy/view?usp=sharing

Veri Tabanı Konfigürasyonu:
	
	Mysql workbench kullanılarak geliştirildi. Denemek için aynı şekilde kullanılabilir.
	Önce lokal bir veri tabanı oluşturun ve ona bir şifre atayın. Kullanıcı adı genelde root oluyor. 
	jdbc:mysql://localhost:3306/ url'i ile lokal veri tabanınıza bağlanabilirsiniz.
	Oluşturduğunuz lokal veri tabanında bir schema oluşturunuz. 
	
	Kodda Driver.java 'nın içerisinde dbUrl, dbName, dbUserName ve dbPassw var bu kısımları size uygun olacak bir biçimde değiştiriniz.
	dbUrl lokal veri tabanınızın url'i
	dbName oluşturduğunuz schema'nın adı
	dbUsername ve dbPassw de lokal veri tabanınıza bağlanmak için gereken kullanıcı adı ve şifre.
  	
	Bundan sonra kodu çalıştırdığınızda, veri tabanında eksik tabloların olduğu tespit edilecek ve oluşturulacaktır.

Kullanılan Kütüphaneler:
	java.sql -> mysql-connector-java-9.0.29.jar
	Java Swing 
	Java Awt
	Java util

Derleme ve Çalıştırma:
	open-jdk 15 ile derlenip test edildi.
	Eclipse geliştirme ortamı kullanıldı.
	Main dosya src/LibrarySystemGUI.java 'dır.
	
UYARI:
	Türkçe karakterler olduğu için direk javac ile derlenemez encoding'i utf-8 yapılmalıdır. 
	Eclipse uygulaması kullanılarak direkmen derlenebilir.



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// ENGLISH VERSION//


Definition:

It is a simple but functional library system. In this system, an admin is created at first and transactions about the books are carried out through the admin.

In the first login section, there are membership login, registration and admin login sections. Registered students can log in from the member login.
They can reserve a book from the member page and update their user information.

Only admins can enter from the admin login. You can edit the library's budget transactions here. Book rental/return, adding/updating/deleting books to students
is also resolved here.

Students can register in the registration section. Student number and username must be different.


Library System Usage Video Link:
https://drive.google.com/file/d/1TxSym3uCoJ6edbQs_m0hMDgFhZbN-Jqy/view?usp=sharing

Database Configuration:

It is developed by using mysql workbench. It can be used in the same way for experimentation.
First create a local database and assign a password to it. Username is usually root.
You can connect to your local database with the url jdbc:mysql://localhost:3306/.
Create a schema in the local database you created.

In the code, there are dbUrl, dbName, dbUserName and dbPassw in Driver.java, change these parts to suit you.
dbUrl is the url of your local database
dbName is the name of the schema you created
Username and password required to connect to your local database in dbUsername and dbPassw.
  
After that, when you run the code, the missing tables are going to be detected and they will be created automatically.

Libraries Used:
java.sql -> mysql-connector-java-9.0.29.jar
Java Swing
Java Awt
Java util

Compiling and Running:
Compiled and tested with open-jdk 15.
Eclipse development environment was used.
The main file is src/LibrarySystemGUI.java .

WARNING:
Since there are Turkish characters, it cannot be compiled directly with javac, its encoding must be utf-8.
It can be compiled directly using the Eclipse application.

