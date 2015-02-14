package pl.waw.mizinski.med.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pl.waw.mizinski.med.model.Cluster;
import pl.waw.mizinski.med.model.Point;

public class DrawUtil {
	
	private List<Cluster> clusters;
	
	private double minX = Integer.MAX_VALUE;
	private double minY = Integer.MAX_VALUE;
	private double maxX = Integer.MIN_VALUE;
	private double maxY = Integer.MIN_VALUE;
	
	public DrawUtil(List<Cluster> clusters){
		this.clusters = clusters;
		for(Cluster cluster : clusters) {
			for(Point point : cluster.getPoints()) {
				double x = point.getCoordinate(0);
				double y = point.getCoordinate(1);
				minX = x < minX ? x : minX;
				minY = y < minY ? y : minY;
				maxX = x > maxX ? x : maxX;
				maxY = y > maxY ? y : maxY;
			}
		}
	}
	
	public void drawClusters(final int size) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new DrawFrame(size);
			}
		});
	}
	
	private class DrawPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		private static final int PANEL_DIMENSION = 768;
		
		private final Color[] COLORS = {
				Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.BLACK, Color.ORANGE
		};
		
		private int size = 1;
		
		public DrawPanel(int size) {
			super();
			this.size = size;
		}
		
		@Override
		public void paintComponent(Graphics g) {
			setBackground(Color.WHITE);
			super.paintComponents(g);
			setBounds(5, 5, PANEL_DIMENSION, PANEL_DIMENSION);
			int i = 0;
			for (Cluster cluster : clusters) {
				g.setColor(COLORS[i++ % COLORS.length]);
				for (Point point : cluster.getPoints()) {
					double x= point.getCoordinate(0);
					double y= point.getCoordinate(1);
					x = (x-minX)/(maxX-minX)*(PANEL_DIMENSION-size-1);
					y = (y-minY)/(maxY-minY)*(PANEL_DIMENSION-size-1);

					g.drawRect((int)x, (int)y, size, size);
				}
			}
		}
	}

	private class DrawFrame extends JFrame {
		
		private static final long serialVersionUID = 1L;
		
		private final DrawPanel drawPanel;
		
		public DrawFrame(int size) {
			super();
			setSize(DrawPanel.PANEL_DIMENSION+20, DrawPanel.PANEL_DIMENSION+40);
			drawPanel = new DrawPanel(size);
			add(BorderLayout.CENTER, drawPanel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setVisible(true);
		}
	}

	public static void main(String args[]) {
		List<Cluster> clusters = new LinkedList<Cluster>();
		clusters.add(new Point(1,2));
		clusters.add(new Point(0,0));
		clusters.add(new Point(-1,-2));
		DrawUtil drawUtil = new DrawUtil(clusters);
		drawUtil.drawClusters(4);
	}
}
