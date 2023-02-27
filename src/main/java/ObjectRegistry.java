import java.util.HashMap;

public class ObjectRegistry {
    private static HashMap<String, Object> objects = new HashMap<String, Object>();
    public static void put(String name, Object obj)
    {
        objects.put(name,obj);
    }
    public static Object get(String name)
    {
        return objects.get(name);
    }
}
