/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Agust
 * @param <T>
 */
public class Zoologico<T extends CSVSerializable & Serializable> implements Almacenable<T> {

    private List<T> animales = new ArrayList<>();

    @Override
    public void agregar(T item) { 
        validarRepetido(item);
        animales.add(item);
    }
    
    @Override
    public boolean eliminar(T item) {
        return animales.remove(item);
    }
    
    @Override
    public void eliminarPorIndice(int index) { 
        validarIndice(index);
        animales.remove(index);
    }
    
    @Override
    public T obtenerPorIndice(int indice) { 
        validarIndice(indice);
        return animales.get(indice);
    }

    
    private void validarIndice(int indice) {
        if (indice < 0 || indice >= animales.size()) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
    }

    private void validarRepetido(T item) {
        if (animales.contains(item)) {
            throw new IllegalArgumentException("El item ya se encuentra en el almacen");
        }
    }


    @Override
    public List<T> filtrar(Predicate<? super T> criterio) { // Permite filtrar según un criterio dado
        List<T> resultado = new ArrayList<>();
        for (T item : animales) {
            if (criterio.test(item)) resultado.add(item);
        }
        return resultado;
    }


    public void ordenar() {
        if (!animales.isEmpty() && animales.get(0) instanceof Comparable) {
            animales.sort((Comparator<? super T>) Comparator.naturalOrder());
        } else {
            throw new UnsupportedOperationException("El tipo de elemento no es Comparable para orden natural.");
        }
    }


    public void ordenar(Comparator<? super T> comparador) {
        animales.sort(comparador);
    }

    @Override
    public Iterator<T> iterator() {
        if (!animales.isEmpty() && animales.get(0) instanceof Comparable) {
            List<T> copia = new ArrayList<>(animales);
            copia.sort((Comparator<? super T>) Comparator.naturalOrder());
            return copia.iterator();
        }
        return animales.iterator();
    }

    public Iterator<T> iterator(Comparator<? super T> comparador) {
        List<T> copia = new ArrayList<>(animales);
        copia.sort(comparador);
        return copia.iterator();
    }

    @Override
    public void paraCadaElemento(Consumer<? super T> accion) {
        for (T item : animales) {
            accion.accept(item);
        }
    }
    
    
    // SERIALIZAR
    public void guardarEnArchivo(String rutaArchivo) throws IOException {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            salida.writeObject(animales);
        }
    }
    
    // DESERIALIZAR PELICULAS
    public void cargarDesdeArchivo(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            this.animales = (List<T>) entrada.readObject();
        }
    }


    public void guardarEnCSV(String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write(Animal.toCSVHeader() + "\n");
            for (T elemento : animales) {
                writer.write(elemento.toCSV());
                writer.newLine();
            }
        }
    }
    

    public void cargarDesdeCSV(String rutaArchivo, Function<String, T> fromCSVFunction) throws IOException {
        animales.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            reader.readLine();
            String linea;
            while ((linea = reader.readLine()) != null) {
                T item = fromCSVFunction.apply(linea);
                if (item != null) {
                    animales.add(item);
                }
            }
        }
    }
    
}

