
package ejemplo.proyecto.pkg4to;

public class cliente {
    String nit, nombre;
    int edad;
    char genero;

    public cliente(String nit, String nombre, int edad, char genero) {
        this.nit = nit;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public String getNit() {
        return this.nit;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public char getGenero() {
        return this.genero;
    }
    
}
