import java.time.LocalDate;
import java.util.Hashtable;

public class Sistema {
    Hashtable usuarios;

    Sistema(){
        usuarios = new Hashtable();
    }

    void agregarUsuario(Persona persona){
        try{
            usuarios.put(persona.getId(), persona);
        }catch(Exception e){
            System.out.println("Error al agregar usuario, puede que ya exista");
        }
    }

    void agregarRelacion(Persona p1, LocalDate fecha, Persona p2){
        if(p1.equals(p2)){
            System.out.println("No puedes agregar a la misma persona como amiga");
            return;
        }

        boolean posible = p1.addAmigo(p2);
        if(posible){
            p2.addAmigo(p1);
        }else{
            System.out.println("Amigos ya se encuentran agregados");
            return;
        }
        return;
    }
}
