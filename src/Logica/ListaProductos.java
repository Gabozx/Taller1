package Logica;
import Dominio.*;
public class ListaProductos {
    private Producto[] productos;
    private int cont;
    private int max ;
    
    public ListaProductos(int max) {
        this.max = max;
        cont=0;
        productos= new Producto[max];
    }
    
    public Producto[] getProductos() {
        return productos;
    }
    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }
    public int getCont() {
        return cont;
    }
    public void setCont(int cont) {
        this.cont = cont;
    }
    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    
    public String getProd(int indice) {
		String nombre= productos[indice].getNombreP();
		String precio= Integer.toString(productos[indice].getPrecio());
		String stock= Integer.toString(productos[indice].getStock());
		String linea=nombre+","+precio+","+stock;
		return linea;
    }
    
    public String getVenta(int indice) {
		String nombre= productos[indice].getNombreP();
		String vecesVendido= Integer.toString(productos[indice].getVecesvendido());
		String linea=nombre+","+vecesVendido;
		if (vecesVendido.equals("0")) {
			return "0";
		}
		else {
			return linea;
		}
    }
    
    public int getCantidadPC(int indice) {
    	int cantidadPC=productos[indice].getCantidadPC();
    	return cantidadPC;
    }
    
    public int indiceProducto(String nombreP) {
        for (int i=0;i<=cont-1;i++) {
            if(productos[i].getNombreP().equals(nombreP)) {
                return i;
            }
        }
        return -1;
    }
    
    public void setCantidadPC(int indice, int cantidad, int maximo) {
    	if (cantidad==0) {
    		productos[indice].setCantidadPC(0);
    	}
    	else {
    		int valor= productos[indice].getCantidadPC()+cantidad;
    		productos[indice].setCantidadPC(valor);
    		productos[indice].setStock(maximo-valor);
    	}
    	
    }
    
    public void borrarPc(int indice, int cantidad) {
    	int valor= productos[indice].getCantidadPC()-cantidad;
    	productos[indice].setCantidadPC(valor);
    	int maximo= productos[indice].getStock();
    	productos[indice].setStock(maximo+cantidad);
    }
    
    public void setVecesVendido(int indice,int cantCompra) {
    	productos[indice].setVecesvendido(cantCompra);
    }
    
    public void setStock(int plusStock,int indice) {
    	productos[indice].setStock((productos[indice].getStock()+plusStock));
    }
    
    public void setPrecio(int precio,int indice) {
    	productos[indice].setPrecio(precio);
    }
    
    public void ingresarProducto(Producto producto){
        if(max > cont){
            productos[cont] = producto;
            cont++;
        }
    }
}