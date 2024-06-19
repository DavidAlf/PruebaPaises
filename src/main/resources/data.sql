DROP TABLE IF EXISTS countries;


CREATE TABLE IF NOT EXISTS countries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(100) NOT NULL,
    capital VARCHAR(100) NOT NULL,
    population INT NOT NULL,
    currency VARCHAR(100) NOT NULL
);


INSERT INTO countries (country, capital, population, currency) VALUES
('ARGENTINA', 'Buenos Aires', 45376763, 'Peso - ARS'),
('BRAZIL', 'Brasilia', 213993437, 'Real - BRL'),
('CHILE', 'Santiago', 19116209, 'Peso - CLP'),
('COLOMBIA', 'Bogotá', 50882891, 'Peso - COP'),
('PERU', 'Lima', 33359418, 'Sol - PEN'),
('UNITED STATES', 'Washington, D.C.', 331893745, 'Dollar - USD'),
('CANADA', 'Ottawa', 38067903, 'Canadian Dollar - CAD'),
('MEXICO', 'Mexico City', 128932753, 'Peso - MXN'),
('UNITED KINGDOM', 'London', 67081000, 'Pound - GBP'),
('GERMANY', 'Berlin', 83166711, 'Euro - EUR'),
('FRANCE', 'Paris', 65426179, 'Euro - EUR'),
('ITALY', 'Rome', 60317116, 'Euro - EUR'),
('SPAIN', 'Madrid', 47450795, 'Euro - EUR'),
('AUSTRALIA', 'Canberra', 25788215, 'Australian Dollar - AUD'),
('NEW ZEALAND', 'Wellington', 5123640, 'New Zealand Dollar - NZD'),
('JAPAN', 'Tokyo', 125960000, 'Yen - JPY'),
('SOUTH KOREA', 'Seoul', 51305186, 'Won - KRW'),
('CHINA', 'Beijing', 1411778724, 'Yuan - CNY'),
('INDIA', 'New Delhi', 1366417754, 'Rupee - INR'),
('RUSSIA', 'Moscow', 146748590, 'Ruble - RUB'),
('SOUTH AFRICA', 'Pretoria', 60041994, 'Rand - ZAR'),
('EGYPT', 'Cairo', 104258327, 'Pound - EGP'),
('NIGERIA', 'Abuja', 206139589, 'Naira - NGN'),
('KENYA', 'Nairobi', 54985698, 'Shilling - KES'),
('BOLIVIA', 'Sucre', 11673021, 'Boliviano - BOB'),
('URUGUAY', 'Montevideo', 3473730, 'Peso - UYU'),
('PARAGUAY', 'Asunción', 7132538, 'Guarani - PYG'),
('VENEZUELA', 'Caracas', 28515829, 'Bolivar - VEF'),
('ECUADOR', 'Quito', 17643054, 'Dollar - USD'),
('CUBA', 'Havana', 11333483, 'Peso - CUP'),
('DOMINICAN REPUBLIC', 'Santo Domingo', 10953703, 'Peso - DOP'),
('HONDURAS', 'Tegucigalpa', 9746117, 'Lempira - HNL'),
('GUATEMALA', 'Guatemala City', 17423821, 'Quetzal - GTQ'),
('EL SALVADOR', 'San Salvador', 6486205, 'Dollar - USD'),
('PANAMA', 'Panama City', 4351267, 'Balboa - PAB'),
('COSTA RICA', 'San José', 5094118, 'Colón - CRC'),
('NICARAGUA', 'Managua', 6850540, 'Córdoba - NIO'),
('HAITI', 'Port - au - Prince', 11402528, 'Gourde - HTG');
