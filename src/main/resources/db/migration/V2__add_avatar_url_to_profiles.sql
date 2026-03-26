-- V2__add_avatar_url_to_profiles.sql
ALTER TABLE babysitter_profiles ADD COLUMN avatar_url VARCHAR(255);
ALTER TABLE parent_profiles ADD COLUMN avatar_url VARCHAR(255);
