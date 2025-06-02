package backend;

public class PreguntaVF {
    private final String asignatura;
    private final String taxonomia;
    private final String enunciado;
    private final boolean correcta;
    private final int ponderacion;

    public PreguntaVF(String asignatura, String taxonomia, String enunciado,
                      boolean correcta, int ponderacion) {
        this.asignatura  = asignatura;
        this.taxonomia   = taxonomia;
        this.enunciado   = enunciado;
        this.correcta    = correcta;
        this.ponderacion = ponderacion;
    }

    // Getters
    public String getAsignatura()  { return asignatura; }
    public String getTaxonomia()   { return taxonomia; }
    public String getEnunciado()   { return enunciado; }
    public boolean isCorrecta()    { return correcta; }
    public int getPonderacion()    { return ponderacion; }
}