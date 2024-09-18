import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();


        //Updated the Top 5 Destination List with my top destinations, images and links to the image. 
        addDestinationNameAndPicture("1. Barcelona, Spain (Discover the stunning architecture and culture of Barcelona)", new ImageIcon(getClass().getResource("/resources/1280px-Montjuic_Placa_Espanya_Barcelona.jpg")),"https://commons.wikimedia.org/wiki/Barcelona#/media/File:Barcelona_collage.JPG");
        addDestinationNameAndPicture("2. London, England (Explore the historic and vibrant city of London)", new ImageIcon(getClass().getResource("/resources/London_Montage_2016.png")),"https://commons.wikimedia.org/wiki/Category:London#/media/File:London_Montage_2016.png");
        addDestinationNameAndPicture("3. Rome, Italy (Immerse yourself in the ancient history of Rome)", new ImageIcon(getClass().getResource("/resources/800px-RomaMontage1..jpg")),"https://commons.wikimedia.org/wiki/Category:Rome#/media/File:RomaMontage1..jpg");
        addDestinationNameAndPicture("4. New York City, New York (Experience the vibrant culture of New York City)", new ImageIcon(getClass().getResource("/resources/West_side_of_Manhattan_from_Hudson_Commons_(95103p).jpg")),"https://en.wikipedia.org/wiki/Midtown_Manhattan#/media/File:West_side_of_Manhattan_from_Hudson_Commons_(95103p).jpg");
        addDestinationNameAndPicture("5. San Juan, Puerto Rico (Enjoy the beautiful beaches and vibrant culture of Puerto Rico)", new ImageIcon(getClass().getResource("/resources/San_Juan,_Puerto_Rico_(2529298606).jpg")),"https://commons.wikimedia.org/wiki/Category:San_Juan,_Puerto_Rico#/media/File:San_Juan,_Puerto_Rico_(2529298606).jpg");
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(10); //Value increased from 2 to 10 to provide more space around text and icon
        renderer.setBackgroundNonSelectionColor(Color.RED); //Background color for non selected items changed to red
        renderer.setBackgroundSelectionColor(Color.WHITE); //Background color for selected items changed to white
        renderer.setForegroundSelectionColor(Color.BLUE); //Text color for selected items changed to blue

        list.setCellRenderer(renderer);
        list.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		int index = list.locationToIndex(e.getPoint()); //Created to determine which item in the list was clicked using mouse location
        		TextAndIcon item = listModel.getElementAt(index); //Created to retrieve the clicked on item
        		if (item.getLink() != null) { //Created to check if item has link with it
        			try {
        				Desktop.getDesktop().browse(new URI(item.getLink())); //Created to open link in web browser
        			} catach (IOException | URISyntaxException ex) {
        				ex.printStackTrace(); //Created to handle any potential errors while opening links
        			}
        		}
        	}
        });        			

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        JLabel nameLabel = new JLabel("Joseph Cassello Jr."); //Label created with my name
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); //Created to align the text so it is center
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Created to set the font, style and size of letters
        nameLabel.setForeground(Color.BLACK); // Created to set text color to black
        
        JPanel panel = new JPanel(); //Created a panel to hold the label
        panel.add(nameLabel);
        
        getContentPane().add(panel, BorderLayout.NORTH); //Created to add panel to top of screen
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;
    private String link; //Created new private field to store links

    public TextAndIcon(String text, Icon icon, String link) {
        this.text = text;
        this.icon = icon;
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }
    
    public String getLink() { //Created new getter method for links
    	return link;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    
    public void setLink(String link) { //Created new setter method for links
    	this.link = link;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;
    private Color backgroundSelectionColor = Color.white;
    private Color backgroundNonSelectionColor = Color.red;
    private Color foregroundSelectionColor = Color.blue; 
    

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }
    
    public void setBackgroundSelectionColor(Color color) { //Sets background color for the item that is currently listed
    	this.backgroundSelectionColor = color;
    }
    
    public void setBackgroundNonSelectionColor(Color color) { //Sets background color for items that are not selected
    	this.backgroundNonSelectionColor = color;
    }
    
    public void setForegroundSelectionColor(Color color) { //Sets the text color for the item currently listed
    	this.foregroundSelectionColor = color;
    }

    public Component getListCellRendererComponent(JList<? extends TextandIcon> list, TextAndIcon value,
    int index, boolean isSelected, boolean hasFocus) {  
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(backgroundSelectionColor); 
            setForeground(foregroundSelectionColor);
        } else {
            setBackground(backgroundNonSelectionColor);
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }
}

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}