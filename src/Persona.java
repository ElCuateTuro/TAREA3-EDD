import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Persona {
    private int id;
    private String nombre;
    private int dia;
    private int mes;
    private String profesion;
    private String email;
    private ArrayList<Amistad> amistades;
    private HashMap<Integer, LocalDate> bloqueados;

    public Persona(int id, String nombre, int dia, int mes, String profesion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.profesion = profesion;
        this.email = email;
        this.amistades = new ArrayList<>();
        this.bloqueados = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Amistad> getAmistades() {
        return amistades;
    }

    public HashMap<Integer, LocalDate> getBloqueados() {
        return bloqueados;
    }

    public boolean esAmigoDirecto(Persona p) {
        for (Amistad a : amistades) {
            if (a.getId() == p.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean estaBloqueado(Persona p) {
        return bloqueados.containsKey(p.getId());
    }

    public boolean addAmigo(Persona p, LocalDate fecha) {
        if (esAmigoDirecto(p) || estaBloqueado(p) || p.estaBloqueado(this)) {
            return false;
        }
        amistades.add(new Amistad(p, fecha));
        return true;
    }

    public boolean removeAmigo(Persona p) {
        for (int i = 0; i < amistades.size(); i++) {
            if (amistades.get(i).getId() == p.getId()) {
                amistades.remove(i);
                return true;
            }
        }
        return false;
    }

    public void bloquear(Persona p, LocalDate fecha) {
        removeAmigo(p);
        p.removeAmigo(this);
        bloqueados.put(p.getId(), fecha);
    }

    public void printAmigos() {
        System.out.println("Lista de amigos de " + this.getNombre() + ":");
        if (amistades.isEmpty()) {
            System.out.println("  Sin amigos registrados.");
            return;
        }
        for (int i = 0; i < amistades.size(); i++) {
            Amistad a = amistades.get(i);
            Persona p = a.getPersona();
            System.out.println((i + 1) + ". " + p.getNombre() + " | Correo: " + p.getEmail() + " | Amigos desde: " + a.getFecha());
        }
    }
}