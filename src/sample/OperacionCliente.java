package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OperacionCliente {
    Connection connection;

    String exception = null;
    String state = null;
    int error = 0;


    public OperacionCliente(Connection conn){
        this.connection = conn;
    }

    public boolean prbCliente(int id){
        String query = "SELECT clienteId "
                +"FROM cliente "
                +"WHERE clienteId = " + id;
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
               return true;
            }else{
                return false;
            }

    }catch (java.sql.SQLException e){
           return false;
        }
    }
    public boolean prbClienteNom(String nom){
        String query = "SELECT nombre "
                +"FROM cliente "
                +"WHERE nombre = " + "'"+nom+"'";
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                return true;
            }else{
                return false;
            }

        }catch (java.sql.SQLException e){
            return false;
        }
    }
    public boolean prbClienteApe(String nom){
        String query = "SELECT apellidos "
                +"FROM cliente "
                +"WHERE apellidos = " + "'"+nom+"'";
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                return true;
            }else{
                return false;
            }

        }catch (java.sql.SQLException e){
            return false;
        }
    }

    //CONSULT
    public Cliente consultCliente(int id){
        int clienteId=0;
        String nombre = "", apellidos = "", direccion = "";

        String query = "SELECT clienteId, nombre, apellidos, direccion "
                +"FROM cliente "
                +"WHERE clienteId = " + id;
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()){
                clienteId = rs.getInt("clienteId");
                nombre = rs.getString("nombre");
                apellidos =rs.getString("apellidos");
                direccion = rs.getString("direccion");
                return new Cliente(clienteId, nombre, apellidos, direccion);
            }else{
                System.out.println("Cliente no encontrado");
                return null;
            }
            //System.out.println(clienteId + ", " + nombre + ", "+apellidos+", "+direccion);


        }catch (java.sql.SQLException e){
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: "+ e.getSQLState());
            System.out.println("VendorError: "+ e.getErrorCode());

            return null;
        }
    }
    public ArrayList<Cliente> consultClienteApe(String apellido){
        int clienteId=0;
        String nombre = "", apellidos = "", direccion = "";
        ArrayList<Cliente> a=new ArrayList<Cliente>();

        String query = "SELECT clienteId, nombre, apellidos, direccion "
                +"FROM cliente "
                +"WHERE apellidos = " + "'"+apellido+"'";
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                clienteId = rs.getInt("clienteId");
                nombre = rs.getString("nombre");
                apellidos =rs.getString("apellidos");
                direccion = rs.getString("direccion");
                Cliente q =new Cliente(clienteId, nombre, apellidos, direccion);
                a.add(q);
            }
            //System.out.println(clienteId + ", " + nombre + ", "+apellidos+", "+direccion);
            return a;
        }catch (java.sql.SQLException e){
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: "+ e.getSQLState());
            System.out.println("VendorError: "+ e.getErrorCode());

            return null;
        }

    }
    public ArrayList<Cliente> consultClienteNom(String nom){
        int clienteId=0;
        String nombre = "", apellidos = "", direccion = "";
        ArrayList<Cliente> a=new ArrayList<Cliente>();

        String query = "SELECT clienteId, nombre, apellidos, direccion "
                +"FROM cliente "
                +"WHERE nombre = " + "'"+nom+"'";
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                clienteId = rs.getInt("clienteId");
                nombre = rs.getString("nombre");
                apellidos =rs.getString("apellidos");
                direccion = rs.getString("direccion");
                Cliente q =new Cliente(clienteId, nombre, apellidos, direccion);
                a.add(q);
            }
            //System.out.println(clienteId + ", " + nombre + ", "+apellidos+", "+direccion);
            return a;
        }catch (java.sql.SQLException e){
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: "+ e.getSQLState());
            System.out.println("VendorError: "+ e.getErrorCode());

            return null;
        }

    }

    //INSERT
    public Cliente insertCliente(int clienteId, String nombre, String apellidos, String direccion){

        boolean prueba = prbCliente(clienteId);
        int numRegs = 0;
        if (prueba==false){
            String query = "insert into cliente(clienteId, nombre, apellidos, direccion) " +
                    "values ('" + clienteId + "', '" + nombre + "', '" + apellidos + "', '" + direccion + "')";

            try {
                Statement stmt = connection.createStatement();
                numRegs = stmt.executeUpdate(query);

                System.out.println("Cantidad de registros afectados: " + numRegs);
                return new Cliente(clienteId, nombre, apellidos, direccion);
            }
            catch (java.sql.SQLException ex){
                ex.printStackTrace();
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return null;
            }

            //return numRegs;

        }else{
            System.out.println("El id del nuevo cliente ya esta en uso");
            return null;
        }


    }

    //DELETE
    public int deleteCliente(int id){

        int numRegs = 0;
        exception = null;
        state = null;
        error = 0;

        String query = "delete from cliente where clienteId = " + id;

        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
            exception =ex.getMessage();
            state = ex.getSQLState();
            error = ex.getErrorCode();
        }


        return numRegs;
    }
    public int deleteClienteNom(String nom){

        int numRegs = 0;
        exception = null;
        state = null;
        error = 0;

        String query = "delete from cliente where nombre = " + "'"+nom+"'";

        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
            exception = ex.getMessage();
            state = ex.getSQLState();
            error = ex.getErrorCode();
        }


        return numRegs;
    }
    public int deleteClienteApe(String apellido){

        int numRegs = 0;
        exception = null;
        state = null;
        error = 0;

        String query = "delete from cliente where apellidos = " + "'"+apellido+"'";

        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
            exception =ex.getMessage();
            state = ex.getSQLState();
            error = ex.getErrorCode();
        }


        return numRegs;
    }

    //UPDATE
    public Cliente updateCliente(int clienteId, String nombre, String apellidos, String direccion){

        int numRegs = 0;


            String query = "UPDATE cliente SET nombre = '" + nombre + "', apellidos = '" + apellidos + "', direccion =' " +
                    direccion + "' WHERE clienteId = " + clienteId ;

            try {
                Statement stmt = connection.createStatement();
                numRegs = stmt.executeUpdate(query);
                System.out.println("Cantidad de registros afectados: " + numRegs);
                return new Cliente(clienteId, nombre, apellidos, direccion);
            }
            catch (java.sql.SQLException ex){
                ex.printStackTrace();
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return null;
            }

    }


    public String getException(){
        return exception;
    }
    public String getState(){
        return state;
    }
    public int getError(){
        return error;
    }

}
