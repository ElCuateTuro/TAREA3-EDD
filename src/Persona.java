import java.time.LocalDateTime;
import java.util.*;

public class Persona {
    private int id;
    private String nombre;
    private int dia;
    private int mes;
    private String profesion;
    private String email;
    ArrayList<Amistad> amistad = new ArrayList();
    Persona(int id, String nombre, int dia, int mes, String profesion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.profesion = profesion;
        this.email = email;
    }

    int getId() {
        return id;
    }

    boolean addAmigo(Persona p){
        for(Amistad a : amistad){
            if(a.getId() == p.getId()){
                return false;
            }
        }
        amistad.add(new Amistad(p.id, LocalDateTime.now()));
        return true;
    }
}
