package com.example.project;

import java.util.Random;
public class HashLinearProbing {
    private int hsize; // tamano de la tabla hash
    private Persona [] buckets; // array que representa la tabla hash
    private String AVAILABLE;
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.buckets = new Persona[hsize];
        this.hsize = hsize;
        this.AVAILABLE = String.valueOf(Integer.MIN_VALUE);
        this.size = 0;
    }

    public int hashing(String dni) {
    	//Se crea una variable suma para el valor del dni converti en ascii
        int suma = 0;
        int ascii;
        
        //En un ciclo for obtendra el valor ascii de cada caracter del dni, 
        for(int i = 0; i < dni.length(); i++) {
        	ascii = dni.charAt(i);
        	suma = suma + ascii;
        }
        //Al final se calculara el hash correspondiente para el DNI
        int hash = suma % hsize;
        return hash;
    }

    public void insertHash(Persona persona) {
    	//Se obtiene el hash segun el dni de la persona
        int hash = hashing(persona.DNI);

        if (isFull()) {
            System.out.println("Tabla hash esta llena!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] == null || buckets[hash].DNI == AVAILABLE) {
                buckets[hash] = persona;
                size++;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public void deleteHash(String dni) {
        int hash = hashing(dni);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] != null && buckets[hash].DNI.equals(dni)) {
                buckets[hash].DNI = AVAILABLE;
                size--;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + dni + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] == null || buckets[i].DNI == AVAILABLE) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + buckets[i].toString());
            }
        }
    }

    public String findHash(String dni) {
        int hash = hashing(dni);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return null;
        }

        for (int i = 0; i < hsize; i++) {
            try {
                if (buckets[hash].DNI.equals(dni)) {
                    return buckets[hash].nombre;
                }
            } catch (Exception E) {
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + dni + " no encontrada!");
        return null;
    }    
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }

}
