package mx.unam.ingenieria.tienda.recyclerview;

public class MuestraProducto {

    private String titulo;
    private String imagen;
    private String descripcion;

    public MuestraProducto(){ //Cosntructor vacio para realizar la base de datos y que ahi realice la consulta en donde requerira este elemento

    }

    public MuestraProducto(String titulo, String imagen, String descripcion) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
