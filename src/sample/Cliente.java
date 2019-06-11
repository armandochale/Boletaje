package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    private IntegerProperty clienteId;
    private StringProperty nombre;
    private StringProperty apellidos;
    private StringProperty direccion;

    public Cliente(){
        this(0,"","","");
    }

    public Cliente(int clienteId, String nombre, String apellidos, String direccion){
        this.clienteId = new SimpleIntegerProperty(clienteId);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.direccion = new SimpleStringProperty(direccion);
    }
    public int getClienteId() {
        return clienteId.get();
    }

    public void setClienteId(int clienteId) {
        this.clienteId.set(clienteId);
    }

    public IntegerProperty clienteIdProperty(){
        return clienteId;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty(){
        return nombre;
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidosProperty(){
        return apellidos;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccionProperty(){
        return direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
