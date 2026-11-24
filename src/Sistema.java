import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;

public class Sistema {
    Hashtable usuarios;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/YYYY");

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
            recomendarAmigos(p1, p2);
            recomendarAmigos(p2, p1);
        }else{
            System.out.println("Amigos ya se encuentran agregados");
            return;
        }
        return;
    }

    void printUsuarios(){
        for(Object id : usuarios.keySet()){
            Persona persona = (Persona) usuarios.get(id);
            System.out.println("ID: " + id + ", Nombre: " + persona.getNombre() +
                    ", Profesión: " + persona.getProfesion() + ", Email: " + persona.getEmail());
        }
    }

    void recomendarAmigos(Persona p, Persona x){
        ArrayList<Amistad> amistades = p.getAmistades();
        for(int i=0; i< amistades.size(); i++){
            Persona pe = (Persona)usuarios.get(amistades.get(i).getId());
            if(!pe.getEmail().equals(x.getEmail()) && !pe.getAmistades().contains(x)){
                System.out.println("===============");
                System.out.println("De: " + p.getNombre() + " fecha: " + LocalDateTime.now().format(formato));
                System.out.println("");
                System.out.println("Hola " + pe.getNombre() + ", tal parece que " + p.getNombre() + " acaba de agregar a " + x.getNombre() + " a su lista de amigos!, " +
                        "puede que lo conozcas!");
                System.out.println("===============");
            }
        }
    }
}