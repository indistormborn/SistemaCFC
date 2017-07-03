/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc;

import sistemacfc.src.control.AulasControl;
import sistemacfc.src.control.PrincipalControl;

/**
 *
 * @author Indiara
 */
public class SistemaCFC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       PrincipalControl control = new PrincipalControl();
       control.getAgenda().getTelaAgenda().setVisible(true);
    }
    
}
