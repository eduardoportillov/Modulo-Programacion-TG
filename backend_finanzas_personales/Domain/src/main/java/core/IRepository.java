package core;

public interface IRepository<T, TId> {
  public T FindByKey(TId key) throws Exception;

  public void Create(T obj) throws Exception;
}
