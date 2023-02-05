INSERT INTO machinery_leasing.public.lessors
VALUES
('lessor-1',
 'Oak Sun',
 'oaksun@test.com',
 'ACTIVE',
 '2023-01-04'),
('lessor-2',
 'Duck Deduct',
 'duck@test.com',
 'ACTIVE',
 '2023-01-02');

INSERT INTO machinery_leasing.public.machines
VALUES
('machinery-1',
 'Kleine Dozer D3',
 'Caterpillar',
 'ACTIVE',
 'DOZER',
 'YELLOW',
 '234234234',
 60000,
 2014),
('machinery-2',
 'Kleine Dozer D2',
 'Caterpillar',
 'LEASED',
 'DOZER',
 'YELLOW',
 '235252654',
 0,
 2022);

INSERT INTO machinery_leasing.public.photos
VALUES
('machinery-1',
 'Front',
 'https://s7d2.scene7.com/is/image/Caterpillar/CM20200429-9b214-139f6'),
('machinery-2',
 'Front',
 'https://s7d2.scene7.com/is/image/Caterpillar/CM20200429-fe5df-cfd11');

INSERT INTO machinery_leasing.public.buying
VALUES
('machinery-1', '2022-09-07', 'PRIVATE', 35000),
('machinery-2', '2022-08-08', 'VENDOR', 80000);

INSERT INTO machinery_leasing.public.leases
VALUES
('lease-1',
 '2023-01-13',
 85000,
 'lessor-1',
 'machinery-2');

INSERT INTO machinery_leasing.public.lessees
VALUES
('lease-1',
 'Ankle Vasya',
 'vasya@test.com',
 '22835353535');

-- Password: 123456789
INSERT INTO machinery_leasing.public.users
VALUES
('user-id',
 'Oak Sun',
 'oaksun@test.com',
 '$2a$10$Iy4ap5imZew65diSSMAgau0r8Yyi25ANgRn7gLE6nW1WaLo0PQCxO');
