package Logica; 
import Dominio.*;
import java.util.*;
import java.io.*;
/*
 * Daniel Salazar
 * Chriss Brito 
 * Gabriel Zuleta
 */
public class App {

	public static void main(String[] args) throws IOException {
		ListaClientes clientes= new ListaClientes(10000);
		ListaProductos productos= new ListaProductos(10000);
		leerClientes(clientes);
		leerProductos(productos);
		leerVentas(productos);
		boolean cerrar= iniciarSesion(clientes,productos);
		while (cerrar==false) {
			System.out.println("");System.out.println("si desea salir y cerrar el programa escriba 'exit'");
			cerrar= iniciarSesion(clientes,productos);
		}
		cerrarClientes(clientes);
		cerrarProductos(productos);
		cerrarVentas(productos);
	}
	
	/**
	 * A function that reads the file "Clientes.txt" and saves the data in the system through its methods
	 * @param sistema - system
	 * @throws IOException - An exception
	 */
	public static void leerClientes(ListaClientes clientes) throws IOException {
		File arch= new File("Clientes.txt");
		Scanner lector= new Scanner(arch);
		while(lector.hasNextLine()) {
			String line= lector.nextLine();
			String [] part= line.split(",");
			String cliente = part[0];
			String contraseña = part[1];
			int saldo= Integer.parseInt(part[2]);
			String correo= part[3];
			Cliente user= new Cliente (cliente,contraseña,saldo,correo);
			clientes.ingresarCliente(user);
		}
		lector.close();
	}
	
	/**
	 * A function that reads the file "Productos.txt" and saves the data in the system through its methods
	 * @param sistema - system
	 * @throws IOException - An exception
	 */
	public static void leerProductos(ListaProductos productos) throws IOException {
		File arch= new File("Productos.txt");
		Scanner lector= new Scanner(arch);
		while (lector.hasNextLine()) {
			String linea= lector.nextLine();
			String [] part= linea.split(",");
			String nombreProd= part[0];
			int precio= Integer.parseInt(part[1]);
			int stock= Integer.parseInt(part[2]);
			Producto product= new Producto(nombreProd,precio,stock);
			productos.ingresarProducto(product);
		}
		lector.close();
	}
	
	/**
	 * A function that reads the file "Ventas.txt" and saves the data in the system through its methods
	 * @param sistema - system
	 * @throws IOException - An exception
	 */
	public static void leerVentas(ListaProductos productos)throws IOException {
		File arch= new File("Ventas.txt");
		Scanner lector= new Scanner(arch);
		for (int i=0;productos.getCont()-1>=i;i++) {
			productos.setVecesVendido(i,0);
		}
		while (lector.hasNextLine()) {
			String linea= lector.nextLine();
			String[] part= linea.split(",");
			String nombreProd= part[0];
			int cantCompra= Integer.parseInt(part[1]);
			int indice= productos.indiceProducto(nombreProd);
			if (indice!=-1) {
				productos.setVecesVendido(indice, cantCompra);
			}
		}
		lector.close();
	}
	
	/**
	 * with this function the user or ADMIN will be able to log in in order to interact with the system
	 * @param sistema - system
	 */	
	public static boolean iniciarSesion(ListaClientes clientes,ListaProductos productos) {
        Scanner leer = new Scanner(System.in);
        System.out.println("-       -INICIO SESION-       -");
        System.out.println("Usuario: "); 
        String user = leer.nextLine();
        if (user.equals("exit")) {
        	System.out.println("-       Cerrando..       -");
        	return true;
        }
        else {
        	System.out.println("Contraseña: "); 
        	String pass = leer.nextLine();
        	if ((user.equals("ADMIN")) && (pass.equals("NYAXIO"))) {
        		System.out.println("-       -Menú Admin-       -");
        		menuAdmin(clientes,productos);
        	}
        	else {
        		int indice= clientes.verificarCliente(user,pass);
        		if ((indice!=-1) && (indice!=-2)){
        			menuUsuario(clientes,productos,indice);
        		}
        		else {
        			if (clientes.verificarCliente(user,pass)==-1) {
        				System.out.println("Usuario no registrado");System.out.println("Si se desea registrar ingrese '1', de lo contrario, ingrese cualquier otra tecla");
    					String respuesta=leer.nextLine();
    					if (respuesta.equals("1")) {
    						System.out.println("ingrese Nombre:"); String newUser=leer.nextLine();
    						System.out.println("ingrese Contraseña:"); String newPass=leer.nextLine();
    						System.out.println("ingrese Correo:"); String newCorreo=leer.nextLine();
    						Cliente newClient= new Cliente (newUser,newPass,0,newCorreo);
    						clientes.ingresarCliente(newClient);
    					}
        			}
        			else {	
        				System.out.println("Contraseña mal ingresada");
        			}
        		}
        	}
        }
        return false;
	}  

