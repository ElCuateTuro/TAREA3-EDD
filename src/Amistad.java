import java.time.LocalDateTime;

public class Amistad {
    private int id;
    LocalDateTime fecha;

    Amistad(int id,  LocalDateTime fecha) {
        this.id=id;
        this.fecha=fecha;
    }

    Amistad getAmistad(){
        return this;
    }

    int getId(){
        return id;
    }
    LocalDateTime getFecha(){
        return fecha;
    }
}
