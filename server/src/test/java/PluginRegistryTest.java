import com.group4.server.Plugin.PluginRegistry;

import org.junit.Test;

import java.io.File;


public class PluginRegistryTest {
    @Test
    public void testLoadPlugin(){
        PluginRegistry pluginRegistry = new PluginRegistry();
        pluginRegistry.scanDirectory(new File("./plugins"));
    }
}
