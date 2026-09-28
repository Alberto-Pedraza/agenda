package org.generation.agenda.models;

public class Agenda {
    // El TreeMap usa el 'número' (String) como Clave única, y el objeto 'Contacto' como Valor
    private TreeMap<String, Contacto>  contactos;
    private int MAX_SIZE;

    //Constructor por defecto (tamaño 10 por defecto)
    public Agenda () {
        this.contactos = new TreeMap<>();
        this.MAX_SIZE = 10;
    }

    // Constructor: con tamaño personalizado del usuario
    public Agenda(int MAX_SIZE) {
        this.contactos = new TreeMap<>();
        this.MAX_SIZE = MAX_SIZE;
    }
    //Apertura de metodo listar contactos
    public void listarContactos() {
        System.out.println("LISTA DE CONTACTOS (ORDENADOS DE LA A - Z)");
        if(contactos.isEmpty()){
            System.out.println("La agenda esta vacía");
        } else {
            for (Contacto c : contactos.value()){
                System.out.println(c);
            }
        }

        }
    } //Cierre de metodo listar

} //Cierre de clase Agenda
