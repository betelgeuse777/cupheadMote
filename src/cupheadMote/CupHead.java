
package cupheadMote;

import java.awt.AWTException;
import java.awt.Component;
import javax.swing.JOptionPane;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

/**
 *
 * @author tajasoft
 */
public class CupHead 
{

    public static void main(String[] args) throws AWTException 
    {
        Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true, WiiUseApiManager.WIIUSE_STACK_MS);        
        if (wiimotes.length==0) {Component parentComponent = null;JOptionPane.showMessageDialog(parentComponent, "No Wiimote detected");System.exit(0);}        
        else{Wiimote wiimote1 = wiimotes[0];wiimote1.addWiiMoteEventListeners(new Control1(wiimote1));}          
    }
    
    
}
