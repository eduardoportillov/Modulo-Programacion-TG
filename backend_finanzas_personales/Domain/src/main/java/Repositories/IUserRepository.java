package Repositories;

import java.util.List;
import java.util.UUID;

import Entities.User;
import core.IRepository;

public interface IUserRepository extends IRepository<User, UUID> {
  
  public User Delete(User user) throws Exception;
  
  public User Update(User user) throws Exception;
  
  public User FindByEmail(String email) throws Exception;
  
  public User FindByKey(UUID key) throws Exception;
  
  public List<User> GetAll() throws Exception;
}
