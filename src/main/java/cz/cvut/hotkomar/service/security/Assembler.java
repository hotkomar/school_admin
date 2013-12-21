/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.security;

import cz.cvut.hotkomar.model.entity.AbstractUser;
import cz.cvut.hotkomar.model.entity.UserType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Maru
 */
@Service
public class Assembler {
    /**
     * login function for spring security
     * @param userAbs
     * @return
     */
    @Transactional(readOnly= true) 
    public User buildUserFromUserEntity(AbstractUser userAbs)
    {
      boolean enable = true;
      boolean accountNonExpired = true;
      boolean accountNonLocked = true;
      //user role
      Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Set<UserType> idType = userAbs.getIdType();
        for(UserType u: idType){
            System.out.println("R> "+u.getName());
        authorities.add(new SimpleGrantedAuthority(u.getName()));
        }
        
        User user = new User(userAbs.getLogin(),userAbs.getPassword(), enable, accountNonExpired, accountNonExpired, accountNonLocked, authorities);
        System.out.println("USER heslo="+user.getPassword());
        return user;
    }
}
