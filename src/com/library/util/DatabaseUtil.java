package com.library.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;

public class DatabaseUtil {

    private static final Properties properties = new Properties();

    // 'static' blok, bu sınıf yüklendiğinde SADECE BİR KEZ çalışır.
    // Config dosyasını okumak için mükemmel bir yerdir.
    static {
        try (InputStream input = new FileInputStream("config.properties")) {

            // config.properties dosyasını yükle
            properties.load(input);

            // JDBC Sürücüsünü (Tercümanı) yükle
            Class.forName("org.postgresql.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            // Eğer config dosyası veya sürücü bulunamazsa, uygulama başlayamaz.
            throw new RuntimeException("Veritabanı yapılandırması yüklenemedi!", e);
        }
    }

    // Bu sınıfın 'new DatabaseUtil()' ile nesnesinin yaratılmasını engelliyoruz.
    private DatabaseUtil() {
    }

    /**
     * PostgreSQL veritabanına yeni bir bağlantı (Connection) açar ve döndürür.
     * @return Aktif bir java.sql.Connection objesi
     * @throws SQLException Bağlantı hatası olursa
     */
    public static Connection getConnection() throws SQLException {
        // Bilgileri artık 'properties' nesnesinden alıyoruz
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );
    }
}