public class Solicitud {

    public enum EstadoSolicitud {
        PENDIENTE("Pendiente"),
        PROCESADA("Procesada"),
        ACEPTADA("Aceptada"),
        RECHAZADA("Rechazada");

        private final String etiqueta;

        EstadoSolicitud(String etiqueta) {
            this.etiqueta = etiqueta;
        }

        public String getEtiqueta() {
            return etiqueta;
        }
    }

    private int id;
    private String nombre;
    private String fecha;
    private EstadoSolicitud estadoSolicitud;

    public Solicitud(int id, String nombre, String fecha, EstadoSolicitud estadoSolicitud) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.estadoSolicitud = estadoSolicitud;
    }

    public int getId() {
        return id; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public String getFecha() { 
        return fecha; 
    }
    public EstadoSolicitud getEstado() { 
        return estadoSolicitud; 
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setFecha(String fecha) { 
        this.fecha = fecha; 
    }
    public void setEstado(EstadoSolicitud estadoSolicitud) { 
        this.estadoSolicitud = estadoSolicitud; 
    }
}