package com.careconnect.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = false)
    private User parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "babysitter_id", nullable = false)
    private User babysitter;

    @Column(nullable = false)
    private Date createdAt = new Date();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getParent() { return parent; }
    public void setParent(User parent) { this.parent = parent; }
    public User getBabysitter() { return babysitter; }
    public void setBabysitter(User babysitter) { this.babysitter = babysitter; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