	/**
	 * with this function the user will be able to interact with the system
	 * @param sistema - system
	 */
	public static void menuUsuario(ListaClientes clientes,ListaProductos productos,int indice) {
		int salida=-1;
		Scanner leer= new Scanner (System.in);
		while (salida!= 9) {
			System.out.println("Hola "+ clientes.getClientePI(indice)+", que acción deseas realizar?");
			System.out.println("1)Elegir Producto");System.out.println("2)Cambiar contraseña");System.out.println("3)Ver catalogo de productos");System.out.println("4)Ver saldo");System.out.println("5)Recargar saldo");System.out.println("6)Ver carrito");System.out.println("7)Quitar del carrito");System.out.println("8)Pagar carrito");System.out.println("9)Salir");
			salida=Integer.parseInt(leer.nextLine());
			if (salida==1) {
				elegirProd(productos);
			}
			else if (salida==2) {
				cambiarPass(clientes,indice);
			}
			else if (salida==3) {
				catalogo(productos);
			}
			else if (salida==4) {
				verSaldo(clientes,indice);
			}
			else if (salida==5) {
				recargaSaldo(clientes,indice);
			}
			else if (salida==6) {
				verCarrito(productos);
			}
			else if (salida==7) {
				quitarCarrito(productos);
			}
			else if (salida==8) {
				paycarrito(clientes,productos,indice);
			}
			else if (salida==9) {
				System.out.println("Cerrando sesión...");
				for (int i=0; productos.getCont()-1>=i;i++) {
					int valor= productos.getCantidadPC(i);
					productos.borrarPc(i,valor);
				}
			}
			else {
				System.out.println("numero no valido, intente nuevamente");System.out.println("");
			}
		}
	}
	
	/**
	 * with this function the admin will be able to interact with the system
	 * @param sistema - system
	 */
	public static void menuAdmin(ListaClientes clientes,ListaProductos productos) {
		int salida=-1;
		Scanner leer= new Scanner (System.in);
		while (salida!= 6) {
			System.out.println("Hola Todopoderoso ADMIN, que acción deseas realizar?");
			System.out.println("1)Bloquear usuario");System.out.println("2)Ver historial de compras");System.out.println("3)Agregar producto");System.out.println("4)Agregar stock");System.out.println("5)Actualizar datos");System.out.println("6)Salir");
			salida=Integer.parseInt(leer.nextLine());
			if (salida==1) {
				bloqueo(clientes);
			}
			else if (salida==2) {
				historial(productos);
			}
			else if (salida==3) {
				agregarProd(productos);
			}
			else if (salida==4) {
				agregarStock(productos);
			}
			else if (salida==5) {
				actualizar(productos);
			}
			else if (salida==6) {
				System.out.println("Cerrando sesión...");
			}
			else {
				System.out.println("numero no valido, intente nuevamente");System.out.println("");
			}
		}
	}
	
	//USER
	
