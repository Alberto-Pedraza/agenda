package org.generation.agenda.models;

import org.generation.agenda.exceptions.InvalidData;

public class Contacto {
    private String nombre;
    private String apellido;
    private String numero;


    //Constructor de la clase
    public Contacto(String nombre, String apellido, String numero) throws InvalidData{
        //Utilizamos los setters para que pueda utilizar las excepciones de cuando se ingresan campos en blanco
        setNombre(nombre);
        setApellido(apellido);
        setNumero(numero);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre (String nombre) throws InvalidData {
        //Condiciones para tirar la excepción
        if(nombre==null || nombre.trim().isEmpty())
            throw new InvalidData("El nombre no puede estar vacío o ser puros espacios.");
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws InvalidData {
        //Condiciones para tirar la excepción
        if(apellido==null || apellido.trim().isEmpty())
            throw new InvalidData("El apellido no puede estar vacío o ser puros espacios.");
        this.apellido = apellido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) throws InvalidData {
        if (numero == null || numero.trim().isEmpty()) {
            throw new InvalidData("El número no puede estar vacío o ser puros espacios.");
        }
        this.numero = numero.trim();
    }
}
