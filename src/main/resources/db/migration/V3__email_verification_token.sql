-- V3__email_verification_token.sql
CREATE TABLE email_verification_tokens (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    user_id INTEGER NOT NULL UNIQUE REFERENCES users(id),
    expiry_date TIMESTAMP NOT NULL
);