	/**
	 * with this function the user will be able to pick a product with stock
	 * @param sistema - system
	 */
	public static void elegirProd(ListaProductos productos) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		System.out.println("Ingrese el producto que desea:"); String prod = leer.nextLine();
		int index = productos.indiceProducto(prod);
		while (index==-1) {
			System.out.println("Producto no existe, intente nuevamente");
			System.out.println("Ingrese el producto que desea:"); prod = leer.nextLine();
			index = productos.indiceProducto(prod);
		}
		String linea= productos.getProd(index); String part[]= linea.split(",");
		if (part[2].equals("0")) {
			System.out.println("Producto agotado!");
		}
		else {
			System.out.println("cuantas unidades desea agregar al carrito (Maximo: "+part[2]+")");
			int cant= Integer.parseInt(leer.nextLine());
			while ((cant<=0) || (cant>Integer.parseInt(part[2]))) {
				System.out.println("Ingrese un valor valido");
				System.out.println("cuantas unidades desea agregar al carrito (Maximo: "+part[2]+")");
				cant= Integer.parseInt(leer.nextLine());
			}
			productos.setCantidadPC(index, cant, Integer.parseInt(part[2]));
		}
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the user will be able to change his password
	 * @param sistema - system
	 */
	public static void cambiarPass(ListaClientes clientes,int indice) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		System.out.println("ingrese Contraseña antigua: "); String oldPass= leer.nextLine();
		boolean error=false;
		while (error!=true) {
			if (oldPass.equals(clientes.getContraseñaPI(indice))) {
				System.out.println("ingrese Contraseña Nueva (maximo 10 caracteres): "); String newPass= leer.nextLine(); 
				while (newPass.length()>10) {
					System.out.println("contraseña muy larga, intente nuevamente ");
					System.out.println("ingrese Contraseña Nueva (maximo 10 caracteres): "); newPass= leer.nextLine(); 
				}
				clientes.setContraseña(indice,newPass);
				error=true;
			}
			else {
				System.out.println("Contraseña incorrecta");
				System.out.println("ingrese Contraseña antigua: ");oldPass= leer.nextLine();
			}
		}
		System.out.println("--------------------");
		
	}
	
	/**
	 * with this function the user will be able to view every product available
	 * @param sistema - system
	 */
	public static void catalogo(ListaProductos productos) {
		System.out.println("--------------------");
		System.out.println("El catalogo de productos es el siguiente: ");
		int contador=0;
		for (int i=0;productos.getCont()-1>=i;i++) {
			String linea= productos.getProd(i); String part[]= linea.split(",");
			if (Integer.parseInt(part[2])>0) {
				contador++;
				System.out.println(contador+") "+part[0]+" con stock de: "+part[2]+" y valor de: "+part[1]+"$");
			}
		}
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the user will be able to see his balance
	 * @param sistema - system
	 */
	public static void verSaldo(ListaClientes clientes,int indice) {
		System.out.println("--------------------");
		int saldo= clientes.getSaldoPI(indice); 
		System.out.println("Su saldo Actual es: "+saldo+" $");
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the user will be able to charge more currency to his balance
	 * @param sistema - system
	 */
	public static void recargaSaldo(ListaClientes clientes,int indice) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		System.out.println("ingrese el monto que desea agregar a su sueldo:");
		int plusSaldo= Integer.parseInt(leer.nextLine());
		while (plusSaldo<0) {
			System.out.println("No se admiten valores negativos");
			System.out.println("ingrese el monto que desea agregar a su sueldo:");
			plusSaldo= Integer.parseInt(leer.nextLine());
		}
		clientes.setSaldoPI(indice, plusSaldo);
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the user will be able to see the amount of products he picked
	 * @param sistema - system
	 */
	public static void verCarrito(ListaProductos productos) {
		System.out.println("--------------------");
		System.out.println("los productos en su carrito son: ");
		int contador=0;
		for (int i=0;productos.getCont()-1>=i;i++) {
			String linea= productos.getProd(i); String part[]= linea.split(",");
			int cantEnCarrito= productos.getCantidadPC(i);
			if (cantEnCarrito>0) {
				contador++;
				System.out.println(contador+") "+part[0]+" con una cantidad de: "+cantEnCarrito+" unidad(es) en el carrito"+" (total: "+(Integer.parseInt(part[1])*cantEnCarrito)+" $)");
			}
		}
		if (contador==0){
			System.out.println("no hay ningún producto en tu carrito!");
		}
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the user will be able to eliminate a product that he picked early
	 * @param sistema - system
	 */
	public static void quitarCarrito(ListaProductos productos) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		boolean [] indices= new boolean[productos.getCont()];
		boolean existenProd= false; // si existen productos en el carrito
		System.out.println("los productos en su carrito son: ");
		for (int i=0;productos.getCont()-1>=i;i++) {
			String linea= productos.getProd(i); String part[]= linea.split(",");
			int cantEnCarrito= productos.getCantidadPC(i);
			if (cantEnCarrito>0) {
				existenProd= true; indices[i]=true;
				System.out.println(i+") "+part[0]+" con una cantidad de: "+cantEnCarrito+" unidad(es) en el carrito");
			}
		}
		if (existenProd==false) {	
			System.out.println("no hay ningún producto en tu carrito para eliminar!");
		}
		else {
			System.out.println("ingrese (según el numero) el producto que desea eliminar:");int index= Integer.parseInt(leer.nextLine());
		    while (indices[index]!=true) {
		    	System.out.println("valor no disponible, intente nuevamente");
		    	System.out.println("ingrese el valor del objeto que desea eliminar");index= Integer.parseInt(leer.nextLine());
		    }
		    System.out.println("ingrese la cantidad que desea eliminar");int valor= Integer.parseInt(leer.nextLine());
		    while (valor<=0 || valor>productos.getCantidadPC(index)) {
		    	System.out.println("Valor no valido, intenta nuevamente");
		    	System.out.println("ingrese la cantidad que desea eliminar: ");valor= Integer.parseInt(leer.nextLine());
		    }
		    productos.borrarPc(index, valor);
		}
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the user will be able to pay all the products he picked, if he has enough balance on his account
	 * @param sistema - system
	 */
	public static void paycarrito(ListaClientes clientes, ListaProductos productos, int indice) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		int valorTotal=0;
		for (int i=0; productos.getCont()-1>=i;i++) {
			String linea= productos.getProd(i); String[]part= linea.split(",");
			valorTotal= valorTotal+(productos.getCantidadPC(i)*Integer.parseInt(part[1]));
		}
		System.out.println("valor total a pagar: "+valorTotal+" $");
		System.out.println("desea pagar? (ingrese 1 si desea comprar todo: ");
		String respuesta= leer.nextLine();
		if (respuesta.equals("1")){
			int saldo=clientes.getSaldoPI(indice);
			if (saldo<valorTotal) {
				System.out.println("no tienes saldo suficiente, intenta recargando!");
				System.out.println("Compra no realizada");
			}
			else {
				saldo=saldo-valorTotal;
				clientes.setSaldoPI(indice, -saldo);
				for (int i=0; productos.getCont()-1>=i;i++) {
					String ventas=productos.getVenta(i); String[]parte= ventas.split(",");
					int vecesVendido;
					if (parte[0].equals("0")){
						vecesVendido=0;
					}
					else {
						vecesVendido=Integer.parseInt(parte[1]);
					}
					int newVecesVendido= vecesVendido+productos.getCantidadPC(i);
					productos.setCantidadPC(i, 0, 0);
					productos.setVecesVendido(i, newVecesVendido);
				}
			}
		}
		else {
			System.out.println("Compra no realizada");
		}
		System.out.println("--------------------");
	}
	
	//ADMIN
	
	/**
	 * with this function the ADMIN will be able to eliminate a user from the system
	 * @param sistema - system
	 */
	public static void bloqueo(ListaClientes clientes) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		System.out.println("ingrese el nombre del usuario que desea eliminar: ");String Eliminado= leer.nextLine();
		boolean eliminacion= clientes.eliminarClientes(Eliminado);
		while (eliminacion!=true) {
			System.out.println("usuario no encontrado, intnente nuevamente");
			System.out.println("ingrese el nombre del usuario que desea eliminar: ");Eliminado= leer.nextLine();
			eliminacion= clientes.eliminarClientes(Eliminado);
		}
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the ADMIN will be able to see every bought product
	 * @param sistema - system
	 */
	public static void historial(ListaProductos productos) {
		System.out.println("--------------------");
        System.out.println("Todas las ventas hasta el momento son: ");
		String linea;
        int cant= productos.getCont();
        for (int i=0;cant-1>=i;i++) {
        	linea=productos.getVenta(i);
        	if (linea!=null) {
        		System.out.println(linea);
        	}
        }
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the ADMIN will be able to add a product to the system
	 * @param sistema - system
	 */
	public static void agregarProd(ListaProductos productos) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		String producto; boolean existe=false;
		System.out.println("ingrese Nombre del Producto:"); String newProd=leer.nextLine();
		for (int i=0;productos.getCont()-1>=i;i++) {
			producto= productos.getProd(i);
			String [] part= producto.split(",");
			String nuevo= newProd.toUpperCase(); String viejo= part[0].toUpperCase();
			if (nuevo.equals(viejo)){
				existe=true;
			}
		}
		if (existe==false){
			System.out.println("ingrese el precio del producto:"); int newPrice=Integer.parseInt(leer.nextLine());
			Producto newProduct= new Producto (newProd,newPrice,0);
			productos.ingresarProducto(newProduct);
		}
		else {
			System.out.println("Ya existe este producto");
		}

		System.out.println("--------------------");
	}
	
	/**
	 * with this function the ADMIN will be able to add more stock to a product in the system
	 * @param sistema - system
	 */
	public static void agregarStock(ListaProductos productos) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		System.out.println("ingrese Nombre del Producto:"); String prod=leer.nextLine();
		int indice= productos.indiceProducto(prod);
		while (indice==-1) {
			System.out.println("Producto no existente, intente de nuevo");
			System.out.println("ingrese Nombre del Producto:"); prod=leer.nextLine();
			indice= productos.indiceProducto(prod);
		}
		System.out.println("ingrese cuantas unidades desea agregar:"); int plusStock=Integer.parseInt(leer.nextLine());
		while (plusStock<0) {
			System.out.println("No se admiten valores negativos");
			System.out.println("ingrese Nombre del Producto:"); plusStock=Integer.parseInt(leer.nextLine());
		}
		productos.setStock(plusStock, indice);
		System.out.println("--------------------");
	}
	
	/**
	 * with this function the ADMIN will be able to change a product value
	 * @param sistema - system
	 */
	public static void actualizar(ListaProductos productos) {
		System.out.println("--------------------");
		Scanner leer= new Scanner(System.in);
		System.out.println("ingrese el nombre del producto que desea actualizar: ");String prod= leer.nextLine();
		int indice= productos.indiceProducto(prod);
		while(indice==-1) {
			System.out.println("Producto no existente, intente de nuevo");
			System.out.println("ingrese Nombre del Producto:"); prod=leer.nextLine();
			indice= productos.indiceProducto(prod);
		}
		System.out.println("ingrese el nuevo precio:"); int newPrecio=Integer.parseInt(leer.nextLine());
		while (newPrecio<0) {
			System.out.println("No se admiten valores negativos");
			System.out.println("ingrese el nuevo precio:");newPrecio=Integer.parseInt(leer.nextLine());
		}
		productos.setPrecio(newPrecio,indice);
		System.out.println("--------------------");
	}
	
	// CLOSE
	
	/**
	 * A function that overwrites the file "Clientes.txt" when the system is closed
	 * @param sistema - system
	 * @throws IOException - An exception
	 */
	public static void cerrarClientes(ListaClientes clientes)throws IOException {
        FileWriter arch = new FileWriter("Clientes.txt"); 
        BufferedWriter agregar = new BufferedWriter(arch);
        String linea;
        int cant= clientes.getCont();
        for (int i=0;cant-1>=i;i++) {
        	linea=clientes.getTodoPi(i);
        	agregar.write(linea+"\n");
        }
        agregar.close();
	}
	
	/**
	 * A function that overwrites the file "Productos.txt" when the system is closed
	 * @param sistema - system
	 * @throws IOException - An exception
	 */
	public static void cerrarProductos(ListaProductos productos) throws IOException{
        FileWriter arch = new FileWriter("Productos.txt"); 
        BufferedWriter agregar = new BufferedWriter(arch);
        String linea;
        int cant= productos.getCont();
        for (int i=0;cant-1>=i;i++) {
        	linea=productos.getProd(i);
        	agregar.write(linea+"\n");
        }
        agregar.close();
	}
	
	/**
	 * A function that overwrites the file "Ventas.txt" when the system is closed
	 * @param sistema - system
	 * @throws IOException - An exception
	 */
	public static void cerrarVentas(ListaProductos productos) throws IOException{
		FileWriter arch = new FileWriter("Ventas.txt"); 
        BufferedWriter agregar = new BufferedWriter(arch);
        String linea;
        int cant= productos.getCont();
        for (int i=0;cant-1>=i;i++) {
        	linea=productos.getVenta(i);
        	if (linea!="0") {
        		agregar.write(linea+"\n");
        	}
        }
        agregar.close();
	}
	
}