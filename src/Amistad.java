import java.time.*;
import java.time.format.DateTimeFormatter;

public class Amistad {
    private Persona persona;
    private int id;
    private boolean bloqueado;
    LocalDateTime fecha;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    Amistad(Persona p, LocalDateTime fecha) {
        this.persona = p;
        this.fecha=fecha;
        this.id = p.getId();
        this.bloqueado = false;
    }
    int getId(){return id;}
    Persona getPersona(){return persona;}
    String getFecha(){
        return fecha.format(formato);
    }
    public boolean isBloqueado(){
        return bloqueado;
    }
    public void setBloqueado(boolean bloqueado){
        this.bloqueado = bloqueado;
    }
}
