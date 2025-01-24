CREATE TABLE IF NOT EXISTS client(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);
ALTER TABLE client ADD CONSTRAINT client_name_length CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 200 );

CREATE TABLE IF NOT EXISTS planet(
    planet_id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(500) NOT NULL
);
ALTER TABLE planet ADD CONSTRAINT planet_id_values CHECK (planet_id ~* '^[A-Z0-9]+$');
ALTER TABLE planet ADD CONSTRAINT planet_name_length CHECK (LENGTH(name) >= 1 AND LENGTH(name) <= 500 );

CREATE TABLE IF NOT EXISTS ticket(
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP,
    client_id BIGINT,
    from_planet_id VARCHAR(100) NOT NULL,
    to_planet_id VARCHAR(100) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE,
    FOREIGN KEY (from_planet_id) REFERENCES planet(planet_id) ON DELETE CASCADE,
    FOREIGN KEY (to_planet_id) REFERENCES planet(planet_id) ON DELETE CASCADE
);