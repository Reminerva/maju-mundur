# Majumundur API

## Deskripsi
Majumundur API adalah RESTful API yang digunakan untuk mengelola transaksi, produk, merchant, customer, dan autentikasi dalam sistem e-commerce.

## Fitur Utama
- Manajemen transaksi
- Manajemen produk
- Manajemen merchant
- Manajemen customer
- Sistem autentikasi untuk merchant dan customer

## Teknologi yang Digunakan
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok

## Struktur Endpoint
### Autentikasi (`/api/auth`)
- **POST** `/merchant/signup` → Mendaftar sebagai merchant
- **POST** `/customer/signup` → Mendaftar sebagai customer

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

## Cara Menjalankan
1. Clone repository ini
   ```sh
   git clone https://github.com/username/majumundur-api.git
   ```
2. Masuk ke direktori proyek
   ```sh
   cd majumundur-api
   ```
3. Bangun proyek menggunakan Maven atau Gradle
   ```sh
   mvn clean install
   ```
4. Jalankan aplikasi
   ```sh
   mvn spring-boot:run
   ```

## Kontributor
- Reksa Alamsyah


