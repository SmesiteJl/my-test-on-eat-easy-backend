package com.technokratos.eateasy.jwtauthenticationstarter.token.refresh.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

/**
 * Represents a persisted refresh token entity with security context metadata.
 *
 * <p>Stores token data along with client fingerprint and temporal attributes for refresh token
 * rotation and invalidation purposes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "fingerprint")
public class RefreshTokenEntity implements Serializable {

  private UUID id;
  private String fingerprint;
  private Instant expiresAt;
  private Instant createdAt;
  private String username;
}
