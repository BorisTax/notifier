package com.boristax;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;
import javax.swing.*;
import java.time.LocalDateTime;

public class App extends JFrame {
    private static final long serialVersionUID = 1L;
    private static JFrame alert;
    
    public static void main(String[] args) throws AWTException {
        new MyTimer((t)->{
           LocalDateTime ldt=LocalDateTime.now();
           int hours=ldt.getHour();
           int minutes=ldt.getMinute();
           int seconds=ldt.getSecond();
           String title="";
           boolean show=false;
           if((hours==10||hours==12||hours==14||hours==16)&&(minutes==50&&seconds==0)){ show=true;title="ALARMA";}
           if((hours==8||hours==12||hours==17)&&(minutes==55&&seconds==0)){show=true; title="WORK TIME";}
           //if(seconds%15==0){show=true;title="HELLO";}
           if(!show) return;
           if(alert!=null) alert.dispose();
            alert=new Alert(title);
        },1000);
    }
}

class Alert extends JFrame {
    JFrame thisFrame;
    private static final long serialVersionUID = 2L;
    private int x;
    private Timer timerMove;
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private String title;
    public Alert(String title) {
        super("");
        this.title=title;
        thisFrame=this;
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.8f);
        setAlwaysOnTop(true);
        MyPanel contents = new MyPanel(this,title);
        setContentPane(contents);
        setSize(300, 200);
        setLocation((int)screen.getWidth(),(int)screen.getHeight()-getHeight()-50);
        setResizable(false);
        alert();
    }
    public void alert(){
        setVisible(true);
        x=getLocation().x;
        final int width=(int)screen.getWidth();
        timerMove=new Timer(1,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(x>=width-thisFrame.getWidth()-50){ 
                thisFrame.setLocation(x, thisFrame.getLocation().y);
                 x-=50;
                 }else{timerMove.stop();}
        }});
        timerMove.setRepeats(true);
        setVisible(true);
        timerMove.start();
    }
}

class MyPanel extends JPanel{
    private static final long serialVersionUID = 2L;
    private int dragX0;
    private int dragY0;
    private boolean drag=false;
    MyPanel(JFrame parent, String title){
        super();
        setLayout(new BorderLayout());
        setBackground(Color.CYAN);
        JButton button1 = new JButton("ОК");
        button1.addActionListener((e)-> {
                parent.dispose();
        });
        JLabel label=new JLabel(title);
        label.setFont(new Font(null, Font.BOLD, 30));
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(JLabel.CENTER);;
        add(label,BorderLayout.CENTER);
        add(button1,BorderLayout.SOUTH);
        addMouseListener(new MouseListener(){
            @Override
            public void mouseReleased(MouseEvent e) {
                drag=false;
            }
            @Override
            public void mousePressed(MouseEvent e) {
                dragX0=e.getX();
                dragY0=e.getY();
                drag=true;
            }
            @Override
            public void mouseExited(MouseEvent e) {
                drag=false;
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
        addMouseMotionListener(new MouseMotionListener(){
            public void mouseDragged(MouseEvent e) {
                if(drag){
                parent.setLocation(parent.getLocation().x+e.getX()-dragX0,parent.getLocation().y+e.getY()-dragY0);
            }
            }
            public void mouseMoved(MouseEvent e) {
                
            }
        });
    }
}
class MyTimer extends java.util.TimerTask {
    private Consumer func;
    public MyTimer(Consumer func,int interval) {
        this.func=func;
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(this, new java.util.Date(), interval);
    }

     public void run(){
       func.accept(null);
 
     }
 }
