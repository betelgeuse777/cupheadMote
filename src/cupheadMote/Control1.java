
package cupheadMote;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

/**
 *
 * @author tajasoft
 */
public class Control1 implements WiimoteListener
{
    private Wiimote wiimote;
    private int mode;
    private Robot robot;
    
    private int up;
    private int down;
    private int left;
    private int right;
    
    private int jump;
    private int dash;
    private int shoot;
    private int exshoot;
    private int switchWeapon;
    private int lock;
    
    public Control1(Wiimote w) throws AWTException
    {
        wiimote=w;
        robot=new Robot();
       
        up=KeyEvent.VK_UP;
        down=KeyEvent.VK_DOWN;
        left=KeyEvent.VK_LEFT;
        right=KeyEvent.VK_RIGHT;
    
        jump=KeyEvent.VK_Z;
        dash=KeyEvent.VK_SHIFT;
        shoot=KeyEvent.VK_X;
        exshoot=KeyEvent.VK_V;
        switchWeapon=KeyEvent.VK_TAB;
        lock=KeyEvent.VK_C;
        
        wiimote.activateMotionSensing();       
        setleds();
    }

    @Override
    public void onButtonsEvent(WiimoteButtonsEvent wbe) 
    {
        
            if (wbe.isButtonUpPressed()) {robot.keyPress(left);}
            if (wbe.isButtonUpJustReleased()) {robot.keyRelease(left);}
            if (wbe.isButtonDownPressed()) {robot.keyPress(right);}
            if (wbe.isButtonDownJustReleased()) {robot.keyRelease(right);}
            if (wbe.isButtonLeftPressed()) {robot.keyPress(down);}
            if (wbe.isButtonLeftJustReleased()) {robot.keyRelease(down);}
            if (wbe.isButtonRightPressed()) {robot.keyPress(up);}
            if (wbe.isButtonRightJustReleased()) {robot.keyRelease(up);}
            
            if (wbe.isButtonOnePressed()) {robot.keyPress(shoot);}
            if (wbe.isButtonOneJustReleased()) {robot.keyRelease(shoot);}
            if (wbe.isButtonTwoPressed()) {robot.keyPress(jump);}
            if (wbe.isButtonTwoJustReleased()) {robot.keyRelease(jump);}
            
            if (wbe.isButtonAPressed()) {robot.keyPress(switchWeapon);}
            if (wbe.isButtonAJustReleased()) {robot.keyRelease(switchWeapon);}
            if (wbe.isButtonBPressed()) {robot.keyPress(lock);mode=1;}
            if (wbe.isButtonBJustReleased()) {robot.keyRelease(lock);mode=0;}
            
            if (wbe.isButtonPlusPressed()) {}
            if (wbe.isButtonPlusJustReleased()) {robot.keyPress(KeyEvent.VK_ENTER);robot.keyRelease(KeyEvent.VK_ENTER);}
            if (wbe.isButtonMinusPressed()) {}
            if (wbe.isButtonMinusJustReleased()) {robot.keyPress(KeyEvent.VK_ESCAPE);robot.keyRelease(KeyEvent.VK_ESCAPE);}
                     
            if (wbe.isButtonHomeJustReleased()) {wiimote.setLeds(false, false, false, false);System.exit(0);}
        
    }

    @Override
    public void onIrEvent(IREvent ire) {
        
    }

    @Override
    public void onMotionSensingEvent(MotionSensingEvent mse) 
    {
        if (mode==0&&mse.getGforce().getZ()<-1.5) 
        {
            robot.keyPress(dash);
            wiimote.deactivateMotionSensing();  
            try {TimeUnit.MILLISECONDS.sleep(20);}catch (InterruptedException ex) 
            {Logger.getLogger(CupHead.class.getName()).log(Level.SEVERE, null, ex);}
            robot.keyRelease(dash);
            ledEffects(1);
            wiimote.activateMotionSensing();
            
             wiimote.activateRumble();
            try {TimeUnit.MILLISECONDS.sleep(40);}catch (InterruptedException ex) 
             {Logger.getLogger(CupHead.class.getName()).log(Level.SEVERE, null, ex);}
            wiimote.deactivateRumble();  
        }        
        
        if (mode==1&&mse.getGforce().getZ()<-1.5) 
        {
            robot.keyPress(exshoot);
            wiimote.deactivateMotionSensing();  
            try {TimeUnit.MILLISECONDS.sleep(20);}catch (InterruptedException ex) 
            {Logger.getLogger(CupHead.class.getName()).log(Level.SEVERE, null, ex);}
            robot.keyRelease(exshoot);
            ledEffects(2);
            wiimote.activateMotionSensing();
            
             wiimote.activateRumble();
            try {TimeUnit.MILLISECONDS.sleep(40);}catch (InterruptedException ex) 
             {Logger.getLogger(CupHead.class.getName()).log(Level.SEVERE, null, ex);}
            wiimote.deactivateRumble();  
        }
        
        
    }

    @Override
    public void onExpansionEvent(ExpansionEvent ee) {
    }

    @Override
    public void onStatusEvent(StatusEvent se) {
    }

    @Override
    public void onDisconnectionEvent(DisconnectionEvent de) {
    }

    @Override
    public void onNunchukInsertedEvent(NunchukInsertedEvent nie) {
    }

    @Override
    public void onNunchukRemovedEvent(NunchukRemovedEvent nre) {
    }

    @Override
    public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent ghie) {
    }

    @Override
    public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent ghre) {
    }

    @Override
    public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent ccie) {
    }

    @Override
    public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent ccre) {
    }
    
    public void setleds()
    {
       wiimote.setLeds(true, false, false, false);
            
    }
    
    public void ledEffects(int x)
    {
       if(x==1){
           try {
               wiimote.setLeds(true, false, false, false);
               Thread.sleep(5);
               wiimote.setLeds(false, true, false, false);
               Thread.sleep(5);
               wiimote.setLeds(false, false, true, false);
               Thread.sleep(5);
               wiimote.setLeds(false, false, false, true);
               Thread.sleep(5);
               wiimote.setLeds(true, false, false, false);
           } catch (InterruptedException ex) {
               Logger.getLogger(Control1.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       if(x==2){
           try {
               wiimote.setLeds(true, false, false, false);
               Thread.sleep(5);
               wiimote.setLeds(true, true, false, false);
               Thread.sleep(5);
               wiimote.setLeds(true, true, true, false);
               Thread.sleep(5);
               wiimote.setLeds(true, true, true, true);
               Thread.sleep(5);
               wiimote.setLeds(true, true, true, false);
               Thread.sleep(5);
               wiimote.setLeds(true, true, false, false);
               Thread.sleep(5);
               wiimote.setLeds(true, false, false, false);
           } catch (InterruptedException ex) {
               Logger.getLogger(Control1.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
    }
}
