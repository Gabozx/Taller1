package Dominio;

public class Producto {
    private String nombreP;
    private int precio;
    private int stock;
    private int vecesvendido;
    private int cantidadPC;
    
    public Producto(String nombreP, int precio, int stock) {
        this.nombreP = nombreP;
        this.precio = precio;
        this.stock = stock; 
    }
    
    public String getNombreP() {
        return nombreP;
    }
    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getVecesvendido() {
        return vecesvendido;
    }
    public void setVecesvendido(int vecesvendido) {
        this.vecesvendido = vecesvendido;
    }
    public int getCantidadPC() {
        return cantidadPC;
    }
    public void setCantidadPC(int cantidadPC) {
        this.cantidadPC = cantidadPC;
    }
    
}