package Service;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface shellService extends Remote {
    /**
     *
     * @param type 0:提交任务 1：查询进程
     * @param param
     * @return
     * @throws RemoteException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InterruptedException
     */
    String exec(int type,String param,String zh) throws RemoteException, IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InterruptedException;
}
