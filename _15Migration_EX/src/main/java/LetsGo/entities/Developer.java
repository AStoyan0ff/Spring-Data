package LetsGo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "developers")
public class Developer extends BaseClass {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(name = "github_username", nullable = false)
    private String githubUsername;

    @Column(name = "github_profile_url", nullable = false)
    private String githubProfileUrl;

    @ManyToMany
    @JoinTable(
        name = "developers_technologies",
        joinColumns = @JoinColumn(name = "developer_id"),
        inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
