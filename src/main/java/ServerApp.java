import Service.shellService;
import Service.shellServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerApp {
    public static void main(String[] args) {
        System.out.println("创建shellService...");
        // 实例化一个yarnService
        shellService shellService = new shellServiceImpl();
        // 将此服务转换为远程服务接口:
        shellService skeleton = null;
        try {
            skeleton = (shellService) UnicastRemoteObject.exportObject(shellService, 0);
            // 将RMI服务注册到9099端口:
            Registry registry = LocateRegistry.createRegistry(9099);
            // 注册此服务，服务名为"WorldClock":
            registry.rebind("shellService", skeleton);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("创建shellService完成");
    }
}
