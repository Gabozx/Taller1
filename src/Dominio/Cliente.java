package Dominio;

public class Cliente {
    private String nombre;
    private String contraseña;
    private int saldo;
    private String correo;
    
    public Cliente(String nombre, String contraseña, int saldo, String correo) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.saldo = saldo;
        this.correo = correo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public int getSaldo() {
        return saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}