package com.careconnect.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BabysitterProfileRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String bio;

    // Add more fields as needed

    public String getName() { return name == null ? null : name.trim(); }
        public void setName(String name) { this.name = name == null ? null : name.trim(); }
    public String getBio() { return bio == null ? null : bio.trim(); }
        public void setBio(String bio) { this.bio = bio == null ? null : bio.trim(); }
}
