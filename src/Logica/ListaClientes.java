package Logica;
import Dominio.*;
public class ListaClientes {
    private int cont;
    private int max ;
    private Cliente[] clientes ;
    
    public ListaClientes(int max) {
        this.max = max;
        cont = 0;
        clientes= new Cliente[max];
    }
    
    public Cliente[] getClientes() {
        return clientes;
    }
    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
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

    public int indiceCliente(String nombre) {
        for (int i=0;i<=cont-1;i++) {
            if(clientes[i].getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean eliminarClientes(String nombre) {
    	int indice=indiceCliente(nombre);
    	if(indice!=-1){
    		for (int i=indice;cont-1>=i;i++) {
    			clientes[indice]=clientes[indice+1];
    			indice+=1;
    		}
    		cont--;
            return true;
        }
        return false;
    }  
      
	public String getClientePI(int indice) { //PI= por indice
		String nombre= clientes[indice].getNombre();
		return nombre;
    }
	
	public String getContrase�aPI(int indice) { //PI= por indice
		String contrase�a= clientes[indice].getContrase�a();
		return contrase�a;
    }
	
	public void setContrase�a(int indice,String newPass) {
		clientes[indice].setContrase�a(newPass);
	}
	
	public int getSaldoPI(int indice) { //PI= por indice
		int saldo= clientes[indice].getSaldo();
		return saldo;
    }
	
	public void setSaldoPI(int indice,int plusSaldo) {
		int newSaldo= clientes[indice].getSaldo()+plusSaldo;
		clientes[indice].setSaldo(newSaldo);
	}
	
	public String getTodoPi(int indice) { //PI= por indice
		String nombre= clientes[indice].getNombre();
		String contrase�a= clientes[indice].getContrase�a();
		String saldo= Integer.toString(clientes[indice].getSaldo());
		String correo= clientes[indice].getCorreo();
		String linea=nombre+","+contrase�a+","+saldo+","+correo;
		return linea;
    }
    
    
    public int verificarCliente(String nombre,String contrase�a) {
        int i;
        for(i=0;i<cont;i++) {
            if(clientes[i].getNombre().equals(nombre)) {
                break;
            }
        }
        if(i==cont) { // si  no est� usuario
            return -1;
        }
        if (clientes[i].getContrase�a().equals(contrase�a)) { //si usuario y contrase�a coinciden
        	return i;
        }
        else { 
        	return -2;
        }
    }
    
    public void ingresarCliente(Cliente cliente){
        if(max > cont){
            clientes[cont] = cliente;
            cont++;
        }
    }
    
}