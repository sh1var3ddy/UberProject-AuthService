-- Add a non-nullable booking_id column to review table
ALTER TABLE review
    MODIFY COLUMN booking_id BIGINT NOT NULL;