package co.com.ias.certification.backend.controllers;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/auth")
public class TestController {

    /**
     * Ejemplo de como obtener las autoridades de un usuario por el token
     * @param authentication Object Authentication de Spring Security, casteado como KeycloakAuthenticationToken
     * @return Colección de autoridades del usuario.
     */
    @GetMapping("/me")
    public Collection<GrantedAuthority> me(Authentication authentication) throws Exception {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        if(!roles(authenticationToken.getAuthorities(), "ADMIN")){
            throw new Exception("No tiene permisos");
        }

        System.out.println(authenticationToken.getAuthorities());
        return authenticationToken.getAuthorities();
    }

    public boolean roles (Collection<GrantedAuthority> list, String role){
        for(GrantedAuthority value: list){
            System.out.println(value.getAuthority());
            if(value.getAuthority().equals(role)) return true;
        }
        return false;
    }
}
