package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import org.mql.java.model.ProjectEntity;
import org.mql.java.ui.components.ClassDiagram;
import org.mql.java.ui.components.PackageDiagram;

public class SwingDisplayFrame extends JFrame {

    private JPanel mainPanel;

    private static final long serialVersionUID = 1L;

    public SwingDisplayFrame(ProjectEntity project) {
        try {
            // Utilisation du look and feel système pour obtenir l'apparence native
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Affiche le diagramme de package par défaut
        displayHomeFrame(project);

        // Crée un bouton pour basculer entre le diagramme de package et le diagramme de classe
        JButton switchButton = new JButton("Home Page");
        switchButton.setBackground(new Color(245, 247, 249 )); // Bleu
        switchButton.setForeground(new Color(47, 49, 52 ));
        switchButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                displayHomeFrame(project);
            }
        });

        // Ajoute le bouton au haut de la fenêtre
        add(switchButton, BorderLayout.NORTH);

        // Ajoute le panneau principal à la fenêtre
        add(mainPanel, BorderLayout.CENTER);

        // Affiche la fenêtre
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle(project.getProjectName());
    }

    public void displayHomeFrame(ProjectEntity project) {
        JPanel homePanel = new JPanel();
        JButton packageButton = new JButton("Cliquez pour Afficher Diagramme de Package");
        JButton classButton = new JButton("Cliquez pour Afficher Diagramme de Classe");

        // Personnalisation des boutons
        packageButton.setBackground(new Color(50, 150, 200)); // Bleu
        packageButton.setForeground(new Color(7, 41, 71));
        packageButton.setFont(new Font("SansSerif", Font.PLAIN, 16));

        classButton.setBackground(new Color(50, 150, 50)); // Vert
        classButton.setForeground(new Color(27, 71, 7));
        classButton.setFont(new Font("SansSerif", Font.PLAIN, 16));

        packageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                displayPackageDiagram(project);
                mainPanel.revalidate();
                mainPanel.repaint();

            }
        });

        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                displayClassDiagram(project);
                mainPanel.revalidate();
                mainPanel.repaint();

            }
        });

        homePanel.add(packageButton);
        homePanel.add(classButton);
        mainPanel.add(homePanel, BorderLayout.CENTER);
        revalidateAndRepaint();
    }

    public void displayPackageDiagram(ProjectEntity project) {
        PackageDiagram pd = new PackageDiagram(project);
        pd.setBackground(Color.DARK_GRAY);
        JScrollPane scrollPane = new JScrollPane(pd);
        mainPanel.add(scrollPane);
        revalidateAndRepaint();
    }

    public void displayClassDiagram(ProjectEntity project) {
        ClassDiagram cd = new ClassDiagram(project.getPackages());
        cd.setBackground(Color.DARK_GRAY);
        JScrollPane scrollPane = new JScrollPane(cd);
        mainPanel.add(scrollPane);
        revalidateAndRepaint();
    }

    private void revalidateAndRepaint() {
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}

