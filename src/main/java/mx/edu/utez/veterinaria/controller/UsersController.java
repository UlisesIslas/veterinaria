package mx.edu.utez.veterinaria.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.edu.utez.veterinaria.config.AuthCheckPermission;
import mx.edu.utez.veterinaria.entity.GeneralTemplateResponse;
import mx.edu.utez.veterinaria.entity.Roles;
import mx.edu.utez.veterinaria.entity.Users;
import mx.edu.utez.veterinaria.service.RoleService;
import mx.edu.utez.veterinaria.service.UsersService;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthCheckPermission authCheckPermission;
    public String picName;

    @GetMapping()
    public GeneralTemplateResponse findAll() {
        return new GeneralTemplateResponse(usersService.findAll());
    }

    @GetMapping("/{id}")
    public GeneralTemplateResponse findById(@PathVariable("id") int id, @RequestHeader HttpHeaders headers) {
        String token = String.valueOf(headers.get("Authorization"));
        if (authCheckPermission.checkPermission(token, "admin")) {
            return new GeneralTemplateResponse(usersService.findById(id));
        }
        return new GeneralTemplateResponse();
    }

    @GetMapping("/roles")
    public List<Roles> findAllRoles() {
        return roleService.findAllRoles();
    }

    @GetMapping("/roles/{id}")
    public Roles findRoleById(@PathVariable("id") int id) {
        return roleService.findRoleById(id);
    }

    @GetMapping(value = "/actual")
    public GeneralTemplateResponse getActualUser(@RequestHeader HttpHeaders headers) {
        String token = String.valueOf(headers.get("Authorization"));
        if (authCheckPermission.checkPermission(token, "admin") || authCheckPermission.checkPermission(token, "doctor") || authCheckPermission.checkPermission(token, "recepcionista")) {
            return new GeneralTemplateResponse(authCheckPermission.findUserByToken(token));
        }
        return new GeneralTemplateResponse();
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Users obj) {
        return usersService.save(obj);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return usersService.delete(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createTokenAuthentication(@RequestBody Users user) {
        return usersService.createTokenAuthentication(user);
    }

    @PostMapping("/session")
    public boolean verifySession(@RequestBody Users user) {
        return usersService.verifySession(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Users user) {
        return usersService.createTokenAuthentication(user);
    }

    @PostMapping("/upload/picture")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        String separator = FileSystems.getDefault().getSeparator();
        String fileName = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
            file.transferTo(new File(userDirectory + "\\src\\main\\resouces\\static\\imguploads\\" + separator + "profilePics" + separator + fileName + "." + ext));
            this.picName = fileName + "." + ext;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
