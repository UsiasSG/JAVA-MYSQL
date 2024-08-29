/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author paiz2
 */
public class Cliente extends Persona{
    private String nit;
    private int id;
    Conexion cn;
    private String getDireccion;

    public Cliente() {}

    public Cliente(int id,String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    
    public DefaultTableModel leer (){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "Select id_cliente as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento from clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[]= new String[7];
            
            while(consulta.next()){
                datos [0] = consulta.getString("id");
                datos [1] = consulta.getString("nit");
                datos [2] = consulta.getString("nombres");
                datos [3] = consulta.getString("apellidos");
                datos [4] = consulta.getString("direccion");
                datos [5] = consulta.getString("telefono");
                datos [6] = consulta.getString("fecha_nacimiento");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
           
            
            
        }catch(SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error: " + ex.getMessage());
          
        }  
        
        return tabla;
          
    }
        
    
    
    
       public void agregar (){
           try{
               PreparedStatement parametro;
               String query = "INSERT INTO clientes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES(?,?,?,?,?,?);";
               cn = new Conexion();
               cn.abrir_conexion();
               
               parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
               parametro.setString(1, this.getNit());
               parametro.setString(2, this.getNombres());
               parametro.setString(3, this.getApellidos());
               parametro.setString(4, this.getDireccion());
               parametro.setString(5, this.getTelefono());
               parametro.setString(6, this.getFecha_Nacimiento());
               
               int executar = parametro.executeUpdate();
               cn.cerrar_conexion();
               JOptionPane.showMessageDialog(null,Integer.toString(executar) + "Registro Ingresado", "Agregado",JOptionPane.INFORMATION_MESSAGE);

               
               
       }catch(HeadlessException | SQLException ex){
           System.out.println("Error...." + ex.getMessage());
       }
           
       }
    
    
    /**
     *
     * @return  */
/*   @Override
       protected String[] crear(){
           try
            {         
                    String datos[]= new String[6];      
                      datos[0] = getNit();
                      datos[1] = getNombres();
                      datos[2] = getApellidos();
                      datos[3] = getDireccion();
                      datos[4] = getTelefono();
                      datos[5] = getFecha_Nacimiento();
              return  datos;            
                 }
                 
        catch(Exception ex)
            {
                  
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Error en Query",JOptionPane.ERROR_MESSAGE);
                    return  null;
            }
           
           
   }*/
       @Override
       public void actualizar(){
            try{
               PreparedStatement parametro;
               String query = "update clientes set nit = ?,nombres = ?,apellidos = ?,direccion = ?,telefono = ?,fecha_nacimiento = ?" +
                     "where id_cliente =?";
               cn = new Conexion();
               cn.abrir_conexion();
               
               parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
               parametro.setString(1, this.getNit());
               parametro.setString(2, this.getNombres());
               parametro.setString(3, this.getApellidos());
               parametro.setString(4, this.getDireccion());
               parametro.setString(5, this.getTelefono());
               parametro.setString(6, this.getFecha_Nacimiento());
               parametro.setInt(7, this.getId());
               
               
               int executar = parametro.executeUpdate();
               cn.cerrar_conexion();
               JOptionPane.showMessageDialog(null,Integer.toString(executar) + "Registro Actualizado", "Agregado",JOptionPane.INFORMATION_MESSAGE);

               
               
       }catch(HeadlessException | SQLException ex){
           System.out.println("Error...." + ex.getMessage());
       }
           
       }
       
       @Override
       public void borrar(){
           try{
               PreparedStatement parametro;
               String query = "delete from clientes where id_cliente =?";
                     
               cn = new Conexion();
               cn.abrir_conexion();
               
               parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
               parametro.setInt(1, this.getId());
               
               
               int executar = parametro.executeUpdate();
               cn.cerrar_conexion();
               JOptionPane.showMessageDialog(null,Integer.toString(executar) + "Registro Eliminado", "Agregado",JOptionPane.INFORMATION_MESSAGE);

               
               
       }catch(HeadlessException | SQLException ex){
           System.out.println("Error...." + ex.getMessage());
       }
       
       }

    

}
