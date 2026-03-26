package com.careconnect.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profile_views")
public class ProfileView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viewer_id", nullable = false)
    private User viewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viewed_id", nullable = false)
    private User viewed;

    @Column(nullable = false)
    private Date viewedAt = new Date();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getViewer() { return viewer; }
    public void setViewer(User viewer) { this.viewer = viewer; }
    public User getViewed() { return viewed; }
    public void setViewed(User viewed) { this.viewed = viewed; }
    public Date getViewedAt() { return viewedAt; }
    public void setViewedAt(Date viewedAt) { this.viewedAt = viewedAt; }
}
