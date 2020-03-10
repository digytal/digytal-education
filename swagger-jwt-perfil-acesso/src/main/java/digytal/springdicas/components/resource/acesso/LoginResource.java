package digytal.springdicas.components.resource.acesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digytal.springdicas.secutiry.JwtCredencial;
import digytal.springdicas.secutiry.JwtLogin;
import digytal.springdicas.secutiry.JwtTokenProvider;

@RestController
@RequestMapping("/login")
public class LoginResource {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> sigin(@RequestBody JwtLogin login) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		login.getUsername(),
                		login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        JwtCredencial credencial= new JwtCredencial();
        credencial.setLogin(login.getUsername());
        credencial.setToken(token);
        authentication.getAuthorities().forEach(a -> {
        	if(a.getAuthority().endsWith("MENU"))
        		credencial.getMenus().add(a.getAuthority());
		});
        return ResponseEntity.ok(credencial);
    }
}
