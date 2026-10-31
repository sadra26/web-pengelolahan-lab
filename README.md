# 📚 Lab Management System

Aplikasi **Lab Management System** adalah sistem berbasis **Spring Boot** yang digunakan untuk mengelola data laboratorium komputer, seperti data komputer, peminjam, dan peminjaman.

---

# 🚀 Teknologi yang Digunakan

- Java 17/19+
- Spring Boot
- Spring Data JPA 
- Spring Web
- Thymeleaf
- MySQL
- Maven
- Tailwindcss

---

# 📋 Persyaratan

Pastikan software berikut sudah terinstall pada komputer Anda.

| Software | Versi yang Disarankan |
|----------|-----------------------|
| Java JDK | 17 atau lebih baru |
| Maven | 3.9+ |
| MySQL | 8.x |
| Git | Opsional |

---

# ☕ Instalasi Java

Pastikan Java sudah terinstall.

Cek versi Java:

```bash
java -version
```

Jika muncul versi Java, berarti instalasi berhasil.

---

# 🔨 Instalasi Maven (Global)

## 1. Download Maven

Download Apache Maven:

https://maven.apache.org/download.cgi

Pilih file Binary zip archive.

---

## 2. Extract

Ekstrak ke folder misalnya

```
C:\Tools\apache-maven-3.9.11
```

---

## 3. Tambahkan Environment Variable

Tambahkan folder berikut ke **PATH**

```
C:\Tools\apache-maven-3.9.11\bin
```

---

## 4. Cek Instalasi

Buka Command Prompt lalu jalankan

```bash
mvn -version
```

Apabila berhasil akan muncul informasi seperti

```
Apache Maven 3.9.x
Java version: xx
```

---

# 🛢️ Konfigurasi Database

1. Jalankan MySQL.
2. Buat database.

Contoh

```sql
CREATE DATABASE lab_management;
```

3. Buka file

```
src/main/resources/application.properties
```

Sesuaikan konfigurasi berikut.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lab_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# 📥 Clone Project

```bash
git clone <repository-url>
```

Masuk ke folder project

```bash
cd nama-project
```

---

# ▶️ Menjalankan Project

Pastikan berada di folder project, kemudian jalankan

```bash
mvn spring-boot:run
```

Tunggu hingga pesan berhenti tidak menampilkan pesan apapun di terminal

```
Jika pesan menampilakan lokasi folder nya (C/users/), maka gagal menjalankannya
---

# 🌐 Akses Aplikasi

Secara default Spring Boot berjalan pada port **8080**.

Buka browser dan akses

```
http://localhost:8080
```

Jika project memiliki halaman login, maka halaman tersebut akan tampil secara otomatis.

---

# 📁 Struktur Project

```
src
├── main
│   ├── java
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── model
│   │   └── LabManagementApplication.java
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
```

---

# 🔧 Build Project

Untuk melakukan build project

```bash
mvn clean install
```

File hasil build akan berada pada folder

```
target/
```

---

# 🛠️ Troubleshooting

## Maven tidak dikenali

Jika muncul

```
'mvn' is not recognized...
```

Pastikan:

- Maven sudah diinstall.
- Folder **bin** Maven sudah ditambahkan ke Environment Variable **PATH**.
- Restart Command Prompt setelah mengubah PATH.

---

## Port 8080 sedang digunakan

Jika muncul error

```
Port 8080 was already in use
```

Tutup aplikasi lain yang menggunakan port tersebut atau ubah port pada

```properties
server.port=8081
```

Kemudian akses

```
http://localhost:8081
```

---

## Gagal Terhubung ke Database

Pastikan:

- MySQL sudah berjalan.
- Database sudah dibuat.
- Username dan password pada `application.properties` sudah benar.
