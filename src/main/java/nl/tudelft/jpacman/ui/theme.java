// package nl.tudelft.jpacman.ui;

// import javax.swing.ImageIcon;
// import javax.swing.JButton;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import java.awt.*;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.List;

// public class PacManThemesUI extends JPanel {

//     // create a panel to hold the buttons
//     JPanel buttonPanel = new JPanel();
//     // create two buttons to switch between cards

//     JLabel title = new JLabel("PacMan");
//     ImageIcon background;
//     JPanel bgJPanel = new JPanel();
//     JButton btnStart = new JButton("Start");
//     JLabel bg = new JLabel();
//     private JLabel label;
//     private List<ImageIcon> icons = new ArrayList<>();
//     JButton btnLeft = new JButton("Left");
//     JButton btnRight = new JButton("Right");
//     private int i;
//     public JButton getBtnLeft() {
//         return btnLeft;
//     }

//     public JButton getBtnRight() {
//         return btnRight;
//     }

//     public PacManThemesUI(ActionListener actionListener) {
//         i = 0;
//         String src = "src\\main\\resources\\";
//         icons =  new ArrayList<>();
//         icons.add(new ImageIcon(src+"space.png"));
//         icons.add(new ImageIcon(src+"bg1.png"));
//         icons.add(new ImageIcon(src+"cherry.png"));

//         this.setLayout(new GridBagLayout());
//         ImageIcon icon = icons.get(i);
//         label = new JLabel(icon);
//         label.setPreferredSize(new Dimension(200, 200));
//         // label.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

//         GridBagConstraints c = new GridBagConstraints();
//         c.gridx = 1;
//         c.gridy = 1;
//         c.anchor = GridBagConstraints.CENTER;
//         c.weightx = 1.0;
//         c.weighty = 1.0;
//         c.insets = new Insets(10, 20, 40, 20);
    
//         this.add(label,c);
//         btnLeft.addActionListener(actionListener);
//         c.anchor = GridBagConstraints.WEST;
//         this.add(btnLeft,c);
//         btnRight.addActionListener(actionListener);
//         c.anchor = GridBagConstraints.EAST;
//         this.add(btnRight,c);
//         this.setBackground("src\\main\\resources\\space.png");
//     }

//     public void start() {
//         setVisible(true);
//     }

//     public void addButton(JButton btn) {
//         buttonPanel.add(btn);
//         btn.setContentAreaFilled(false);

//         // Set constraints for buttonPanel
//         GridBagConstraints c = new GridBagConstraints();
//         c.gridx = 1;
//         c.gridy = 1;
//         c.anchor = GridBagConstraints.SOUTH;
//         c.weightx = 1.0;
//         c.weighty = 1.0;
//         c.insets = new Insets(10, 20, 40, 20); // add 10-pixel margin
//         // Set preferred size of button
//         btn.setPreferredSize(new Dimension(120, 35));
//         this.add(buttonPanel, c);
//     }

//     public ImageIcon ResizeImage(ImageIcon image, int width, int hight) {

//         Image img = image.getImage().getScaledInstance(bg.getWidth(), bg.getHeight(), Image.SCALE_SMOOTH);

//         return new ImageIcon(img);

//     }

//     public void NextIcon(){
//         if(i==2){i=-1;}
//         i++;
//         ImageIcon newIcon = icons.get(i);
//         label.setIcon(newIcon);
//     }
//     public void PrveIcon(){
//         if(i==0){i=3;}
//         i--;
//         ImageIcon newIcon = icons.get(i);
//         label.setIcon(newIcon);
//     }
//     @Override
//     public void paintComponent(Graphics g) {
//         // Call the super method
//         super.paintComponent(g);
//         // Draw the image icon on the panel
//         g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
//     }

//     public void setBackground(String path) {
//         background = new ImageIcon(path);

//     }

// }