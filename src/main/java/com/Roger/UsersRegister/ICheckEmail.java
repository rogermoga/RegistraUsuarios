package com.Roger.UsersRegister;

public interface ICheckEmail {
    void CheckIfEmpty() throws NoEmailException;
    Usuario FindUser( UsuarioRepository usuarioRepository);
}
