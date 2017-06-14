import com.group4.server.Plugin.PluginRegistry;
import com.group4.shared.plugin.IPersistencePlugin;
import com.group4.shared.plugin.InvalidPluginException;

import org.junit.Test;

import java.io.File;


public class PluginRegistryTest {
    @Test
    public void testLoadPlugin(){
        PluginRegistry.scanDirectory(new File("./plugins"));
        try {
            PluginRegistry.loadPlugin("TestJar");
        } catch (InvalidPluginException e) {
            e.printStackTrace();
        }

        IPersistencePlugin persistencePlugin = PluginRegistry.getLoadedPlugin();
    }
}
