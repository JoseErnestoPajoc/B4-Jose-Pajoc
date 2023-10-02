
package ejemplo.proyecto.pkg4to;


public class usuario {
    String nombreUsuario, contraUsuario;
    
    public usuario(String nombreUsuario, String contraUsuario){
        this.nombreUsuario = nombreUsuario;
        this.contraUsuario = contraUsuario;
    }
    
    public String getNombre(){
        return this.nombreUsuario;
    }
    
    public String getContra(){
        return this.contraUsuario;
    }
}
