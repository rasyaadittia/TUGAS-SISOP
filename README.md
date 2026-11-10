# Praktikum Java RMI - Monitoring Data Mahasiswa

## Isi
- `rmi-common`: interface remote dan model Mahasiswa.
- `rmi-server`: implementasi layanan dan RMI Registry port 1099.
- `rmi-client`: program console untuk menguji koneksi dan mengambil data.
- `rmi-web`: aplikasi JSP/Servlet sebagai perantara browser ke RMI Server.

## Kebutuhan
JDK 17, Maven, Apache Tomcat 10.1+, NetBeans/IntelliJ/VS Code dengan ekstensi Java.

## Menjalankan di satu komputer
1. Buka terminal pada folder proyek.
2. Jalankan `mvn clean package`.
3. Jalankan server: `java -jar rmi-server/target/rmi-server-1.0.0-jar-with-dependencies.jar`.
4. Uji client pada terminal lain: `java -jar rmi-client/target/rmi-client-1.0.0-jar-with-dependencies.jar`.
5. Salin `rmi-web/target/monitoring-mahasiswa.war` ke folder `webapps` Tomcat.
6. Jalankan Tomcat dan buka `http://localhost:8080/monitoring-mahasiswa/mahasiswa`.

Login demo: username `admin`, password `admin123`.

## Menggunakan dua komputer pada LAN
1. Jalankan RMI Server di komputer server dan cari IP LAN server (misalnya `192.168.1.10`).
2. Jalankan server dengan `java -Djava.rmi.server.hostname=192.168.1.10 -jar rmi-server/target/rmi-server-1.0.0-jar-with-dependencies.jar`.
3. Izinkan koneksi TCP port 1099 dan port objek RMI pada firewall. Untuk jaringan praktikum, pastikan kedua komputer berada di subnet yang sama.
4. Jalankan client: `java -jar rmi-client/target/rmi-client-1.0.0-jar-with-dependencies.jar 192.168.1.10`.
5. Atur environment variable `RMI_HOST=192.168.1.10` pada komputer Tomcat sebelum Tomcat dijalankan, lalu deploy WAR.
6. Buka aplikasi web melalui alamat Tomcat.

Catatan: untuk konfigurasi firewall produksi, tetapkan port RMI objek secara tetap dan gunakan autentikasi serta TLS/VPN. Jangan membuka RMI ke internet secara langsung.

## Catatan implementasi
Data contoh disimpan di memori (`ConcurrentHashMap`), sehingga akan kembali ke data awal saat server dimulai ulang. MySQL bersifat opsional pada lembar tugas dan belum diaktifkan pada contoh ini. Login demo masih memakai kredensial contoh dan perlu diganti untuk penggunaan sebenarnya. Fitur CRUD tersedia melalui tambah dan hapus pada halaman; method update sudah tersedia di service untuk pengembangan form edit.
