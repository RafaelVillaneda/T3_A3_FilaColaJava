import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Impresion{
	private int indentificador;
	private double tamaño;
	private int numeroHojas;
	public Impresion(int indentificador, double tamaño, int numeroHojas) {
		super();
		this.indentificador = indentificador;
		this.tamaño =tamaño;
		this.numeroHojas = numeroHojas;
	}
	@Override
	public String toString() {
		return "Impresion [indentificador=" + indentificador + ", tamaño=" + tamaño + ", numeroHojas=" + numeroHojas
				+ "]";
	}
	
}
interface RegistroImpresiones{
	public boolean verificarFilaLlena();
	public boolean verficarFilaVacia();
	public boolean insertarImpresion(Impresion i);
	public Impresion eliminarImpresion();
	
}
class ImplementacionFilaEstatica implements RegistroImpresiones{
	private Impresion fila[];
	private int tamaño;
	private int pociFinal;
	public ImplementacionFilaEstatica(int tamaño) {
		fila=new Impresion[tamaño];
		pociFinal=-1;
		this.tamaño=tamaño;
	}
	@Override
	public boolean verificarFilaLlena() {
		return pociFinal==tamaño-1;
	}
	@Override
	public boolean verficarFilaVacia() {
		return pociFinal==-1;
	}
	@Override
	public boolean insertarImpresion(Impresion i) {
		if(!verificarFilaLlena()) {
			pociFinal++;
			fila[pociFinal]=i;
			return true;
		}
		return false;
	}
	@Override
	public Impresion eliminarImpresion() {
		if(verficarFilaVacia()==false) {
			Impresion retorno=fila[pociFinal];
			fila[pociFinal]=null;
			pociFinal--;
			for(int i=0;i<pociFinal;i++) {
				fila[i]=fila[i+1];
			}
			return retorno;
		}else {
			System.out.println("Esta vacia");
			return null;
		}
		
	}
	
	
	
	
}
class ImplementacionFilaDinamica implements RegistroImpresiones{
	Queue<Impresion> filaImpresiones=new LinkedList<Impresion>();
	private int tamaño;
	private int poci;
	public ImplementacionFilaDinamica(int tamaño) {
		super();
		this.tamaño = tamaño;
		poci=-1;
	}
	@Override
	public boolean verificarFilaLlena() {
		return  poci==tamaño-1;
	}
	@Override
	public boolean verficarFilaVacia() {
		return filaImpresiones.isEmpty();
	}
	@Override
	public boolean insertarImpresion(Impresion i) {
		if(!verificarFilaLlena()) {
			poci++;
			filaImpresiones.add(i);
			return true;
		}
		return false;
	}
	@Override
	public Impresion eliminarImpresion() {
		if(verficarFilaVacia()==false) {
			poci--;
			return filaImpresiones.poll();
		}
		return null;
	}
	
}
public class Prueba {

	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);
		System.out.println("Tamaño de la fil sera de 3 pociciones");
		ImplementacionFilaEstatica filaE=new ImplementacionFilaEstatica(3);
		ImplementacionFilaDinamica filaDi=new ImplementacionFilaDinamica(3);
		String op="";
		int id=0;
		int id2=0;
		do {
			System.out.println("A) Agregar a cola de impresion");
			System.out.println("B) Imprimir");
			System.out.println("C) Salir");
			op=entrada.nextLine().toUpperCase().replace(" ","");
			switch (op) {
			case "A":
				System.out.println("A que Impresora deseas mandar a imprimir A o B");
				String op2=entrada.nextLine();
				if(op2.equalsIgnoreCase("A")) {
					System.out.println("Numero de hojas que se van a imprimir");
					try {
					int num=entrada.nextInt();
					Impresion im=new Impresion(++id,0.0,num);
					System.out.println(filaE.insertarImpresion(im)?"Se mando a la cola de impresion":"No se pudo mandar a la cola de impresion");
					}catch (NumberFormatException e) {
						System.out.println("Debes de ingresar un numero entero");
					}
				}else if(op2.equalsIgnoreCase("B")){
					System.out.println("Numero de hojas que se van a imprimir");
					try {
					int num=entrada.nextInt();
					Impresion im2=new Impresion(++id,0.0,num);
					System.out.println(filaDi.insertarImpresion(im2)?"Se mando a la cola de impresion":"No se pudo mandar a la cola de impresion");
					}catch (NumberFormatException e) {
						System.out.println("Debes de ingresar un numero entero");
					}
				}
				entrada.nextLine();
				break;
			case "B":
				System.out.println("A que Impresora deseas imprimir a imprimir A o B");
				String opB=entrada.nextLine();
				if(opB.equalsIgnoreCase("A")) {
					System.out.println("Se imprimio:");
					System.out.println(filaE.eliminarImpresion());
					id--;
				}else if(opB.equalsIgnoreCase("B")) {
					System.out.println("Se imprimio:");
					System.out.println(filaDi.eliminarImpresion());
					id2--;
				}
				break;
			case "C":
				System.out.println("Saliendo");
				break;
			default:
				System.out.println("Opcion no encontrada");
				break;
			}
		}while(!op.equalsIgnoreCase("C"));
	}

}
