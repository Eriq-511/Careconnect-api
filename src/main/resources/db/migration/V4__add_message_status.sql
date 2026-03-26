-- V4__add_message_status.sql
ALTER TABLE messages ADD COLUMN status VARCHAR(16) NOT NULL DEFAULT 'DELIVERED';