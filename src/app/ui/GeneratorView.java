package app.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GeneratorView
{
    private JFrame frame;
    private JTextField projectPathTextField,basePackageTextField,entityPackageTextField,entityNamesTextField;
    private JButton generateButton;
    public GeneratorView()
    {
        createUI();
    }
    private void createUI()
    {
        frame=new JFrame("Generator");
        frame.setBounds(100,100,600,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel projectPathLabel=new JLabel("Project Path:");
        projectPathLabel.setBounds(10,10,400,25);
        frame.add(projectPathLabel);

        projectPathTextField=new JTextField();
        projectPathTextField.setBounds(10,40,400,25);
        frame.add(projectPathTextField);

        JLabel basePackageLabel=new JLabel("Base Package Name:");
        basePackageLabel.setBounds(10,70,400,25);
        frame.add(basePackageLabel);

        basePackageTextField=new JTextField("com.example.app");
        basePackageTextField.setBounds(10,100,400,25);
        frame.add(basePackageTextField);

        JLabel entityPackageLabel=new JLabel("Entity Package Name:");
        entityPackageLabel.setBounds(10,130,400,25);
        frame.add(entityPackageLabel);

        entityPackageTextField=new JTextField("entity");
        entityPackageTextField.setBounds(10,160,400,25);
        frame.add(entityPackageTextField);

        JLabel entityLabel=new JLabel("Entity Names:");
        entityLabel.setBounds(10,190,400,25);
        frame.add(entityLabel);

        entityNamesTextField=new JTextField("Book,Author");
        entityNamesTextField.setBounds(10,220,400,25);
        frame.add(entityNamesTextField);

        generateButton=new JButton("Generate");
        generateButton.setBounds(10,250,400,25);
        frame.add(generateButton);
    }
    public String getProjectPath()
    {
        return projectPathTextField.getText();
    }
    public String getBasePackage()
    {
        return basePackageTextField.getText();
    }
    public String getEntityPackage()
    {
        return entityPackageTextField.getText();
    }
    public String getEntityNames()
    {
        return entityNamesTextField.getText();
    }
    public void setOnClick(ActionListener listener)
    {
        generateButton.addActionListener(listener);
    }
    public void show()
    {
        frame.setVisible(true);
    }
    public void showMessage(String text)
    {
        JOptionPane.showMessageDialog(frame,text);
    }
}