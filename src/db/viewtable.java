/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Soe min hein
 */
public class viewtable {
                
    
    public static void createview(){
        String sql="CREATE view a as select member.id,member.name,membership.membership_name,member.nrc,invoice.end_date FROM invoice , member,membership WHERE invoice.member_id=member.id AND invoice.membership_id=membership.membership_id AND invoice.id in ( SELECT MAX(id) from invoice group by (member_id) ) ORDER BY member_id ASC";
    
           try(Connection con=ConnectDB.CreateConnection();
                   PreparedStatement ps=con.prepareStatement(sql);
                   ) {
               ps.execute();
               System.out.println("view table create");
            
        } catch (Exception e) {

e.printStackTrace();

        }
    
    }
    
}
