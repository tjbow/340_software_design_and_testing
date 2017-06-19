package plugin;

import com.group4.shared.Model.CommandList;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;
import com.group4.shared.plugin.ICommandDao;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class SQLiteCommandDao extends SQLiteBaseDao implements ICommandDao {

    public SQLiteCommandDao(SQLiteTransaction transaction)
    {
        super(transaction);
    }

    @Override
    public void updateCommands(String gameName, List<Command> commands) {
        String sqlString = "INSERT INTO Command " +
                "(GameName, CommandList) values (?,?)";
        List<Object> entries = new ArrayList<>();
        entries.add(gameName);
//        CommandList commandList = new CommandList();
//        commandList.setCommandList(commands);
        entries.add(commands);

        update(sqlString, entries);
    }

    @Override
    public List<Command> getCommands(String gameName) {
        String sqlString = "SELECT CommandList FROM Command WHERE GameName = ?";
        List<Object> fields = new ArrayList<>();
        List<Class> types = new ArrayList<>();
        fields.add(gameName);
        types.add(InputStream.class);


        List<List<Object>> resultList = query(sqlString, fields, types);
        //CommandList commandList = new CommandList();
        List<Command> commandList = new ArrayList<>();
        if (!resultList.isEmpty() && !resultList.get(0).isEmpty() )
        {
            commandList = (ArrayList<Command>) resultList.get(0).get(0);
        }

        return commandList;
    }

    public void deleteCommandList(String gameName) {
        String sqlString = "DELETE FROM Command WHERE GameName = ?";
        List<Object> queryFields = new ArrayList<>();
        queryFields.add(gameName);

        deleteSingle(sqlString, queryFields);
    }

    @Override
    public void clear() {
        String dropTableSQL = "DROP TABLE IF EXISTS main.Command;\n";
        dropTable(dropTableSQL);
        create();
    }


    public void create() {
        String sqlString = "CREATE  TABLE  IF NOT EXISTS \"main\".\"Command\" (\n" +
                "\t\"GameName\" TEXT PRIMARY KEY  NOT NULL  UNIQUE, \n" +
                "\t\"CommandList\" BLOB NOT NULL \n" +
                ");";
        createTable(sqlString);
    }
}
