package com.boristax;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame {
    JFrame thisFrame;

    public static void main(String[] args) throws AWTException {
        Timer timer=new Timer(1000,(a)->{
            
        });
        timer.setRepeats(true);
        timer.start();
    }
    private static final long serialVersionUID = 1L;
    private int x;
    private Timer timerMove;
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    public App() {
        super("");
        thisFrame=this;
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        this.setOpacity(0.8f);
        this.setAlwaysOnTop(true);
        MyPanel contents = new MyPanel(this);
        setContentPane(contents);
        setSize(300, 200);
        setLocation((int)screen.getWidth(),(int)screen.getHeight()/2);
        setResizable(false);
       alert();
    }
    public void alert(){
        setVisible(true);
        x=getLocation().x;
        timerMove=new Timer(1,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(x>=screen.getWidth()/2){ 
                thisFrame.setLocation(x, thisFrame.getLocation().y);
                 x-=1;
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
    MyPanel(JFrame parent){
        super();
        setLayout(new BorderLayout());
        JButton button1 = new JButton("ОК");
        button1.addActionListener((e)-> {
                parent.dispose();
        });
        JLabel label=new JLabel("ALARMA!!!");
        label.setFont(new Font(null, Font.BOLD, 20));
        label.setForeground(Color.RED);
        //label.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        label.setHorizontalAlignment(JLabel.CENTER);;
        add(label,BorderLayout.CENTER);
        //add(new JLabel("EAST"),BorderLayout.WEST);
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
