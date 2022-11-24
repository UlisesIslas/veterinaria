package mx.edu.utez.veterinaria.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import mx.edu.utez.veterinaria.service.UsersService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException | ExpiredJwtException e) {
                logger.error("Error: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.usersService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    /*
     * @Override
     * protected void doFilterInternal(HttpServletRequest httpServletRequest,
     * HttpServletResponse httpServletResponse, FilterChain filterChain) throws
     * ServletException, IOException {
     * final String requestTokenHeader =
     * httpServletRequest.getHeader("Authorization");
     * 
     * String username = null;
     * String jwtToken = null;
     * 
     * if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
     * jwtToken = requestTokenHeader.substring(7);
     * try {
     * username = jwtTokenUtil.getUsernameFromToken(jwtToken);
     * } catch (IllegalArgumentException | ExpiredJwtException e) {
     * logger.error("Error: ", e.getMessage());
     * }
     * }
     * 
     * if (username != null &&
     * SecurityContextHolder.getContext().getAuthentication() == null) {
     * UserDetails userDetails = this.usersService.loadUserByUsername(username);
     * 
     * if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
     * UsernamePasswordAuthenticationToken authenticationToken = new
     * UsernamePasswordAuthenticationToken(userDetails, null,
     * userDetails.getAuthorities());
     * authenticationToken.setDetails(new
     * WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
     * SecurityContextHolder.getContext().setAuthentication(authenticationToken);
     * }
     * }
     * filterChain.doFilter(httpServletRequest, httpServletResponse);
     * }
     */

}
