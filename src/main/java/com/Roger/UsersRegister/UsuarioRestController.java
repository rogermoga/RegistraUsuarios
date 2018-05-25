package com.Roger.UsersRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(path = "/usuarios", method = RequestMethod.POST)
    public ResponseEntity<String> signUser(@RequestParam String user) {
        if (user.isEmpty()) {
            return new ResponseEntity<>(("No email"), HttpStatus.FORBIDDEN);
        }else {

            Usuario usuario = usuarioRepository.findByEmail(user);
            if (usuario != null) {

                Date date = new Date();
                usuario.addDate(date);
                usuarioRepository.save(usuario);
                return new ResponseEntity<>(("Logged in"), HttpStatus.ACCEPTED);
            }else {

                return new ResponseEntity<>(("You need to register" ), HttpStatus.CONFLICT);
            }
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestParam String user, String name, String business) {
        if (user.isEmpty()|| name.isEmpty()) {
            return new ResponseEntity<>(("No email or name"), HttpStatus.FORBIDDEN);
        }else {

            Usuario usuario = usuarioRepository.findByEmail(user);
            if (usuario != null) {
                return new ResponseEntity<>(("This user is already registered"), HttpStatus.CONFLICT);
            }else {
                usuarioRepository.save(new Usuario(name, user, business));
                return new ResponseEntity<>(("User registered" ), HttpStatus.CREATED);
            }
        }
    }

    @RequestMapping(path = "/resumen", method = RequestMethod.GET)
    public List<Map> makeResumen() {

        return usuarioRepository.findAll().stream()
                .map(usuario -> usuarioToDTO(usuario))
                .collect(Collectors.toList());
    }


    public Map<String, Object> usuarioToDTO (Usuario usuario) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Nombre", usuario.getName());
        map.put("Email", usuario.getEmail());
        map.put("Business", usuario.getEmpresa());
        map.put("FechaRegistro", usuario.getFechaRegistro());
        map.put("AccessTimes", usuario.getAccessTimes());
        return map;
    }


    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(key, value);
        return map;
    }
}
