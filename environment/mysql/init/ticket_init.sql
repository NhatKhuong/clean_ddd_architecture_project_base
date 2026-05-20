CREATE DATABASE IF NOT EXISTS vattunongnghiep CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE vattunongnghiep;

CREATE TABLE IF NOT EXISTS product_categories_eco (
                                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                                      code VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NULL,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_product_categories_eco_code (code)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS customers_eco (
                                             id BIGINT NOT NULL AUTO_INCREMENT,
                                             name VARCHAR(255) NOT NULL,
    phone VARCHAR(50) NULL,
    email VARCHAR(255) NULL,
    adress VARCHAR(255) NULL,
    created_by BIGINT NULL,
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by BIGINT NULL,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_customers_eco_email (email)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS products_eco (
                                            id BIGINT NOT NULL AUTO_INCREMENT,
                                            name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    description TEXT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT NULL,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by BIGINT NULL,
    price DOUBLE NULL,
    currency VARCHAR(20) NULL,
    quantity BIGINT NULL,
    category_id BIGINT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_products_eco_code (code),
    KEY idx_products_eco_category_id (category_id),
    CONSTRAINT fk_products_eco_category FOREIGN KEY (category_id) REFERENCES product_categories_eco(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS product_image_eco (
                                                 id BIGINT NOT NULL AUTO_INCREMENT,
                                                 product_id BIGINT NOT NULL,
                                                 original_url VARCHAR(2048) NOT NULL,
    `primary` BOOLEAN NOT NULL DEFAULT FALSE,
    sort_order BIGINT NULL,
    PRIMARY KEY (id),
    KEY idx_product_image_eco_product_id (product_id),
    CONSTRAINT fk_product_image_eco_product FOREIGN KEY (product_id) REFERENCES products_eco(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS orders_eco (
                                          id BIGINT NOT NULL AUTO_INCREMENT,
                                          customer_id BIGINT NOT NULL,
                                          total_amount DOUBLE NOT NULL DEFAULT 0,
                                          created_by BIGINT NULL,
                                          created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
                                          updated_by BIGINT NULL,
                                          updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                          status VARCHAR(50) NULL,
    PRIMARY KEY (id),
    KEY idx_orders_eco_customer_id (customer_id),
    CONSTRAINT fk_orders_eco_customer FOREIGN KEY (customer_id) REFERENCES customers_eco(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS orders_details_eco (
                                                  id BIGINT NOT NULL AUTO_INCREMENT,
                                                  order_id BIGINT NOT NULL,
                                                  product_id BIGINT NOT NULL,
                                                  quantity BIGINT NOT NULL DEFAULT 1,
                                                  price DOUBLE NOT NULL DEFAULT 0,
                                                  total_price DOUBLE NOT NULL DEFAULT 0,
                                                  PRIMARY KEY (id),
    KEY idx_orders_details_eco_order_id (order_id),
    KEY idx_orders_details_eco_product_id (product_id),
    CONSTRAINT fk_orders_details_eco_order FOREIGN KEY (order_id) REFERENCES orders_eco(id) ON DELETE CASCADE,
    CONSTRAINT fk_orders_details_eco_product FOREIGN KEY (product_id) REFERENCES products_eco(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO product_categories_eco (code, name, created_by, updated_by) VALUES
                                                                            ('PHANBON', 'Phân bón', 1, 1),
                                                                            ('THUOCBVTV', 'Thuốc bảo vệ thực vật', 1, 1),
                                                                            ('HATGIONG', 'Hạt giống', 1, 1)
    ON DUPLICATE KEY UPDATE name = VALUES(name), updated_by = VALUES(updated_by);

INSERT INTO customers_eco (name, phone, email, adress, created_by, updated_by) VALUES
                                                                                   ('Nguyễn Văn An', '0901234567', 'an.nguyen@example.com', 'Củ Chi, TP.HCM', 1, 1),
                                                                                   ('Trần Thị Bình', '0912345678', 'binh.tran@example.com', 'Hóc Môn, TP.HCM', 1, 1),
                                                                                   ('Lê Văn Cường', '0923456789', 'cuong.le@example.com', 'Đức Hòa, Long An', 1, 1)
    ON DUPLICATE KEY UPDATE name = VALUES(name), phone = VALUES(phone), adress = VALUES(adress), updated_by = VALUES(updated_by);

INSERT INTO products_eco (name, code, description, active, created_by, updated_by, price, currency, quantity, category_id) VALUES
                                                                                                                               ('Phân NPK 16-16-8', 'SP001', 'Phân bón tổng hợp dùng cho nhiều loại cây trồng', TRUE, 1, 1, 350000, 'VND', 120, 1),
                                                                                                                               ('Thuốc trừ sâu sinh học Neem', 'SP002', 'Thuốc sinh học hỗ trợ phòng trừ sâu hại', TRUE, 1, 1, 185000, 'VND', 80, 2),
                                                                                                                               ('Hạt giống cà chua F1', 'SP003', 'Hạt giống cà chua năng suất cao', TRUE, 1, 1, 95000, 'VND', 200, 3)
    ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description), active = VALUES(active), updated_by = VALUES(updated_by), price = VALUES(price), currency = VALUES(currency), quantity = VALUES(quantity), category_id = VALUES(category_id);

INSERT INTO product_image_eco (product_id, original_url, `primary`, sort_order) VALUES
                                                                                    (1, 'https://example.com/images/npk-16-16-8.jpg', TRUE, 1),
                                                                                    (2, 'https://example.com/images/neem.jpg', TRUE, 1),
                                                                                    (3, 'https://example.com/images/hat-giong-ca-chua-f1.jpg', TRUE, 1);

INSERT INTO orders_eco (customer_id, total_amount, created_by, updated_by, status) VALUES
                                                                                       (1, 700000, 1, 1, 'PENDING'),
                                                                                       (2, 185000, 1, 1, 'COMPLETED');

INSERT INTO orders_details_eco (order_id, product_id, quantity, price, total_price) VALUES
                                                                                        (1, 1, 2, 350000, 700000),
                                                                                        (2, 2, 1, 185000, 185000);