package com.Roger.UsersRegister;

import org.hibernate.annotations.Check;
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

    //este es el metodo que comprueba que el usuario este ya registrado.
    @RequestMapping(path = "/usuarios", method = RequestMethod.POST)
    public ResponseEntity<String> signUser(@RequestParam String user) {

        //comprueba que el email no este vacio y si lo esta lanzamos una excepcion personalizada
        try {
                CheckEmail c = new CheckEmail(user); //le pasamos el email en el contructor
                c.CheckIfEmpty();   // llamamos al metodo que contrale que no este vacio, es el que lanza la excepcion
                Usuario usuario = c.FindUser(usuarioRepository); //llamamos al metodo que busca el email dentro del repositorio

                if (usuario != null) {

                    //si esta registrado guarda la fecha dentro de la Lista de accesos del usuario
                    Date date = new Date();
                    usuario.addDate(date);
                    usuarioRepository.save(usuario);
                    return new ResponseEntity<>(("Logged in"), HttpStatus.ACCEPTED);
                } else {

                    //utilizaremos esta respuesta para redirigir a la pagina de registro desde el front
                    return new ResponseEntity<>(("You need to register"), HttpStatus.CONFLICT);
                }

        }
        catch (NoEmailException e){
            return new ResponseEntity<>(("No email"), HttpStatus.NOT_ACCEPTABLE);
        }
    }

        //metodo que registra los nuevos usuarios
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestParam String user, String name, String business) {

        //comprueba que esten los campos obligatorios Nombre y Email
        if (user.isEmpty()|| name.isEmpty()) {
            return new ResponseEntity<>(("No email or name"), HttpStatus.FORBIDDEN);
        }else {

            //comprueba que no este ya registrado
            Usuario usuario = usuarioRepository.findByEmail(user);
            if (usuario != null) {
                return new ResponseEntity<>(("This user is already registered"), HttpStatus.CONFLICT);
            }else {
                //lo guarda
                usuarioRepository.save(new Usuario(name, user, business));
                return new ResponseEntity<>(("User registered" ), HttpStatus.CREATED);
            }
        }
    }

    //metodo que devuelve toda la info de los usuarios. Una lista con una Map para cada usuario
    @RequestMapping(path = "/resumen", method = RequestMethod.GET)
    public List<Map> makeResumen() {

        //el stream nos consigue todos los usuarios y llamamos al metodo usuarioToDTO para llenar todos los campos para cada uno
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

    //habia creado este metodo para hacer Maps pero al final no lo he usado
  /*  private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(key, value);
        return map;
    }*/
}
