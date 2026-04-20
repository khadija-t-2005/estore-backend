package com.estore.estorebackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Users
        jdbcTemplate.execute("""
            INSERT IGNORE INTO users (email, password, role, created_at)
            VALUES
            ('admin@estore.ma', '$2a$10$8.UnVuG9HHgffUDAlk8q6uy59u6F62.vK1YI.Z2d1a3A3.n.6.1.', 'ADMIN', NOW()),
            ('user1@email.com', '$2a$10$8.UnVuG9HHgffUDAlk8q6uy59u6F62.vK1YI.Z2d1a3A3.n.6.1.', 'CUSTOMER', NOW())
        """);

        // Categories
        jdbcTemplate.execute("""
            INSERT IGNORE INTO categories (name, description)
            VALUES
            ('Electronics', 'Smartphones, Laptops and gadgets'),
            ('Fashion', 'Clothing, shoes and accessories'),
            ('Home & Garden', 'Furniture and decoration')
        """);

        // Products
        jdbcTemplate.execute("""
            INSERT IGNORE INTO products (name, description, price, category_id, stock_quantity)
            VALUES
            ('iPhone 15 Pro', 'Apple smartphone 256GB', 12000.00, 1, 10),
            ('MacBook Air M2', 'Laptop Apple 13 inch', 14000.00, 1, 5),
            ('T-Shirt Cotton', 'Premium white t-shirt', 150.00, 2, 50)
        """);

        System.out.println("✅ Données initialisées avec succès!");
    }
}