//package org.hcl.healthcare.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//    private UserDetailsService userDetailsService;
//
//    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String path = request.getRequestURI();
//        // Skip filter for public paths (swagger + auth endpoints)
//        if (path.startsWith("/api/auth")
//                || path.startsWith("/swagger-ui")
//                || path.startsWith("/v3/api-docs")
//                || path.startsWith("/swagger-resources")
//                || path.startsWith("/webjars")
//                || path.startsWith("/swagger-ui.html")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String authHeader = request.getHeader("Authorization");
//        String jwtToken = null;
//        String username = null;
//        if(authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwtToken = authHeader.substring(7);
//            username = jwtService.extractUsername(jwtToken);
//        }else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
//            return; // ⛔ Stop filter chain
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtService.validateToken(jwtToken)) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails, null, userDetails.getAuthorities()
//                        );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
