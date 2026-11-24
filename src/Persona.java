import java.time.LocalDateTime;
import java.util.*;

public class Persona {
    private int id;
    private String nombre;
    private int dia;
    private int mes;
    private String profesion;
    private String email;
    private ArrayList<Amistad> amistades = new ArrayList<>();
    Persona(int id, String nombre, int dia, int mes, String profesion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.profesion = profesion;
        this.email = email;
    }

    public int getId(){
        return id;
    }

    String getEmail(){
        return email;
    }

    String getProfesion(){
        return profesion;
    }

    String getNombre(){
        return nombre;
    }

    boolean addAmigo(Persona p){
        for(int i=0; i<amistades.size(); i++){
            if(amistades.get(i).getPersona() == p){
                return false;
            }
        }
        amistades.add(new Amistad(p, LocalDateTime.now()));
        return true;
    }

    void printAmigos(){
        System.out.println("Lista de amigos de " + this.getNombre());
        for(int i=0; i<amistades.size(); i++){
            Persona p = amistades.get(i).getPersona();
            System.out.println((i+1) + ". " + p.getNombre() + ". Correo: " + p.getEmail());
        }
    }

    ArrayList<Amistad> getAmistades(){
        return amistades;
    }


}
