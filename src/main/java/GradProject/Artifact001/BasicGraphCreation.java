package GradProject.Artifact001;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface extends JPanel {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue);

        Dimension size = getSize();
        Insets insets = getInsets();

        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        Random r = new Random();

        for (int i = 0; i < 1000; i++) {

            int x = Math.abs(r.nextInt()) % w;
            int y = Math.abs(r.nextInt()) % h;
            g2d.drawLine(x, y, x, y);
        }
    }
    

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class BasicGraphCreation extends JFrame {

    public BasicGraphCreation() {

        initUI();
    }

    private void initUI() {
        
        setTitle("Points");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //DataNode dn0 = new DataNode(0);
		DataNode dn1 = new DataNode(1);
		DataNode dn2 = new DataNode(2);
		DataNode dn3 = new DataNode(3);
		DataNode dn4 = new DataNode(4);
		DataNode dn5 = new DataNode(5);
		
		dn2.setColor(new Color(255, 0, 0));
		dn2.addConnection(1);
		dn2.addConnection(3);
		dn2.addConnection(4);
		dn2.setOrder(0);
		dn2.setResolution(0);
		
		dn4.setColor(new Color(255, 51, 51));
		dn4.addConnection(2);
		dn4.addConnection(3);
		dn4.addConnection(5);
		dn4.setOrder(1);
		dn4.setResolution(3.610);
		
		dn3.setColor(new Color(255, 102, 102));
		dn3.addConnection(2);
		dn3.addConnection(4);
		dn3.addConnection(5);
		dn3.setOrder(2);
		dn3.setResolution(1.492);
		
		dn5.setColor(new Color(255, 153, 153));
		dn5.addConnection(1);
		dn5.addConnection(3);
		dn5.addConnection(4);
		dn5.setOrder(3);
		dn5.setResolution(1.518);
		
		dn1.setColor(new Color(255, 204, 204));
		dn1.addConnection(2);
		dn1.addConnection(5);
		dn1.setOrder(4);
		dn1.setResolution(2.079);
		
		
		ArrayList<Node> nodelist = new ArrayList<Node>();
		//nodelist.add(dn0);
		nodelist.add(dn1);
		nodelist.add(dn2);
		nodelist.add(dn3);
		nodelist.add(dn4);
		nodelist.add(dn5);
		
		
		Dendogram ng = new Dendogram(nodelist);
        //add(new Surface());
		add(ng);

        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                BasicGraphCreation ps = new BasicGraphCreation();
                ps.setVisible(true);
            }
        });
    }
}
