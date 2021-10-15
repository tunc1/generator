package app.ui.view;

import app.consts.Consts;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GeneratorView
{
    private JFrame frame;
    private JTextField projectPathTextField,basePackageTextField,entityPackageTextField;
    private JLabel projectPathLabel,basePackageLabel,entityPackageLabel,idTypeLabel;
    private JButton generateButton;
    private JComboBox<String> idTypeComboBox;
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

        projectPathLabel=new JLabel("Project Path:");
        projectPathLabel.setBounds(10,10,400,25);
        frame.add(projectPathLabel);

        projectPathTextField=new JTextField();
        projectPathTextField.setBounds(10,40,400,25);
        frame.add(projectPathTextField);

        basePackageLabel=new JLabel("Base Package Name:");
        basePackageLabel.setBounds(10,70,400,25);
        frame.add(basePackageLabel);

        basePackageTextField=new JTextField("com.example.app");
        basePackageTextField.setBounds(10,100,400,25);
        frame.add(basePackageTextField);

        entityPackageLabel=new JLabel("Entity Package Name:");
        entityPackageLabel.setBounds(10,130,400,25);
        frame.add(entityPackageLabel);

        entityPackageTextField=new JTextField("entity");
        entityPackageTextField.setBounds(10,160,400,25);
        frame.add(entityPackageTextField);

        idTypeLabel=new JLabel("Id Type for All Entity Classes:");
        idTypeLabel.setBounds(10,190,400,25);
        frame.add(idTypeLabel);

        idTypeComboBox=new JComboBox<>(Consts.idTypes);
        idTypeComboBox.setBounds(10,220,400,25);
        frame.add(idTypeComboBox);

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
    public String getIdType()
    {
        return idTypeComboBox.getSelectedItem().toString();
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