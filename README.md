# Majumundur API

## Deskripsi
Majumundur API adalah RESTful API yang digunakan untuk mengelola transaksi, produk, merchant, customer, dan autentikasi dalam sistem e-commerce.

## Fitur Utama
- Manajemen transaksi
- Manajemen produk
- Manajemen merchant
- Manajemen customer
- Sistem autentikasi untuk merchant dan customer
- **Dokumentasi API dengan Swagger**

## Teknologi yang Digunakan
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok
- **Swagger OpenAPI** (untuk dokumentasi API)

## Dokumentasi API
API ini didokumentasikan menggunakan **Swagger UI**.  
Untuk melihat dokumentasi lengkap dan menguji endpoint secara langsung, jalankan aplikasi dan akses:  
📌 **`http://localhost:xxxx/swagger-ui/index.html#/`**  

## Struktur Endpoint
### Autentikasi (`/api/auth`)
- **POST** `/merchant/signup` → Mendaftar sebagai merchant
- **POST** `/customer/signup` → Mendaftar sebagai customer
- **GET** `/login` → Login merchant atau customer
- **GET** `/logout` → Logout

### Customer (`/api/customers`)
- **POST** `/{user_id}` → Membuat customer baru
- **GET** `/{id}` → Mendapatkan customer berdasarkan ID
- **PUT** `/` → Memperbarui data customer
- **DELETE** `/{id}` → Menghapus customer berdasarkan ID

### Merchant (`/api/merchants`)
- **POST** `/{user_id}` → Membuat merchant baru
- **GET** `/{id}` → Mendapatkan merchant berdasarkan ID
- **PUT** `/` → Memperbarui data merchant
- **DELETE** `/{id}` → Menghapus merchant berdasarkan ID

### Produk (`/api/products`)
- **POST** `/` → Menambahkan produk baru
- **GET** `/` → Mendapatkan daftar produk dengan filter opsional
- **GET** `/{id}` → Mendapatkan produk berdasarkan ID
- **PUT** `/` → Memperbarui produk
- **DELETE** `/{id}` → Menghapus produk berdasarkan ID

### Transaksi (`/api/transactions`)
- **POST** `/` → Membuat transaksi baru
- **GET** `/{id}` → Mendapatkan transaksi berdasarkan ID
- **PUT** `/` → Memperbarui transaksi
- **DELETE** `/{id}` → Menghapus transaksi berdasarkan ID

## Clone Repository Ini
   ```sh
   git clone https://github.com/Reminerva/maju-mundur.git
   ```

## Catatan
   Setting terlebih dahulu application.properties di src/main/resources/application.properties sebelum aplikasi digunakan.

## Kontributor
- Reksa Alamsyah
