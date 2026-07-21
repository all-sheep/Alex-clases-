public class Curso {

    private String clave;
    private String nombre;
    private String docente;
    private int cupoMaximo;
    private int inscritos;

    public Curso(String clave, String nombre, String docente, int cupoMaximo) {
        this.clave = clave;
        this.nombre = nombre;
        this.docente = docente;
        this.cupoMaximo = cupoMaximo;
        this.inscritos = 0; // Todo curso nuevo inicia sin inscritos
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    public boolean hayCupoDisponible() {
        return inscritos < cupoMaximo;
    }

    public void inscribirEstudiante() {
        inscritos++;
    }

    public boolean darDeBajaEstudiante() {
        if (inscritos > 0) {
            inscritos--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(
            "Clave: %-10s | Nombre: %-20s | Docente: %-20s | Cupo: %d/%d",
            clave, nombre, docente, inscritos, cupoMaximo
        );
    }
}
