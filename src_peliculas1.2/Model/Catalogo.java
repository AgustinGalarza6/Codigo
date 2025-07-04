/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;

/**
 *
 * @author Agust
 * @param <T>
 */

public class Catalogo<T extends CSVSerializable & Serializable> implements Almacenable<T> {

    private List<T> items = new ArrayList<>();

    @Override
    public void agregar(T item) { 
        validarRepetido(item);
        items.add(item);
    }
    
    @Override
    public boolean eliminar(T item) {
        return items.remove(item);
    }
    
    @Override
    public void eliminarPorIndice(int index) { 
        validarIndice(index);
        items.remove(index);
    }
    
    @Override
    public T obtenerPorIndice(int indice) { 
        validarIndice(indice);
        return items.get(indice);
    }

    
    private void validarIndice(int indice) {
        if (indice < 0 || indice >= items.size()) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
    }

    private void validarRepetido(T item) {
        if (items.contains(item)) {
            throw new IllegalArgumentException("El item ya se encuentra en el almacen");
        }
    }


    @Override
    public List<T> filtrar(Predicate<? super T> criterio) { // Permite filtrar según un criterio dado
        List<T> resultado = new ArrayList<>();
        for (T item : items) {
            if (criterio.test(item)) resultado.add(item);
        }
        return resultado;
    }


    public void ordenar() {
        if (!items.isEmpty() && items.get(0) instanceof Comparable) {
            items.sort((Comparator<? super T>) Comparator.naturalOrder());
        } else {
            throw new UnsupportedOperationException("El tipo de elemento no es Comparable para orden natural.");
        }
    }


    public void ordenar(Comparator<? super T> comparador) {
        items.sort(comparador);
    }

    @Override
    public Iterator<T> iterator() {
        if (!items.isEmpty() && items.get(0) instanceof Comparable) {
            List<T> copia = new ArrayList<>(items);
            copia.sort((Comparator<? super T>) Comparator.naturalOrder());
            return copia.iterator();
        }
        return items.iterator();
    }

    public Iterator<T> iterator(Comparator<? super T> comparador) {
        List<T> copia = new ArrayList<>(items);
        copia.sort(comparador);
        return copia.iterator();
    }

    @Override
    public void paraCadaElemento(Consumer<? super T> accion) {
        for (T item : items) {
            accion.accept(item);
        }
    }

    // SERIALIZAR
    public void guardarEnArchivo(String rutaArchivo) throws IOException {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            salida.writeObject(items);
        }
    }
    
    // DESERIALIZAR PELICULAS
    public void cargarDesdeArchivo(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            this.items = (List<T>) entrada.readObject();
        }
    }


    public void guardarEnCSV(String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write(Pelicula.toCSVHeader() + "\n");
            for (T elemento : items) {
                writer.write(elemento.toCSV());
                writer.newLine();
            }
        }
    }
    

    public void cargarDesdeCSV(String rutaArchivo, Function<String, T> fromCSVFunction) throws IOException {
        items.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            reader.readLine();
            String linea;
            while ((linea = reader.readLine()) != null) {
                T item = fromCSVFunction.apply(linea);
                if (item != null) {
                    items.add(item);
                }
            }
        }
    }
    
}
