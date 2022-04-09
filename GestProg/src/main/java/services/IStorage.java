package services;

public interface IStorage<T> {
    /**
     * Guarda los elementos almacenados en item
     * @param item
     * @return trye si se ha almacenado correctamente
     */
    boolean save(T item);

    /**
     * Carga lo almacenado y lo devuelve en un item T
     * @return Elementos almacenados
     */
    T load();

    /**
     * Devuelve el path de ubicación de almacenamiento
     * @return Path de ubicación del almacenamiento
     */
    String getStoragePath();
}