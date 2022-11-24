package mx.edu.utez.veterinaria.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import mx.edu.utez.veterinaria.config.JwtResponse;
import mx.edu.utez.veterinaria.config.JwtTokenUtil;
import mx.edu.utez.veterinaria.entity.UserConnection;
import mx.edu.utez.veterinaria.entity.Users;
import mx.edu.utez.veterinaria.repository.IUserConnectionRepository;
import mx.edu.utez.veterinaria.repository.IUsersRepository;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserConnectionRepository userConnectionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Users findById(int id) {
        return usersRepository.findById(id).get();
    }

    @Transactional
    public boolean save(Users obj) {
        UserConnection userConnection = new UserConnection();
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        boolean flag = false;
        Users tmp = usersRepository.save(obj);
        if (tmp != null) {
            flag = true;
            userConnection.setUser(tmp);
            if (tmp != null) {
                flag = true;
                userConnection.setUser(tmp);
                userConnection.setStatus(1);
                userConnectionRepository.save(userConnection);
            }
        }
        return flag;
    }

    @Transactional
    public boolean delete(int id) {
        Users tmp = findById(id);
        if (tmp != null) {
            usersRepository.delete(tmp);
            return true;
        }
        return false;
    }

    public ResponseEntity<?> createTokenAuthentication(@RequestBody Users user) {
        if (authentication(user.getUsername(), user.getPassword())) {
            String token;
            try {
                UserDetails userDetails = loadUserByUsername(user.getUsername());
                token = jwtTokenUtil.generateToken(userDetails);
                if (userConnectionRepository.findByUser_Username(user.getUsername()) != null) {
                    user = usersRepository.findByUsername(user.getUsername());
                    if (!user.isEnabled()) {
                        return ResponseEntity.ok(false);
                    }
                } else {
                    ResponseEntity.ok(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

    /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);

        if (user != null) {
            List<Users> users = new ArrayList<>(Collection.singletonList(user));
            List<GrantedAuthority> authorities = getUserAuthority(users);
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("Not found");
        }
    } */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);

        if (user != null) {
            List<Users> employees = new ArrayList<>(Collections.singletonList(user));
            List<GrantedAuthority> authorities = getUserAuthority(employees);
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("Not found");
        }
    }

    public boolean authentication(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return true;
        } catch (DisabledException | BadCredentialsException e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    private List<GrantedAuthority> getUserAuthority(List<Users> users) {
        Set<GrantedAuthority> roles = new HashSet<>();
        users.forEach((user) -> roles.add(new SimpleGrantedAuthority(user.getRoles().getName())));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    public boolean verifySession(Users user) {
        UserConnection userConnection = userConnectionRepository.findByUser_Username(user.getUsername());
        if (userConnection != null) {
            return userConnection.getStatus() == 1;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
    
}
