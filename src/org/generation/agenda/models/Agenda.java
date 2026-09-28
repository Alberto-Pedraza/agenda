package org.generation.agenda.models;

import java.util.TreeMap;

public class Agenda {
    // El TreeMap usa el 'número' (String) como Clave única, y el objeto 'Contacto' como Valor
    private TreeMap<String, Contacto> misContactos;

    // Constructor: Inicializa la agenda vacía
    public Agenda() {
        this.misContactos = new TreeMap<>();
    }


    // Verifica si un contacto ya existe en la agenda
    public boolean existeContacto(Contacto c) {

        // Recorremos todos los contactos guardados
        for (Contacto contactoGuardado : misContactos.values()) {

            // Comparamos nombre y apellido ignorando mayúsculas/minúsculas
            if (c.getNombre().equalsIgnoreCase(contactoGuardado.getNombre())
                    && c.getApellido().equalsIgnoreCase(contactoGuardado.getApellido())) {

                return true;
            }
        }

        // Si terminó de buscar y no encontró coincidencias
        return false;
    }

} //Cierre de clase Agenda
