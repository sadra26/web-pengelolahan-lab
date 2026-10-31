untuk running : lihat file mvnw.cmd. Apakah berada di dalam folder lab-management atau tidak. Jika iya, maka hanya perlu alamat terminal-nya di lab-management lalu berikan run command "./mvnw.cmd spring-boot:run".

netstat -ano | findstr : port kamu : ini adalah cara untuk mengetahui port sedang digunakan atau tidak.

Constructor : method khusus yang otomatis dipanggil saat sebuah object dibuat dengan new.

import org.springframework.ui.Model : digunakan untuk mengirim data dari Controller ke View (biasanya Thymeleaf, JSP, atau template engine lainnya).

Hibernate adalah salah satu istilah pada Spring Data JPA sebagai penerjemah data java menjadi query SQL.

Spring Data JPA adalah alat yang membantu Java berkomunikasi dengan database tanpa perlu banyak menulis SQL.

Anotasi adalah cara merepresentasikan class dengan tools yang digunakan

@RestController
Membuat class menjadi controller API yang bisa menerima request dan mengirim response (biasanya JSON atau String).

@Controller
Digunakan jika ingin mengembalikan halaman HTML (Thymeleaf, JSP, dll.).

@RequestMapping
Digunakan untuk mengambil berbagai data (GET, POST, PUT, DELETE).

@GetMapping
Digunakan untuk mengambil atau membaca data (GET).

@PostMapping
Digunakan untuk menambahkan atau menyimpan data baru (POST).

@PutMapping
Digunakan untuk mengubah atau memperbarui data yang sudah ada (PUT).

@DeleteMapping
Digunakan untuk menghapus data (DELETE).

@RequestBody
Mengambil data dari body request (biasanya format JSON) dan mengubahnya menjadi object Java.

@PathVariable
Mengambil nilai yang ada di URL.

Contoh:

/user/1

Nilai 1 dapat diambil sebagai ID.

@RequestParam
Mengambil parameter yang dikirim melalui URL.

Contoh:

/search?name=Sadra

Nilai Sadra dapat diambil.

JpaRepository
Interface yang menyediakan operasi database otomatis seperti save(), findAll(), findById(), dan deleteById() tanpa perlu menulis SQL.

@Entity → class = tabel.
@Table → nama tabel.
@Id → primary key.
@GeneratedValue → ID otomatis.
GenerationType.IDENTITY → auto increment dari database.

userRepository.save(user);       // Simpan data
userRepository.findAll();        // Ambil semua data
userRepository.findById(id);     // Cari berdasarkan ID
userRepository.deleteById(id);   // Hapus berdasarkan ID
userRepository.count();          // Hitung jumlah data