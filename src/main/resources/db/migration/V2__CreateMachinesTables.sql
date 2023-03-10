CREATE TABLE photos (
    machinery_id VARCHAR(36) NULL,
    description TEXT,
    url TEXT
);

CREATE TABLE buying (
    machinery_id VARCHAR(36) NOT NULL,
    date DATE NOT NULL,
    source VARCHAR(50) NOT NULL,
    price INTEGER NOT NULL
);

CREATE TABLE machines (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    type VARCHAR(50) NOT NULL,
    color VARCHAR(50) NOT NULL,
    chassis VARCHAR(100) NOT NULL,
    mileage INTEGER NOT NULL,
    releaseYear INTEGER NOT NULL
);