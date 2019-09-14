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
           String text="";
           boolean show=false;
           if((hours==10||hours==12||hours==14||hours==16)&&(minutes==50&&seconds==0)){ show=true;text="ALARMA";}
           if((hours==8||hours==12||hours==13||hours==17)&&(minutes==55&&seconds==0)){show=true; text="WORK TIME";}
           //if(seconds%10==0){show=true;text="HELLO";}
           if(!show) return;
           if(alert!=null) alert.dispose();
            alert=new Alert(text);
        },1000);
    }
}

class Alert extends JFrame {
    JFrame thisFrame;
    private static final long serialVersionUID = 2L;
    private Timer timerMove;
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private String title;
    public Alert(String title) {
        super("");
        this.title=title;
        thisFrame=this;
        setUndecorated(true);
        setOpacity(0.8f);
        setAlwaysOnTop(true);
        MyPanel contents = new MyPanel(this,this.title);
        setContentPane(contents);
        setSize(300, 200);
        setLocation((int)screen.getWidth(),(int)screen.getHeight()-getHeight()-50);
        setResizable(false);
        alert();
    }
    public void alert(){
        setVisible(true);
        timerMove=new Timer(20,new ActionListener(){
            private double a=thisFrame.getWidth()+50;
            private final double A=thisFrame.getWidth()+100;
            private double a1=A;
            private double c=0;
            private double t=0;
            private double k=1;
            private double dt=0.1;
            private double x0=0;
            private boolean flag=false;
            @Override
            public void actionPerformed(ActionEvent e) {
                t=t+dt;
                double x=a*Math.sin(t)+c;
                if(x<x0) flag=true;
                if(flag){k=k*0.8;c=A*0.8;a1=A*0.2;dt=dt+0.1;}
                if(a<1){timerMove.stop();}
                x0=x;
                a=a1*k;
                thisFrame.setLocation((int)(screen.getWidth()-x), thisFrame.getLocation().y);
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
