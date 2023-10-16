package Repository;

import java.util.List;
import java.util.UUID;

import Context.IWriteDbContext;
import Entities.User;
import Fourteam.db.DbSet;
import Fourteam.db.IDbSet.BooleanFunction;
import Repositories.IUserRepository;

public class UserRepository implements IUserRepository {

    DbSet<User> dbSet;

    public UserRepository(IWriteDbContext database) {
        dbSet = database.user;
    }

    public BooleanFunction<User> equalKey(UUID key) {
        return obj -> obj.key.equals(key);
    }

    public BooleanFunction<User> equalEmail(String email) {
        return obj -> obj.email.equals(email);
      }

    @Override
    public User FindByKey(UUID key) throws Exception {
        return dbSet.Single(equalKey(key));
    }

    @Override
    public void Create(User obj) throws Exception {
        dbSet.Add(obj);
    }

    @Override
    public List<User> GetAll() throws Exception {
        return dbSet.All();
    }

    @Override
    public User Delete(User user) throws Exception {
        dbSet.Delete(equalKey(user.key));
        return user;
    }

    @Override
    public User Update(User user) throws Exception {
        dbSet.Update(user, equalKey(user.key));
        return user;
    }

    @Override
    public User FindByEmail(String email) throws Exception {
        return dbSet.Single(equalEmail(email));
    }

}
