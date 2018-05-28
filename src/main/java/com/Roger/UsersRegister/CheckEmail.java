package com.Roger.UsersRegister;

public class CheckEmail implements ICheckEmail {
    private String email;

    public CheckEmail(String email) {
        this.email = email;
    }

    public void CheckIfEmpty() throws NoEmailException{
        if (email.isEmpty()) throw new NoEmailException();
    }

    public Usuario FindUser( UsuarioRepository usuarioRepository){
        return  usuarioRepository.findByEmail(email);
    }
}
