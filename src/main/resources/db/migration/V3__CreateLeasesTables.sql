CREATE TABLE lessees (
    lessee_id VARCHAR(36) NOT NULL,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE leases (
    id VARCHAR(36) NOT NULL,
    date DATE NOT NULL,
    price INTEGER NOT NULL,
    lessor_id VARCHAR(36) NOT NULL,
    machinery_id varchar(36) NOT NULL
);