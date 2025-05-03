ALTER TABLE passenger
    ADD phone_number VARCHAR(20) NOT NULL,
    MODIFY name VARCHAR(255) NOT NULL,  -- specify the type if not already NOT NULL
    ADD COLUMN email VARCHAR(200) NOT NULL,
    ADD COLUMN password VARCHAR(200) NOT NULL;
