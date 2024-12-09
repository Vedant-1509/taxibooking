INSERT INTO app_user (name, email, password) VALUES
('Aarav Mehta', 'aarav.mehta@example.com', 'password123'),
('Aditi Sharma', 'aditi.sharma@example.com', 'password123'),
('Aryan Patel', 'aryan.patel@example.com', 'password123'),
('Ishita Verma', 'ishita.verma@example.com', 'password123'),
('Kunal Desai', 'kunal.desai@example.com', 'password123'),
('Riya Kapoor', 'riya.kapoor@example.com', 'password123'),
('Aditya Joshi', 'aditya.joshi@example.com', 'password123'),
('Meera Iyer', 'meera.iyer@example.com', 'password123'),
('Rohan Malhotra', 'rohan.malhotra@example.com', 'password123'),
('Pooja Singh', 'pooja.singh@example.com', 'password123'),
('Kabir Gupta', 'kabir.gupta@example.com', 'password123'),
('Sanya Roy', 'sanya.roy@example.com', 'password123'),
('Nikhil Menon', 'nikhil.menon@example.com', 'password123'),
('Simran Chawla', 'simran.chawla@example.com', 'password123'),
('Vikram Nair', 'vikram.nair@example.com', 'password123'),
('Ananya Rao', 'ananya.rao@example.com', 'password123'),
('Rajesh Kulkarni', 'rajesh.kulkarni@example.com', 'password123'),
('Tara Reddy', 'tara.reddy@example.com', 'password123'),
('Neha Saxena', 'neha.saxena@example.com', 'password123'),
('Varun Pillai', 'varun.pillai@example.com', 'password123');

-- Assign roles to users in the user_roles table
INSERT INTO user_roles (user_id, roles) VALUES
(1, 'RIDER'),
(2, 'RIDER'),
(2, 'DRIVER'),
(3, 'RIDER'),
(3, 'DRIVER'),
(4, 'RIDER'),
(4, 'DRIVER'),
(5, 'RIDER'),
(5, 'DRIVER'),
(6, 'RIDER'),
(6, 'DRIVER'),
(7, 'RIDER'),
(7, 'DRIVER'),
(8, 'RIDER'),
(8, 'DRIVER'),
(9, 'RIDER'),
(9, 'DRIVER'),
(10, 'RIDER'),
(10, 'DRIVER'),
(11, 'RIDER'),
(11, 'DRIVER'),
(12, 'RIDER'),
(12, 'DRIVER'),
(13, 'RIDER'),
(13, 'DRIVER'),
(14, 'RIDER'),
(14, 'DRIVER'),
(15, 'RIDER'),
(15, 'DRIVER'),
(16, 'RIDER'),
(16, 'DRIVER'),
(17, 'RIDER'),
(17, 'DRIVER'),
(18, 'RIDER'),
(18, 'DRIVER'),
(19, 'RIDER'),
(19, 'DRIVER'),
(20, 'RIDER'),
(20, 'DRIVER');


INSERT INTO rider (id,user_id,rating)VALUES
(1,1,4.9);

INSERT INTO driver (id, user_id, rating, available, current_location) VALUES
(2, 2, 4.7, true, ST_GeomFromText('POINT(77.1025 28.7041)', 4326)), -- New Delhi
(3, 3, 4.5, false, ST_GeomFromText('POINT(77.0266 28.4595)', 4326)), -- Gurugram
(4, 4, 4.8, true, ST_GeomFromText('POINT(77.3910 28.5355)', 4326)), -- Noida
(5, 5, 4.2, false, ST_GeomFromText('POINT(77.4538 28.6692)', 4326)), -- Faridabad
(6, 6, 4.9, true, ST_GeomFromText('POINT(77.2507 28.5869)', 4326)), -- South Delhi
(7, 7, 4.6, true, ST_GeomFromText('POINT(77.0910 28.6378)', 4326)), -- Old Delhi
(8, 8, 4.4, false, ST_GeomFromText('POINT(77.2090 28.6139)', 4326)), -- Connaught Place
(9, 9, 4.3, true, ST_GeomFromText('POINT(77.3178 28.6679)', 4326)), -- Ghaziabad
(10, 10, 4.7, true, ST_GeomFromText('POINT(77.0500 28.6467)', 4326)), -- Dwarka
(11, 11, 4.1, false, ST_GeomFromText('POINT(77.2888 28.7045)', 4326)), -- East Delhi
(12, 12, 4.5, true, ST_GeomFromText('POINT(77.1840 28.5547)', 4326)), -- Vasant Kunj
(13, 13, 4.6, false, ST_GeomFromText('POINT(77.0801 28.6278)', 4326)), -- Karol Bagh
(14, 14, 4.3, true, ST_GeomFromText('POINT(77.2481 28.6132)', 4326)), -- Green Park
(15, 15, 4.8, true, ST_GeomFromText('POINT(77.1115 28.6550)', 4326)), -- Patel Nagar
(16, 16, 4.2, false, ST_GeomFromText('POINT(77.1286 28.5123)', 4326)), -- Saket
(17, 17, 4.7, true, ST_GeomFromText('POINT(77.1862 28.5336)', 4326)), -- Malviya Nagar
(18, 18, 4.4, false, ST_GeomFromText('POINT(77.2798 28.6173)', 4326)), -- Mayur Vihar
(19, 19, 4.9, true, ST_GeomFromText('POINT(77.1502 28.5893)', 4326)), -- Hauz Khas
(20, 20, 4.5, true, ST_GeomFromText('POINT(77.2913 28.6077)', 4326)); -- Laxmi Nagar

INSERT INTO wallet (id,user_id,balance) VALUES
(1,1,100),
(2,2,500);