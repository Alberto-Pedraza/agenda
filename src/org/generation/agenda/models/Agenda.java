package org.generation.agenda.models;

import java.util.TreeMap;

public class Agenda {
    // El TreeMap usa el 'número' (String) como Clave única, y el objeto 'Contacto' como Valor
    private final int MAX_SIZE = 10;
    private TreeMap<String, Contacto> misContactos;

    // Constructor: Inicializa la agenda vacía
    public Agenda() {
        this.misContactos = new TreeMap<String, Contacto>();
    }

    public void eliminarContacto(String numero) {
        misContactos.remove(numero);
        System.out.println(String.format("Se elimino el contacto %s", numero));
    }
    public Boolean agendaLlena () {
        if (misContactos.size() == MAX_SIZE) {
            System.out.println("No hay espacio disponible para nuevos contactos.");
            return true;
        } else {
            return false;
        }
    } // Fin agendaLlena


} //Cierre de clase Agenda
