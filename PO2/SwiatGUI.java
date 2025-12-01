package laboratorium.po2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwiatGUI implements ActionListener {
    private final Toolkit toolkit;
    private final JFrame jFrame;
    private final Dimension dimension;
    private final JPanel mainPanel;
    private final JButton startButton;
    private PlanszaGraphics planszaGraphics = null;
    private KomentatorGraphics komentatorGraphics = null;
    private Oznaczenia oznaczenia = null;
    private final JButton nextRoundButton;
    private final JMenuBar menuBar;
    private Swiat swiat;
    private final int ODSTEP;

    public SwiatGUI(String title) {
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        ODSTEP = dimension.height / 50;
        jFrame = createJFrame(title);
        mainPanel = createMainPanel();
        startButton = createStartButton();
        nextRoundButton = createNextRoundButton();

        menuBar = createMenuBar();

        jFrame.setVisible(true);
    }

    private JFrame createJFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(null);
        jFrame.add(panel, BorderLayout.CENTER);
        return panel;
    }

    private JButton createStartButton() {
        JButton button = new JButton("Start");
        button.setBounds(350, 250, 100, 50);
        button.addActionListener(e -> initializeGame());
        mainPanel.add(button);
        return button;
    }

    private JButton createNextRoundButton() {
        JButton button = new JButton("Następna tura");
        button.setBounds(350, 500, 150, 30);
        button.addActionListener(e -> executeNextRound());
        button.setVisible(false);
        return button;
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem load = new JMenuItem("Wczytaj");
        JMenuItem save = new JMenuItem("Zapisz");
        load.addActionListener(this);
        save.addActionListener(this);
        menu.add(load);
        menu.add(save);
        bar.add(menu);
        jFrame.setJMenuBar(bar);
        return bar;
    }

    private void initializeGame() {
        startButton.setVisible(false);
        menuBar.setVisible(true);
        mainPanel.add(nextRoundButton);
        nextRoundButton.setVisible(true);

        int sizeX = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Podaj szerokość świata:"));
        int sizeY = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Podaj wysokość świata:"));

        swiat = new Swiat(sizeX, sizeY, this);
        swiat.GenerujSwiat();

        startGame();
    }

    private void executeNextRound() {
        if (swiat != null) {
            Komentator.WyczyscKomentarzy();
            swiat.WykonajTure();
            odswiezSwiat();
        }
    }

    public void odswiezSwiat() {
        if (planszaGraphics != null) planszaGraphics.odswiezPlansze();
        if (komentatorGraphics != null) komentatorGraphics.odswiezKomentarzy();
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Wczytaj")) {
            loadGame();
        } else if (e.getActionCommand().equals("Zapisz")) {
            saveGame();
        }
    }

    private void loadGame() {
        String fileName = JOptionPane.showInputDialog(jFrame, "Podaj nazwę pliku:");
        swiat = Swiat.OdtworzSwiat(fileName);
        swiat.setSwiatGUI(this);
        clearPreviousGameData();
        startGame();
    }

    private void saveGame() {
        String fileName = JOptionPane.showInputDialog(jFrame, "Podaj nazwę pliku:");
        swiat.ZapiszSwiat(fileName);
    }

    private void clearPreviousGameData() {
        if (planszaGraphics != null) {
            mainPanel.remove(planszaGraphics);
        }
        if (komentatorGraphics != null) {
            mainPanel.remove(komentatorGraphics);
        }
        if (oznaczenia != null) {
            mainPanel.remove(oznaczenia);
        }
    }

    private void startGame() {
        planszaGraphics = new PlanszaGraphics(swiat);
        mainPanel.add(planszaGraphics);

        komentatorGraphics = new KomentatorGraphics();
        mainPanel.add(komentatorGraphics);

        oznaczenia = new Oznaczenia();
        mainPanel.add(oznaczenia);

        odswiezSwiat();
    }
    
   private class PlanszaGraphics extends JPanel {
    private final int sizeX;
    private final int sizeY;
    private final PolePlanszy[][] polaPlanszy;

    public PlanszaGraphics(Swiat swiat) {
        super();
        setBounds(mainPanel.getX() + ODSTEP, mainPanel.getY() + ODSTEP,
                mainPanel.getHeight() * 5 / 6 - ODSTEP, mainPanel.getHeight() * 5 / 6 - ODSTEP);
        this.sizeX = swiat.getSizeX();
        this.sizeY = swiat.getSizeY();

        polaPlanszy = new PolePlanszy[sizeY][sizeX];
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                polaPlanszy[i][j] = new PolePlanszy(j, i);
            }
        }

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                this.add(polaPlanszy[i][j]);
            }
        }
        this.setLayout(new GridLayout(sizeY, sizeX));
    }
    
    private class PolePlanszy extends JPanel {  
            private boolean isEmpty;
            private Color kolor;
            private final int pozX;
            private final int pozY;

            public PolePlanszy(int X, int Y) {
                super();
                kolor = Color.WHITE;
                setBackground(kolor); 
                isEmpty = true;
                pozX = X;
                pozY = Y;
                setPreferredSize(new Dimension(40, 40)); 
            }

            public boolean isEmpty() {
                return isEmpty;
            }

            public void setEmpty(boolean empty) {
                isEmpty = empty;
            }

            public Color getKolor() {
                return kolor;
            }

            public void setKolor(Color kolor) {
                this.kolor = kolor;
                setBackground(kolor);  
            }

            public int getPozX() {
                return pozX;
            }

            public int getPozY() {
                return pozY;
            }
    }


        public void odswiezPlansze() {
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    Organizm tmpOrganizm = swiat.getPlansza()[i][j];
                    if (tmpOrganizm != null) {
                        polaPlanszy[i][j].setEmpty(false);
                        polaPlanszy[i][j].setEnabled(false);
                        polaPlanszy[i][j].setKolor(tmpOrganizm.getKolor());
                    } else {
                        polaPlanszy[i][j].setEmpty(true);
                        polaPlanszy[i][j].setEnabled(true);
                        polaPlanszy[i][j].setKolor(Color.WHITE);
                        }
                }
            }
        }

        public int getSizeX() {
            return sizeX;
        }

        public int getSizeY() {
            return sizeY;
        }

        public PolePlanszy[][] getPolaPlanszy() {
            return polaPlanszy;
        }
    }

    private class KomentatorGraphics extends JPanel {
        private String tekst;
        private final String instriction = "";
        private final JTextArea textArea;

        public KomentatorGraphics() {
            super();
            setBounds(planszaGraphics.getX() + planszaGraphics.getWidth() + ODSTEP,
                    mainPanel.getY() + ODSTEP,
                    mainPanel.getWidth() - planszaGraphics.getWidth() - ODSTEP * 2,
                    mainPanel.getHeight() * 5 / 6 - ODSTEP);
            tekst = Komentator.getTekst();
            textArea = new JTextArea(tekst);
            textArea.setEditable(false);
            setLayout(new CardLayout());
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setMargin(new Insets(5, 5, 5, 5));
            JScrollPane sp = new JScrollPane(textArea);
            add(sp);
        }

        public void odswiezKomentarzy() {
            tekst = Komentator.getTekst();
            textArea.setText(tekst);
        }
    }

    private class Oznaczenia extends JPanel {
        private final int ILOSC_TYPOW = 8;
        private final JPanel[] jPanels;  

        public Oznaczenia() {
            super();
            setBounds(mainPanel.getX() + ODSTEP, mainPanel.getHeight() * 5 / 6 + ODSTEP,
                    mainPanel.getWidth() - ODSTEP * 2,
                    mainPanel.getHeight() * 2 / 6 - 4 * ODSTEP);
            setBackground(Color.white);
            setLayout(new FlowLayout(FlowLayout.CENTER));
            jPanels = new JPanel[ILOSC_TYPOW]; 

            jPanels[0] = createColorPanel("Koka", new Color(60, 179, 113));
            jPanels[1] = createColorPanel("Trawa", new Color(107, 142, 35));
            jPanels[2] = createColorPanel("Mlecz", Color.YELLOW);
            jPanels[3] = createColorPanel("Jeż", new Color(102, 51, 0));
            jPanels[4] = createColorPanel("Lis", new Color(255, 128, 0));
            jPanels[5] = createColorPanel("Owca", new Color(253, 245, 230));
            jPanels[6] = createColorPanel("Wilk", new Color(64, 64, 64));
            jPanels[7] = createColorPanel("Żmija", new Color(34, 139, 34));


            for (int i = 0; i < ILOSC_TYPOW; i++) {
                jPanels[i].setEnabled(false);  
                add(jPanels[i]);
            }
        }
        private JPanel createColorPanel(String text, Color color) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(60, 25)); 
            panel.setBackground(color); 
            JLabel label = new JLabel(text);
            panel.add(label);
            return panel;
        }
    }
}
