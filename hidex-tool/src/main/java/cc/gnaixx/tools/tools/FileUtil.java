package cc.gnaixx.tools.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cc.gnaixx.tools.tools.Log.log;

/**
 * 名称: FileUtil
 * 描述:
 *
 * @author xiangqing.xue
 * @date 2016/11/22
 */

public class FileUtil {
    //
    public static void write(String path, byte[] data) {
        File file = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public static byte[] read(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int len = fis.available();
            byte entry[] = new byte[len];
            int count = fis.read(entry);
            fis.close();
            return entry;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    public static Map<String, List<String>> readConfig(String path) {
        try {
            Map<String, List<String>> config = new HashMap<>();
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#") && !line.equals("")) {
                    String conf[] = line.split(":");
                    if(conf.length != 2){
                        log("warning", "error config at :" + line);
                        System.exit(0);
                    }

                    String key = conf[0];
                    String values[] = conf[1].split(" ");
                    List<String> valueList = new ArrayList<>();
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] != null && !values[i].equals("")) {
                            valueList.add(values[i]);
                        }
                    }
                    config.put(key, valueList);
                }
            }
            fr.close();
            br.close();
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
