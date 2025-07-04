/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 * @author Agust
 * @param <T>
 */
public interface Almacenable<T> extends Iterable<T> {

    public void agregar(T item);

    public T obtenerPorIndice(int indice);

    public boolean eliminar(T item);

    public void eliminarPorIndice(int index);

    public List<T> filtrar(Predicate<? super T> criterio);

    public void paraCadaElemento(Consumer<? super T> accion);

}