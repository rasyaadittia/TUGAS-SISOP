package id.ac.praktikum.server;
import id.ac.praktikum.rmi.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
public class MahasiswaServiceImpl extends UnicastRemoteObject implements MahasiswaService {
    private final Map<String,Mahasiswa> data = new ConcurrentHashMap<>();
    public MahasiswaServiceImpl() throws RemoteException {
        super();
        data.put("20250040101",new Mahasiswa("20250040101","Andi Saputra","Informatika","andi@mail.com"));
        data.put("20250040102",new Mahasiswa("20250040102","Rasya Adittia","Informatika","rasya@mail.com"));
        data.put("20250040103",new Mahasiswa("20250040103","Siti Aminah","Sistem Informasi","siti@mail.com"));
    }
    public List<Mahasiswa> getAll(){return new ArrayList<>(data.values());}
    public Mahasiswa findByNim(String nim){return data.get(nim);}
    public boolean add(Mahasiswa m){return m!=null && m.getNim()!=null && !m.getNim().isBlank() && data.putIfAbsent(m.getNim(),m)==null;}
    public boolean update(Mahasiswa m){if(m==null||!data.containsKey(m.getNim()))return false; data.put(m.getNim(),m);return true;}
    public boolean delete(String nim){return data.remove(nim)!=null;}
    public List<Mahasiswa> findByNama(String nama){String q=nama==null?"":nama.toLowerCase();return data.values().stream().filter(m->m.getNama().toLowerCase().contains(q)).collect(Collectors.toList());}
    public int count(){return data.size();}
}
