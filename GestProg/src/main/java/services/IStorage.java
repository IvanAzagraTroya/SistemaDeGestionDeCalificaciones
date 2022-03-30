package services;

public interface IStorage<T> {
    boolean save(T lista);

    T load();

    String getStoragePath();
}