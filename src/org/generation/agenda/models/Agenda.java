package org.generation.agenda.models;

import java.util.TreeMap;

public class Agenda {
    // El TreeMap usa el 'número' (String) como Clave única, y el objeto 'Contacto' como Valor
    private TreeMap<String, Contacto> misContactos;

    // Constructor: Inicializa la agenda vacía
    public Agenda() {
        this.misContactos = new TreeMap<>();
    }

    public void eliminarContacto(String numero) {
        misContactos.remove(numero);
        System.out.println(String.format("Se elimino el contacto %s", numero));
    }
} //Cierre de clase Agenda
