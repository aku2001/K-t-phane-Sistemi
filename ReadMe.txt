Tanım:

	Basit ama işlevsel bir kütüphane sistemidir. Bu sistemde ilk başta bir admin oluşturulur ve kitap işlemleri admin üzerinden gerçekleştirilir.

	İlk giriş kısmında üyelik girişi, kayıt ve admin girişi kısımları vardır. Kayıt yaptıran öğrenciler, üye girişinden giriş yapabilirler.
	Üye sayfasından kitap rezerve edip, kullanıcı bilgilerini güncelleyebilirler. 

	Admin girişinden sadece adminler girebilir. Kütüphanenin bütçe işlemlerini buradan düzenleyebilir. Öğrencilere kitap kiralama/iade, kitap ekleme/güncelleme/silme işlemleri 
	de buradan halledilir. 

	Kayıt kısmında öğrenciler kayıt yaptırabilir. Öğrenci numarası ve kullanıcı adları farklı olmak zorundadır.


Kullanım Video Linki:
https://drive.google.com/file/d/1MxPPOZium10kN4ZWJsVFu3r-wEcG452m/view?usp=sharing

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








