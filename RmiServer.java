package id.ac.praktikum.server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class RmiServer {
    public static void main(String[] args) {
        try {
            String host=System.getProperty("java.rmi.server.hostname","127.0.0.1");
            System.setProperty("java.rmi.server.hostname",host);
            Registry registry;
            try { registry=LocateRegistry.createRegistry(1099); }
            catch(Exception e){ registry=LocateRegistry.getRegistry(1099); }
            registry.rebind("MahasiswaService",new MahasiswaServiceImpl());
            System.out.println("RMI Server aktif di "+host+":1099");
            System.out.println("Service terdaftar: MahasiswaService");
        } catch(Exception e){System.err.println("Gagal menjalankan RMI Server: "+e.getMessage());e.printStackTrace();}
    }
}
