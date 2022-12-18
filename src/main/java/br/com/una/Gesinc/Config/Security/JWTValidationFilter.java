package br.com.una.Gesinc.Config.Security;

import br.com.una.Gesinc.Domain.Users;
import br.com.una.Gesinc.Repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTValidationFilter extends BasicAuthenticationFilter {

    public static final String HEADER = "Authorization";
    public static final String ATTRIBUTE_PREFIX = "Bearer ";

    private UserRepository userRepository;

    public JWTValidationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String atributo = request.getHeader(HEADER);
        if(atributo == null || atributo.isEmpty() || atributo.startsWith(ATTRIBUTE_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        String token = atributo.replace(ATTRIBUTE_PREFIX, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){

        Optional<Users> optionalUser = userRepository.findById(this.getUserId(token));

        String user = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.SECRET))
                .build()
                .verify(token)
                .getSubject();

        if(user == null || optionalUser.isEmpty()){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, optionalUser.get().getAuthorities());
    }

    public Long getUserId(String token){
        Claims claim = Jwts.parser().setSigningKey(JWTAuthenticationFilter.SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claim.getSubject());
    }
}
