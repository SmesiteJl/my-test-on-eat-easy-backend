package com.technokratos.eateasy.jwtauthenticationstarter.token.access.service.impl;

import com.technokratos.eateasy.jwtauthenticationstarter.token.access.service.AccessTokenService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Unified access token service coordinating generation and parsing operations.
 *
 * <p>Combines {@link JwtAccessTokenGeneratorService} and {@link JwtAccessTokenParserService} to
 * provide complete access token lifecycle management through method delegation.
 *
 * @see AccessTokenService
 */
@Slf4j
@RequiredArgsConstructor
public class CompositeAccessTokenService implements AccessTokenService {

  /** Token generation component delegate */
  private final JwtAccessTokenGeneratorService generator;

  /** Token parsing/validation component delegate */
  private final JwtAccessTokenParserService parser;

  /**
   * {@inheritDoc}
   *
   * <p>Delegates token creation to generator service
   */
  @Override
  public String generate(UserDetails userDetails, @NonNull Map<String, Object> extraClaims) {
    return generator.generate(userDetails, extraClaims);
  }

  /**
   * {@inheritDoc}
   *
   * <p>Delegates username extraction to parser service
   */
  @Override
  public String extractUsername(String token) throws IllegalArgumentException {
    return parser.extractUsername(token);
  }

  /**
   * {@inheritDoc}
   *
   * <p>Delegates token validation to parser service
   */
  @Override
  public void validate(String token) throws AuthenticationException {
    parser.validate(token);
  }

  /**
   * {@inheritDoc}
   *
   * <p>Delegates claims extraction to parser service
   */
  @Override
  public Map<String, Object> extractAllClaims(String token) throws IllegalArgumentException {
    return parser.extractAllClaims(token);
  }
}
