package plugin;

import com.group4.shared.Model.User;
import com.group4.shared.plugin.IUserDao;

import java.util.ArrayList;
import java.util.List;


public class SQLiteUserDao extends SQLiteBaseDao implements IUserDao {

    public SQLiteUserDao(SQLiteTransaction transaction)
    {
        super(transaction);
    }

    @Override
    public void saveUsers(List<User> users) {
        String sqlString = "INSERT INTO User " +
                "(Username, Password) values (?,?)";
        List<List<Object>> entriesList = new ArrayList<>();
        for (User user : users)
        {
            List<Object> entries = new ArrayList<>();
            entries.add(user.getUsername());
            entries.add(user.getPassword());
            entriesList.add(entries);
        }

        updateBatch(sqlString, entriesList);
    }

    @Override
    public List<User> getUsers() {
        String sqlString = "SELECT Username, Password FROM User";
        List<Object> fields = new ArrayList<>();
        List<Class> types = new ArrayList<>();
        types.add(String.class);
        types.add(Integer.class);

        List<User> users = new ArrayList<>();

        List<List<Object>> resultList = query(sqlString, fields, types);
        for (List<Object> results : resultList)
        {
            String username = (String) results.get(0);
            String password = (String) results.get(1);
            users.add(new User(username, password));
        }

        return users;
    }

    @Override
    public void clear() {
        String dropTableSQL = "DROP TABLE IF EXISTS main.User;\n";
        dropTable(dropTableSQL);
        create();
    }


    public void create() {
        String createTableSQL = "CREATE  TABLE  IF NOT EXISTS \"main\".\"User\" (\n" +
                "\tUsername TEXT PRIMARY KEY  NOT NULL UNIQUE, \n" +
                "\tPassword TEXT NOT NULL \n" +
                ");";

        createTable(createTableSQL);
    }
}
