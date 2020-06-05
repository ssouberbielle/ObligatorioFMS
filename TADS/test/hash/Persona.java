package hash;

public class Persona {
    private long cedula;
    private String nombre;
    private int edad;

    public Persona(long cedula, String nombre, int edad){
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
