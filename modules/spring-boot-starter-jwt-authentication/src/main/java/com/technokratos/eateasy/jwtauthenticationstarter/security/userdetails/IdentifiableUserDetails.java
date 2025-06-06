package com.technokratos.eateasy.jwtauthenticationstarter.security.userdetails;

import java.io.Serializable;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Extended {@link UserDetails} with user identifier capability.
 *
 * <p>Provides access to the user's unique persistent identifier while maintaining Spring Security's
 * standard user details contract.
 *
 * @param <T> type of the user identifier, must be {@link Serializable}
 */
public interface IdentifiableUserDetails<T extends Serializable> extends UserDetails {
  T getId();
}
