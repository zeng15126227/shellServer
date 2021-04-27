package Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class shellServiceImpl implements shellService {

    @Override
    public String exec(int type, String param,String zh) throws RemoteException, IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMdd");
        String date = df.format(new Date());

        List<String> strList = new ArrayList<String>();
        Process process;
        String ret = "";
        if (type == 0) {
            process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", String.format("sh /data1/ubd_nl/extract/submit_task.sh %s >> /data1/ubd_nl/extract/log/%s_%s.log", param,zh,date)}, null, null);
            Class<?> clazz = Class.forName("java.lang.UNIXProcess");
            Field field = clazz.getDeclaredField("pid");
            field.setAccessible(true);
            ret = String.valueOf(field.get(process));
        } else if (type == 1) {
            process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", String.format("ps -aux|grep %s|grep -v grep", param)}, null, null);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            process.waitFor();
            while ((line = input.readLine()) != null) {
                strList.add(line);
            }
            ret = strList.toString();
        } else {
            //TODO 参数异常
        }
        return ret;
    }
}
