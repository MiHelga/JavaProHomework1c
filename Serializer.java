import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class Serializer {
    public static void serialize(Object obj, String path) {
        Class objClass = obj.getClass();

        try (FileWriter fileWriter = new FileWriter(path)) {

            Field[] fields = objClass.getDeclaredFields();

            for (Field field : fields) {

                if (field.isAnnotationPresent(Save.class)) {
                    Boolean isAccessible = field.isAccessible();
                    if (!isAccessible) field.setAccessible(true);
                    fileWriter.write(field.getType().getName() + "; " + field.getName() + "; " + field.get(obj).toString() + "\r\n");

                }
            }
            System.out.println("Serialized successfully!");

        } catch (IOException | IllegalAccessException e) {
            System.out.println("Some problems have occurred during serialization!");
            e.printStackTrace();

        }
    }

    public static void deserialize(Object obj, String path){
        Class objClass = obj.getClass();
        try (FileReader fileReader = new FileReader(path)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line!=null){
                String[] str = line.split("; ");
                Field field = objClass.getDeclaredField(str[1]);
                //check if the field is accessible or not
                boolean initAccess = field.isAccessible();
                //if not - set accessible
                if (!initAccess) field.setAccessible(true);
                switch (str[0]){
                    case "boolean":
                        field.setBoolean(obj, Boolean.valueOf(str[2]));
                        break;
                    case "byte":
                        field.setByte(obj, Byte.parseByte(str[2]));
                        break;
                    case "char":
                        field.setChar(obj, (str[2].charAt(0)));
                        break;
                    case "double":
                        field.setDouble(obj, Double.parseDouble(str[2]));
                        break;
                    case "float":
                        field.setFloat(obj, Float.parseFloat(str[2]));
                        break;
                    case "int":
                        field.setInt(obj, Integer.parseInt(str[2]));
                        break;
                    case "long":
                        field.setLong(obj, Long.parseLong(str[2]));
                        break;
                    case "short":
                        field.setShort(obj, Short.parseShort(str[2]));
                        break;
                    default:
                        field.set(obj, str[2]);
                        break;
                }
                //return initial access status
                field.setAccessible(initAccess);
                line = bufferedReader.readLine();
            }
            System.out.println("Deserialized successfully!");
            bufferedReader.close();
        }
        catch(IOException|NoSuchFieldException|IllegalAccessException e){
            System.out.println("Some problems have occurred during deserialization!");
            e.printStackTrace();
        }
    }
}
