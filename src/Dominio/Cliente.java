package Dominio;

public class Cliente {
    private String nombre;
    private String contrase�a;
    private int saldo;
    private String correo;
    
    public Cliente(String nombre, String contrase�a, int saldo, String correo) {
        this.nombre = nombre;
        this.contrase�a = contrase�a;
        this.saldo = saldo;
        this.correo = correo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContrase�a() {
        return contrase�a;
    }
    public void setContrase�a(String contrase�a) {
        this.contrase�a = contrase�a;
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