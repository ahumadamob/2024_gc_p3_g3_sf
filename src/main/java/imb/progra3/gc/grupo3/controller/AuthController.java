package imb.progra3.gc.grupo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.progra3.gc.grupo3.dto.JwtResponse;
import imb.progra3.gc.grupo3.dto.LoginRequest;
import imb.progra3.gc.grupo3.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

		@Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtUtils jwtUtils;

	    @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
	        try {
	            Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
	            );

	            String jwt = jwtUtils.generateJwtToken(authentication);
	            return ResponseEntity.ok(new JwtResponse(jwt));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de autenticación: " + e.getMessage());
	        }
	    }
	}

