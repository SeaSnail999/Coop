package common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class Saver {
    static String pathToSaver = "/home/hs/Documents/cmd.sh";

    public static void main(String[] args) throws IOException {
        //System.out.println(Runtime.getRuntime().exec("touch /home/hs/Documents/a"));
        //String cmd = "/home/hs/Downloads/automate-save-page-as-master/save_page_as \"https://www.okeydostavka.ru/msk/khleb-khlebnyi-dom-tostovyi-v-narezke-500g\" --browser \"firefox\" --destination \"/home/hs/Documents/\"";
        //String cmd = "ls";
        //System.out.println(cmd);
        //Process process = Runtime.getRuntime().exec(cmd);
        //pb("/home/hs/Documents/cmd.sh", "\"https://www.okeydostavka.ru/msk/khleb-khlebnyi-dom-tostovyi-v-narezke-500g\"");
        //saveByLink("https://www.okeydostavka.ru/msk/khleb-khlebnyi-dom-tostovyi-v-narezke-500g");
        saveByLink("https://www.auchan.ru/product/kdyayco_kurinoe_s0_30_sht/");
    }

    static void saveByLink(String link) throws IOException {
        pb(pathToSaver, '"' + link + '"');
    }

    static void pb(String cmd, String... args) throws IOException {
        //ProcessBuilder pb = new ProcessBuilder(s, adr, browser, dest);
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pbVars(pb, args[0]);
        //pb.directory(new File("myDir"));
        Process p = pb.start();
        printProcess(p);
    }

    static void pbVars(ProcessBuilder pb, String... args) {
        Map<String, String> env = pb.environment();
        env.put("adr", args[0]);
    }

    static void printProcess(Process process) throws IOException {
        InputStream stream1 = process.getInputStream();
        String s = new String(stream1.readAllBytes());
        stream1.close();
        System.out.println(s);
    }
}
