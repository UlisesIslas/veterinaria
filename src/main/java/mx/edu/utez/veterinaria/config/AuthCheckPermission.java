package mx.edu.utez.veterinaria.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.veterinaria.entity.Roles;
import mx.edu.utez.veterinaria.entity.Users;
import mx.edu.utez.veterinaria.service.UsersService;

@Service
public class AuthCheckPermission {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsersService usersService;

    public boolean checkPermission(String token, String roleAlias) {
        String requestTokenHeader = token.replace("[", "").replace("]", "");
        String username = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            String jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                Users user = usersService.findByUsername(username);
                Roles role = user.getRoles();
                if (role.getAlias().equalsIgnoreCase(roleAlias)) return true;
            } catch (Exception e) {
                LOGGER.error(String.format("AuthCheckPermission: checkPermission %s", e.getMessage()));
            }
        }
        return false;
    }

    public Users findUserByToken(String token) {
        String requestTokenHeader = token.replace("[", "").replace("]", "");
        Users user = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            String jwtToken = requestTokenHeader.substring(7);
            try {
                user = usersService.findByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
                return user;
            } catch (Exception e) {
                LOGGER.error(String.format("AuthCheckPermission: findUserByToken %s", e.getMessage()));
            }
        }
        return null;
    }

    public boolean isLoguedUser(String token, int id) {
        Users tmp = usersService.findById(id);
        Users logued = findUserByToken(token);
        if (tmp.getId() == logued.getId()) return true;
        return false;
    }
    
}
