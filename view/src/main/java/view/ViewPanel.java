package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

	char[][] map;	/** 2d array when we copy the one from the model **/
	int r = 0;	/** used to display highscores log **/

	/** The view frame. */
	private ViewFrame					viewFrame;
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -998294702363713521L;

	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame
	 *          the view frame
	 */
	public ViewPanel(final ViewFrame viewFrame) {
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
		viewFrame.setBackground(Color.BLACK);
	}

	/**
	 * Gets the view frame.
	 *
	 * @return the view frame
	 */
	private ViewFrame getViewFrame() {
		return this.viewFrame;
	}

	/**
	 * Sets the view frame.
	 *
	 * @param viewFrame
	 *          the new view frame
	 */
	private void setViewFrame(final ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable arg0, final Object arg1) {
		this.viewFrame.getModel().getMapInTab();	/** fulfill the 2d array with what we got in the DB in the model **/
		this.repaint();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(final Graphics graphics) {
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		graphics.setColor(Color.BLACK);		/** background color is now black **/
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		graphics.setColor(Color.WHITE);		/** color change to write stuff **/
		graphics.setFont(new Font("default", Font.BOLD, 16));	/** now want you write is bold **/
		graphics.drawString("SCORE : " + this.viewFrame.getModel().getScore(),4,400);	/** display actual score **/
		graphics.setColor(Color.BLACK);		/** everything next is black again just in case **/

		this.map = this.viewFrame.getModel().getMap();		/** we copy the 2D array from the model to use it to display images **/

		for(int i = 0; i < this.map.length; i++)			/** beginning of the parser **/
		{
			for (int j = 0; j < this.map[i].length; j++)
			{
				switch (this.map[i][j]){
					case 'B':
						try {
							Image img = ImageIO.read(new File("sprite/bone.png"));
							graphics.drawImage(img, 32*j, 32*i, this);	/** displays an image **/
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 'H':
						try {
							Image img = ImageIO.read(new File("sprite/horizontal_bone.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 'V':
						try {
							Image img = ImageIO.read(new File("sprite/vertical_bone.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 'P':
						try {
							Image img = ImageIO.read(new File("sprite/purse.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 'L' :
						graphics.drawImage((new ImageIcon("sprite/lorann.gif")).getImage(),32*j,32*i,this);
						break;
					case 'C' :
						try {
							Image img = ImageIO.read(new File("sprite/gate_closed.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 'K' :
						try {
							Image img = ImageIO.read(new File("sprite/crystal_ball.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case '1' :
						try {
							Image img = ImageIO.read(new File("sprite/monster_1.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case '2' :
						try {
							Image img = ImageIO.read(new File("sprite/monster_2.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case '3' :
						try {
							Image img = ImageIO.read(new File("sprite/monster_3.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case '4' :
						try {
							Image img = ImageIO.read(new File("sprite/monster_4.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					default:
						try {
							Image img = ImageIO.read(new File("sprite/noimage.png"));
							graphics.drawImage(img, 32*j, 32*i, this);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
				}
			}
		}			/** end of the parser **/
		if (this.r < 2){		/** display highscore log only when you start the game **/
			graphics.setColor(Color.WHITE);
			for(int c = 1;c < 8; c++){
				if(c==1) {
					graphics.drawString("Highscores :",256,32*c);
				}
				else {
					graphics.drawString("tamère c'est pas encore implémenté",256,32*c);
				}
			}
			this.r++;
		}
	}
}
